package com.ew.electronicwardrobe.act;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.ColorBean;
import com.ew.electronicwardrobe.domain.ImageBean;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.ew.electronicwardrobe.view.SpacesItemDecorationLeft;

import java.util.ArrayList;
import java.util.List;

public class EditLookActivity extends BaseActivity {
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private RecyclerView mRvColor;
    private ArrayList<ColorBean> datasColor;
    private MyColorAdapter myColorAdapter;
    private RecyclerView mRvOccasion;
    private MyAdapter myAdapter;
    private List<ImageBean> datas;
    private ImageView mIvDelete;
    private RecyclerView mRvClothes;

    @Override
    protected void initEvent() {
        mIvBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        //设置颜色图标适配器
        datasColor = new ArrayList<>();
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color1));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color2));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color3));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color4));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color5));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color6));
        datasColor.add(new ColorBean(false, R.drawable.icon_search_color7));
        //添加间隔线
        mRvColor.addItemDecoration(new SpacesItemDecorationLeft((int) getResources().getDimension(R.dimen.dp_10)));
        mRvColor.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        myColorAdapter = new MyColorAdapter(R.layout.item_search_color, datasColor);
        mRvColor.setAdapter(myColorAdapter);


        //设置数据
        datas = new ArrayList<>();
        datas.add(new ImageBean(R.drawable.icon_bottom1_top1));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top2));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top3));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top4));
        datas.add(new ImageBean(R.drawable.icon_bottom1_top5));
        myAdapter = new MyAdapter(R.layout.item_bottom1_imageview, datas);
        mRvOccasion.setLayoutManager(new GridLayoutManager(this, 3));
        mRvOccasion.setAdapter(myAdapter);


        mRvClothes.setLayoutManager(new GridLayoutManager(this, 3));
        mRvClothes.setAdapter(myAdapter);
    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mRvColor = (RecyclerView) findViewById(R.id.rv_color);
        StatusBarUtil.setMargin(this, mLlTitle);
        mRvOccasion = (RecyclerView) findViewById(R.id.rv_occasion);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);
        mRvClothes = (RecyclerView) findViewById(R.id.rv_clothes);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_look;
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
            ImageUtils.setBitmap(EditLookActivity.this, item.getImage(), view);
        }
    }


    /**
     * 颜色列表适配器
     */
    private class MyColorAdapter extends BaseQuickAdapter<ColorBean, BaseViewHolder> {

        public MyColorAdapter(int layoutResId, @Nullable List<ColorBean> data) {
            super(layoutResId, data);
        }

        public MyColorAdapter(@Nullable List<ColorBean> data) {
            super(data);
        }

        public MyColorAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ColorBean item) {
            ImageView mIvPic = helper.getView(R.id.iv_color);
            ImageUtils.setBitmap(EditLookActivity.this, item.getImage(), mIvPic);
            LinearLayout mLlRoot = helper.getView(R.id.ll_root);
            if (item.isChecked()) {
                mLlRoot.setBackgroundResource(R.drawable.bg_search_grenn_circle);
            } else {
                mLlRoot.setBackground(null);
            }
        }
    }
}
