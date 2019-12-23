package com.example.myapplication.mvp.model.entity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/6 11
 * Package_Name : yoho
 */
public class ChildCateRightEntity {

    /**
     * statues : 1
     * msg : 请求成功
     * values : [{"category_id":"1","name":"上衣1","image_path":"/goods_img/goods_img_1.jpg"},{"category_id":"2","name":"上衣2","image_path":"/goods_img/goods_img_2.jpg"},{"category_id":"1","name":"上衣3","image_path":"/goods_img/goods_img_3.jpg"},{"category_id":"2","name":"上衣4","image_path":"/goods_img/goods_img_4.jpg"},{"category_id":"1","name":"上衣5","image_path":"/goods_img/goods_img_5.jpg"},{"category_id":"2","name":"上衣6","image_path":"/goods_img/goods_img_6.jpg"},{"category_id":"1","name":"上衣7","image_path":"/goods_img/goods_img_7.jpg"},{"category_id":"2","name":"上衣8","image_path":"/goods_img/goods_img_8.jpg"},{"category_id":"1","name":"上衣9","image_path":"/goods_img/goods_img_9.jpg"},{"category_id":"2","name":"上衣10","image_path":"/goods_img/goods_img_10.jpg"}]
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
         * name : 上衣1
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
