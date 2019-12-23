package com.example.myapplication.doman;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiDoman {

    public static final int CATEGORY_ALL=1;
    public static final int CATEGORY_GOODS=2;
    public static final int BRAND_LIST=3;
    public static final int SHOES_LIST=4;
    public static final int GOODS_LIST = 5 ;
    public static final int COMMUNITY_LIST = 6 ;
    public static final int ZHOU_LIST = 7 ;
    public static final int ADD_CART = 8;
    public static final int CART_LIST = 9;
    public static final int GOODS_VALUE = 10;
    public static final int CHANGE_USER = 11;
    public static final int ADD_ADDRESS = 12;
    public static final int ADDRESSMANAGER = 13;
    public static final int DELETE_ADDRESS = 14;
    public static final int COUPON_LIST = 15;
    public static final int ORDER_LIST = 16;
    public static final int FOOT_PRINT = 17;
    public static final int LOGIN_TAG = 101 ;
    public static final int REGISTER_TAG = 102 ;



    public static boolean toHomeFragment = false ;
    public static String joint(String name,String id){
        String str = "";
        try {
            JSONObject object=new JSONObject();
            object.put(name,id);
            str=object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
