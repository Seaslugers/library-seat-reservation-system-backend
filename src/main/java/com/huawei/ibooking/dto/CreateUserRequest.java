package com.huawei.ibooking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String password;
    private String displayName;
}
