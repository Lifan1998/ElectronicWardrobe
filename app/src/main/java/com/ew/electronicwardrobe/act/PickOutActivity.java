package com.ew.electronicwardrobe.act;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ew.electronicwardrobe.R;
import com.ew.electronicwardrobe.base.BaseActivity;
import com.ew.electronicwardrobe.domain.MultipleItem;
import com.ew.electronicwardrobe.util.ImageUtils;
import com.ew.electronicwardrobe.util.StatusBarUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Pick Out Outfit
 */
public class PickOutActivity extends BaseActivity {
    private LinearLayout mLlTop;
    private ImageButton mIbBack;
    private LinearLayout mLlContent;
    private TabLayout mTl;
    private RecyclerView mRv;
    private LinearLayout mLlNetwork;
    private ImageView mIvNetwork;
    private TextView mRp;
    private LinearLayout mLlEmpty;
    private ImageView mIvEmpty;
    private Button mBtnConfirm;
    private String[] titles = new String[]{"Top", "Bottom", "Long", "Shoes", "Bag", "Other"};
    private List<MultipleItem> datas;
    private MultipleItemQuickAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void initEvent() {
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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //列表
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MultipleItem multipleItem = datas.get(position);
            if (multipleItem.getItemType() == MultipleItem.IMG) {
                //如果点击是的图片
                //  startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });

        //返回
        mIbBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        //准备列表数据
        datas = new ArrayList<>();
        //Top数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Top", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_top1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_top2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_top3));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_top4));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_top5));
        //Bottom 数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Bottom", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_bottom1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_bottom2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_bottom3));
        //Long数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Long", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_long1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_long2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_long3));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_long4));
        //Shoes数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Shoes", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes3));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes4));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes5));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_shoes6));
        //Bag数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Bag", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_bag1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_bag2));

        //Other数据
        datas.add(new MultipleItem(MultipleItem.TEXT, "Other", 0));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_other1));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_other2));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_other3));
        datas.add(new MultipleItem(MultipleItem.IMG, "", R.drawable.icon_bottom1_other4));

        //设置适配器
        mGridLayoutManager = new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(mGridLayoutManager);
        mRv.setAdapter(mAdapter = new MultipleItemQuickAdapter(datas));
        mAdapter.setSpanSizeLookup((gridLayoutManager, i) -> {
            MultipleItem multipleItem = datas.get(i);
            if (multipleItem.getItemType() == MultipleItem.TEXT) return 3;
            return 1;
        });

    }

    @Override
    protected void initView() {

        mLlTop = (LinearLayout) findViewById(R.id.ll_top);
        mIbBack = (ImageButton) findViewById(R.id.ib_back);
        mLlContent = (LinearLayout) findViewById(R.id.ll_content);
        mTl = (TabLayout) findViewById(R.id.tl);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mLlNetwork = (LinearLayout) findViewById(R.id.ll_network);
        mIvNetwork = (ImageView) findViewById(R.id.iv_network);
        mRp = (TextView) findViewById(R.id.rp);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);

        StatusBarUtil.setMargin(this, mLlTop);
        //设置Tab
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mTl.newTab().setText(titles[i]);
            mTl.addTab(tab);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pick_out;
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
                    ImageUtils.setBitmap(PickOutActivity.this, item.getImage(), mIvPic);
                    break;
            }
        }

    }
}
