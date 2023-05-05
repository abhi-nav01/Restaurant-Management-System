package com.thespicetable.restaurant.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail extends User implements UserDetails {
    public CustomUserDetail(User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List is used because a user can be authorised as both
        List<GrantedAuthority> authorityList=new ArrayList<>();
        super.getRoles().forEach(role ->{
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        });
        // here, in authorityList we are adding authorities using forEACH loop
        //the role is converted to GrantedAuthority class using SimpleGrantedAuthority
        return authorityList;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
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
}
