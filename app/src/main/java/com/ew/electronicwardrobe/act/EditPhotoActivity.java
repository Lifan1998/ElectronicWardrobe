package com.ew.electronicwardrobe.act;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;

import java.io.File;

/**
 * 编辑照片页面
 */
public class EditPhotoActivity extends BaseActivity {
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private ImageView mIvDelete;
    private ImageView mIvPic;

    @Override
    protected void initEvent() {
        mIvBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        String filePath = getIntent().getStringExtra("data");
        File file = new File(filePath);
        Log.e("TAG", "-----------------------file:" + file.getName());
        ImageUtils.setBitmap(this, file, mIvPic);
    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        StatusBarUtil.setMargin(this, mLlTitle);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_photo;
    }
}
