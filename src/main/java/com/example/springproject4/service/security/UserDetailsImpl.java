package com.example.springproject4.service.security;

import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final boolean isSuperAdmin;

    public UserDetailsImpl(User user, boolean isSuperAdmin) {
        this.user = user;
        this.isSuperAdmin = isSuperAdmin;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public Collection<? extends GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles)
    {
        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();

//        Collection<? extends GrantedAuthority> authorities = roles.stream()
//                .map(role -> new GrantedAuthority() {
//                    @Override
//                    public String getAuthority() {
//                        return role.getName();
//                    }
//                }).toList();

        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToGrantedAuthorities(user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail(){
        return user.getEmail();
    }
    public Integer getAge() {
        return user.getAge();
    }
}
