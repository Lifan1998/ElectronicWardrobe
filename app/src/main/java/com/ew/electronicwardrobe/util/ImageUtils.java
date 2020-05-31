package com.ew.electronicwardrobe.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ew.electronicwardrobe.AppClient;
import com.ew.electronicwardrobe.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageUtils {

    /**
     * 设置DrawableLeft等的大小
     *
     * @param textView 需要设置的控件(TextView or EditText)
     */
    public static void setDrawableSize(TextView textView) {
        if (textView == null) {
            return;
        }
        Context context = AppClient.getmContext();
        //左，上，右，下
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        for (int i = 0; i < compoundDrawables.length; i++) {
            if (compoundDrawables[i] != null) {
                int size = (int) context.getResources().getDimension(R.dimen.dp_20);
                compoundDrawables[i].setBounds(0, 0, size, size);
            }
        }
        textView.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    public static void setDrawableSize(TextView textView, int size) {
        if (textView == null) {
            return;
        }
        Context context = AppClient.getmContext();
        //左，上，右，下
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        for (int i = 0; i < compoundDrawables.length; i++) {
            if (compoundDrawables[i] != null) {
                compoundDrawables[i].setBounds(0, 0, size, size);
            }
        }
        textView.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }


    /**
     * 裁剪填充
     *
     * @param resId     图片资源
     * @param imageView 图片显示控件
     */
    @SuppressLint("CheckResult")
    public static void setBitmap(Context context, @DrawableRes int resId, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context).load(resId).apply(requestOptions).into(imageView);
    }

    public static void setBitmap(Context context, File resId, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context).load(resId).apply(requestOptions).into(imageView);
    }

    public static void setBitmap(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }


}
