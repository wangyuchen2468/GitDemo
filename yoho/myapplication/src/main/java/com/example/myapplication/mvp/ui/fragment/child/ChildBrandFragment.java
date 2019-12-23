package com.example.myapplication.mvp.ui.fragment.child;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.utils.BannerImageLoader;
import com.example.myapplication.mvp.ui.adapter.BrandListHorAdapter;
import com.example.myapplication.mvp.ui.adapter.BrandListSecAdapter;
import com.example.myapplication.view.MyScorllRight;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.squareup.haha.perflib.Main;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ：created by
 * Create Date ：2019/12/9 09
 * Package_Name : yoho
 */
public class ChildBrandFragment extends Fragment {

    private BrandListEntity brandListEntity;
    private int type ;
    private Banner banner;
    private ArrayList<Integer> bannerList;
    private RecyclerView horRec;
    private BrandListHorAdapter adapter;
    private BrandListHorAdapter adapter2;
    private TabLayout tabLayout;
    private RecyclerView secRec;
    private BrandListSecAdapter secAdapter ;
    private MyScorllRight right;
    private AppBarLayout appBarLayout;
    private ArrayList<BrandListEntity.ValuesBean> objects = new ArrayList<>();

    //    设置横向
    public void setShoesEntity(ShoesEntity shoesEntity) {
        adapter = new BrandListHorAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horRec.setLayoutManager(linearLayoutManager);
        horRec.setAdapter(adapter);

        adapter.setList(brandListEntity.getValues());
        adapter2 = new BrandListHorAdapter(getContext());
        initSec();
        preData();
    }

    private void preData() {
        List<BrandListEntity.ValuesBean> values = brandListEntity.getValues();
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i).getHot_tag().equals("true")){
                objects.add(values.get(i));
            }
        }
    }

    public ChildBrandFragment(BrandListEntity brandListEntity, int type){
        this.brandListEntity = brandListEntity;
        this.type = type;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.frag_brand_list, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        banner = view.findViewById(R.id.brand_list_banner);
        horRec = view.findViewById(R.id.brand_list_hor_rec);
        tabLayout = view.findViewById(R.id.brand_list_tab);
        secRec = view.findViewById(R.id.brand_list_sec_rec);
        right = view.findViewById(R.id.brand_list_scrollRight);
        appBarLayout = view.findViewById(R.id.brand_appbar);
       initBanner();

    }

    /**
    * 作用:初始化下半部分
    */
    private void initSec() {

        tabLayout.addTab(tabLayout.newTab().setText("全部品牌"));
        tabLayout.addTab(tabLayout.newTab().setText("新注入品牌"));
        tabLayout.addTab(tabLayout.newTab().setText("热门品牌"));
        tabLayout.setSelectedTabIndicatorHeight(0);
        secAdapter = new BrandListSecAdapter(brandListEntity.getValues());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        secRec.setLayoutManager(linearLayoutManager);
        secRec.setAdapter(secAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case "全部品牌":
                        right.setVisibility(View.VISIBLE);
                        secRec.setLayoutManager(linearLayoutManager);
                        secRec.setAdapter(secAdapter);
                        break;
                    case "新注入品牌":
                        right.setVisibility(View.GONE);
                        secRec.setLayoutManager(new GridLayoutManager(getContext(),3));
                        secRec.setAdapter(adapter);
                        break;
                    case "热门品牌":
                        right.setVisibility(View.GONE);
                        secRec.setLayoutManager(new GridLayoutManager(getContext(),3));
                        adapter2.setList(objects);
                        secRec.setAdapter(adapter2);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        right.setTouchEvent(new MyScorllRight.IOnTouchEvent() {
            @Override
            public void onTouchEvent(int index) {
                appBarLayout.setExpanded(false);
                ((LinearLayoutManager)secRec.getLayoutManager()).scrollToPositionWithOffset(index,0);
            }
        });
    }

    /**
    * 作用:初始化banner
    */
    private void initBanner() {
        bannerList = new ArrayList<>();
        switch (type){
            case 1:
                bannerList.add(R.drawable.brand1_banner1);
                bannerList.add(R.drawable.brand1_banner2);
                bannerList.add(R.drawable.brand1_banner3);
                break;
            case 2:
                bannerList.add(R.drawable.nv_1);
                bannerList.add(R.drawable.nv_2);
                bannerList.add(R.drawable.nv_3);
                break;
            case 3:
                bannerList.add(R.drawable.life_1);
                bannerList.add(R.drawable.life_2);
                break;
            case 4:
                bannerList.add(R.drawable.kids_1);
                bannerList.add(R.drawable.kids_2);
                bannerList.add(R.drawable.kids_3);
                break;
            case 5:
                bannerList.add(R.drawable.blk_1);
                bannerList.add(R.drawable.blk_2);
                bannerList.add(R.drawable.blk_3);
                break;
        }
        banner.setImages(bannerList);
        banner.setDelayTime(2000);
        banner.setImageLoader(new BannerImageLoader());
        banner.start();
    }
}
