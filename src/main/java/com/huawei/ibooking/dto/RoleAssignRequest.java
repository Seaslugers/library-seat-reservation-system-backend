package com.huawei.ibooking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RoleAssignRequest {
    private List<String> roleCodes = new ArrayList<>();
}
