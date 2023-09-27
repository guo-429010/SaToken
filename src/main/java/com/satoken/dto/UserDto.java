package com.satoken.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {

    @NonNull
    private String username;

    @NonNull
    private String password;
}
