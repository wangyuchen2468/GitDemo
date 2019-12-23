package com.example.myapplication.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.activity.SearchAcitvity;
import com.example.myapplication.mvp.ui.fragment.child.Classify_BrandFragment;
import com.example.myapplication.mvp.ui.fragment.child.Classify_CategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClassifyFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.classify_tab)
    public TabLayout classify_tab;

    @BindView(R.id.classify_viewpager)
    public ViewPager classify_viewpager;
    @BindView(R.id.classify_search)
    ImageView classifySearch;

    private Unbinder bind;
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_classify_layout, null);
        bind = ButterKnife.bind(this, inflate);


        init();

        return inflate;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("categoryid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.CATEGORY_GOODS);

        JSONObject object1 = new JSONObject();
        try {
            object1.put("menuid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object1.toString(), ApiDoman.BRAND_LIST);

        classifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchAcitvity.class));
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.CATEGORY_GOODS) {
            if (entity instanceof CategoryGoodsEntity) {
                CategoryGoodsEntity categoryGoodsEntity = (CategoryGoodsEntity) entity;
                Log.e("categoryGoodsEntity", categoryGoodsEntity.toString());
            }

        } else if (type == ApiDoman.BRAND_LIST) {
            if (entity instanceof BrandListEntity) {
                BrandListEntity brandListEntity = (BrandListEntity) entity;
                Log.e("brandListEntity", brandListEntity.toString());
            }

        }


    }

    @Override
    public void error(String error) {
        if (error != null) {
            Log.e("error", error);
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    private void init() {
        title.clear();
        fragments.clear();

        title.add("品类");
        title.add("品牌");
        fragments.add(new Classify_CategoryFragment());
        fragments.add(new Classify_BrandFragment());
        classify_tab.setSelectedTabIndicatorColor(Color.WHITE);
        classify_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });


        classify_tab.setupWithViewPager(classify_viewpager);

    }

}
