package com.huawei.ibooking.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class AdminSessionUser implements Serializable {
    private int userId;
    private String username;
    private String displayName;
    private List<String> roleCodes = new ArrayList<>();
    private List<String> permissionCodes = new ArrayList<>();

    public boolean hasPermission(String permissionCode) {
        Set<String> permissionSet = new HashSet<>(permissionCodes);
        return permissionSet.contains(permissionCode);
    }
}
