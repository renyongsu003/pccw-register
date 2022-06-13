package com.pccw.register.domain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String obj2Json(Object obj)throws Exception {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(obj);
    }

    public static <T> T json2Obj(String str,Class<T> cls)throws Exception{
        ObjectMapper om = new ObjectMapper();
        return om.readValue(str,cls);
    }
}
