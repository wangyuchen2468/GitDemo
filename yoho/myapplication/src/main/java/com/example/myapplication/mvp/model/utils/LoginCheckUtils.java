package com.example.myapplication.mvp.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：created by
 * Create Date ：2019/12/13 14
 * Package_Name : yoho
 */
public class LoginCheckUtils {
    private static LoginCheckUtils instance;
    private LoginCheckUtils(){}
    private Context mContext;
    public static LoginCheckUtils getInstance() {
        if(instance == null){
            synchronized (LoginCheckUtils.class){
                if(instance == null){
                    instance = new LoginCheckUtils();
                }
            }
        }
        return instance;
    }

    public void bindContext(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isLogin(){
        SharedPreferences userTag = mContext.getSharedPreferences("userTag", Context.MODE_PRIVATE);
        boolean login = userTag.getBoolean("login", false);
        return login;
    }

    public void unbindContext(){
        mContext = null;
    }
}
