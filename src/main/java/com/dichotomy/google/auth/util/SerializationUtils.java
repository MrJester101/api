package com.dichotomy.google.auth.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.dichotomy.google.auth.serializer.DateAdapter;

public class SerializationUtils {
    private static final Logger logger = LoggerFactory.getLogger(SerializationUtils.class);
    private static Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).registerTypeAdapter(Date.class, new DateAdapter()).create();
    private static Gson prettyGson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).registerTypeAdapter(Date.class, new DateAdapter()).create();
    private static YAMLFactory factory = new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES).disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
    private static ObjectMapper yaml = new ObjectMapper(factory).enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING).setSerializationInclusion(JsonInclude.Include.NON_NULL).setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    public static <T> T fromJson(Reader json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonIOException | JsonSyntaxException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T fromFile(String filename, Type typeOfT) {
        try (FileReader fileReader = new FileReader(filename)) {
            return fromJson(fileReader, typeOfT);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T fromJson(JsonReader json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonIOException | JsonSyntaxException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Tries to parse the json and return the object. On failure returns null
     * without logging exception
     * 
     * @param json
     * @param typeOf T
     * @return T
     */
    public static <T> T fromJsonQuiet(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException ex) {
            return null;
        }
    }

    public static String toJson(Object src, boolean pretty) {
        if (pretty == true) {
            return prettyGson.toJson(src);
        } else {
            return gson.toJson(src);
        }
    }

    public static String toJson(Object src) {
        return toJson(src, false);
    }

    public static String toYaml(Object src) {
        try {
            return yaml.writeValueAsString(src);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T fromYaml(String json, TypeReference<T> typeOfT) {
        try {
            return yaml.readValue(json, typeOfT);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T convertFromYaml(Object obj, TypeReference<T> typeOfT) {
        if (obj == null) {
            return null;
        }

        return fromYaml(toYaml(obj), typeOfT);
    }

    public static <T> T convert(Object obj, Type typeOfT) {
        if (obj == null) {
            return null;
        }

        return fromJsonQuiet(toJson(obj), typeOfT);
    }
}
