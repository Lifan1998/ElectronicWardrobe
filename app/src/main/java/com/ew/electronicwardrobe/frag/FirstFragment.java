package com.ew.electronicwardrobe.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.act.EditActivity;
import com.ew.electronicwardrobe.act.LoginActivity;
import com.ew.electronicwardrobe.act.PhotoActivity;
import com.ew.electronicwardrobe.act.SearchActivity;
import com.ew.electronicwardrobe.domain.MultipleItem;
import com.ew.electronicwardrobe.entity.Cloths;
import com.ew.electronicwardrobe.service.ClothsService;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 衣橱
 */
public class FirstFragment extends Fragment {
    private LinearLayout mLlTop;
    private ImageButton mIbAdd;
    private ImageButton mIbShaixuan;
    private LinearLayout mLlContent;
    private TabLayout mTl;
    private RecyclerView mRv;
    private LinearLayout mLlNetwork;
    private ImageView mIvNetwork;
    private TextView mRp;
    private String[] titles = new String[]{"Top", "Bottom", "Long", "Shoes", "Bag", "Other"};
    private List<MultipleItem> datas;
    private List<Cloths> cloths = new ArrayList<>();
    private MultipleItemQuickAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private ImageView mIvEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    private void initData() {
        //准备列表数据
        datas = new ArrayList<>();
        cloths = ClothsService.query(new Cloths());
        //Top数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Top", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 1)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);
                });
        //Bottom 数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Bottom", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 2)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);

                });
        //Long数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Long", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 3)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);

                });
        //Shoes数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Shoes", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 4)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);

                });
        //Bag数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Bag", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 5)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);

                });

        //Other数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Other", 0));
        cloths.stream().filter(cloths1 -> cloths1.getCategory() == 6)
                .forEach(cloths1 -> {
                    MultipleItem item = new MultipleItem();
                    item.setCloths(cloths1);
                    item.setItemType(MultipleItem.IMG);
                    datas.add(item);
                });

        //设置适配器
        mGridLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(mGridLayoutManager);
        mRv.setAdapter(mAdapter = new MultipleItemQuickAdapter(datas));
        mAdapter.setSpanSizeLookup((gridLayoutManager, i) -> {
            MultipleItem multipleItem = datas.get(i);
            if (multipleItem.getItemType() == MultipleItem.TEXT) return 3;
            return 1;
        });


    }

    private void initEvent() {
        //切换监听
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String s = tab.getText().toString();
                for (int i = 0; i < datas.size(); i++) {
                    MultipleItem multipleItem = datas.get(i);
                    if (multipleItem.getText().equals(s)) {
                        mGridLayoutManager.scrollToPositionWithOffset(i, 0);
                        break;
                    }
                }
                // 取所有的衣物数据
                cloths = ClothsService.query(new Cloths());
                Log.v(FirstFragment.class.getSimpleName(), "onTabSelected: " + cloths.size());
                Log.v(FirstFragment.class.getSimpleName(), "onTabSelected: " + cloths.size());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //筛选按钮
        mIbShaixuan.setOnClickListener(v -> startActivity(new Intent(getActivity(), SearchActivity.class)));

        //列表
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MultipleItem multipleItem = datas.get(position);
            if (multipleItem.getItemType() == MultipleItem.IMG) {
                //如果点击是的图片
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });

        mIbAdd.setOnClickListener(v -> startActivity(new Intent(getActivity(), PhotoActivity.class)));

    }

    private void initView(View view) {
        mLlTop = (LinearLayout) view.findViewById(R.id.ll_top);
        mIbAdd = (ImageButton) view.findViewById(R.id.ib_add);
        mIbShaixuan = (ImageButton) view.findViewById(R.id.ib_shaixuan);
        mLlContent = (LinearLayout) view.findViewById(R.id.ll_content);
        mTl = (TabLayout) view.findViewById(R.id.tl);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        //没有网络的布局
        mLlNetwork = (LinearLayout) view.findViewById(R.id.ll_network);
        mIvNetwork = (ImageView) view.findViewById(R.id.iv_network);
        //衣橱空
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        ImageUtils.setBitmap(getActivity(), R.drawable.icon_bottom1_empty, mIvEmpty);
        mRp = (TextView) view.findViewById(R.id.rp);
        StatusBarUtil.setMargin(getActivity(), mLlTop);
        //设置Tab
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mTl.newTab().setText(titles[i]);
            mTl.addTab(tab);
        }
    }

    private class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

        public MultipleItemQuickAdapter(List<MultipleItem> data) {
            super(data);
            addItemType(MultipleItem.TEXT, R.layout.item_bottom1_textview);
            addItemType(MultipleItem.IMG, R.layout.item_bottom1_imageview);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultipleItem item) {
            switch (item.getItemType()) {
                case MultipleItem.TEXT:
                    helper.setText(R.id.tv_text, item.getText());
                    break;
                case MultipleItem.IMG:
                    ImageView mIvPic = helper.getView(R.id.iv_pic);
//                    ImageUtils.setBitmap(getActivity(), item.getImage(), mIvPic);
                    ImageUtils.setBitmap(getActivity(), item.getCloths().getImageUrl(), mIvPic);
                    break;
            }
        }

    }
}
