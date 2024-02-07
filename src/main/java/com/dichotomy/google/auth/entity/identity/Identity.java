package com.dichotomy.google.auth.entity.identity;

import com.dichotomy.google.auth.util.MembershipType;
import org.apache.commons.lang3.StringUtils;

import com.dichotomy.google.auth.entity.BaseEntity;
import com.dichotomy.google.auth.util.RandomUtil;


public class Identity extends BaseEntity {
    protected boolean enabled;
    protected String handle = RandomUtil.generateUUIDKey();
    protected String universalId;
    protected String group = StringUtils.EMPTY;
    protected String avatarId = StringUtils.EMPTY;
    protected MembershipType membership = MembershipType.MEMBER;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getUniversalId() {
        return universalId;
    }

    public void setUniversalId(String universalId) {
        this.universalId = universalId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public MembershipType getMembership() {
        return membership;
    }

    public void setMembership(MembershipType membership) {
        this.membership = membership;
    }
}
