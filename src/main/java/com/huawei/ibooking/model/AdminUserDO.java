package com.huawei.ibooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminUserDO {
    private int id;
    private String username;
    private String passwordHash;
    private String displayName;
    private int status;
}
