package com.example.myapplication.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.utils.LoginCheckUtils;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.activity.LoginActivity;
import com.example.myapplication.mvp.ui.activity.SearchAcitvity;
import com.example.myapplication.mvp.ui.activity.SerMesageCenterActivity;
import com.example.myapplication.mvp.ui.activity.SerOfMyselfActivity;
import com.example.myapplication.mvp.ui.adapter.BannercommunityAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerCommunity_Adpater;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommunityFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.community_tab)
    public TabLayout community_tab;

    @BindView(R.id.community_recycler)
    public XRecyclerView community_recycler;

    @BindView(R.id.comm_login)
    public ImageView comm_login;
    @BindView(R.id.masfasd)
    ImageView masfasd;
    @BindView(R.id.community_mine)
    ImageView communityMine;
    @BindView(R.id.community_search)
    ImageView communitySearch;

    private Unbinder bind;
    private ArrayList<String> tabList = new ArrayList<>();
    private int flag = 1;
    private ArrayList<CommunityEntity.ValuesBean> CommunityBean = new ArrayList<>();
    private ArrayList<String> BannerList = new ArrayList<>();
    private RecyclerCommunity_Adpater recyclerCommunity_adpater;
    private ArrayList<CommunityEntity.BannerBean> bannerBeans = new ArrayList<>();
    @BindView(R.id.community_head_banner)
    public Banner banners;
    @BindView(R.id.com_recycler)
    public RecyclerView com_recycler;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_community_layout, null);
        bind = ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject object = new JSONObject();
        try {
            object.put("page", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        flag = 1;
        mPresenter.lreAll(object.toString(), ApiDoman.COMMUNITY_LIST);

        masfasd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SerMesageCenterActivity.class));
            }
        });

        communitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchAcitvity.class));
            }
        });

        communityMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginCheckUtils.getInstance().bindContext(getContext());
                if(LoginCheckUtils.getInstance().isLogin()){
                    startActivity(new Intent(getContext(), SerOfMyselfActivity.class));
                }else {
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }

            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoginCheckUtils.getInstance().unbindContext();
        bind.unbind();
    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.COMMUNITY_LIST) {
            if (entity instanceof CommunityEntity) {

                CommunityEntity communityEntity = (CommunityEntity) entity;
                Log.e("communityEntity", entity.toString());
                List<CommunityEntity.BannerBean> banner = communityEntity.getBanner();
                List<CommunityEntity.ValuesBean> values = communityEntity.getValues();
                CommunityBean.clear();
                BannerList.clear();
                bannerBeans.clear();
                if (flag == 1) {
                    banners.releaseBanner();
                    banners.stopAutoPlay();
                    banners.setVisibility(View.GONE);
                    bannerBeans.addAll(banner);
                    com_recycler.setVisibility(View.VISIBLE);
                    community_recycler.setVisibility(View.GONE);
                    community_recycler.removeAllViews();
                    com_recycler.removeAllViews();
                    BannercommunityAdpater bannercommunityAdpater = new BannercommunityAdpater(bannerBeans, getContext());
                    com_recycler.setAdapter(bannercommunityAdpater);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                    com_recycler.setLayoutManager(linearLayoutManager);


                    LoginCheckUtils.getInstance().bindContext(getContext());
                    if (LoginCheckUtils.getInstance().isLogin()) {
                        comm_login.setVisibility(View.GONE);
                        community_recycler.setVisibility(View.VISIBLE);
                        CommunityBean.addAll(values);
                        recyclerCommunity_adpater = new RecyclerCommunity_Adpater(CommunityBean, getContext());
                        community_recycler.setAdapter(recyclerCommunity_adpater);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        community_recycler.setLayoutManager(staggeredGridLayoutManager);
                        recyclerCommunity_adpater.notifyDataSetChanged();

                    } else {
                        comm_login.setVisibility(View.VISIBLE);
                        community_recycler.setVisibility(View.GONE);
                    }


                } else if (flag == 2) {
                    banners.setVisibility(View.VISIBLE);
                    community_recycler.setVisibility(View.VISIBLE);
                    com_recycler.setVisibility(View.GONE);
                    for (int i = 0; i < banner.size(); i++) {
                        BannerList.add(Api.APP_DOMAIN + banner.get(i).getRecommend_url());
                    }
                    banners.setImages(BannerList);
                    banners.start();
                    com_recycler.removeAllViews();
                    CommunityBean.addAll(values);
                    comm_login.setVisibility(View.GONE);
                    community_recycler.removeAllViews();
                    recyclerCommunity_adpater = new RecyclerCommunity_Adpater(CommunityBean, getContext());
                    community_recycler.setAdapter(recyclerCommunity_adpater);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    community_recycler.setLayoutManager(staggeredGridLayoutManager);
                    recyclerCommunity_adpater.notifyDataSetChanged();

                } else if (flag == 3) {
                    banners.releaseBanner();
                    banners.stopAutoPlay();
                    banners.setVisibility(View.GONE);
                    community_recycler.setVisibility(View.VISIBLE);
                    com_recycler.setVisibility(View.GONE);
                    CommunityBean.addAll(values);
                    comm_login.setVisibility(View.GONE);
                    community_recycler.removeAllViews();
                    recyclerCommunity_adpater = new RecyclerCommunity_Adpater(CommunityBean, getContext());
                    community_recycler.setAdapter(recyclerCommunity_adpater);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    community_recycler.setLayoutManager(staggeredGridLayoutManager);
                    recyclerCommunity_adpater.notifyDataSetChanged();
                }


            }

        }
    }


    private void init() {

        community_recycler.setPullRefreshEnabled(false);

        tabList.clear();
        tabList.add("关注");
        tabList.add("推荐");
        tabList.add("最新");
        community_tab.setSelectedTabIndicatorColor(Color.BLACK);
        for (int i = 0; i < tabList.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) community_tab.getChildAt(0);
            linearLayout.setDividerPadding(45);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            community_tab.addTab(community_tab.newTab().setText(tabList.get(i)));
        }

        community_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String text = tab.getText().toString();
                if (text.equals("关注")) {
                    flag = 1;
                    JSONObject object = new JSONObject();
                    try {
                        object.put("page", "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mPresenter.lreAll(object.toString(), ApiDoman.COMMUNITY_LIST);
                } else if (text.equals("推荐")) {
                    flag = 2;
                    JSONObject object = new JSONObject();
                    try {
                        object.put("page", "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mPresenter.lreAll(object.toString(), ApiDoman.COMMUNITY_LIST);
                } else if (text.equals("最新")) {
                    flag = 3;
                    JSONObject object = new JSONObject();
                    try {
                        object.put("page", "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mPresenter.lreAll(object.toString(), ApiDoman.COMMUNITY_LIST);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        community_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                community_recycler.refreshComplete();
                if (recyclerCommunity_adpater != null) {
                    recyclerCommunity_adpater.notifyDataSetChanged();
                }
            }

            @Override
            public void onLoadMore() {
                community_recycler.loadMoreComplete();
                if (recyclerCommunity_adpater != null) {
                    recyclerCommunity_adpater.notifyDataSetChanged();
                }
            }
        });


        banners.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banners.setDelayTime(2000);
        banners.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        comm_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

    }


    @Override
    public void error(String error) {
        Log.e("###", error + "");
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
