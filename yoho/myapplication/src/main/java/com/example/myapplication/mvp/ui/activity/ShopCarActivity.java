package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.myapplication.R;
import com.example.myapplication.alipay.AuthResult;
import com.example.myapplication.alipay.PayResult;
import com.example.myapplication.alipay.util.OrderInfoUtil2_0;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.ZhouSonEntity;
import com.example.myapplication.mvp.model.utils.ShopCarListener;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.ShopCarGoodsRecyclerViewAdapter;
import com.example.myapplication.mvp.ui.adapter.ShopcarOptimizationRecyclerViewAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCarActivity extends BaseActivity<LrePresenter> implements LreContact.LreView, View.OnClickListener {
    @BindView(R.id.iv_shopcar_goback)
    ImageView ivShopcarGoback;
    @BindView(R.id.iv_shopcar_compile)
    TextView ivShopcarCompile;
    @BindView(R.id.iv_shopcar_null_linearlayout)
    LinearLayout ivShopcarNullLinearlayout;
    @BindView(R.id.iv_shopcar_goodslist_rcv)
    RecyclerView ivShopcarGoodslistRcv;
    @BindView(R.id.iv_shopcar_raise_buy)
    LinearLayout ivShopcarRaiseBuy;
    @BindView(R.id.iv_shopcar_money)
    TextView ivShopcarMoney;
    @BindView(R.id.iv_shopcar_good_text)
    TextView ivShopcarGoodText;
    @BindView(R.id.iv_shopcar_optimization_rcv)
    RecyclerView ivShopcarOptimizationRcv;
    @BindView(R.id.iv_shopcar_allselect)
    CheckBox ivShopcarAllselect;
    @BindView(R.id.iv_shopcar_total)
    TextView ivShopcarTotal;
    @BindView(R.id.iv_shopcar_pay)
    Button ivShopcarPay;
    @BindView(R.id.iv_shopcar_money_buy)
    LinearLayout ivShopcarMoneyBuy;
    @BindView(R.id.iv_shopcar_stroll)
    Button btn_stroll;

    ArrayList<ZhouSonEntity.ValuesBean> list_optimization = new ArrayList<>();
    ArrayList<CarListEntity.RecommendGoodsBean> list_Goods = new ArrayList<>();
    ArrayList<CarListEntity.ValuesBean> list_user = new ArrayList<>();
    ArrayList<Boolean> list_select = new ArrayList<>();
    ShopcarOptimizationRecyclerViewAdapter shopcarOptimizationRecyclerViewAdapter;
    ShopCarGoodsRecyclerViewAdapter shopCarGoodsRecyclerViewAdapter;
    @BindView(R.id.iv_shopcar_num)
    TextView ivShopcarNum;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shop_car;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        //判断是否登录
        isLogin();
        //请求网络数据
        load();
        //设置为您优选页面
        setOptimization();
        cilck();

//        支付宝支付
        ivShopcarPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ivShopcarTotal.getText().toString();

                payV2(s);
            }
        });
    }
    /**
    * 作用:购物车请求
    */
    private void isLogin() {
        boolean aBoolean = getSharedPreferences("userTag", MODE_PRIVATE).getBoolean("login", false);
        String string = "1";
        Log.i(TAG, "isLogin: "+string);
        if (aBoolean) {
            JSONObject shopcar = new JSONObject();
            try {
                shopcar.put("userid", string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPresenter.lreAll(shopcar.toString(), ApiDoman.CART_LIST);
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016100200644433";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "2088102178199916";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6PKWlWrmGuYJriRjVxP/aYi8nK4Ma3Of0Tiqs78DIGUIqjC2qbgRfmdISUWvRG1o0ek56SELuxrCcrOX8a0Kn6IX3p5oySD1gIRmQFdcEQMrm6dEjyC3JLM63YTYXKe5vYk2E6oinYap9EccLwjcV6ckbdqZc3Xc3SdLGhf4ZgmDPlCLPAcpAksx4pUCKasZGyJVS6nlcBiloevRxEWOPKguMrnF5fomIaZcwAwkUm9kL68ebkpmWtSYz6N5VzuZZyC6AERDjoDPW74hiq6XbaAqKOo1TCuiIi5Bpx6a89dew9++xsM/Z7XeLP93m2mzoQzWCxeQTCMbOMZFuCLGPAgMBAAECggEAQNMzZWc7BOY1u3CxMFeuJznd5+iBbixOoP9EY4XguMXx/hVlH9nDs/M66B1nRhYbRAD+qBnXEn4eo6qbM02ymfeaCiZQygfTNU7QMxMnENfsyIvCvI3FQi1SlwpDZbS2xhaVnRp2Dq00RuyVOEaWHg6ObfzS06hTAu+zal7hn8blUakDDLiVc2Yiu2U1Rs+vCyZVzyCl74O5RgnJ4wR7fNIrjkgFhqEKauhytYnWyp97tNuHrvZocYzzb2xgb1HvDDzNpGietGHxFi66P8oSmNODhtu8zPPL0yYRDvfX5Uso1Kl3RDwwgqBqitFAOXBFTbNgmm8WtrQZ27XmtFMAIQKBgQDyIguj2p417guvAqrjKDCvX+phZusFx/bw1GqUBYO5ySjMjEAI6ZijGpMKFZlYHIGCraLbT0JnnsfGRTsAht59WyWAueGoBzzSqXwg87ScOCdBpX2zK3FZ3w+alCY6xs4CEFvX/El2h2eOT6NYyUW9Z88DNvqye81HceLvUAy+6wKBgQDE5xljdp12HMz7mP3nSAvJ11IvMTBHRXxWI+XSspXlAwjq9w3mZm/gR6aMDGbFEvc1U5CmJ2+rNpN/IDGGxczU7XHdHjs5q/Iy5VyYg248Mdcz4aYNdGXKvCL9GEezucp8tHg6NXomHiCsjU+i77L0Yjx2ux786o8eZzfCAgtW7QKBgQCxoBIj8MOxUmDV0UIlhB06+kUJkSOGSSw5fcp1rhn1F8HLVHPBVgQ4Ws1VT4T/o2HKyLA47EsNkuiA5dajy0y6bzvy0+nbhvuymszJq55qH7OEr++nmY383OsofdgRtDf6QZ/usU//GNP6DSgKgRSdHuM5O4LrnnB4Ie7VmkbPYwKBgQCw4Mi1yL/6cjTRbsXnFZlBX+O78Pvlik3/M5AojbSJXQiNyhD6f9rQJo0+EYXgUwEvLjoqiBKQQx6MfGcBIn62Z/Z5I8cgL44Kmm9rseU8vakMOg/o1j76t7bWlREUKjnzfErkP1XTGA1R9D8tpxWi4vxdzAoyOKMINMCpzxxQoQKBgEqAXEp5DbW0X1Gs37ZT6alk1qCH6BEVDrtU4Kaxo3fXpNvNHFPOesP8HUzqI/OVHV7tSFWe6fh/UjNTM+QuBYmEtMTJsnDxCl7xz7pnrPItW9fGYqyXt1u5MzaIuyZ37+D6GLGuz/S18YvII2mt0m/ZBUOsx0gGJ5Am/cLrrm7h";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                    } else {
                        // 其他状态值则为授权失败
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    public void payV2(String str) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,str);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ShopCarActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
    * 作用:点击事件
    */
    private void cilck() {
        btn_stroll.setOnClickListener(this);
        ivShopcarCompile.setOnClickListener(this);
        ivShopcarAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ivShopcarAllselect.isChecked()){
                    for (int i = 0; i <list_select.size() ; i++) {
                        list_select.set(i,true);
                    }
                    shopCarGoodsRecyclerViewAdapter.notifyDataSetChanged();
                    calculate();
                }else{
                    ivShopcarTotal.setText("0.00");
                    ivShopcarNum.setText("0");
                    for (int i = 0; i <list_select.size() ; i++) {
                        list_select.set(i,false);
                    }
                    shopCarGoodsRecyclerViewAdapter.notifyDataSetChanged();
                }
                ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());
            }
        });
        ivShopcarGoback.setOnClickListener(this);
    }

    private void load() {
        JSONObject optimization = new JSONObject();
        try {
            //request={"categoryid":"1","page":"1"}
            optimization.put("categoryid", "1");
            optimization.put("page", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(optimization.toString(), ApiDoman.ZHOU_LIST);
//        mPresenter.lreAll(optimization.toString(), ApiDoman.GOODS_LIST);
    }

    private void setOptimization() {
        shopcarOptimizationRecyclerViewAdapter = new ShopcarOptimizationRecyclerViewAdapter(list_optimization, this);
        ivShopcarOptimizationRcv.setLayoutManager(new GridLayoutManager(this, 2));
        ivShopcarOptimizationRcv.setAdapter(shopcarOptimizationRecyclerViewAdapter);
        shopCarGoodsRecyclerViewAdapter = new ShopCarGoodsRecyclerViewAdapter(list_select, list_user, this);
        ivShopcarGoodslistRcv.setLayoutManager(new LinearLayoutManager(this));
        ivShopcarGoodslistRcv.setAdapter(shopCarGoodsRecyclerViewAdapter);


        shopCarGoodsRecyclerViewAdapter.setShopCarListener(new ShopCarListener() {
            @Override
            public void isSelect(Boolean select, int position) {
                list_select.set(position,select);
                if (select) {
                    ivShopcarTotal.setText(Double.parseDouble(ivShopcarTotal.getText().toString()) +
                            Integer.parseInt(list_user.get(position).getShop_num()) * Integer.parseInt(list_user.get(position).getShop_price())+"");
                    ivShopcarNum.setText(Integer.parseInt(ivShopcarNum.getText().toString())+Integer.parseInt(list_user.get(position).getShop_num())+"");
                    ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());

                    int num = 0;
                    for (int i = 0; i < list_select.size(); i++) {
                        if (!list_select.get(i)) {
                            num++;
                            Log.e("###", "11");
                            ivShopcarAllselect.setChecked(false);
                            break;
                        }
                    }
                    if (num == 0) {
                        ivShopcarAllselect.setChecked(true);
                    }
                }else {

                    ivShopcarTotal.setText(Double.parseDouble(ivShopcarTotal.getText().toString()) -
                            Integer.parseInt(list_user.get(position).getShop_num()) * Integer.parseInt(list_user.get(position).getShop_price())+"");
                    ivShopcarNum.setText(Integer.parseInt(ivShopcarNum.getText().toString())-Integer.parseInt(list_user.get(position).getShop_num())+"");
                    ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());
                    ivShopcarAllselect.setChecked(false);
                }
            }

            @Override
            public void add(Boolean select, int position) {
                if (select){
                    ivShopcarTotal.setText(Double.parseDouble(ivShopcarTotal.getText().toString()) +
                            1 * Integer.parseInt(list_user.get(position).getShop_price())+"");
                    ivShopcarNum.setText(Integer.parseInt(ivShopcarNum.getText().toString())+1+"");
                    ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());

                }
            }

            @Override
            public void remove(boolean select, int position) {
                if (select){
                    ivShopcarTotal.setText(Double.parseDouble(ivShopcarTotal.getText().toString()) -
                            1 * Integer.parseInt(list_user.get(position).getShop_price())+"");
                    ivShopcarNum.setText(Integer.parseInt(ivShopcarNum.getText().toString())-1+"");
                    ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());
                }
            }
        });

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_shopcar_stroll:
                startActivity(new Intent(this, StrollActivity.class));
                break;
            case R.id.iv_shopcar_compile:
                if (ivShopcarCompile.getText().toString().equals("编辑")) {
                    shopCarGoodsRecyclerViewAdapter.change = true;
                    shopCarGoodsRecyclerViewAdapter.notifyDataSetChanged();
                    ivShopcarCompile.setText("完成");
                } else {
                    ivShopcarCompile.setText("编辑");
                    shopCarGoodsRecyclerViewAdapter.change = false;
                    shopCarGoodsRecyclerViewAdapter.notifyDataSetChanged();
                    ivShopcarTotal.setText("0.00");
                    ivShopcarNum.setText("0");
                    calculate();

                }
                break;
            case R.id.iv_shopcar_goback:
                finish();
                break;

        }
    }
    public void calculate(){
        for (int i = 0; i <list_user.size() ; i++) {
            if (list_select.get(i)){
                ivShopcarTotal.setText((Double.parseDouble(ivShopcarTotal.getText().toString()) +
                        Integer.parseInt(list_user.get(i).getShop_num()) * Integer.parseInt(list_user.get(i).getShop_price()))+"");
                ivShopcarNum.setText(Integer.parseInt(ivShopcarNum.getText().toString())+Integer.parseInt(list_user.get(i).getShop_num())+"");
                ivShopcarMoney.setText("总计￥"+ivShopcarTotal.getText()+"=商品金额￥"+ivShopcarTotal.getText());
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }

    private final String TAG= "###cart";
    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.ZHOU_LIST) {
            List<ZhouSonEntity.ValuesBean> values = ((ZhouSonEntity) entity).getValues();
            list_optimization.addAll(values);
            shopcarOptimizationRecyclerViewAdapter.notifyDataSetChanged();
        } else if (type == ApiDoman.CART_LIST) {
            Log.i(TAG, "success: GOUWUCHE");
            List<CarListEntity.ValuesBean> values = ((CarListEntity) entity).getValues();
            List<CarListEntity.RecommendGoodsBean> recommend_goods = ((CarListEntity) entity).getRecommend_goods();
            if (recommend_goods != null && recommend_goods.size() > 0) {
                Log.i(TAG, "success: "+recommend_goods.get(0).toString());
                list_Goods.addAll(recommend_goods);
            }

            if (values != null && values.size() > 0) {
                for (int i = 0; i < values.size(); i++) {
                    list_user.add(values.get(i));
                    list_select.add(true);
                    Log.i(TAG, "success: "+values.get(i).toString());
                }
                ivShopcarNullLinearlayout.setVisibility(View.GONE);
                ivShopcarGoodslistRcv.setVisibility(View.VISIBLE);
                ivShopcarRaiseBuy.setVisibility(View.VISIBLE);
                ivShopcarMoney.setVisibility(View.VISIBLE);
                ivShopcarMoneyBuy.setVisibility(View.VISIBLE);
                ivShopcarCompile.setVisibility(View.VISIBLE);
                shopCarGoodsRecyclerViewAdapter.notifyDataSetChanged();
                calculate();
            } else {
                ivShopcarNullLinearlayout.setVisibility(View.VISIBLE);
                ivShopcarGoodslistRcv.setVisibility(View.GONE);
                ivShopcarRaiseBuy.setVisibility(View.GONE);
                ivShopcarMoney.setVisibility(View.GONE);
                ivShopcarMoneyBuy.setVisibility(View.GONE);
                ivShopcarCompile.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void error(String error) {
        Log.i(TAG, "success: 获取数据失败"+error);
    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }
}
