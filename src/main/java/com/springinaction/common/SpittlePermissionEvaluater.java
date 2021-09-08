package com.springinaction.common;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;

/**
 * Created by zjjfly on 2017/2/22.
 */
public class SpittlePermissionEvaluater implements PermissionEvaluator {
    private static final GrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("ROLE_ADMIN");

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(targetDomainObject instanceof Spittle){
            Spittle spittle = (Spittle) targetDomainObject;
            if("add".equals(permission)){
                return isAdmin(authentication) || spittle.getMessage().length()<=140;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

    private boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
    }
}
