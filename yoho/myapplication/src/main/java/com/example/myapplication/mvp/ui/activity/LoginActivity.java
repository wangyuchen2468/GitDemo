package com.example.myapplication.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.login_changlogin_method)
    TextView changePwd;
    @BindView(R.id.login_forget_pwd)
    TextView forgetPwd;
    @BindView(R.id.password_layout)
    RelativeLayout passLayout;
    @BindView(R.id.login_phone_img)
    ImageView login_phone_img;
    @BindView(R.id.login_edit_admin)
    EditText edit_admin;
    @BindView(R.id.login_edit_password)
    EditText edit_password;
    @BindView(R.id.login_yanzheng_edit)
    EditText edit_yanzheng;
    @BindView(R.id.login_doit)
    Button button;
    @BindView(R.id.login_change_seepwd)
    CheckBox change_seepwd;
    @BindView(R.id.login_img1 )
    ImageView image1;
    @BindView(R.id.login_img2 )
    ImageView image2;
    @BindView(R.id.login_img3 )
    ImageView image3;
    @BindView(R.id.login_img4 )
    ImageView image4;
    @BindView(R.id.login_exit)
    TextView login_exit;
    @BindView(R.id.login_toRegister)
    TextView login_toRegister;
    @BindView(R.id.login_getzcode)
    TextView login_getzcode;
    @BindView(R.id.login_change_onepieces)
    TextView changeOnePieces;
    private int[] pics = new int[8];

    private int[] isTops = new int[4];
    private int[] nowPics = new int[4];
    private final String TAG = "###login--";
    int loginType = 0;
    private String nowName = "";
    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPics();
        initMyView();
        initAnmi();
        initIMG();
        initDoit();
        login_toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("###454", "onActivityResult: "+requestCode +"---"+resultCode);
        if(resultCode== 1002){
            finish();
        }
    }

    /**
    * 作用:登录
    */
    private void initDoit() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( getIsTops()){
                    if(loginType == 0){
                        if(!edit_admin.getText().toString().equals("")){
                                if(!edit_password.getText().toString().equals("")){
                                    nowName = edit_admin.getText().toString();
                                    login(edit_admin.getText().toString(),edit_password.getText().toString());
                                }
                        }else {
                            Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if(!edit_admin.getText().toString().equals("")){
                                if(!edit_yanzheng.getText().toString().equals("")){
                                    Log.i("###login--", "onClick: 点击登录按钮");
                                    nowName = edit_admin.getText().toString();
                                    login(edit_admin.getText().toString(),edit_yanzheng.getText().toString());
                                }
                        } else {
                            Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "图片多多少少有点毛病 ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initPics() {
        pics[0] = R.drawable.login_piece_icon1;
        pics[1] = R.drawable.login_piece_icon2;
        pics[2] = R.drawable.login_piece_icon3;
        pics[3] = R.drawable.login_piece_icon4;
        pics[4] = R.drawable.login_piece_icon5;
        pics[5] = R.drawable.login_piece_icon6;
        pics[6] = R.drawable.login_piece_icon7;
        pics[7] = R.drawable.login_piece_icon8;

    }

    public boolean getIsTops() {
        for (int i = 0; i < isTops.length; i++) {
            if(isTops[i] !=0){
                return false;
            }
        }
        return true;
    }

    /**
    * 作用:初始化四个图片的部分
    */
    private void initIMG() {
        randomPickimg();
        setImageOnClick();

    }

    private void setImageOnClick() {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[0] == 3){
                    isTops[0] = 0;
                }else {
                    isTops[0]= isTops[0]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(image1, "rotation", 0f, isTops[0]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image1"+isTops[0]);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[1] == 3){
                    isTops[1] = 0;
                }else {
                    isTops[1]= isTops[1]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(image2, "rotation", 0f, isTops[1]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image2"+isTops[1]);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[2] == 3){
                    isTops[2] = 0;
                }else {
                    isTops[2]= isTops[2]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(image3, "rotation", 0f, isTops[2]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image3"+isTops[2]);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[3] == 3){
                    isTops[3] = 0;
                }else {
                    isTops[3]= isTops[3]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(image4, "rotation", 0f, isTops[3]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image4"+isTops[3]);
            }
        });
    }
    /**
    * 作用:登录
    */
    private void login(String shouji, String parmas) {
        byte[] buf = EncryptUtils.encryptRSA(parmas.getBytes(),
                android.util.Base64.decode(privateKey.getBytes(), android.util.Base64.DEFAULT)
                ,false,"RSA/ECB/PKCS1Padding");
        byte[]buff = android.util.Base64.encode(buf, android.util.Base64.DEFAULT);
        String rasPwd = new String(buff);

        JSONObject object=new JSONObject();
        try {
            object.put("username",shouji);
            object.put("password",rasPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresenter.lreAll(object.toString(), ApiDoman.LOGIN_TAG);


    }
    /**
    * 作用:随机抽取图片
    */
    private void randomPickimg() {
        int i = 0;
        while (i<4){
            int x = (int )( Math.random()*8);

            for (int j = 0; j < nowPics.length; j++) {
                if(nowPics[j] ==  pics[x]){
                    continue;
                }
            }
            nowPics[i] = pics[x];
            i++;
        }

        for (int x = 0; x < isTops.length; x++) {
            int ram = (int )( Math.random()*3);
            isTops[x] = ram;
        }

        makePicTurn(image1,0);
        makePicTurn(image2,1);
        makePicTurn(image3,2);
        makePicTurn(image4,3);
    }

    private void makePicTurn(ImageView imageView,int position) {
        imageView.setBackgroundResource(nowPics[position]);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f, isTops[position]*90f);
        rotate.setDuration(1);//时间
        rotate.start();//开始

    }

    private void initMyView() {
        change_seepwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edit_password.setInputType(InputType.TYPE_CLASS_TEXT);
                }else { 
                    edit_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        login_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiDoman.toHomeFragment = true;
                finish();
            }
        });

        String keyword = "板鞋";
        String url = "http://172.17.8.100/smal/commodity/v1/findCommodityByKeyword?page-2&count=5&keyword="+keyword;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApiDoman.toHomeFragment = true;
        finish();
    }

    /**
    * 作用:动画切换登录方式
    */
    private void initAnmi() {
        loginByZCode();
        loginType = 1;
        button.setEnabled(false);
        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = changePwd.getText().toString();
                if(s.equals("账户密码登录")){
                    loginBypwd();
                    loginType = 0;
                    login_phone_img.setBackgroundResource(R.drawable.login_y_icon);
                    edit_admin.setHint("请输入手机号/邮箱");
                    pingyi(-180f);
                    changePwd.setText("手机验证码登录");
                    forgetPwd.setVisibility(View.VISIBLE);
                }else {
                    loginByZCode();
                    loginType = 1;
                    login_phone_img.setBackgroundResource(R.drawable.login_admin_icon);
                    edit_admin.setHint("请输入手机号");
                    pingyi(180f);
                    changePwd.setText("账户密码登录");
                    forgetPwd.setVisibility(View.GONE);
                }
            }
        });
        changeOnePieces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initIMG();
            }
        });
    }

    /**
    * 作用:设计edit监听
    */
    private void loginBypwd() {
        edit_yanzheng.setText("");
        button.setEnabled(false);
        edit_admin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!edit_password.getText().toString().equals("")){
                    button.setBackgroundColor(Color.parseColor("#B0B0B0"));
                    button.setEnabled(true);
                    initDoit();
                }else {
                    button.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    button.setEnabled(false);
                    initDoit();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!edit_admin.getText().toString().equals("")){
                    button.setBackgroundColor(Color.parseColor("#B0B0B0"));
                    button.setEnabled(true);
                }else {
                    button.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 作用:设计edit监听
     */
    private void loginByZCode() {
        edit_password.setText("");
        button.setEnabled(false);
        edit_admin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!edit_yanzheng.getText().toString().equals("")){
                    button.setBackgroundColor(Color.parseColor("#B0B0B0"));
                    button.setEnabled(true);
                }else {
                    button.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_yanzheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!edit_admin.getText().toString().equals("")){
                    button.setBackgroundColor(Color.parseColor("#B0B0B0"));
                    button.setEnabled(true);
                }else {
                    button.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
    * 作用：平移动画
    */
    private void pingyi(float v) {
        if(v>0){
            ObjectAnimator translationX = new ObjectAnimator().ofFloat(passLayout,"translationX",0,0f);
            ObjectAnimator translationY = new ObjectAnimator().ofFloat(passLayout,"translationY",-v,0);
            AnimatorSet animatorSet = new AnimatorSet();  //组合动画
            animatorSet.playTogether(translationX,translationY); //设置动画
            animatorSet.setDuration(800);  //设置动画时间
            animatorSet.start(); //启动
        }else {
            ObjectAnimator translationX = new ObjectAnimator().ofFloat(passLayout,"translationX",0,0f);
            ObjectAnimator translationY = new ObjectAnimator().ofFloat(passLayout,"translationY",0,v);
            AnimatorSet animatorSet = new AnimatorSet();  //组合动画
            animatorSet.playTogether(translationX,translationY); //设置动画
            animatorSet.setDuration(800);  //设置动画时间
            animatorSet.start(); //启动
        }

    }

    @Override
    public void success(BaseEntity entity, int type) {
        Log.i(TAG, "success: 得到返回结果");
        if(type == 101){
            LoginEntity loginEntity = (LoginEntity) entity;
            Log.i(TAG, "success: "+loginEntity.getMsg());
            if(loginEntity.getMsg().equals("请求成功")){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                SharedPreferences userTag = getSharedPreferences("userTag", MODE_PRIVATE);
                SharedPreferences.Editor edit = userTag.edit();
                edit.putBoolean("login",true);
                edit.putString("username",nowName);
                edit.putString("userId","1");
                edit.commit();
                finish();
            }else {
                Toast.makeText(this, "请求繁忙，网络连接失败", Toast.LENGTH_SHORT).show();

            }
        }

    }

    @Override
    public void error(String error) {
        Log.e("###", error+"" );

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
