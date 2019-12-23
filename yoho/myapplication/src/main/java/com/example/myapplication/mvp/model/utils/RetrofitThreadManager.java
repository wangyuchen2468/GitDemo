package com.example.myapplication.mvp.model.utils;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ：created by
 * Create Date ：2019/12/2 10
 * Package_Name : meiyouho
 */
public class RetrofitThreadManager  {
    private RetrofitThreadManager(){}
    private static RetrofitThreadManager instance;
    public static RetrofitThreadManager getInstance(){
        if(instance == null){
            synchronized (RetrofitThreadManager.class){
                if (instance == null){
                    instance = new RetrofitThreadManager();
                }
            }
        }
        return  instance;
    }



    public <T> void async(Observable<T> observable,Observer<T> tObserver){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tObserver);
    }
}
