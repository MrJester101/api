package com.dichotomy.google.auth.entity;

import java.beans.Transient;
import java.util.HashMap;

import com.dichotomy.google.auth.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.reflect.TypeToken;


import com.dichotomy.google.auth.util.SerializationUtils;
import com.dichotomy.google.auth.util.TimeUtils;

public class BaseEntity {
    protected long createdOn = TimeUtils.getCurrentTime();
    protected long modifiedOn = TimeUtils.getCurrentTime();
    protected String info = SerializationUtils.toJson(new HashMap<String, Object>());
    protected String metadata = SerializationUtils.toJson(new HashMap<String, Object>());

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @JsonIgnore
    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public BaseEntity() {
        if (StringUtils.isEmpty(metadata) == true) {
            HashMap<String, Object> map = new HashMap<>();
            metadata = SerializationUtils.toJson(map);
        }

        if (StringUtils.isEmpty(metadata) == true) {
            HashMap<String, Object> map = new HashMap<>();
            metadata = SerializationUtils.toJson(map);
        }
    }

    public void updateEntity() {
        this.modifiedOn = TimeUtils.getCurrentTime();
    }

    public void updateEntityMetadata(HashMap<String, Object> metadata) {
        this.metadata = SerializationUtils.toJson(metadata);
        updateEntity();
    }

    public void updateEntityInfo(HashMap<String, Object> metadata) {
        this.info = SerializationUtils.toJson(metadata);
        updateEntity();
    }

    @Transient
    @JsonIgnore
    public HashMap<String, Object> getEntityInfo() {
        if (metadata == null) {
            return new HashMap<>();
        }

        return SerializationUtils.fromJson(info, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    @Transient
    @JsonIgnore
    public HashMap<String, Object> getEntityMetadata() {
        if (metadata == null) {
            return new HashMap<>();
        }

        return SerializationUtils.fromJson(metadata, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    public void addEntry(String key, Object value) {
        HashMap<String, Object> metadata = getEntityMetadata();
        metadata.put(key, value);
        updateEntityMetadata(metadata);
    }

    @JsonIgnore
    public Object getEntry(String key) {
        HashMap<String, Object> metadata = getEntityMetadata();
        return metadata.get(key);
    }

    public void addInfoEntry(String key, Object value) {
        HashMap<String, Object> info = getEntityInfo();
        info.put(key, value);
        updateEntityInfo(info);
    }

    @JsonIgnore
    public Object getInfoEntry(String key) {
        HashMap<String, Object> info = getEntityInfo();
        return info.get(key);
    }
}