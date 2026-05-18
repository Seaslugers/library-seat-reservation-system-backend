package com.huawei.ibooking.controller.admin;

import com.huawei.ibooking.business.AdminRbacBusiness;
import com.huawei.ibooking.constants.PermissionCode;
import com.huawei.ibooking.constants.SessionConstants;
import com.huawei.ibooking.dto.AdminUserVO;
import com.huawei.ibooking.dto.CreateUserRequest;
import com.huawei.ibooking.dto.RoleAssignRequest;
import com.huawei.ibooking.security.AdminSessionUser;
import com.huawei.ibooking.security.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminUserController {
    @Autowired
    private AdminRbacBusiness adminRbacBusiness;

    @RequirePermission(PermissionCode.USER_ROLE_ASSIGN)
    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<AdminUserVO>> listUsers() {
        return new ResponseEntity<>(adminRbacBusiness.listAdminUsers(), HttpStatus.OK);
    }

    @RequirePermission(PermissionCode.USER_ROLE_ASSIGN)
    @PutMapping(value = "/admin/users/{userId}/roles")
    public ResponseEntity<Void> assignRoles(@PathVariable("userId") int userId,
                                            @RequestBody RoleAssignRequest request,
                                            HttpServletRequest httpServletRequest) {
        List<String> roleCodes = request == null ? Collections.<String>emptyList() : request.getRoleCodes();
        boolean success = adminRbacBusiness.assignRoles(userId, roleCodes);
        if (!success) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            AdminSessionUser currentUser = (AdminSessionUser) session.getAttribute(SessionConstants.ADMIN_SESSION_USER);
            if (currentUser != null && currentUser.getUserId() == userId) {
                Optional<AdminSessionUser> refreshedUser = adminRbacBusiness.loadSessionUser(userId);
                if (refreshedUser.isPresent()) {
                    session.setAttribute(SessionConstants.ADMIN_SESSION_USER, refreshedUser.get());
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequirePermission(PermissionCode.USER_ROLE_ASSIGN)
    @PostMapping(value = "/admin/users")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean success = adminRbacBusiness.createAdminUser(
                request.getUsername(),
                request.getPassword(),
                request.getDisplayName()
        );

        return new ResponseEntity<>(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
