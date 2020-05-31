package com.ew.electronicwardrobe.act;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.CategoryBean;
import com.ew.electronicwardrobe.domain.ColorBean;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.ew.electronicwardrobe.view.SpacesItemDecoration;
import com.ew.electronicwardrobe.view.SpacesItemDecorationLeft;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity {
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private RecyclerView mRvClothes;
    private RecyclerView mRvColor;
    private Button mBtnSearch;
    private List<ColorBean> datasColor;
    private MyColorAdapter myColorAdapter;
    private MyCategoryAdapter myCategoryAdapter;
    private List<CategoryBean> datasCategory;

    @Override
    protected void initEvent() {
        //返回上个页面
        mIvBack.setOnClickListener(v -> finish());

        //颜色控件点击事件
        myColorAdapter.setOnItemClickListener((adapter, view, position) -> {
            ColorBean colorBean = datasColor.get(position);
            boolean checked = colorBean.isChecked();
            for (int i = 0; i < datasColor.size(); i++) {
                datasColor.get(i).setChecked(false);
            }
            colorBean.setChecked(!checked);
            myColorAdapter.notifyDataSetChanged();
        });

        //类别控件点击事件
        myCategoryAdapter.setOnItemClickListener((adapter, view, position) -> {
            CategoryBean categoryBean = datasCategory.get(position);
            boolean checked = categoryBean.isChecked();
            for (int i = 0; i < datasCategory.size(); i++) {
                datasCategory.get(i).setChecked(false);
            }
            categoryBean.setChecked(!checked);
            myCategoryAdapter.notifyDataSetChanged();
        });

        //跳转到搜索结果页面
        mBtnSearch.setOnClickListener(v -> startActivity(new Intent(this, SearchResultActivity.class)));
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

        //设置类别
        datasCategory = new ArrayList<>();
        datasCategory.add(new CategoryBean("Top", R.drawable.icon_search_category1));
        datasCategory.add(new CategoryBean("Bottom", R.drawable.icon_search_category2));
        datasCategory.add(new CategoryBean("Long", R.drawable.icon_search_category3));
        datasCategory.add(new CategoryBean("Shoes", R.drawable.icon_search_category4));
        datasCategory.add(new CategoryBean("Bag", R.drawable.icon_search_category5));
        myCategoryAdapter = new MyCategoryAdapter(R.layout.item_search_category_clothes, datasCategory);
        mRvClothes.addItemDecoration(new SpacesItemDecorationLeft((int) getResources().getDimension(R.dimen.dp_10)));
        mRvClothes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRvClothes.setAdapter(myCategoryAdapter);

    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        StatusBarUtil.setMargin(this, mLlTitle);
        ImageUtils.setBitmap(this, R.drawable.icon_search_back, mIvBack);
        mRvClothes = (RecyclerView) findViewById(R.id.rv_clothes);
        mRvColor = (RecyclerView) findViewById(R.id.rv_color);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }


    /**
     * 类别列表适配器
     */
    private class MyCategoryAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {

        public MyCategoryAdapter(int layoutResId, @Nullable List<CategoryBean> data) {
            super(layoutResId, data);
        }

        public MyCategoryAdapter(@Nullable List<CategoryBean> data) {
            super(data);
        }

        public MyCategoryAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, CategoryBean item) {
            ImageView mIvPic = helper.getView(R.id.iv_pic);
            ImageUtils.setBitmap(SearchActivity.this, item.getImage(), mIvPic);
            TextView textView = helper.getView(R.id.tv_text);
            textView.setText(item.getText());

            RelativeLayout mLl = helper.getView(R.id.ll_rl);
            //ff999999
            if (item.isChecked()) {
                mLl.setBackgroundResource(R.drawable.bg_search_grenn_rect);
                textView.setTextColor(Color.parseColor("#47B38F"));
            } else {
                mLl.setBackground(null);
                textView.setTextColor(Color.parseColor("#ff999999"));
            }
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
            ImageUtils.setBitmap(SearchActivity.this, item.getImage(), mIvPic);
            LinearLayout mLlRoot = helper.getView(R.id.ll_root);
            if (item.isChecked()) {
                mLlRoot.setBackgroundResource(R.drawable.bg_search_grenn_circle);
            } else {
                mLlRoot.setBackground(null);
            }
        }
    }

}
