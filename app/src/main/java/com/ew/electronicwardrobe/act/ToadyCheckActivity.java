package com.ew.electronicwardrobe.act;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.MultipleItem;
import com.ew.electronicwardrobe.frag.SecondFragment;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.ew.electronicwardrobe.view.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Toady‘s OutFit
 */
public class ToadyCheckActivity extends BaseActivity {
    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private ImageView mIvDelete;
    private LinearLayout mLlVpParent;
    private ViewPager mVp;
    private TextView mTvTitle;
    private TextView mTvRight;
    private RecyclerView mRvOutfits;
    private Button mBtnConfirm;

    private GridLayoutManager mGridLayoutManager;
    private MultipleItemQuickAdapter mAdapter;
    private String[] titles = new String[]{"Profession", "Casual", "Vacation", "Sports", "Luxurious"};
    private @DrawableRes
    int[] pictures = new int[]{R.drawable.pic01, R.drawable.pic02, R.drawable.pic03, R.drawable.pic04, R.drawable.pic05};
    private List<MultipleItem> datas;


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        //设置Outfits适配器
        datas = new ArrayList<>();
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long1));
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes1));
        datas.add(new MultipleItem(MultipleItem.TEXT, "", R.drawable.icon_bottom1_long1));
        mGridLayoutManager = new GridLayoutManager(ToadyCheckActivity.this, 2, RecyclerView.HORIZONTAL, false);
        mRvOutfits.addItemDecoration(new GridSpacingItemDecoration(2, (int) getResources().getDimension(R.dimen.dp_10), false));
        mRvOutfits.setLayoutManager(mGridLayoutManager);
        mRvOutfits.setAdapter(mAdapter = new MultipleItemQuickAdapter(datas));
        mAdapter.setSpanSizeLookup((gridLayoutManager, i) -> {
            MultipleItem multipleItem = datas.get(i);
            if (multipleItem.getItemType() == MultipleItem.TEXT) return 2;
            return 1;
        });

        //设置轮播适配器
        mVp.setAdapter(new MyPagerAdapter());
        mVp.setOffscreenPageLimit(pictures.length);
        mVp.setPageMargin((int) getResources().getDimension(R.dimen.dp_20));
    }

    @Override
    protected void initView() {
        StatusBarUtil.darkMode(this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);
        mLlVpParent = (LinearLayout) findViewById(R.id.ll_vp_parent);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mRvOutfits = (RecyclerView) findViewById(R.id.rv_outfits);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        ImageUtils.setBitmap(ToadyCheckActivity.this, R.drawable.icon_search_back, mIvBack);
        StatusBarUtil.setMargin(this, mLlTitle);
        ImageUtils.setDrawableSize(mTvRight, (int) getResources().getDimension(R.dimen.dp_16));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_toady_check;
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
            ImageUtils.setBitmap(ToadyCheckActivity.this, item.getImage(), mIvPic);
        }

    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pictures.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int picture = pictures[position];
            ImageView view = new ImageView(ToadyCheckActivity.this);
            int height = (int) getResources().getDimension(R.dimen.dp_289);
            int width = (int) getResources().getDimension(R.dimen.dp_200);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(height, width);
            view.setLayoutParams(layoutParams);
            ImageUtils.setBitmap(ToadyCheckActivity.this, picture, view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
