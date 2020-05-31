package com.ew.electronicwardrobe.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.act.EditActivity;
import com.ew.electronicwardrobe.act.EditLookActivity;
import com.ew.electronicwardrobe.act.PickOutActivity;
import com.ew.electronicwardrobe.act.SearchLookActivity;
import com.ew.electronicwardrobe.domain.MultipleItem;
import com.ew.electronicwardrobe.entity.Cloths;
import com.ew.electronicwardrobe.entity.Outfit;
import com.ew.electronicwardrobe.service.OutfitService;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.ew.electronicwardrobe.view.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SecondFragment extends Fragment {

    private RelativeLayout mLlTitle;
    private ImageView mIvBack;
    private ImageView mIvDelete;
    private List<MultipleItem> datas = new ArrayList<>();
    private List<Outfit> outfitList = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    private MultipleItemQuickAdapter mAdapter;
    private RecyclerView mRvOutfits;
    private TextView mTvRight;
    private String[] titles = new String[]{"Profession", "Casual", "Vacation", "Sports", "Luxurious"};
    private ViewPager mVp;
    private TextView mTvTitle;
    private @DrawableRes
    int[] pictures = new int[]{R.drawable.pic01, R.drawable.pic02, R.drawable.pic03, R.drawable.pic04, R.drawable.pic05};
    private LinearLayout mLlVpParent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarUtil.darkMode(getActivity(), false);
        initView(view);
        initData();
        initEvent();
    }

    private void initEvent() {
        //视图切换器
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvTitle.setText(titles[position % titles.length]);
                // 获取当前场景下的穿搭
                Outfit outfit = new Outfit();
                outfit.setDressup(position + 1);
                outfitList = OutfitService.query(outfit);

                List<Cloths> clothsList = outfitList.stream()
                        .flatMap(outfit1 -> outfit1.getClothsList().stream())
                        .collect(Collectors.toList());

                datas = clothsList.stream().map(cloths -> {
                    MultipleItem item = new MultipleItem();
                    item.setItemType(MultipleItem.IMG);
                    item.setCloths(cloths);
                    return item;
                }).collect(Collectors.toList());
                mAdapter.replaceData(datas);
                mTvRight.setText(datas.size() + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        //除了只有中间一个ViewPager布局能滑动，左右滑动不了的问题
        mLlVpParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mVp.dispatchTouchEvent(event);
            }
        });

        //跳转到筛选页面
        mIvDelete.setOnClickListener(v -> startActivity(new Intent(getActivity(), SearchLookActivity.class)));


        mIvBack.setOnClickListener(v -> startActivity(new Intent(getActivity(), PickOutActivity.class)));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), EditLookActivity.class));
            }
        });


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
            ImageView view = new ImageView(getActivity());
            int height = (int) getResources().getDimension(R.dimen.dp_289);
            int width = (int) getResources().getDimension(R.dimen.dp_200);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(height, width);
            view.setLayoutParams(layoutParams);
            ImageUtils.setBitmap(getActivity(), picture, view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }


    private void initData() {
        // 获取当前场景下的穿搭
        Outfit outfit = new Outfit();
        outfit.setDressup(1);
        outfitList = OutfitService.query(outfit);

        List<Cloths> clothsList = outfitList.stream()
                .flatMap(outfit1 -> outfit1.getClothsList().stream())
                .collect(Collectors.toList());

        datas = clothsList.stream().map(cloths -> {
            MultipleItem item = new MultipleItem();
            item.setItemType(MultipleItem.IMG);
            item.setCloths(cloths);
            return item;
        }).collect(Collectors.toList());
        mTvRight.setText(datas.size() + "");
        //设置Outfits适配器
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false);
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
            ImageUtils.setBitmap(getActivity(), item.getCloths().getImageUrl(), mIvPic);
        }

    }

    private void initView(View view) {
        mLlTitle = (RelativeLayout) view.findViewById(R.id.ll_title);
        mIvBack = (ImageView) view.findViewById(R.id.iv_back);
        mIvDelete = (ImageView) view.findViewById(R.id.iv_delete);
        mRvOutfits = (RecyclerView) view.findViewById(R.id.rv_outfits);
        mTvRight = (TextView) view.findViewById(R.id.tv_right);
        mVp = (ViewPager) view.findViewById(R.id.vp);

        mTvTitle = (TextView) view.findViewById(R.id.tv_title);

        mLlVpParent = (LinearLayout) view.findViewById(R.id.ll_vp_parent);

        StatusBarUtil.setMargin(getActivity(), mLlTitle);
        ImageUtils.setBitmap(getActivity(), R.drawable.icon_bottom1_add, mIvBack);
        ImageUtils.setBitmap(getActivity(), R.drawable.icon_bottom1_shaixuan, mIvDelete);
        ImageUtils.setDrawableSize(mTvRight, (int) getResources().getDimension(R.dimen.dp_16));
    }
}
