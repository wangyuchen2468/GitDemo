package com.example.myapplication.mvp.model.entity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/6 11
 * Package_Name : yoho
 */
public class ChildCategoryEntity {

    /**
     * statues : 1
     * msg : 请求成功
     * values : [{"category_id":"1","name":"男装","image_path":"/goods_img/goods_img_1.jpg"},{"category_id":"2","name":"女装","image_path":"/goods_img/goods_img_2.jpg"},{"category_id":"3","name":"球鞋","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"4","name":"运动","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"5","name":"高街","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"6","name":"服配","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"7","name":"包类","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"8","name":"美妆","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"9","name":"生活","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"10","name":"数码","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"11","name":"潮玩","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"12","name":"潮食","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"13","name":"潮童","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"14","name":"好物","image_path":"/goods_img/goods_img_3.jpg"}]
     */

    private String statues;
    private String msg;
    private List<ValuesBean> values;

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * category_id : 1
         * name : 男装
         * image_path : /goods_img/goods_img_1.jpg
         */

        private String category_id;
        private String name;
        private String image_path;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
}
