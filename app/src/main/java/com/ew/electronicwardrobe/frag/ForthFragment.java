package com.ew.electronicwardrobe.frag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.entity.User;
import com.ew.electronicwardrobe.service.UserService;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.SharedPreferencesUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;

/**
 * 个人中心
 */
public class ForthFragment extends Fragment {
    private ImageButton mIbLingdang;
    private ImageButton mIbSetting;
    private ImageView mIv;
    private ImageView mIvBg;
    private TextView tv1;
    private TextView tv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forth, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        User user = SharedPreferencesUtils.getUser();
        if (user == null) {
            user = UserService.get(1);
        }
        mIvBg = (ImageView) view.findViewById(R.id.iv_bg);
        mIbLingdang = (ImageButton) view.findViewById(R.id.ib_lingdang);
        mIbSetting = (ImageButton) view.findViewById(R.id.ib_setting);
        mIv = (ImageView) view.findViewById(R.id.iv_head);
        tv1 = view.findViewById(R.id.tv_1);
        tv2 = view.findViewById(R.id.tv_2);
        tv1.setText(user.getUsername());
        tv2.setText(user.getDesc());
        ImageUtils.setBitmap(getActivity(), user.getAvatar(), mIv);
        ImageUtils.setBitmap(getActivity(), R.drawable.bg_bottom4_pink_pic, mIvBg);
        StatusBarUtil.setMargin(getActivity(), mIbLingdang);
        StatusBarUtil.setMargin(getActivity(), mIbSetting);
    }
}
