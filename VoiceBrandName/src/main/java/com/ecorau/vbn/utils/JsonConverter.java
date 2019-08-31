package com.ecorau.vbn.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static byte[] serialize(Object obj) throws Exception {
        return mapper.writeValueAsBytes(obj);
    }

    public static <T> T deserialize(byte[] data, Class clazz) throws Exception {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        T student = (T) mapper.readValue(data, clazz);

        return student;
    }
}
