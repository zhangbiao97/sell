package co.zhangbiao.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Create By ZhangBiao
 * 2020/3/3
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
