package com.tool.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Jackson
 */
public class JacksonController {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = "{\"1001\" : \"Comedy\"}";

        TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {};
        Map<String, String> map = mapper.readValue(jsonInput, typeRef);

        System.out.println(map);
    }


}
