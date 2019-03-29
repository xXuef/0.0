package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/20 9:30
 */

public class HotRecommed {

    /**
     * code : 0
     * message : {"count":8,"rows":[{"id":"3","shopname":"原炭烤鱼","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ky.jpg","recommindex":"3333","shopprice":"188.00","shopdescribtion":"\u201c原炭烤鱼\u201d是重庆诸葛烤鱼公司总部研究决定，应广大创业者的强烈要求，总部继\u201d诸葛烤鱼\u201c品牌成功推出后，于2010年隆重出炉全新财富计划\u2014\u2014专为3\u201410万元投资额的创业人士启动\u201d原炭烤鱼\"财富篇章！","shopaddress":"南中环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.50","type":"1"},{"id":"1","shopname":"川渝火锅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/cy.jpg","recommindex":"666","shopprice":"45.00","shopdescribtion":"火锅，以麻，辣，鲜，香著称，他来源于民间，升华于庙堂，无论是贩夫走卒、达官显宦、文人骚客、商贾农工，还是红男绿女、黄发垂髫，其消费群体涵盖之广泛、人均消费次数之大，都是他地望尘莫及的。作为一种美食，火锅已成为四川和重庆两地的代表美食。","shopaddress":"许坦东街","longitude":null,"latitude":null,"countries":null,"province":"","city":null,"area":null,"spotid":null,"distance":"1.50","type":"1"},{"id":"6","shopname":"海洋之星","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/hy.jpg","recommindex":"800","shopprice":"128.00","shopdescribtion":"不知道该写啥了","shopaddress":"平阳路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"3.20","type":"2"},{"id":"5","shopname":"锦江之星","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/jj.jpg","recommindex":"300","shopprice":"88.00","shopdescribtion":"锦江之星连锁酒店官网为您提供全国188个大中型城市经济型快捷酒店预订,酒店报价查询,酒店优惠推荐。锦江之星中国经济型连锁酒店品牌先锋!","shopaddress":"南内环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"2.30","type":"2"},{"id":"8","shopname":"唐久便利","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/tj.jpg","recommindex":"800","shopprice":null,"shopdescribtion":"唐久便利","shopaddress":"龙城大街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"2.00","type":"3"},{"id":"7","shopname":"美特好","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/mth.jpg","recommindex":"20","shopprice":null,"shopdescribtion":"美特好","shopaddress":"长风街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"5.50","type":"3"},{"id":"10","shopname":"蓝山游戏厅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/yxt.jpg","recommindex":"333","shopprice":"24.00","shopdescribtion":"游戏厅","shopaddress":"体育路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.50","type":"4"},{"id":"9","shopname":"德玛网咖","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/dm.jpg","recommindex":"222","shopprice":"6.00","shopdescribtion":"网吧","shopaddress":"太榆路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.10","type":"4"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 8
         * rows : [{"id":"3","shopname":"原炭烤鱼","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ky.jpg","recommindex":"3333","shopprice":"188.00","shopdescribtion":"\u201c原炭烤鱼\u201d是重庆诸葛烤鱼公司总部研究决定，应广大创业者的强烈要求，总部继\u201d诸葛烤鱼\u201c品牌成功推出后，于2010年隆重出炉全新财富计划\u2014\u2014专为3\u201410万元投资额的创业人士启动\u201d原炭烤鱼\"财富篇章！","shopaddress":"南中环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.50","type":"1"},{"id":"1","shopname":"川渝火锅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/cy.jpg","recommindex":"666","shopprice":"45.00","shopdescribtion":"火锅，以麻，辣，鲜，香著称，他来源于民间，升华于庙堂，无论是贩夫走卒、达官显宦、文人骚客、商贾农工，还是红男绿女、黄发垂髫，其消费群体涵盖之广泛、人均消费次数之大，都是他地望尘莫及的。作为一种美食，火锅已成为四川和重庆两地的代表美食。","shopaddress":"许坦东街","longitude":null,"latitude":null,"countries":null,"province":"","city":null,"area":null,"spotid":null,"distance":"1.50","type":"1"},{"id":"6","shopname":"海洋之星","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/hy.jpg","recommindex":"800","shopprice":"128.00","shopdescribtion":"不知道该写啥了","shopaddress":"平阳路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"3.20","type":"2"},{"id":"5","shopname":"锦江之星","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/jj.jpg","recommindex":"300","shopprice":"88.00","shopdescribtion":"锦江之星连锁酒店官网为您提供全国188个大中型城市经济型快捷酒店预订,酒店报价查询,酒店优惠推荐。锦江之星中国经济型连锁酒店品牌先锋!","shopaddress":"南内环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"2.30","type":"2"},{"id":"8","shopname":"唐久便利","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/tj.jpg","recommindex":"800","shopprice":null,"shopdescribtion":"唐久便利","shopaddress":"龙城大街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"2.00","type":"3"},{"id":"7","shopname":"美特好","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/mth.jpg","recommindex":"20","shopprice":null,"shopdescribtion":"美特好","shopaddress":"长风街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"5.50","type":"3"},{"id":"10","shopname":"蓝山游戏厅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/yxt.jpg","recommindex":"333","shopprice":"24.00","shopdescribtion":"游戏厅","shopaddress":"体育路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.50","type":"4"},{"id":"9","shopname":"德玛网咖","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/dm.jpg","recommindex":"222","shopprice":"6.00","shopdescribtion":"网吧","shopaddress":"太榆路","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":"0.10","type":"4"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 3
             * shopname : 原炭烤鱼
             * shopimg : http://123.57.247.239/android/smart_tourism/shop/img/ky.jpg
             * recommindex : 3333
             * shopprice : 188.00
             * shopdescribtion : “原炭烤鱼”是重庆诸葛烤鱼公司总部研究决定，应广大创业者的强烈要求，总部继”诸葛烤鱼“品牌成功推出后，于2010年隆重出炉全新财富计划——专为3—10万元投资额的创业人士启动”原炭烤鱼"财富篇章！
             * shopaddress : 南中环街
             * longitude : null
             * latitude : null
             * countries : null
             * province : null
             * city : null
             * area : null
             * spotid : null
             * distance : 0.50
             * type : 1
             */

            public String id;
            public String shopname;
            public String shopimg;
            public String recommindex;
            public String shopprice;
            public String shopdescribtion;
            public String shopaddress;
            public Object longitude;
            public Object latitude;
            public Object countries;
            public Object province;
            public Object city;
            public Object area;
            public Object spotid;
            public String distance;
            public String type;
        }
    }
}
