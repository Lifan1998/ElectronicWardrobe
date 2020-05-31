package com.ew.electronicwardrobe.service;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ew.electronicwardrobe.entity.Cloths;
import com.ew.electronicwardrobe.entity.User;
import com.ew.electronicwardrobe.util.SharedPreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ClothsService {

    public static String localhost = "http://10.0.2.2:8082/";
    public static String remoteHost = "http://106.14.141.90:8082/";
    public static String host = "http://192.168.199.159:8082/";
    public static String baseUrl;

    public static String TAG = "ClothsService";


    static {
        baseUrl = localhost;
    }

    public static List<Cloths> query(Cloths cloths) {
        User user = SharedPreferencesUtils.getUser();
        if (user == null) {
            cloths.setUserId(1);
        } else {
            cloths.setUserId(user.getId());
        }

        OkHttpClient client = new OkHttpClient();

        String url = baseUrl + "cloths/query";
        // 创建一个请求 Builder
        Request.Builder builder = new Request.Builder();
        // 创建一个 request
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();

        Map<String, Object> objectMap = JSON.parseObject(JSON.toJSONString(cloths), Map.class);

        objectMap.keySet().stream().forEach(s -> {
            urlBuilder.addQueryParameter(s, objectMap.get(s).toString());
        });
        builder.url(urlBuilder.build());


        try {

            Callable<List<Cloths>> responseCallable = new Callable<List<Cloths>>() {
                @Override
                public List<Cloths> call() throws Exception {
                    Response response =  client.newCall(builder.build()).execute();
                    String responseString = response.body().string();
                    Log.d(TAG, "response " + responseString);
                    if (response.code() == 200) {
                        JSONArray jsonObject = JSON.parseArray(responseString);
                        List<Cloths> clothsList = JSON.parseArray(jsonObject.toJSONString(), Cloths.class);
                        Log.d(TAG, "clothsList " + clothsList);
                        return clothsList;
                    } else {
                        return new ArrayList<>();
                    }
                }
            };

            FutureTask<List<Cloths>> responseFutureTask = new FutureTask<>(responseCallable);
            new Thread(responseFutureTask).start();
            return responseFutureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
