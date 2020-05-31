package com.ew.electronicwardrobe.act;

import android.graphics.Color;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.CategoryBean;
import com.ew.electronicwardrobe.domain.ColorBean;
import com.ew.electronicwardrobe.domain.MultipleItem;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.ew.electronicwardrobe.view.GridSpacingItemDecoration;
import com.ew.electronicwardrobe.view.SpacesItemDecorationLeft;
import com.ew.electronicwardrobe.view.SpacesItemDecorationLeft;


import java.util.ArrayList;
import java.util.List;

/**
 * 管理单品
 */
public class EditActivity extends BaseActivity {
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private ImageView mIvDelete;
    private ImageView mIvGood;
    private RecyclerView mRvClothes;
    private RecyclerView mRvColor;
    private List<ColorBean> datasColor;
    private MyColorAdapter myColorAdapter;
    private MyCategoryAdapter myCategoryAdapter;
    private List<CategoryBean> datasCategory;
    private RecyclerView mRvPrice;
    private List<String> dataPrice;
    private MyPriceAdapter myPriceAdapter;
    private TextView mTvRight;
    private RecyclerView mRvOutfits;
    private MultipleItemQuickAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<MultipleItem> datas;
    private Button mBtnConfirm;


    @Override
    protected void initEvent() {
        //返回箭头
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

        mBtnConfirm.setOnClickListener(v -> finish());



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

        //设置价格
        dataPrice = new ArrayList<>();
        dataPrice.add("< 200");
        dataPrice.add("200~500");
        dataPrice.add("500~1k");
        dataPrice.add("> 1000");
        myPriceAdapter = new MyPriceAdapter(R.layout.item_edit_price_textview, dataPrice);
        mRvPrice.addItemDecoration(new SpacesItemDecorationLeft((int) getResources().getDimension(R.dimen.dp_10)));
        mRvPrice.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRvPrice.setAdapter(myPriceAdapter);


        //设置Outfits适配器
        datas = new ArrayList<>();
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long1));
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes1));
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long1));
        mGridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        mRvOutfits.addItemDecoration(new GridSpacingItemDecoration(2, (int) getResources().getDimension(R.dimen.dp_10), false));
        mRvOutfits.setLayoutManager(mGridLayoutManager);
        mRvOutfits.setAdapter(mAdapter = new MultipleItemQuickAdapter(datas));
        mAdapter.setSpanSizeLookup((gridLayoutManager, i) -> {
            MultipleItem multipleItem = datas.get(i);
            if (multipleItem.getItemType() == MultipleItem.TEXT) return 2;
            return 1;
        });


    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);
        mIvGood = (ImageView) findViewById(R.id.iv_good);
        StatusBarUtil.setMargin(this, mLlTitle);
        ImageUtils.setBitmap(this, R.drawable.icon_edit_clo, mIvGood);
        ImageUtils.setBitmap(this, R.drawable.icon_search_back, mIvBack);
        ImageUtils.setBitmap(this, R.drawable.icon_delete, mIvDelete);
        mRvClothes = (RecyclerView) findViewById(R.id.rv_clothes);
        mRvColor = (RecyclerView) findViewById(R.id.rv_color);
        mRvPrice = (RecyclerView) findViewById(R.id.rv_price);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        ImageUtils.setDrawableSize(mTvRight, (int) getResources().getDimension(R.dimen.dp_16));
        mRvOutfits = (RecyclerView) findViewById(R.id.rv_outfits);

        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit;
    }

    private class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

        public MultipleItemQuickAdapter(List<MultipleItem> data) {
            super(data);
            addItemType(MultipleItem.TEXT, R.layout.item_bottom1_imageview_95);
            addItemType(MultipleItem.IMG, R.layout.item_bottom1_imageview_45);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultipleItem item) {
            ImageView mIvPic = null;
            mIvPic = helper.getView(R.id.iv_pic);
            ImageUtils.setBitmap(EditActivity.this, item.getImage(), mIvPic);
        }

    }

    /**
     * 价格列表适配器
     */

    private class MyPriceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyPriceAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            CheckBox view = helper.getView(R.id.cb);
            view.setText(item);
        }
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
            ImageUtils.setBitmap(EditActivity.this, item.getImage(), mIvPic);
            TextView textView = helper.getView(R.id.tv_text);
            textView.setText(item.getText());
            RelativeLayout mLl = helper.getView(R.id.ll_rl);
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
            ImageUtils.setBitmap(EditActivity.this, item.getImage(), mIvPic);
            LinearLayout mLlRoot = helper.getView(R.id.ll_root);
            if (item.isChecked()) {
                mLlRoot.setBackgroundResource(R.drawable.bg_search_grenn_circle);
            } else {
                mLlRoot.setBackground(null);
            }
        }
    }
}
