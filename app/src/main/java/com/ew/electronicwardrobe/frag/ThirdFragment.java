package com.ew.electronicwardrobe.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.act.ToadyCheckActivity;
import com.ew.electronicwardrobe.service.WetherService;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;

/**
 * 天气
 */
public class ThirdFragment extends Fragment {
    private ImageView mIvBg;
    private TextView mTvCloudy;
    private TextView mTvNum;
    private RelativeLayout mRl;
    private TextView mTvTomorrow;
    private TextView mTvSo;
    private TextView mTvToday;
    private TextView mTvTodayChecked;
    private TextView cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initEvent() {
        //跳转到Toady‘s OutFit页面
        mTvTodayChecked.setOnClickListener(v -> startActivity(new Intent(getActivity(), ToadyCheckActivity.class)));
    }

    private void initView(View view) {
        //背景图片
        mIvBg = (ImageView) view.findViewById(R.id.iv_bg);

        //设置背景图片
        ImageUtils.setBitmap(getActivity(), R.drawable.bg_bottom4, mIvBg);
        mIvBg = (ImageView) view.findViewById(R.id.iv_bg);
        //雨雪
        mTvCloudy = (TextView) view.findViewById(R.id.tv_cloudy);
        //设置状态栏高度
        StatusBarUtil.setMargin(getActivity(), mTvCloudy);
        //温度
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        cityName = view.findViewById(R.id.city_name);
        WetherService.test(mTvNum, cityName);
        mRl = (RelativeLayout) view.findViewById(R.id.rl);
        //Tomorrow按钮
        mTvTomorrow = (TextView) view.findViewById(R.id.tv_tomorrow);
        mTvSo = (TextView) view.findViewById(R.id.tv_so);
        //Today 按钮
        mTvToday = (TextView) view.findViewById(R.id.tv_today);


        mTvTodayChecked = (TextView) view.findViewById(R.id.tv_today_checked);

    }
}
