package com.pl.portal.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
    private Json(){}
    static ObjectMapper om = new ObjectMapper();

    public static String to(Object value) {
        try {
            return om.writeValueAsString(value);
        } catch (Exception e) {
            return "";
        }
    }

    public static <T> T from(String json, Class<T> c) {
        try {
            return om.readValue(json, c);
        } catch (IOException e) {
            return null;
        }
    }
}
