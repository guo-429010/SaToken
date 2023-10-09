package com.satoken.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private Integer id;

    private String menuName;

    private String menuPath;

    private String menuComponent;

    private String menuIcon;

    private List<MenuVo> children;
}
