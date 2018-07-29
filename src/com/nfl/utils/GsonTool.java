package com.nfl.utils;

import com.google.gson.Gson;

public class GsonTool {

    private static Gson gson ;

    static {
        gson = new Gson() ;
    }

    public static String object2String(Object object){
        return gson.toJson(object) ;
    }
}
