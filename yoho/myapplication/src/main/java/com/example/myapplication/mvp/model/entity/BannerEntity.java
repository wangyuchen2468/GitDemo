package com.example.myapplication.mvp.model.entity;

import java.util.List;

public class BannerEntity extends BaseEntity {


    private List<ValuesBean> values;

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * recommend_id : 1
         * recommend_name : 重点推荐1
         * recommend_url : /recommend_img/recommend1.jpg
         * recommend_path : http://www.baidu.com/
         */

        private String recommend_id;
        private String recommend_name;
        private String recommend_url;
        private String recommend_path;

        public String getRecommend_id() {
            return recommend_id;
        }

        public void setRecommend_id(String recommend_id) {
            this.recommend_id = recommend_id;
        }

        public String getRecommend_name() {
            return recommend_name;
        }

        public void setRecommend_name(String recommend_name) {
            this.recommend_name = recommend_name;
        }

        public String getRecommend_url() {
            return recommend_url;
        }

        public void setRecommend_url(String recommend_url) {
            this.recommend_url = recommend_url;
        }

        public String getRecommend_path() {
            return recommend_path;
        }

        public void setRecommend_path(String recommend_path) {
            this.recommend_path = recommend_path;
        }
    }
}
