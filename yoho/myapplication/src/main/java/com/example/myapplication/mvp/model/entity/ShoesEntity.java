package com.example.myapplication.mvp.model.entity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/9 11
 * Package_Name : yoho
 */
public class ShoesEntity {


    /**
     * statues : 1
     * msg : 请求成功
     * banner : [{"recommend_id":"1","recommend_name":"重点推荐1","recommend_url":"/recommend_img/recommend1.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"2","recommend_name":"重点推荐2","recommend_url":"/recommend_img/recommend2.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"3","recommend_name":"重点推荐3","recommend_url":"/recommend_img/recommend3.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"4","recommend_name":"重点推荐4","recommend_url":"/recommend_img/recommend4.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"5","recommend_name":"重点推荐5","recommend_url":"/recommend_img/recommend5.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"6","recommend_name":"重点推荐6","recommend_url":"/recommend_img/recommend6.jpg","recommend_path":"http://www.baidu.com/"},{"recommend_id":"7","recommend_name":"重点推荐7","recommend_url":"/recommend_img/recommend7.jpg","recommend_path":"http://www.baidu.com/"}]
     * brand : [{"brand_id":"1","menu_id":"1","brand_name":"AAAA","brand_icon":"/brand_img/brand1.png"},{"brand_id":"2","menu_id":"1","brand_name":"BBBB","brand_icon":"/brand_img/brand2.png"},{"brand_id":"3","menu_id":"1","brand_name":"CCCC","brand_icon":"/brand_img/brand3.png"},{"brand_id":"4","menu_id":"1","brand_name":"DDDD","brand_icon":"/brand_img/brand4.png"},{"brand_id":"5","menu_id":"1","brand_name":"EEEE","brand_icon":"/brand_img/brand5.png"},{"brand_id":"6","menu_id":"1","brand_name":"FFFF","brand_icon":"/brand_img/brand6.png"},{"brand_id":"7","menu_id":"1","brand_name":"GGGG","brand_icon":"/brand_img/brand7.png"},{"brand_id":"8","menu_id":"1","brand_name":"HHHH","brand_icon":"/brand_img/brand8.png"},{"brand_id":"9","menu_id":"1","brand_name":"IIII","brand_icon":"/brand_img/brand9.png"}]
     * values : [{"goods_id":"1","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服1","goods_original_price":"120","goods_discount_price":"100","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221466","goods_img_id":"1","goods_img_path":"/goods_img/goods_img1.jpg","detail_flag":"1"},{"goods_id":"2","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服2","goods_original_price":"121","goods_discount_price":"101","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221467","goods_img_id":"6","goods_img_path":"/goods_img/goods_img6.jpg","detail_flag":"1"},{"goods_id":"3","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服3","goods_original_price":"122","goods_discount_price":"102","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221468","goods_img_id":"10","goods_img_path":"/goods_img/goods_img10.jpg","detail_flag":"1"},{"goods_id":"4","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服4","goods_original_price":"123","goods_discount_price":"103","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221469","goods_img_id":"31","goods_img_path":"/goods_img/goods_img1.jpg","detail_flag":"1"},{"goods_id":"5","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服5","goods_original_price":"124","goods_discount_price":"104","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221410","goods_img_id":"36","goods_img_path":"/goods_img/goods_img6.jpg","detail_flag":"1"},{"goods_id":"6","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服6","goods_original_price":"125","goods_discount_price":"105","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221470","goods_img_id":"40","goods_img_path":"/goods_img/goods_img10.jpg","detail_flag":"1"},{"goods_id":"7","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服7","goods_original_price":"126","goods_discount_price":"106","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221471","goods_img_id":"61","goods_img_path":"/goods_img/goods_img1.jpg","detail_flag":"1"},{"goods_id":"8","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服8","goods_original_price":"127","goods_discount_price":"107","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221472","goods_img_id":"66","goods_img_path":"/goods_img/goods_img6.jpg","detail_flag":"1"},{"goods_id":"9","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服9","goods_original_price":"128","goods_discount_price":"108","discount_detail":"8","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221473","goods_img_id":"70","goods_img_path":"/goods_img/goods_img10.jpg","detail_flag":"1"},{"goods_id":"10","category_id":"1","brand_id":"1","shop_id":"1","goods_name":"漂亮的衣服10","goods_original_price":"129","goods_discount_price":"109","discount_detail":"9","goods_guarantee":"7天无理由退货","goods_popularity":"100","follow":"99","time":"1552221474","goods_img_id":"91","goods_img_path":"/goods_img/goods_img1.jpg","detail_flag":"1"}]
     */

    private String statues;
    private String msg;
    private List<BannerBean> banner;
    private List<BrandBean> brand;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class BannerBean {
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

    public static class BrandBean {
        /**
         * brand_id : 1
         * menu_id : 1
         * brand_name : AAAA
         * brand_icon : /brand_img/brand1.png
         */

        private String brand_id;
        private String menu_id;
        private String brand_name;
        private String brand_icon;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_icon() {
            return brand_icon;
        }

        public void setBrand_icon(String brand_icon) {
            this.brand_icon = brand_icon;
        }
    }

    public static class ValuesBean {
        /**
         * goods_id : 1
         * category_id : 1
         * brand_id : 1
         * shop_id : 1
         * goods_name : 漂亮的衣服1
         * goods_original_price : 120
         * goods_discount_price : 100
         * discount_detail : 8
         * goods_guarantee : 7天无理由退货
         * goods_popularity : 100
         * follow : 99
         * time : 1552221466
         * goods_img_id : 1
         * goods_img_path : /goods_img/goods_img1.jpg
         * detail_flag : 1
         */

        private String goods_id;
        private String category_id;
        private String brand_id;
        private String shop_id;
        private String goods_name;
        private String goods_original_price;
        private String goods_discount_price;
        private String discount_detail;
        private String goods_guarantee;
        private String goods_popularity;
        private String follow;
        private String time;
        private String goods_img_id;
        private String goods_img_path;
        private String detail_flag;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_original_price() {
            return goods_original_price;
        }

        public void setGoods_original_price(String goods_original_price) {
            this.goods_original_price = goods_original_price;
        }

        public String getGoods_discount_price() {
            return goods_discount_price;
        }

        public void setGoods_discount_price(String goods_discount_price) {
            this.goods_discount_price = goods_discount_price;
        }

        public String getDiscount_detail() {
            return discount_detail;
        }

        public void setDiscount_detail(String discount_detail) {
            this.discount_detail = discount_detail;
        }

        public String getGoods_guarantee() {
            return goods_guarantee;
        }

        public void setGoods_guarantee(String goods_guarantee) {
            this.goods_guarantee = goods_guarantee;
        }

        public String getGoods_popularity() {
            return goods_popularity;
        }

        public void setGoods_popularity(String goods_popularity) {
            this.goods_popularity = goods_popularity;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getGoods_img_id() {
            return goods_img_id;
        }

        public void setGoods_img_id(String goods_img_id) {
            this.goods_img_id = goods_img_id;
        }

        public String getGoods_img_path() {
            return goods_img_path;
        }

        public void setGoods_img_path(String goods_img_path) {
            this.goods_img_path = goods_img_path;
        }

        public String getDetail_flag() {
            return detail_flag;
        }

        public void setDetail_flag(String detail_flag) {
            this.detail_flag = detail_flag;
        }
    }
}
