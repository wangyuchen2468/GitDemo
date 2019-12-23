package com.example.myapplication.mvp.ui.fragment.child;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerChildCateGoryComponent;
import com.example.myapplication.di.module.ChildModule;
import com.example.myapplication.mvp.contact.ChildCategoryContact;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.presenter.ChildPresenter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 分类页面
 *  子页面
 *  品牌
 * */

public class Classify_BrandFragment extends BaseFragment<ChildPresenter> implements ChildCategoryContact.IChildCateView {
    @BindView(R.id.child_brand_tab)
    TabLayout tabLayout;

    @BindView(R.id.child_brand_pager)
    ViewPager viewPager;

    int x = 0;
    private ChildBrandFragment fragMan;
    private ChildBrandFragment fragWoman;
    private ChildBrandFragment fragLife;
    private ChildBrandFragment fragKids;
    private ChildBrandFragment fragBLK;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChildCateGoryComponent.builder().childModule(new ChildModule(this))
        .appComponent(appComponent).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_child_brand_layout, null);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.postBrandList("1");
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    /**
    * 作用:need brandlist
    */
    @Override
    public void onSuccessBrand(BrandListEntity brandListEntity) {
        Log.i("###shoes", "onSuccessBrand: 数据返回长度"+brandListEntity.getValues().size());
        if(x == 0){
            ArrayList<String> strings = new ArrayList<>();
            strings.add("男装");
            strings.add("女装");
            strings.add("生活方式");
            strings.add("潮童");
            strings.add("高街BLK");

            ArrayList<Fragment> fragments = new ArrayList<>();
             fragMan = new ChildBrandFragment(brandListEntity, 1);
             fragWoman = new ChildBrandFragment(brandListEntity, 2);
             fragLife = new ChildBrandFragment(brandListEntity, 3);
             fragKids = new ChildBrandFragment(brandListEntity, 4);
             fragBLK = new ChildBrandFragment(brandListEntity, 5);
            fragments.add(fragMan);
            fragments.add(fragWoman);
            fragments.add(fragLife);
            fragments.add(fragKids);
            fragments.add(fragBLK);

            viewPager.setOffscreenPageLimit(fragments.size());
            viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
                    return strings.get(position);
                }
            });
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setSelectedTabIndicatorHeight(0);

            tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    TextView textView = new TextView(getActivity());
                    float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, getResources().getDisplayMetrics());
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                    textView.setTextColor(Color.BLACK);
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
            x = 1;
        }
        mPresenter.postShoes("1");
    }

    @Override
    public void onSuccessShoes(ShoesEntity shoesEntity) {
        Log.i("###shoes", "onSuccessShoes: 数据返回长度"+shoesEntity.getBrand().size());
        fragBLK.setShoesEntity(shoesEntity);
        fragKids.setShoesEntity(shoesEntity);
        fragLife.setShoesEntity(shoesEntity);
        fragWoman.setShoesEntity(shoesEntity);
        fragMan.setShoesEntity(shoesEntity);
    }

    @Override
    public void onSuccessLeft(ChildCategoryEntity childCategoryEntity) {
    }

    @Override
    public void onSuccessRight(ChildCateRightEntity childCateRightEntity) {

    }


    @Override
    public void onError() {
        Log.i(TAG, "onError: 请求失败");
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
