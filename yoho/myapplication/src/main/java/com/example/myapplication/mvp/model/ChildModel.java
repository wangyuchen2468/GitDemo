package com.example.myapplication.mvp.model;

import com.example.myapplication.mvp.contact.ChildCategoryContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author ：created by
 * Create Date ：2019/12/6 13
 * Package_Name : yoho
 */
@ActivityScope
public class ChildModel extends BaseModel implements ChildCategoryContact.IChildCateModel {

    @Inject
    public ChildModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ChildCategoryEntity> getLeft(String request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("menuid",request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String s = jsonObject.toString();
        return mRepositoryManager.obtainRetrofitService(Api.class).postChildLeft(s);
    }

    @Override
    public Observable<ChildCateRightEntity> getRight(String request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("categoryid",request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String s = jsonObject.toString();
        return mRepositoryManager.obtainRetrofitService(Api.class).postChildRight(s);
    }

    @Override
    public Observable<BrandListEntity> getBrandList(String request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("menuid",request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String s = jsonObject.toString();
        return mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(s);
    }

    @Override
    public Observable<ShoesEntity> postShoes(String request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page",request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = jsonObject.toString();
        return mRepositoryManager.obtainRetrofitService(Api.class).postShoes(s);
    }
}
