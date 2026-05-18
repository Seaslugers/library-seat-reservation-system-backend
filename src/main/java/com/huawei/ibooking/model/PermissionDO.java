package com.huawei.ibooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PermissionDO {
    private int id;
    private String permCode;
    private String permName;
    private String permDesc;
}