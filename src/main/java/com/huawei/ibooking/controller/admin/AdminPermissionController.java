package com.huawei.ibooking.controller.admin;

import com.huawei.ibooking.business.AdminRbacBusiness;
import com.huawei.ibooking.constants.PermissionCode;
import com.huawei.ibooking.constants.SessionConstants;
import com.huawei.ibooking.model.PermissionDO;
import com.huawei.ibooking.security.AdminSessionUser;
import com.huawei.ibooking.security.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminPermissionController {
    @Autowired
    private AdminRbacBusiness adminRbacBusiness;

    @GetMapping(value = "/admin/permissions/current")
    public ResponseEntity<List<String>> currentPermissions(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AdminSessionUser sessionUser = (AdminSessionUser) session.getAttribute(SessionConstants.ADMIN_SESSION_USER);
        if (sessionUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(sessionUser.getPermissionCodes(), HttpStatus.OK);
    }

    @RequirePermission(PermissionCode.USER_ROLE_ASSIGN)
    @GetMapping(value = "/admin/permissions")
    public ResponseEntity<List<PermissionDO>> listPermissions() {
        return new ResponseEntity<>(adminRbacBusiness.listPermissions(), HttpStatus.OK);
    }
}
