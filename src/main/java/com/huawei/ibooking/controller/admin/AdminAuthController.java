package com.huawei.ibooking.controller.admin;

import com.huawei.ibooking.business.AdminRbacBusiness;
import com.huawei.ibooking.constants.SessionConstants;
import com.huawei.ibooking.dto.AdminAuthResponse;
import com.huawei.ibooking.dto.LoginRequest;
import com.huawei.ibooking.security.AdminSessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class AdminAuthController {
    @Autowired
    private AdminRbacBusiness adminRbacBusiness;

    @PostMapping(value = "/admin/auth/login")
    public ResponseEntity<AdminAuthResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<AdminSessionUser> userOptional = adminRbacBusiness.login(request.getUsername(), request.getPassword());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AdminSessionUser sessionUser = userOptional.get();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(SessionConstants.ADMIN_SESSION_USER, sessionUser);
        return new ResponseEntity<>(toResponse(sessionUser), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/auth/me")
    public ResponseEntity<AdminAuthResponse> me(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AdminSessionUser sessionUser = (AdminSessionUser) session.getAttribute(SessionConstants.ADMIN_SESSION_USER);
        if (sessionUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(toResponse(sessionUser), HttpStatus.OK);
    }

    private AdminAuthResponse toResponse(AdminSessionUser sessionUser) {
        AdminAuthResponse response = new AdminAuthResponse();
        response.setUserId(sessionUser.getUserId());
        response.setUsername(sessionUser.getUsername());
        response.setDisplayName(sessionUser.getDisplayName());
        response.setRoleCodes(sessionUser.getRoleCodes());
        response.setPermissionCodes(sessionUser.getPermissionCodes());
        return response;
    }
}
