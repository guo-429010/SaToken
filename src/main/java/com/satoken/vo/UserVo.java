package com.satoken.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserVo {

    private Integer id;

    private String username;

    private List<String> roles;

    private List<String> permissions;
}
