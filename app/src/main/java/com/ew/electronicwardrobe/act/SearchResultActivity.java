package com.ew.electronicwardrobe.act;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.ImageBean;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果页面
 */
public class SearchResultActivity extends BaseActivity {
    private RecyclerView mRv;
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private LinearLayout mLlNetwork;
    private ImageView mIvNetwork;
    private TextView mRp;
    private MyAdapter myAdapter;
    private List<ImageBean> datas;

    @Override
    protected void initEvent() {
        mIvBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {

        //设置数据
        datas = new ArrayList<>();
        datas.add(new ImageBean(R.drawable.icon_bottom1_top1));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top2));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top3));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top4));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top5));
        myAdapter = new MyAdapter(R.layout.item_bottom1_imageview, datas);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.setAdapter(myAdapter);
    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mLlNetwork = (LinearLayout) findViewById(R.id.ll_network);
        mIvNetwork = (ImageView) findViewById(R.id.iv_network);
        mRp = (TextView) findViewById(R.id.rp);
        StatusBarUtil.setMargin(this, mLlTitle);
        ImageUtils.setBitmap(this, R.drawable.icon_search_back, mIvBack);
        mRv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    /**
     * 搜索结果的适配器
     */
    private class MyAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<ImageBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ImageBean item) {
            ImageView view = helper.getView(R.id.iv_pic);
            ImageUtils.setBitmap(SearchResultActivity.this, item.getImage(), view);
        }
    }

}
