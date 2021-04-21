package com.gyy.boot.vo;

import lombok.Data;

@Data
public class Menu {
    // 路径
    String path;
    // 导航名
    String name;
    // 图标
    String icon;
    // 子节点
    Menu[] child;

    public Menu(String path, String name, String icon, Menu[] child) {
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.child = child;
    }
    public Menu(String path, String name, String icon) {
        this.name = name;
        this.path = path;
        this.icon = icon;
    }
}
