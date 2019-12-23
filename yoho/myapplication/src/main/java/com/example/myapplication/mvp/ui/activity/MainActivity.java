package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.myapplication.R;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.utils.LoginCheckUtils;
import com.example.myapplication.mvp.presenter.MenuPresenter;
import com.example.myapplication.mvp.ui.fragment.ClassifyFragment;
import com.example.myapplication.mvp.ui.fragment.CommunityFragment;
import com.example.myapplication.mvp.ui.fragment.HomeFragment;
import com.example.myapplication.mvp.ui.fragment.MineFragment;
import com.example.myapplication.mvp.ui.fragment.UfoFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/mains/nimaxiang")
public class MainActivity extends BaseActivity<MenuPresenter> implements MenuContact.MenuView {

    @BindView(R.id.main_framelayout)
    FrameLayout main_framelayout;

    @BindView(R.id.main_gb)
    RadioGroup main_gb;

    @BindView(R.id.main_homepage_rb)
    RadioButton main_homepage_rb;

    @BindView(R.id.main_classify_rb)
    RadioButton main_classify_rb;

    @BindView(R.id.main_shoes_ufo_rb)
    RadioButton main_shoes_ufo_rb;

    @BindView(R.id.main_community_rb)
    RadioButton main_community_rb;

    @BindView(R.id.main_mine_rb)
    RadioButton main_mine_rb;

    @BindView(R.id.main_ufo_img)
    ImageView main_ufo_img;
    @BindView(R.id.main_cart)
    ImageView mainCart;

    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private UfoFragment ufoFragment;
    private CommunityFragment communityFragment;
    private MineFragment mineFragment;

    @Override
    public void ok(MenuEntity menuEntity) {
//        if (menuEntity==null){
//
//        }else{
//            Log.e("menuEntity",menuEntity.toString());
//        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerMenuComponent.builder().appComponent(appComponent).menuModule(new MenuModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        init();

//        mPresenter.requestMenu();
    }

    private void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new HomeFragment()).commit();
        main_gb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.main_homepage_rb:
                        mainCart.setVisibility(View.VISIBLE);
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, homeFragment).commit();
                        break;
                    case R.id.main_classify_rb:
                        mainCart.setVisibility(View.VISIBLE);
                        if (classifyFragment == null) {
                            classifyFragment = new ClassifyFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, classifyFragment).commit();
                        break;
                    case R.id.main_shoes_ufo_rb:
                        mainCart.setVisibility(View.VISIBLE);
                        if (ufoFragment == null) {
                            ufoFragment = new UfoFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, ufoFragment).commit();
                        break;
                    case R.id.main_community_rb:
                        mainCart.setVisibility(View.GONE);
                        if (communityFragment == null) {
                            communityFragment = new CommunityFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, communityFragment).commit();
                        break;
                    case R.id.main_mine_rb:

                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                        }
                        LoginCheckUtils.getInstance().bindContext(MainActivity.this);
                        if (!LoginCheckUtils.getInstance().isLogin()) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        } else {
                            mainCart.setVisibility(View.VISIBLE);
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mineFragment).commit();
                        }
                        break;
                }
            }
        });

        mainCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShopCarActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ApiDoman.toHomeFragment) {
            main_homepage_rb.setChecked(true);
            ApiDoman.toHomeFragment = false;
        }

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginCheckUtils.getInstance().unbindContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
