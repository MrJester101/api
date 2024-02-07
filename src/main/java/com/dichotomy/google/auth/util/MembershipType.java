package com.dichotomy.google.auth.util;

import java.util.HashMap;
import java.util.Map;

public enum MembershipType {
    PERSONAL(3),
    OWNER(2),
    MODERATOR(1),
    MEMBER(0);

    private int value;
    private MembershipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static final Map<String, MembershipType> displayNameIndex = new HashMap<>();
    static {
        for (MembershipType suit : MembershipType.values()) {
            displayNameIndex.put(suit.toString(), suit);
        }
    }

    public static MembershipType parse(Object name) {
        if (name == null) {
            return null;
        }

        return displayNameIndex.get(name.toString().toUpperCase());
    }
}
