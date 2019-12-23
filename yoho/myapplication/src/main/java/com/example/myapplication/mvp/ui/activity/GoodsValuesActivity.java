package com.example.myapplication.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.AddCarEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsValuesEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;

import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
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

public class GoodsValuesActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {


    @BindView(R.id.activity_detail_tool)
    Toolbar activityDetailTool;
    @BindView(R.id.activity_detail_banner)
    Banner activityDetailBanner;
    @BindView(R.id.activity_detail_title)
    TextView activityDetailTitle;
    @BindView(R.id.activity_detail_price)
    TextView activityDetailPrice;
    @BindView(R.id.activity_detail_priceno)
    TextView activityDetailPriceno;
    @BindView(R.id.activity_detail_tab)
    TabLayout activityDetailTab;
    @BindView(R.id.activity_detail_biaoqian)
    ImageView activityDetailBiaoqian;
    @BindView(R.id.activity_detail_tuijian_banner)
    Banner activityDetailTuijianBanner;
    @BindView(R.id.activity_detail_mote_recy)
    RecyclerView activityDetailMoteRecy;
    @BindView(R.id.activity_detail_chanpin_recy)
    RecyclerView activityDetailChanpinRecy;
    @BindView(R.id.activity_detail_diantui_recy)
    RecyclerView activityDetailDiantuiRecy;
    @BindView(R.id.activity_detail_youlike_recy)
    RecyclerView activityDetailYoulikeRecy;
    @BindView(R.id.activity_detail_shou_img)
    ImageView activityDetailShouImg;
    @BindView(R.id.activity_detail_shou_tv)
    TextView activityDetailShouTv;
    @BindView(R.id.activity_detail_shou_lin)
    LinearLayout activityDetailShouLin;
    ArrayList<String> bannerlist = new ArrayList<>();
    ArrayList<String> recommendlist = new ArrayList<>();
    ArrayList<GoodsValuesEntity.ValuesBean> recommendGoodsBeans = new ArrayList<>();

    int i = 0;
    @BindView(R.id.iv_goodsvalues_button)
    Button ivGoodsvaluesButton;
    private String goodsid;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_goods_values;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //网络请求
        load();


        activityDetailTab.addTab(activityDetailTab.newTab().setText("晒单评价"));
        activityDetailTab.addTab(activityDetailTab.newTab().setText("常见问题"));


        activityDetailShouLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i % 2 == 0) {
                    activityDetailShouImg.setImageResource(R.drawable.shoucang2);
                    activityDetailShouTv.setText("已收藏");
                } else {
                    activityDetailShouImg.setImageResource(R.drawable.shoucang1);
                    activityDetailShouTv.setText("收藏");
                }
            }
        });
        ivGoodsvaluesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSharedPreferences("userTag",MODE_PRIVATE).getBoolean("login",false)){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userid",getSharedPreferences("user",MODE_PRIVATE).getString("userid","0"));
                        jsonObject.put("goodsid","1");
                        jsonObject.put("shopname","wang son");
                        jsonObject.put("shopcolor","1");
                        jsonObject.put("shopsize",2);
                        jsonObject.put("shopnum",1);
                        jsonObject.put("shopprice",222);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(jsonObject.toString(), ApiDoman.ADD_CART);
                }

                //request={"userid":"1","goodsid":"1","shopname":"hehe","shopcolor":"1","shopsize":"1","shopnum":"1","shopprice":"1"}

            }
        });
    }

    private void load() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("goodsid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(jsonObject.toString(), ApiDoman.GOODS_VALUE);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiDoman.GOODS_VALUE) {
            List<GoodsValuesEntity.ValuesBean> values = ((GoodsValuesEntity) baseEntity).getValues();
            List<GoodsValuesEntity.ValuesBean.ImgsBean> imgs = values.get(0).getImgs();
            for (int i = 0; i < imgs.size() - 1; i++) {
                if (!imgs.get(i).equals("")) {
                    bannerlist.add(Api.APP_DOMAIN + imgs.get(i).getGoods_img_path());
                }
            }
            activityDetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            activityDetailBanner.setDelayTime(1500);
            activityDetailBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            activityDetailBanner.setImages(bannerlist);
            activityDetailBanner.isAutoPlay(true);
            activityDetailBanner.start();

            int a = 0;
            Intent intent = getIntent();
             goodsid = intent.getStringExtra("goodsid");
            for (int j = 0; j < values.size() - 1; j++) {
                if (goodsid.equals(values.get(j).getGoods_id())) {
                    a = j;
                    break;
                }
            }
            activityDetailTitle.setText(values.get(a).getGoods_name());
            activityDetailPrice.setText(values.get(a).getGoods_discount_price());

            SpannableString spanString = new SpannableString("￥" + values.get(a).getGoods_original_price());
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, values.get(a).getGoods_original_price().length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //尝试用固定下标
            activityDetailPriceno.setText(spanString);

            List<GoodsValuesEntity.RecommendGoodsBean> recommend_goods = ((GoodsValuesEntity) baseEntity).getRecommend_goods();
            for (int i = 0; i < recommend_goods.size() - 1; i++) {
                recommendlist.add(Api.APP_DOMAIN + recommend_goods.get(i).getGoods_img_path());
            }
            activityDetailTuijianBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            activityDetailTuijianBanner.setDelayTime(1500);
            activityDetailTuijianBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            activityDetailTuijianBanner.setImages(recommendlist);
            activityDetailTuijianBanner.isAutoPlay(true);
            activityDetailTuijianBanner.start();

//            mydetailadapter.notifyDataSetChanged();
//            mydianputuijianadapter.notifyDataSetChanged();
        }else if (type == ApiDoman.ADD_CART){
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
