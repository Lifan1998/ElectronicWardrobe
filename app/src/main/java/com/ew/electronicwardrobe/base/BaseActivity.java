package com.ew.electronicwardrobe.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.ew.electronicwardrobe.util.StatusBarUtil;

/**
 * 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConfigBeforeContentView();
        setContentView(getLayoutRes());
        initView();
        initData();
        initEvent();
    }


    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化布局
     */
    protected abstract void initView();


    /**
     * 在setContentView之前设置数据
     */
    protected void setConfigBeforeContentView() {
        //android 5.0以上状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        //沉浸式
        StatusBarUtil.immersive(this);
    }


    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutRes();


}
