package com.example.myapplication.mvp.model.utils;

/**
 * @ProjectName: yoho
 * @Package: com.example.zhq_yoho.util
 * @ClassName: ShopCarListener
 * @Description: java类作用描述
 * @Author: 周贺棋
 * @CreateDate: 2019/12/18 9:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/18 9:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ShopCarListener {
    public void isSelect(Boolean select, int position);
    public void add(Boolean select, int position);
    public void remove(boolean select, int position);

}
