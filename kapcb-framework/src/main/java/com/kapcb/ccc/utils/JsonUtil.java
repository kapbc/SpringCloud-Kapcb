package com.kapcb.ccc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapcb.ccc.constants.enmus.ResultStatus;
import com.kapcb.ccc.model.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a>Title: JsonUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:12
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final String DEFAULT_JSON_VALUE = "{}";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }


    /**
     * convert service transfer json result
     * please check the return value weather is null
     *
     * @param jsonString String
     * @param clazz      Class<? extends T>
     * @param <T>        T
     * @return T
     */
    public static <T> T convertJsonToData(String jsonString, Class<? extends T> clazz) {
        if (StringUtils.isBlank(jsonString) || Objects.isNull(clazz)) {
            log.info("the json string or clazz is null, please check your input args...");
            return null;
        }
        try {
            Result result = OBJECT_MAPPER.readValue(jsonString, Result.class);
            if (Objects.nonNull(result) && Objects.equals(result.getCode(), ResultStatus.SUCCESS.value())) {
                log.info("the result data is : " + result.getData());
                if (Objects.nonNull(result.getData())) {
                    return OBJECT_MAPPER.convertValue(result.getData(), clazz);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("json process error, the exception message info is : " + e.getMessage());
        }
        return null;
    }

    /**
     * convert value
     *
     * @param source Object
     * @param clazz  Class<? extends T>
     * @param <T>    <T>
     * @return T
     */
    public static <T> T convertValue(Object source, Class<? extends T> clazz) {
        if (Objects.isNull(source)) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(source, clazz);
    }

    /**
     * convert object to json string
     *
     * @param object T
     * @param <T>    <T>
     * @return String
     */
    public static <T> String convertObjectToString(T object) {
        if (Objects.equals(null, object)) {
            return null;
        }
        String convertResult = null;
        try {
            return object instanceof String ? (String) object : OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json process error, the exception is : " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T convertBytesToObject(byte[] bytes, Class<? extends T> clazz) {
        try {
            return Objects.equals(String.class, clazz) ? (T) new String(bytes) : OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("process json process error, the exception is : " + e.getMessage());
        }
        return (T) DEFAULT_JSON_VALUE;
    }

    public static <T> String convertObjectToStringPretty(T object) {
        if (Objects.equals(null, object)) {
            return null;
        }
        String convertResult = null;
        try {
            return object instanceof String ? (String) object : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json process error, the exception is : " + e.getMessage());
            return null;
        }
    }

    /**
     * convert json string to object
     *
     * @param jsonString String
     * @param clazz      Class<T>
     * @param <T>        <T>
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertStringToObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        T convertResult = null;
        try {
            return Objects.equals(clazz, String.class) ? (T) jsonString : OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("json process error, the exception is : " + e.getMessage());
            return null;
        }
    }

    /**
     * jsonString convert to collection object
     *
     * @param jsonString    String
     * @param typeReference TypeReference<T>
     * @param <T>           <T>
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertStringToObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString) || typeReference == null) {
            return null;
        }
        try {
            return Objects.equals(typeReference.getType(), String.class) ? (T) jsonString : OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            log.error("json process error, the exception is : " + e.getMessage());
            return null;
        }
    }

    /**
     * jsonString convert to collection object
     *
     * @param jsonString      String
     * @param collectionClass Class<?>
     * @param elementClasses  Class<?>
     * @param <T>             <T>
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertStringToObject(String jsonString, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return OBJECT_MAPPER.readValue(jsonString, javaType);
        } catch (IOException e) {
            log.error("json process error, the exception is : " + e.getMessage());
            return null;
        }
    }

    /**
     * Map builder
     */
    public static class JsonBuilder {

        private final Map<String, Object> builderMap = new HashMap<>(INITIAL_CAPACITY);

        public JsonBuilder() {
        }

        public JsonUtil.JsonBuilder put(String key, Object value) {
            builderMap.put(key, value);
            return this;
        }

        public String builder() {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this.builderMap);
            } catch (JsonProcessingException e) {
                log.error("json process error, the exception is : " + e.getMessage(), e);
            }
            return DEFAULT_JSON_VALUE;
        }
    }
}
