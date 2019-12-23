package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.mvp.ui.fragment.ClassifyFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrollActivity extends BaseActivity{


    @BindView(R.id.iv_stroll_framelayout)
    FrameLayout ivStrollFramelayout;
    @BindView(R.id.iv_categrory_goback)
    ImageView imageView;



    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_stroll;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.iv_stroll_framelayout,new ClassifyFragment()).commit();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
