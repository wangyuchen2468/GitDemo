package com.example.myapplication.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MyGoodsEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.activity.LoginActivity;
import com.example.myapplication.mvp.ui.adapter.KingAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UfoFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.ufo_rec)
    XRecyclerView ufo_rec;

    @BindView(R.id.ufo_tab)
    TabLayout tabLayout;

    @BindView(R.id.ufo_fnc)
    ImageView ufo_fnc;

    ArrayList<MyGoodsEntity> entities ;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_ufo_layout, null);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ufo_fnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        ufo_rec.setLayoutManager(new LinearLayoutManager(getContext()));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("category",1);
            jsonObject.put("page",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = jsonObject.toString();
        mPresenter.lreAll(s,5);
    }

    private void initTab() {
        tabLayout.setSelectedTabIndicatorHeight(0);





        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("新品"));
        tabLayout.addTab(tabLayout.newTab().setText("人气"));
        tabLayout.addTab(tabLayout.newTab().setText("潮搭"));
        tabLayout.addTab(tabLayout.newTab().setText("配饰"));
        tabLayout.addTab(tabLayout.newTab().setText("实战"));
        tabLayout.addTab(tabLayout.newTab().setText("女神"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals("推荐")){
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(R.layout.king_banner);
                    integers.add(R.layout.ufo_recommend_pic);
                    initRec(integers);
                }else if(tab.getText().equals("新品")||tab.getText().equals("实战")||tab.getText().equals("女神")){
                    initRec(null);
                }else if(tab.getText().equals("人气")){
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(R.layout.ufo_renqi_pic);
                    initRec(integers);
                }else if(tab.getText().equals("配饰")){
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(R.layout.ufo_peishi_pic);
                    initRec(integers);
                }else if(tab.getText().equals("潮搭")){
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(R.layout.ufo_chaoda_pic);
                    initRec(integers);
                }

                TextView textView = new TextView(getActivity());
                float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, getResources().getDisplayMetrics());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setText(tab.getText());
                tab.setCustomView(textView);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.layout.king_banner);
        integers.add(R.layout.ufo_recommend_pic);
        initRec(integers);

    }

//    设置适配器
    private void initRec( ArrayList<Integer> integers) {


        KingAdapter kingAdapter = new KingAdapter(getContext(), entities, 2, integers, null);
        ufo_rec.setAdapter(kingAdapter);

        if(integers!=null&&integers.get(0) == R.layout.king_banner){
            kingAdapter.setCallBack(R.layout.king_banner, new KingAdapter.KingViewCallBack() {
                @Override
                public void onKingBindView(View view) {
                    XBanner banner = view.findViewById(R.id.king_xbanner);
                    ArrayList<Integer> pics = new ArrayList<>();
                    pics.add(R.drawable.xbanner_1);
                    pics.add(R.drawable.xbanner_2);
                    pics.add(R.drawable.xbanner_3);
                    pics.add(R.drawable.xbanner_4);
                    pics.add(R.drawable.xbanner_5);

                    banner.setData(pics,null);

                    banner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(banner.getContext()).load(pics.get(position)).into((ImageView) view);
                            banner.setPageChangeDuration(2000);

                        }
                    });


                }
            });
        }

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void success(BaseEntity entity, int type) {
        Log.i("###cart", "success: 这个接口有数据，就是周贺棋的问题");
        if (type == 5){
            GoodsListEntity goodsListEntity = (GoodsListEntity) entity;
            List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
            ArrayList<MyGoodsEntity> myGoodsEntities = new ArrayList<>();
            for (int i = 0; i < values.size(); i++) {
                MyGoodsEntity myGoodsEntity = new MyGoodsEntity();
                myGoodsEntity.setName(values.get(i).getGoods_name());
                myGoodsEntity.setCategoryId(values.get(i).getGoods_id());
                myGoodsEntity.setPrice(values.get(i).getGoods_discount_price());
                myGoodsEntity.setUrlPath(values.get(i).getGoods_img_path());
                myGoodsEntities.add(myGoodsEntity);
            }

            entities = myGoodsEntities;
        }


        initTab();
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
