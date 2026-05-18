package com.huawei.ibooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDO {
    private int id;
    private String roleCode;
    private String roleName;
    private String roleDesc;
}
