package com.dichotomy.google.auth.persistence;

import java.util.HashMap;

public class Persistence {
    private PersistenceType type = PersistenceType.MYSQL;
    private PersistenceReplicaType replica = PersistenceReplicaType.WRITE;
    private String endpoint;
    private int port;
    private String database;
    private String username;
    private String password;
    private HashMap<String, Object> metadata = new HashMap<>();

    public PersistenceType getType() {
        return type;
    }

    public void setType(PersistenceType type) {
        this.type = type;
    }

    public PersistenceReplicaType getReplica() {
        return replica;
    }

    public void setReplica(PersistenceReplicaType replica) {
        this.replica = replica;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, Object> metadata) {
        this.metadata = metadata;
    }
}