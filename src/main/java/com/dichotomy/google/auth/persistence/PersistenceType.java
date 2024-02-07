package com.dichotomy.google.auth.persistence;

import java.util.HashMap;
import java.util.Map;

public enum PersistenceType {
    MYSQL("com.mysql.cj.jdbc.MysqlDataSource"),
    TIMESCALE("org.postgresql.ds.PGSimpleDataSource"),
    SQLITE("org.sqlite.SQLiteDataSource");

    private static final Map<String, PersistenceType> displayNameIndex = new HashMap<>();
    private final String driver;

    PersistenceType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return this.driver;
    }

    static {
        for (PersistenceType suit : PersistenceType.values()) {
            displayNameIndex.put(suit.toString(), suit);
        }
    }

    public static PersistenceType parse(Object name) {
        if (name == null) {
            return null;
        }

        return displayNameIndex.get(name.toString().toUpperCase());
    }
}