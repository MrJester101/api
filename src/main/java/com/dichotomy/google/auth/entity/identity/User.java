package com.dichotomy.google.auth.entity.identity;

import java.util.HashMap;



import com.dichotomy.google.auth.util.RandomUtil;
import com.dichotomy.google.auth.entity.BaseEntity;
import com.dichotomy.google.auth.entity.UserInternalMetadata;


public class User extends BaseEntity {
    protected boolean enabled;
    protected String universalId = RandomUtil.generateUUIDKey();
    protected String name;
    protected String email;
    protected String password;
    protected String authority;
    protected boolean using2FA;
    protected String mfaMode;
    protected boolean locked;
    protected String phone;
    protected String country;
    protected String city;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUniversalId() {
        return universalId;
    }

    public void setUniversalId(String universalId) {
        this.universalId = universalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isUsing2FA() {
        return using2FA;
    }

    public void setUsing2FA(boolean using2fa) {
        using2FA = using2fa;
    }

    public String getMfaMode() {
        return mfaMode;
    }

    public void setMfaMode(String mfaMode) {
        this.mfaMode = mfaMode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
        HashMap<String, Object> metadata = getEntityMetadata();
        if (locked == false) {
            metadata.remove(UserInternalMetadata.ACCOUNT_FAILED_ATTEMPT.name());
            metadata.remove(UserInternalMetadata.ACCOUNT_LOCKOUT_TIME.name());
            updateEntityMetadata(metadata);
        }
    }
}
