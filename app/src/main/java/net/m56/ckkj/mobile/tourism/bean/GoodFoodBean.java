package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/20 15:18
 */

public class GoodFoodBean {


    /**
     * code : 0
     * message : {"count":3,"rows":[{"id":1,"shopname":"川渝火锅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/cy.jpg","recommindex":666,"shopprice":"45.00","shopdescribtion":"火锅，以麻，辣，鲜，香著称，他来源于民间，升华于庙堂，无论是贩夫走卒、达官显宦、文人骚客、商贾农工，还是红男绿女、黄发垂髫，其消费群体涵盖之广泛、人均消费次数之大，都是他地望尘莫及的。作为一种美食，火锅已成为四川和重庆两地的代表美食。","shopaddress":"许坦东街","longitude":null,"latitude":null,"countries":null,"province":"","city":null,"area":null,"spotid":null,"distance":1.5,"type":1},{"id":2,"shopname":"乌巢披萨","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ps.jpg","recommindex":33,"shopprice":"65.00","shopdescribtion":"比萨（Pizza），又译比萨饼、匹萨、批萨、披萨，是一种发源于意大利的食品，在全球颇受欢迎。比萨饼的通常做法是用发酵的圆面饼上面覆盖番茄酱，奶酪以及其他配料，并由烤炉烤制而成。","shopaddress":"许坦西街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":1.8,"type":1},{"id":3,"shopname":"原炭烤鱼","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ky.jpg","recommindex":3333,"shopprice":"188.00","shopdescribtion":"\u201c原炭烤鱼\u201d是重庆诸葛烤鱼公司总部研究决定，应广大创业者的强烈要求，总部继\u201d诸葛烤鱼\u201c品牌成功推出后，于2010年隆重出炉全新财富计划\u2014\u2014专为3\u201410万元投资额的创业人士启动\u201d原炭烤鱼\"财富篇章！","shopaddress":"南中环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":0.5,"type":1}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 3
         * rows : [{"id":1,"shopname":"川渝火锅","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/cy.jpg","recommindex":666,"shopprice":"45.00","shopdescribtion":"火锅，以麻，辣，鲜，香著称，他来源于民间，升华于庙堂，无论是贩夫走卒、达官显宦、文人骚客、商贾农工，还是红男绿女、黄发垂髫，其消费群体涵盖之广泛、人均消费次数之大，都是他地望尘莫及的。作为一种美食，火锅已成为四川和重庆两地的代表美食。","shopaddress":"许坦东街","longitude":null,"latitude":null,"countries":null,"province":"","city":null,"area":null,"spotid":null,"distance":1.5,"type":1},{"id":2,"shopname":"乌巢披萨","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ps.jpg","recommindex":33,"shopprice":"65.00","shopdescribtion":"比萨（Pizza），又译比萨饼、匹萨、批萨、披萨，是一种发源于意大利的食品，在全球颇受欢迎。比萨饼的通常做法是用发酵的圆面饼上面覆盖番茄酱，奶酪以及其他配料，并由烤炉烤制而成。","shopaddress":"许坦西街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":1.8,"type":1},{"id":3,"shopname":"原炭烤鱼","shopimg":"http://123.57.247.239/android/smart_tourism/shop/img/ky.jpg","recommindex":3333,"shopprice":"188.00","shopdescribtion":"\u201c原炭烤鱼\u201d是重庆诸葛烤鱼公司总部研究决定，应广大创业者的强烈要求，总部继\u201d诸葛烤鱼\u201c品牌成功推出后，于2010年隆重出炉全新财富计划\u2014\u2014专为3\u201410万元投资额的创业人士启动\u201d原炭烤鱼\"财富篇章！","shopaddress":"南中环街","longitude":null,"latitude":null,"countries":null,"province":null,"city":null,"area":null,"spotid":null,"distance":0.5,"type":1}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 1
             * shopname : 川渝火锅
             * shopimg : http://123.57.247.239/android/smart_tourism/shop/img/cy.jpg
             * recommindex : 666
             * shopprice : 45.00
             * shopdescribtion : 火锅，以麻，辣，鲜，香著称，他来源于民间，升华于庙堂，无论是贩夫走卒、达官显宦、文人骚客、商贾农工，还是红男绿女、黄发垂髫，其消费群体涵盖之广泛、人均消费次数之大，都是他地望尘莫及的。作为一种美食，火锅已成为四川和重庆两地的代表美食。
             * shopaddress : 许坦东街
             * longitude : null
             * latitude : null
             * countries : null
             * province :
             * city : null
             * area : null
             * spotid : null
             * distance : 1.5
             * type : 1
             */

            public int id;
            public String shopname;
            public String shopimg;
            public int recommindex;
            public String shopprice;
            public String shopdescribtion;
            public String shopaddress;
            public Object longitude;
            public Object latitude;
            public Object countries;
            public String province;
            public Object city;
            public Object area;
            public Object spotid;
            public double distance;
            public int type;
        }
    }
}
