package com.huawei.ibooking.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.ibooking.BookingApplication;
import com.huawei.ibooking.dto.LoginRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WebAppConfiguration
public class AdminAuthControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_be_unauthorized_when_access_me_without_login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/auth/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_be_unauthorized_when_login_with_wrong_password() throws Exception {
        loginExpect("admin", "wrong-password", status().isUnauthorized());
    }

    @Test
    public void should_be_success_when_login_as_admin_and_query_permissions() throws Exception {
        MockHttpSession session = login("admin", "test123");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/permissions/current")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<String> permissions = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(), new TypeReference<List<String>>() {
                });

        Assert.assertTrue(permissions.contains("PERM_USER_ROLE_ASSIGN"));
    }

    @Test
    public void should_be_forbidden_when_auditor_query_admin_users() throws Exception {
        MockHttpSession session = login("auditor1", "test123");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/users")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void should_be_unauthorized_after_logout() throws Exception {
        MockHttpSession session = login("admin", "test123");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/auth/logout")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/auth/me")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    private MockHttpSession login(String username, String password) throws Exception {
        MvcResult loginResult = loginExpect(username, password, status().isOk());
        return (MockHttpSession) loginResult.getRequest().getSession(false);
    }

    private MvcResult loginExpect(String username, String password,
                                  org.springframework.test.web.servlet.ResultMatcher matcher) throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return mockMvc.perform(MockMvcRequestBuilders.post("/admin/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher)
                .andReturn();
    }
}