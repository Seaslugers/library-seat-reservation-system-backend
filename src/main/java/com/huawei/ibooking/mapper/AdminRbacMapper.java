package com.huawei.ibooking.mapper;

import com.huawei.ibooking.model.AdminUserDO;
import com.huawei.ibooking.model.AdminUserRoleRowDO;
import com.huawei.ibooking.model.PermissionDO;
import com.huawei.ibooking.model.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminRbacMapper {
    AdminUserDO getAdminUserByUsername(@Param("username") String username);

    AdminUserDO getAdminUserById(@Param("userId") int userId);

    List<String> getRoleCodesByUserId(@Param("userId") int userId);

    List<String> getPermissionCodesByUserId(@Param("userId") int userId);

    List<PermissionDO> getPermissionsByUserId(@Param("userId") int userId);

    List<AdminUserRoleRowDO> listAdminUserRoleRows();

    List<RoleDO> getRolesByCodes(@Param("roleCodes") List<String> roleCodes);

    int deleteRolesByUserId(@Param("userId") int userId);

    int insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    int updatePasswordHash(@Param("userId") int userId, @Param("passwordHash") String passwordHash);

    List<RoleDO> listRoles();

    List<PermissionDO> listPermissions();

    int insertAdminUser(AdminUserDO user);
}