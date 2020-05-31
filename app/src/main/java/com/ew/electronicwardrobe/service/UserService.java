package com.ew.electronicwardrobe.service;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ew.electronicwardrobe.entity.Cloths;
import com.ew.electronicwardrobe.entity.User;
import com.ew.electronicwardrobe.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class UserService {

    public static String localhost = "http://10.0.2.2:8082/";
    public static String remoteHost = "http://106.14.141.90:8082/";
    public static String host = "http://192.168.199.159:8082/";
    public static String baseUrl;

    public static String TAG = "UserService";


    static {
        baseUrl = localhost;
    }

    public static User get(Integer userId) {
        

        OkHttpClient client = new OkHttpClient();

        String url = baseUrl + "user/get";
        // 创建一个请求 Builder
        Request.Builder builder = new Request.Builder();
        // 创建一个 request
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("userId", userId.toString());
        builder.url(urlBuilder.build());


        try {

            Callable<User> responseCallable = new Callable<User>() {
                @Override
                public User call() throws Exception {
                    Response response =  client.newCall(builder.build()).execute();
                    String responseString = response.body().string();
                    Log.d(TAG, "response " + responseString);
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSON.parseObject(responseString);
                        User user = JSON.parseObject(jsonObject.toJSONString(), User.class);
                        Log.d(TAG, "user " + user);
                        return user;
                    } else {
                        return null;
                    }
                }
            };

            FutureTask<User> responseFutureTask = new FutureTask<>(responseCallable);
            new Thread(responseFutureTask).start();
            return responseFutureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }


}
