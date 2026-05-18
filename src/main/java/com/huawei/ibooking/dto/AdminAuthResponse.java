package com.huawei.ibooking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AdminAuthResponse {
    private int userId;
    private String username;
    private String displayName;
    private List<String> roleCodes = new ArrayList<>();
    private List<String> permissionCodes = new ArrayList<>();
}
