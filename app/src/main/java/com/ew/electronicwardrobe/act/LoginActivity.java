package com.ew.electronicwardrobe.act;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.SharedPreferencesUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    private ImageView mIv;
    private ImageView mIvLoginIcon;
    private ImageView mIvLoginIconFacebook;
    private LinearLayout mLlWechart;
    private LinearLayout mLlFacebook;


    @Override
    protected void initEvent() {
        //TODO 微信登录按钮，进行登录的业务逻辑
        mLlWechart.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        //TODO facebook登录的按钮
        mLlFacebook.setOnClickListener(v -> {
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferencesUtils.init(this);
        StatusBarUtil.darkMode(this);
        //设置背景图片
        mIv = (ImageView) findViewById(R.id.iv_head);
        ImageUtils.setBitmap(this, R.drawable.bg_login, mIv);
        //设置登录图标
        mIvLoginIcon = (ImageView) findViewById(R.id.iv_login_icon_wechart);
        ImageUtils.setBitmap(this, R.drawable.icon_wechart, mIvLoginIcon);
        mIvLoginIconFacebook = (ImageView) findViewById(R.id.iv_login_icon_facebook);
        ImageUtils.setBitmap(this, R.drawable.icon_facebook, mIvLoginIconFacebook);
        //两个登录按钮
        mLlWechart = (LinearLayout) findViewById(R.id.ll_wechart);
        mLlFacebook = (LinearLayout) findViewById(R.id.ll_facebook);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }
}
