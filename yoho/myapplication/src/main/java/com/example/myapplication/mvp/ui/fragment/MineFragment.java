package com.example.myapplication.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.utils.AnimationUtils;
import com.example.myapplication.mvp.ui.activity.ChangeUserActivity;
import com.example.myapplication.mvp.ui.activity.LoginActivity;
import com.example.myapplication.mvp.ui.activity.MessageActivity;
import com.example.myapplication.mvp.ui.activity.ZXingActivity;
import com.example.myapplication.mvp.ui.fragment.minechild.MineChildMarFragment;
import com.example.myapplication.mvp.ui.fragment.minechild.MineChildSorFragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_share)
    ImageView mineShare;
    @BindView(R.id.mine_mess)
    ImageView mineMess;
    @BindView(R.id.mine_zxing)
    ImageView mineZxing;
    @BindView(R.id.mine_title_bg)
    RelativeLayout mineTitleBg;
    @BindView(R.id.mine_right_setting)
    ImageView mineRightSetting;
    @BindView(R.id.mine_left_setting)
    ImageView mineLeftSetting;
    @BindView(R.id.mine_title_head)
    ImageView mineTitleHead;
    @BindView(R.id.mine_grow)
    TextView mineGrow;
    @BindView(R.id.mine_default_header)
    ImageView mineDefaultHeader;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_qianming)
    TextView mineQianming;
    @BindView(R.id.mine_sor)
    RadioButton mineSor;
    @BindView(R.id.mine_market)
    RadioButton mineMarket;
    @BindView(R.id.mine_viewpager)
    ViewPager mineViewpager;
    @BindView(R.id.mine_scroll)
    ScrollView mineScroll;

    private AnimationUtils animationUtils;
//    @BindView(R.id.mine_exit_login)
//    Button mineExitLogin;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine_layout, null);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        mineExitLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences userTag = getContext().getSharedPreferences("userTag", Context.MODE_PRIVATE);
//                userTag.edit().putBoolean("login",false).commit();
//            }
//        });
        initFragment();
        Log.i("###scro----- ","onScrollChange: 设置fragment");
        initMyView();
        initAnmi();
    }
    /**
    * 作用:初始化动画效果
    */
    @SuppressLint("ClickableViewAccessibility")
    private void initAnmi() {
        mineScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int eventAction = motionEvent.getAction();
                int scrollY = mineScroll.getScrollY();
                switch (eventAction) {
                    case MotionEvent.ACTION_MOVE:
                        if(scrollY<=500){
                            animationUtils.showAndHiddenAnimation(mineTitleHead,1,0);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,1,0);
                            float scrollY1 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,1f-scrollY1/100);
                        }else if(scrollY>500){
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,0);
                            float scrollY1 = (scrollY-500)/5;
                            animationUtils.showAndHiddenAnimation(mineTitleHead,0,scrollY1/100);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,0,scrollY1/100);
                        }

//                        if(scrollY<=1000){
                            float scrollY1 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineGrow,0,scrollY1/100);
                            animationUtils.showAndHiddenAnimation(mineTitleBg,0,scrollY1/100);

//                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(scrollY<=500){
                            animationUtils.showAndHiddenAnimation(mineTitleHead,1,0);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,1,0);
                            float scrollY2 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,1f-scrollY2/100);
                        }else if(scrollY>500){
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,0);
                            float scrollY2 = (scrollY-500)/5;
                            animationUtils.showAndHiddenAnimation(mineTitleHead,0,scrollY2/100);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,0,scrollY2/100);
                        }
//                        if(scrollY<=1000){
                        float scrollY2 = scrollY/5;
                        animationUtils.showAndHiddenAnimation(mineGrow,0,scrollY2/100);
                        animationUtils.showAndHiddenAnimation(mineTitleBg,0,scrollY2/100);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
    /**
    * 作用:初始化viewpager
    */
    private void initFragment() {
        Fragment[] fragments = {new MineChildSorFragment(), new MineChildMarFragment()};
        mineViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        mineSor.setChecked(true);
        mineViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mineSor.setChecked(true);
                } else {
                    mineMarket.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mineSor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mineViewpager.setCurrentItem(0);
                }
            }
        });
        mineMarket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mineViewpager.setCurrentItem(1);
                }
            }
        });

        mineMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MessageActivity.class));
            }
        });
    }

    //初始化页面信息
    private void initMyView() {
        animationUtils = new AnimationUtils();
        Glide.with(getActivity()).load(R.drawable.photos).apply(RequestOptions.circleCropTransform()).into(mineTitleHead);
        Glide.with(getActivity()).load(R.drawable.default_head_icon).apply(RequestOptions.circleCropTransform()).into(mineDefaultHeader);
        mineSor.setChecked(true);
        animationUtils.showAndHiddenAnimation(mineTitleBg, 1, 0);
        animationUtils.showAndHiddenAnimation(mineTitleHead, 1, 0);
        animationUtils.showAndHiddenAnimation(mineGrow, 1, 0);
        animationUtils.showAndHiddenAnimation(mineRightSetting, 1, 0);

        mineShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        SharedPreferences userTag = getContext().getSharedPreferences("userTag", Context.MODE_PRIVATE);
        String username = userTag.getString("username", "");
        if(username!=null&&!username.equals("")){
            mineName.setText(username);
        }else {
            mineName.setText("NULL");
        }

        mineZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZXingActivity.class));
            }
        });

        mineLeftSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userTag = getContext().getSharedPreferences("userTag", Context.MODE_PRIVATE);
                userTag.edit().putBoolean("login",false).commit();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        mineDefaultHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangeUserActivity.class));
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animationUtils = null;
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享至");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(getActivity());
    }
}
