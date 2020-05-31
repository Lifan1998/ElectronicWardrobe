package com.ew.electronicwardrobe.act;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.frag.FirstFragment;
import com.ew.electronicwardrobe.frag.ForthFragment;
import com.ew.electronicwardrobe.frag.SecondFragment;
import com.ew.electronicwardrobe.frag.ThirdFragment;
import com.ew.electronicwardrobe.util.StaticClass;
import com.ew.electronicwardrobe.util.StatusBarUtil;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    private FrameLayout mFlContent;
    private RadioGroup mRg;
    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;

    @Override
    protected void initEvent() {
        //底部按钮切换
        mRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_1:
                    chengeFragment(new FirstFragment());
                    StatusBarUtil.darkMode(this);
                    break;
                case R.id.rb_2:
                    chengeFragment(new SecondFragment());
                    break;
                case R.id.rb_3:
                    chengeFragment(new ThirdFragment());
                    break;
                case R.id.rb_4:
                    chengeFragment(new ForthFragment());
                    StatusBarUtil.darkMode(this);
                    break;
            }
        });


        if (StaticClass.IS_DEBUG) {
            mRb1.setChecked(true);
        } else {
            //默认选中第四个
            mRb4.setChecked(true);
        }
    }

    //切换Fragment
    private void chengeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mRb1 = (RadioButton) findViewById(R.id.rb_1);
        mRb2 = (RadioButton) findViewById(R.id.rb_2);
        mRb3 = (RadioButton) findViewById(R.id.rb_3);
        mRb4 = (RadioButton) findViewById(R.id.rb_4);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }
}
