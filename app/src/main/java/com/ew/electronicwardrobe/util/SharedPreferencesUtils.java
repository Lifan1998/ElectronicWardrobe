package com.ew.electronicwardrobe.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.ew.electronicwardrobe.entity.User;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {

    public static final String TOKEN = "token";

    private static SharedPreferences sharedPreferences;
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE);
        }
    }
    public static SharedPreferences getShardPreferences() {
        return sharedPreferences;
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static User getUser() {
        String userJsonString =  sharedPreferences.getString("user", null);
        return JSON.parseObject(userJsonString, User.class);
    }

    public static void setUser(User user) {
        sharedPreferences.edit().putString("user", JSON.toJSONString(user)).apply();
    }


    public static void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }
}

