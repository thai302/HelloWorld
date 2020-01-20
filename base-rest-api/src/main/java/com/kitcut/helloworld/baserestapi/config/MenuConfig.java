package com.kitcut.helloworld.baserestapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("app") // prefix app, find app.* values
@PropertySource("classpath:menu.yaml")
public class MenuConfig {
    private List<Menu> menus = new ArrayList<>();

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public static class Menu {
        private String name;
        private String path;
        private String title;

        // getter - setter
        @Override
        public String toString() {
            return "Menu{" + "name='" + name + '\'' + ", path='" + path + '\'' + ", title='" + title + '\'' + '}';
        }
    }
}
