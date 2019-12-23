package com.example.myapplication.mvp.ui.fragment.child;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerChildCateGoryComponent;
import com.example.myapplication.di.module.ChildModule;
import com.example.myapplication.mvp.contact.ChildCategoryContact;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.presenter.ChildPresenter;
import com.example.myapplication.mvp.ui.adapter.ChildLeftAdapter;
import com.example.myapplication.mvp.ui.adapter.ChildRightAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;


/**
 * 分类页面
 *  子页面
 *  品类
 * */
public class Classify_CategoryFragment extends BaseFragment<ChildPresenter> implements ChildCategoryContact.IChildCateView {
    @BindView(R.id.child_category_recLeft)
    RecyclerView recLeft;

    @BindView(R.id.child_category_recRight)
    XRecyclerView recRight;

    private static final String TAG = "###child";
    private ChildLeftAdapter childLeftAdapter ;
    private ChildRightAdapter rightAdapter;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChildCateGoryComponent.builder().appComponent(appComponent)
                .childModule(new ChildModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_child_category_layout, null);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        childLeftAdapter = new ChildLeftAdapter(getContext());


        recLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        recLeft.setAdapter(childLeftAdapter);


        childLeftAdapter.setChildAdapterCallback(new ChildLeftAdapter.ChildAdapterCallback() {
            @Override
            public void refreshRight(int position) {
                recRight.scrollToPosition(0);
                recRight.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        mPresenter.postRight(String.valueOf(position));
                        recRight.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {

                    }
                });

                recRight.refresh();

            }
        });
       mPresenter.postLeft();
        rightAdapter = new ChildRightAdapter(getContext());
        recRight.setLayoutManager(new LinearLayoutManager(getContext()));
        recRight.setAdapter(rightAdapter);

        mPresenter.postRight("1");
        recRight.setLoadingMoreEnabled(false);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onSuccessLeft(ChildCategoryEntity childCategoryEntity) {
        Log.i(TAG, "onSuccessLeft: ;left请求成功"+childCategoryEntity.getValues().get(0).getName());
        childLeftAdapter.setList(childCategoryEntity.getValues());
    }

    @Override
    public void onSuccessRight(ChildCateRightEntity childCateRightEntity) {
        Log.i(TAG, "onSuccessLeft: ;right请求成功");
        rightAdapter.setmData(childCateRightEntity.getValues());
    }

    @Override
    public void onSuccessBrand(BrandListEntity brandListEntity) {

    }

    @Override
    public void onSuccessShoes(ShoesEntity shoesEntity) {

    }

    @Override
    public void onError() {
        Log.i(TAG, "onError: 请求失败");
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
