package com.example.myapplication.mvp.model;

import android.util.Log;

import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.AddCarEntity;
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.ChangeUserEntity;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.entity.CouponEntity;
import com.example.myapplication.mvp.model.entity.FootPrintEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.GoodsValuesEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.OrderEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.ZhouSonEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import retrofit2.http.PATCH;

@ActivityScope
public class LreModel extends BaseModel implements LreContact.LreModel {
    @Inject
    public LreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity> lreRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(),false,1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .GOODS_LIST:
                Observable<GoodsListEntity> goodsListEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(params);
                ob = Observable.fromArray(goodsListEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .LOGIN_TAG:
                Observable<LoginEntity> loginEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).userLogin(params);
                ob = Observable.fromArray(loginEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .REGISTER_TAG:
                Observable<RegisterEntity> registerEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).userRegister(params);
                ob = Observable.fromArray(registerEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .COMMUNITY_LIST:
                Observable<CommunityEntity> community = mRepositoryManager.obtainRetrofitService(Api.class).postCommunity(params);
                ob = Observable.fromArray(community).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .ZHOU_LIST:
                Observable<ZhouSonEntity> zhou = mRepositoryManager.obtainRetrofitService(Api.class).postZhouList(params);
                ob = Observable.fromArray(zhou).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .CART_LIST:
                Observable<CarListEntity> carList = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(carList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .GOODS_VALUE:
                Observable<GoodsValuesEntity> goodsValuesEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsValues(params);
                ob = Observable.fromArray(goodsValuesEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .ADD_CART:
                Log.i("###addcart", "lreRequest: 添加购物车");
                Observable<AddCarEntity> addCarEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postAddCar(params);
                ob = Observable.fromArray(addCarEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .CHANGE_USER:
                Observable<ChangeUserEntity> changeUserEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postChangeUser(params);
                ob = Observable.fromArray(changeUserEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .ADD_ADDRESS:
                Observable<BaseEntity> addressEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postAddData(params);
                ob = Observable.fromArray(addressEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .ADDRESSMANAGER:
                Observable<AddressEntity> observable = mRepositoryManager.obtainRetrofitService(Api.class).postAddressData(params);
                ob = Observable.fromArray(observable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .DELETE_ADDRESS:
                Observable<BaseEntity> dassafa = mRepositoryManager.obtainRetrofitService(Api.class).postDeleteCarData(params);
                ob = Observable.fromArray(dassafa).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .COUPON_LIST:
                Observable<CouponEntity> couponEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postCoupon(params);
                ob = Observable.fromArray(couponEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.ORDER_LIST:
                Observable<OrderEntity> orderEntity = mRepositoryManager.obtainRetrofitService(Api.class).postOrder(params);
                ob = Observable.fromArray(orderEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman
                    .FOOT_PRINT:
                Observable<FootPrintEntity> footPrintEntity = mRepositoryManager.obtainRetrofitService(Api.class).postFootPrint(params);
                ob = Observable.fromArray(footPrintEntity).flatMap((Function) Functions.identity(), false, 1);
                break;
        }

        return ob;
    }

    @Override
    public Observable<BaseEntity> lreRefreshRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type) {
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
        }
        return ob;
    }

    @Override
    public Observable<BaseEntity> lreLoadRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(),false,1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
        }

        return ob;
    }
}
