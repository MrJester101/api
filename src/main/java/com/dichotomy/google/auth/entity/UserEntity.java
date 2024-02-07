package com.dichotomy.google.auth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dichotomy.google.auth.entity.identity.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.dichotomy.google.auth.entity.BaseEntity;



@Entity
@Table(name = "t_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserEntity extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Transient
    public String getUsername() {
        return universalId;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    @Id
//    @JsonIgnore
//    @Transient
    @Size(min = 0, max = 100)
    @Column(length = 100, name = "univeral_id")
    public String getUniversalId() {
        return universalId;
    }

    @NotNull
    @Size(min = 0, max = 100)
    @Column(length = 100, name = "name")
    public String getName() {
        return name;
    }

    @Email
    @Size(min = 0, max = 100)
    @NotNull
    @Column(length = 100, unique = true, name = "email")
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Column(length = 1024, name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    @Column(name = "enabled_2fa")
    public boolean isUsing2FA() {
        return using2FA;
    }

    @Column(name = "mfa_mode")
    public String getMfaMode() {
        return mfaMode;
    }

    public boolean isLocked() {
        return locked;
    }

    @Column(length = 32, name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(length = 64, name = "country")
    public String getCountry() {
        return country;
    }

    @Column(length = 64, name = "city")
    public String getCity() {
        return city;
    }

    @Column(length = 64, name = "created_on")
    public long getCreatedOn() {
        return createdOn;
    }

    @Column(length = 64, name = "modified_on")
    public long getModifiedOn() {
        return modifiedOn;
    }

    @Lob
    @Column(name = "info", columnDefinition = "LONGTEXT")
    public String getInfo() {
        return info;
    }

    @JsonIgnore
    @Lob
    @NotNull
    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    public String getMetadata() {
        return metadata;
    }

    @JsonIgnore
    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return locked;
    }

    @JsonIgnore
    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @JsonIgnore
    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    public UserEntity() {
    }

    public UserEntity(User dto) {
        if (dto == null) {
            return;
        }

        setEnabled(dto.isEnabled());
        setUniversalId(dto.getUniversalId());
        setAuthority(dto.getAuthority());
        setEmail(dto.getEmail());
        setName(dto.getName());
        setPassword(dto.getPassword());
        setUsing2FA(dto.isUsing2FA());
        setMfaMode(dto.getMfaMode());
        setPhone(dto.getPhone());
        setCountry(dto.getCountry());
        setCity(dto.getCity());
        setInfo(dto.getInfo());
        setMetadata(dto.getMetadata());
    }

    public static List<User> transform(List<UserEntity> entityList) {
        List<User> list = new ArrayList<>();
        if (entityList != null) {
            for (UserEntity e : entityList) {
                list.add(e);
            }
        }

        return list;
    }

    public static User transform(UserEntity entity) {
        return entity;
    }

    public static List<UserEntity> reverse(List<User> list) {
        List<UserEntity> entityList = new ArrayList<>();
        if (list != null) {
            for (User e : list) {
                entityList.add(new UserEntity(e));
            }
        }

        return entityList;
    }
}