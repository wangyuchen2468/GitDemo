package com.example.myapplication.di.component;


import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.mvp.ui.activity.AddAddressActivity;
import com.example.myapplication.mvp.ui.activity.AddressActivity;
import com.example.myapplication.mvp.ui.activity.ChangeUserActivity;
import com.example.myapplication.mvp.ui.activity.CouponActivity;
import com.example.myapplication.mvp.ui.activity.FootprintActivity;
import com.example.myapplication.mvp.ui.activity.GoodsValuesActivity;
import com.example.myapplication.mvp.ui.activity.LoginActivity;
import com.example.myapplication.mvp.ui.activity.RegisterActivity;
import com.example.myapplication.mvp.ui.activity.ShopCarActivity;
import com.example.myapplication.mvp.ui.activity.ShowDdActivity;
import com.example.myapplication.mvp.ui.fragment.ClassifyFragment;
import com.example.myapplication.mvp.ui.fragment.CommunityFragment;
import com.example.myapplication.mvp.ui.fragment.UfoFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LreModule.class,dependencies = AppComponent.class)
public interface LreComponent {
    void inject(ClassifyFragment classifyFragment);
    void inject(UfoFragment fragment);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(CommunityFragment communityFragment);
    void inject(ShopCarActivity shopCarActivity);
    void inject(GoodsValuesActivity goodsValuesActivity);
    void inject(ChangeUserActivity changeUserActivity);
    void inject(AddressActivity addressActivity);
    void inject(AddAddressActivity addAddressActivity);
    void inject(CouponActivity couponActivity);
    void inject(ShowDdActivity showDdActivity);
    void inject(FootprintActivity footprintActivity);
}
