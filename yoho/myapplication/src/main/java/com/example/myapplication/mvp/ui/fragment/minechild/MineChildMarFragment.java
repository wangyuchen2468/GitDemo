package com.example.myapplication.mvp.ui.fragment.minechild;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.mvp.ui.activity.AddressActivity;
import com.example.myapplication.mvp.ui.activity.CouponActivity;
import com.example.myapplication.mvp.ui.activity.FootprintActivity;
import com.example.myapplication.mvp.ui.activity.ShowDdActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ：created by
 * Create Date ：2019/12/18 09
 * Package_Name : yoho
 */
public class MineChildMarFragment extends BaseFragment {
    @BindView(R.id.ot_myaddress)
    LinearLayout otMyaddress;
    @BindView(R.id.mine_coupon)
    LinearLayout mineCoupon;
    @BindView(R.id.mine_order)
    RelativeLayout mineOrder;
    @BindView(R.id.mine_footprint)
    LinearLayout mineFootprint;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.mine_right_child_frag, container, false);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        otMyaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddressActivity.class));
            }
        });

        mineCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CouponActivity.class));
            }
        });
        mineOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ShowDdActivity.class));
            }
        });

        mineFootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FootprintActivity.class));
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @OnClick(R.id.ot_myaddress)
    public void onViewClicked() {

    }

}
