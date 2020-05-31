package com.ew.electronicwardrobe.act;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.view.CameraView;
import androidx.core.app.ActivityCompat;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.util.ImageUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 拍照功能
 */
public class PhotoActivity extends BaseActivity {
    private CameraView mViewCamera;
    private Button mBtnTake;
    private Button mBtnTorch;
    private Button mBtnReplace;
    private ImageView mIvBack;
    private ImageView mIvDelete;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private RelativeLayout mRlLine;


    @Override
    protected void initEvent() {
        //进行拍照
        mBtnTake.setOnClickListener(v -> {
            String fileName = simpleDateFormat.format(new Date());
            File file = new File("/mnt/sdcard/aaa/" + fileName + ".jpg");
            mViewCamera.takePicture(file, command -> {
                EventBus.getDefault().post(file);
            }, new ImageCapture.OnImageSavedCallback() {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                }

                @Override
                public void onError(@NonNull ImageCaptureException exception) {
                    exception.printStackTrace();
                }
            });
        });

        //打开关闭闪光灯
        mBtnTorch.setOnClickListener(v -> mViewCamera.enableTorch(!mViewCamera.isTorchOn()));

        //切换摄像头
        mBtnReplace.setOnClickListener(v -> mViewCamera.toggleCamera());

        //打开关闭参考线
        mIvDelete.setOnClickListener(v -> mRlLine.setVisibility(mRlLine.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exeFile(File file) {
        Log.e("TAG", "-----------------------" + file.getName());
        //保存到系统相册
        saveImageToGallery(PhotoActivity.this, file);
        Toast.makeText(this, "拍照成功" + file.getName(), Toast.LENGTH_SHORT).show();
        //跳转到照片编辑页面
        Intent intent = new Intent(PhotoActivity.this, EditPhotoActivity.class);
        intent.putExtra("data", file.getAbsolutePath());
        startActivity(intent);

    }

    @Override
    protected void initData() {
        //设置自动对焦
        mViewCamera.setFocusable(true);
        //双指缩放
        mViewCamera.setPinchToZoomEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mViewCamera = (CameraView) findViewById(R.id.view_camera);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.

            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.CAMERA},
                                       222);
        }
        mViewCamera.bindToLifecycle(this);
        mBtnTake = (Button) findViewById(R.id.btn_take);
        mBtnTorch = (Button) findViewById(R.id.btn_torch);
        mBtnReplace = (Button) findViewById(R.id.btn_replace);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);

        mRlLine = (RelativeLayout) findViewById(R.id.rl_line);

        ImageUtils.setBitmap(this, R.drawable.icon_open__line, mIvDelete);
        ImageUtils.setBitmap(this, R.drawable.icon_search_back, mIvBack);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        CameraX.unbindAll();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setConfigBeforeContentView() {
        super.setConfigBeforeContentView();
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void saveImageToGallery(Context context, File file) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
    }

}
