package com.huawei.ibooking.dao;

import com.huawei.ibooking.mapper.AdminRbacMapper;
import com.huawei.ibooking.model.AdminUserDO;
import com.huawei.ibooking.model.AdminUserRoleRowDO;
import com.huawei.ibooking.model.PermissionDO;
import com.huawei.ibooking.model.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminRbacDao {
    @Autowired
    private AdminRbacMapper adminRbacMapper;

    public AdminUserDO getAdminUserByUsername(String username) {
        return adminRbacMapper.getAdminUserByUsername(username);
    }

    public AdminUserDO getAdminUserById(int userId) {
        return adminRbacMapper.getAdminUserById(userId);
    }

    public List<String> getRoleCodesByUserId(int userId) {
        return adminRbacMapper.getRoleCodesByUserId(userId);
    }

    public List<String> getPermissionCodesByUserId(int userId) {
        return adminRbacMapper.getPermissionCodesByUserId(userId);
    }

    public List<PermissionDO> getPermissionsByUserId(int userId) {
        return adminRbacMapper.getPermissionsByUserId(userId);
    }

    public List<AdminUserRoleRowDO> listAdminUserRoleRows() {
        return adminRbacMapper.listAdminUserRoleRows();
    }

    public List<RoleDO> getRolesByCodes(List<String> roleCodes) {
        if (roleCodes == null || roleCodes.isEmpty()) {
            return new ArrayList<>();
        }
        return adminRbacMapper.getRolesByCodes(roleCodes);
    }

    @Transactional
    public void replaceUserRoles(int userId, List<Integer> roleIds) {
        adminRbacMapper.deleteRolesByUserId(userId);
        for (Integer roleId : roleIds) {
            adminRbacMapper.insertUserRole(userId, roleId);
        }
    }

    public void updatePasswordHash(int userId, String passwordHash) {
        adminRbacMapper.updatePasswordHash(userId, passwordHash);
    }

    public List<RoleDO> listRoles() {
        return adminRbacMapper.listRoles();
    }

    public List<PermissionDO> listPermissions() {
        return adminRbacMapper.listPermissions();
    }

    public boolean createAdminUser(AdminUserDO user) {
        return adminRbacMapper.insertAdminUser(user) > 0;
    }
}