package com.huawei.ibooking.business;

import com.huawei.ibooking.dao.AdminRbacDao;
import com.huawei.ibooking.dto.AdminUserVO;
import com.huawei.ibooking.model.AdminUserDO;
import com.huawei.ibooking.model.AdminUserRoleRowDO;
import com.huawei.ibooking.model.PermissionDO;
import com.huawei.ibooking.model.RoleDO;
import com.huawei.ibooking.security.AdminSessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class AdminRbacBusiness {
    @Autowired
    private AdminRbacDao adminRbacDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<AdminSessionUser> login(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return Optional.empty();
        }

        AdminUserDO adminUser = adminRbacDao.getAdminUserByUsername(username);
        if (adminUser == null || adminUser.getStatus() != 1) {
            return Optional.empty();
        }

        String storedHash = adminUser.getPasswordHash();
        if (!passwordMatches(password, storedHash)) {
            return Optional.empty();
        }

        if (!looksLikeBcrypt(storedHash)) {
            adminRbacDao.updatePasswordHash(adminUser.getId(), passwordEncoder.encode(password));
        }

        AdminSessionUser sessionUser = new AdminSessionUser();
        sessionUser.setUserId(adminUser.getId());
        sessionUser.setUsername(adminUser.getUsername());
        sessionUser.setDisplayName(adminUser.getDisplayName());
        sessionUser.setRoleCodes(adminRbacDao.getRoleCodesByUserId(adminUser.getId()));
        sessionUser.setPermissionCodes(adminRbacDao.getPermissionCodesByUserId(adminUser.getId()));

        return Optional.of(sessionUser);
    }

    public Optional<AdminSessionUser> loadSessionUser(int userId) {
        AdminUserDO adminUser = adminRbacDao.getAdminUserById(userId);
        if (adminUser == null || adminUser.getStatus() != 1) {
            return Optional.empty();
        }

        AdminSessionUser sessionUser = new AdminSessionUser();
        sessionUser.setUserId(adminUser.getId());
        sessionUser.setUsername(adminUser.getUsername());
        sessionUser.setDisplayName(adminUser.getDisplayName());
        sessionUser.setRoleCodes(adminRbacDao.getRoleCodesByUserId(adminUser.getId()));
        sessionUser.setPermissionCodes(adminRbacDao.getPermissionCodesByUserId(adminUser.getId()));
        return Optional.of(sessionUser);
    }

    public List<AdminUserVO> listAdminUsers() {
        List<AdminUserRoleRowDO> rows = adminRbacDao.listAdminUserRoleRows();
        Map<Integer, AdminUserVO> userMap = new LinkedHashMap<>();

        for (AdminUserRoleRowDO row : rows) {
            AdminUserVO user = userMap.get(row.getUserId());
            if (user == null) {
                user = new AdminUserVO();
                user.setUserId(row.getUserId());
                user.setUsername(row.getUsername());
                user.setDisplayName(row.getDisplayName());
                user.setStatus(row.getStatus());
                userMap.put(row.getUserId(), user);
            }

            if (!isBlank(row.getRoleCode()) && !user.getRoleCodes().contains(row.getRoleCode())) {
                user.getRoleCodes().add(row.getRoleCode());
            }
        }

        return new ArrayList<>(userMap.values());
    }

    @Transactional
    public boolean assignRoles(int userId, List<String> roleCodes) {
        AdminUserDO adminUser = adminRbacDao.getAdminUserById(userId);
        if (adminUser == null) {
            return false;
        }

        Set<String> cleanedRoleCodes = new LinkedHashSet<>();
        if (roleCodes != null) {
            for (String roleCode : roleCodes) {
                if (!isBlank(roleCode)) {
                    cleanedRoleCodes.add(roleCode);
                }
            }
        }

        List<RoleDO> roleList = adminRbacDao.getRolesByCodes(new ArrayList<>(cleanedRoleCodes));
        if (roleList.size() != cleanedRoleCodes.size()) {
            return false;
        }

        List<Integer> roleIds = new ArrayList<>();
        for (RoleDO role : roleList) {
            roleIds.add(role.getId());
        }

        adminRbacDao.replaceUserRoles(userId, roleIds);
        return true;
    }

    private boolean passwordMatches(String rawPassword, String storedHash) {
        if (isBlank(storedHash)) {
            return false;
        }

        if (looksLikeBcrypt(storedHash)) {
            return passwordEncoder.matches(rawPassword, storedHash);
        }

        return rawPassword.equals(storedHash);
    }

    private boolean looksLikeBcrypt(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }

    public List<RoleDO> listRoles() {
        return adminRbacDao.listRoles();
    }

    public List<PermissionDO> listPermissions() {
        return adminRbacDao.listPermissions();
    }

    public boolean createAdminUser(String username, String password, String displayName) {
        if (isBlank(username) || isBlank(password) || isBlank(displayName)) {
            return false;
        }

        AdminUserDO existing = adminRbacDao.getAdminUserByUsername(username);
        if (existing != null) {
            return false;
        }

        AdminUserDO user = new AdminUserDO();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setDisplayName(displayName);
        user.setStatus(1);

        return adminRbacDao.createAdminUser(user);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
