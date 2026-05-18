package com.huawei.ibooking.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.ibooking.constants.SessionConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/admin/")) {
            return true;
        }

        if ("/admin/auth/login".equals(uri)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
            return false;
        }

        AdminSessionUser sessionUser = (AdminSessionUser) session.getAttribute(SessionConstants.ADMIN_SESSION_USER);
        if (sessionUser == null) {
            writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
            return false;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequirePermission requirePermission = handlerMethod.getMethodAnnotation(RequirePermission.class);
        if (requirePermission == null) {
            requirePermission = handlerMethod.getBeanType().getAnnotation(RequirePermission.class);
        }

        if (requirePermission != null && !sessionUser.hasPermission(requirePermission.value())) {
            writeError(response, HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN");
            return false;
        }

        return true;
    }

    private void writeError(HttpServletResponse response, int status, String errorCode) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> payload = new HashMap<>();
        payload.put("status", status);
        payload.put("error", errorCode);
        response.getWriter().write(objectMapper.writeValueAsString(payload));
    }
}
