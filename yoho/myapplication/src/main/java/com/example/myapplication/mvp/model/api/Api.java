package com.example.myapplication.mvp.model.api;

import com.example.myapplication.mvp.model.entity.AddCarEntity;
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.ChangeUserEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.entity.CouponEntity;
import com.example.myapplication.mvp.model.entity.FootPrintEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.GoodsValuesEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.OrderEntity;
import com.example.myapplication.mvp.model.entity.QueryUserEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.entity.UploadHeadEntity;
import com.example.myapplication.mvp.model.entity.ZhouSonEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 11/29/2019 14:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://169.254.105.174/yoho/";

    //首页菜单
    @GET("home_menu.php")
    Observable<MenuEntity> getMenuList();

    //banner
    @GET("home_banner.php")
    Observable<BannerEntity> getBannerList();

    //推荐
    @POST("home_recommend.php")
    @FormUrlEncoded
    Observable<RecommendEntity> postRecommendList(@Field("request")String request);

    //商品列表
    @POST("home_goods.php")
    @FormUrlEncoded
    Observable<GoodsListEntity> postGoodsList(@Field("request")String request);

    //周贺棋的商品列表
    @POST("goods_list.php")
    @FormUrlEncoded
    Observable<ZhouSonEntity> postZhouList(@Field("request")String request);

    //品类商品接口
    @POST("category_goods.php")
    @FormUrlEncoded
    Observable<CategoryGoodsEntity> postCategoryGoodsList(@Field("request")String request);

    //品牌列表接口
    @POST("Brand_list.php")
    @FormUrlEncoded
    Observable<BrandListEntity> postBrandList(@Field("request")String request);

    //品牌列表接口
    @POST("category_all.php")
    @FormUrlEncoded
    Observable<ChildCategoryEntity> postChildLeft(@Field("request")String request);

    //品牌列表接口
    @POST("category_goods.php")
    @FormUrlEncoded
    Observable<ChildCateRightEntity> postChildRight(@Field("request")String request);

    //品牌列表接口
    @POST("shoes_list.php")
    @FormUrlEncoded
    Observable<ShoesEntity> postShoes(@Field("request")String request);

    //登录接口
    //    Request={“username”:”wangzhonghua”,”password”:”rsa私钥加密后的密码”}
    @POST("Login.php")
    @FormUrlEncoded
    Observable<LoginEntity> userLogin(@Field("request")String request);

    // 注册接口
    // Request={“username”:”wangzhonghua”,” password”:”rsa私钥加密后的密码”}
    @POST("Register.php")
    @FormUrlEncoded
    Observable<RegisterEntity> userRegister(@Field("request")String request);

    /**
    * 作用:社区接口
    */
    @POST("community.php")
    @FormUrlEncoded
    Observable<CommunityEntity> postCommunity(@Field("request")String request);

    //加入购物车接口
    @FormUrlEncoded
    @POST("add_car.php")
    Observable<AddCarEntity> postAddCar(@Field("request")String request);
    //购物车列表接口
    @FormUrlEncoded
    @POST("car_list.php")
    Observable<CarListEntity> postCarList(@Field("request")String request);

    @FormUrlEncoded
    @POST("goods_values.php")
    Observable<GoodsValuesEntity> postGoodsValues(@Field("request")String request);

    //查询用户信息接口
    @POST("sel_user.php")
    @FormUrlEncoded
    Observable<QueryUserEntity> postQueryUser(@Field("request")String request);

    //上传用户头像接口
    @POST("upload_head.php")
    @FormUrlEncoded
    Observable<UploadHeadEntity> postUploadHead(@Field("request")String request);

    //更改用户信息接口
    @POST("update_user.php")
    @FormUrlEncoded
    Observable<ChangeUserEntity> postChangeUser(@Field("request")String request);

    //收货地址列表
    @POST("address_list.php")
    @FormUrlEncoded
    Observable<AddressEntity> postAddressData(@Field("request") String goods);

    //新增收货地址
    @POST("add_address.php")
    @FormUrlEncoded
    Observable<BaseEntity> postAddData(@Field("request") String goods);

    //删除地址
    @POST("del_address.php")
    @FormUrlEncoded
    Observable<BaseEntity> postDeleteCarData(@Field("request") String goods);

    //优惠券列表
    @POST("coupon_list.php")
    @FormUrlEncoded
    Observable<CouponEntity> postCoupon(@Field("request") String goods);

    //订单接口
    @POST("create_order.php")
    @FormUrlEncoded
    Observable<OrderEntity> postOrder(@Field("request")String request);

    @POST("footprint.php")
    @FormUrlEncoded
    Observable<FootPrintEntity> postFootPrint(@Field("request") String request);
}
