package com.example.springtest.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, DungeonMaster;

    @Override
    public String getAuthority() {
        return name();
    }
}
