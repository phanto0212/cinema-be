package com.phanvanto.cinema.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;
    private final String email;
    private final String fullName;
    private final String password;
    private final String username;
    private final Boolean gender;
    private final String address;
    private final String telephone;
    private final Boolean enabled;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;

    public CustomUserDetails(Collection<? extends GrantedAuthority> authorities, String email, String fullName,
            String password, String username, Boolean gender, String address, String telephone, Boolean enabled,
            boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
        this.authorities = authorities;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.address = address;
        this.telephone = telephone;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Getters for additional fields
    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}

