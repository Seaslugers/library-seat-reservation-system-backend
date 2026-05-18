package com.huawei.ibooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminUserRoleRowDO {
    private int userId;
    private String username;
    private String displayName;
    private int status;
    private String roleCode;
}
