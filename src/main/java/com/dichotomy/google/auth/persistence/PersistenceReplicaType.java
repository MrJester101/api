package com.dichotomy.google.auth.persistence;

import java.util.HashMap;
import java.util.Map;

public enum PersistenceReplicaType {
    READ,
    WRITE;

    private static final Map<String, PersistenceReplicaType> displayNameIndex = new HashMap<>();

    static {
        for (PersistenceReplicaType suit : PersistenceReplicaType.values()) {
            displayNameIndex.put(suit.toString(), suit);
        }
    }

    public static PersistenceReplicaType parse(Object name) {
        if (name == null) {
            return null;
        }

        return displayNameIndex.get(name.toString().toUpperCase());
    }
}