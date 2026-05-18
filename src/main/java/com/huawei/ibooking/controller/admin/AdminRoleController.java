package com.huawei.ibooking.controller.admin;

import com.huawei.ibooking.business.AdminRbacBusiness;
import com.huawei.ibooking.constants.PermissionCode;
import com.huawei.ibooking.model.RoleDO;
import com.huawei.ibooking.security.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminRoleController {
    @Autowired
    private AdminRbacBusiness adminRbacBusiness;

    @RequirePermission(PermissionCode.USER_ROLE_ASSIGN)
    @GetMapping(value = "/admin/roles")
    public ResponseEntity<List<RoleDO>> listRoles() {
        return new ResponseEntity<>(adminRbacBusiness.listRoles(), HttpStatus.OK);
    }
}
