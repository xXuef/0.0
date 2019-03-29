package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/16 14:19
 */

public class ListBean {


    /**
     * code : 0
     * message : {"count":2,"rows":[{"id":"1","spotname":"庞泉沟漂流","spotimg":"http://123.57.247.239/android/smart_tourism/spot/img/pqg.jpg","recommindex":"10000","spotprice":"120.00","spotdescribtion":"庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区\u2014\u2014 庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区\u2014\u2014庞泉沟国家自然保护区的核心区域，这里动植物种类繁多，是国家一级保护动物褐马鸡的栖息地。漂流河段全长10公里，落差130米，以其参与度高、体验性强、落差大、水量足、船行急而成为华北地区最具刺激性的动感、时尚、新潮水上运动娱乐项目，被誉为\u201c华北第一漂\u201d","address":"山西省吕梁市交城县庞泉沟镇","longitude":"111.657669","latitude":"37.676292","countries":"中国","province":"山西","city":"吕梁市","area":"交城县","buystate":"0"},{"id":"2","spotname":"玄中寺","spotimg":"http://123.57.247.239/android/smart_tourism/spot/img/xzs.jpg","recommindex":"30","spotprice":"30.00","spotdescribtion":"玄中寺位于山西省吕梁市交城县西北十公里的石壁山上，是净土宗发源地之一，也被日本佛教净土宗和净土真宗视为祖庭。玄中寺始创于北魏延兴二年（472年），建成于承明元年（476年）。因此地层峦叠嶂，山形如壁，故又改名\u201c石壁寺\u201d。 玄中寺的原存建筑，除建于明万历三十三年（1605年）的天王殿和明代的牌楼门外，其余都已毁弃","address":"山西省吕梁市交城县石壁山","longitude":"112.092013","latitude":"37.569971","countries":"中国","province":"山西","city":"吕梁市","area":"交城县","buystate":"0"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 2
         * rows : [{"id":"1","spotname":"庞泉沟漂流","spotimg":"http://123.57.247.239/android/smart_tourism/spot/img/pqg.jpg","recommindex":"10000","spotprice":"120.00","spotdescribtion":"庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区\u2014\u2014 庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区\u2014\u2014庞泉沟国家自然保护区的核心区域，这里动植物种类繁多，是国家一级保护动物褐马鸡的栖息地。漂流河段全长10公里，落差130米，以其参与度高、体验性强、落差大、水量足、船行急而成为华北地区最具刺激性的动感、时尚、新潮水上运动娱乐项目，被誉为\u201c华北第一漂\u201d","address":"山西省吕梁市交城县庞泉沟镇","longitude":"111.657669","latitude":"37.676292","countries":"中国","province":"山西","city":"吕梁市","area":"交城县","buystate":"0"},{"id":"2","spotname":"玄中寺","spotimg":"http://123.57.247.239/android/smart_tourism/spot/img/xzs.jpg","recommindex":"30","spotprice":"30.00","spotdescribtion":"玄中寺位于山西省吕梁市交城县西北十公里的石壁山上，是净土宗发源地之一，也被日本佛教净土宗和净土真宗视为祖庭。玄中寺始创于北魏延兴二年（472年），建成于承明元年（476年）。因此地层峦叠嶂，山形如壁，故又改名\u201c石壁寺\u201d。 玄中寺的原存建筑，除建于明万历三十三年（1605年）的天王殿和明代的牌楼门外，其余都已毁弃","address":"山西省吕梁市交城县石壁山","longitude":"112.092013","latitude":"37.569971","countries":"中国","province":"山西","city":"吕梁市","area":"交城县","buystate":"0"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 1
             * spotname : 庞泉沟漂流`
             * spotimg : http://123.57.247.239/android/smart_tourism/spot/img/pqg.jpg`
             * recommindex : 10000`
             * spotprice : 120.00`
             * spotdescribtion : 庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区—— 庞泉沟峡谷漂流位于山西省吕梁市交城县庞泉沟镇，地处国家级自然保护区——庞泉沟国家自然保护区的核心区域，这里动植物种类繁多，是国家一级保护动物褐马鸡的栖息地。漂流河段全长10公里，落差130米，以其参与度高、体验性强、落差大、水量足、船行急而成为华北地区最具刺激性的动感、时尚、新潮水上运动娱乐项目，被誉为“华北第一漂”
             * address : 山西省吕梁市交城县庞泉沟镇
             * longitude : 111.657669
             * latitude : 37.676292
             * countries : 中国
             * province : 山西
             * city : 吕梁市
             * area : 交城县
             * buystate : 0
             */

            public String id;
            public String spotname;
            public String spotimg;
            public String recommindex;
            public String spotprice;
            public String spotdescribtion;
            public String address;
            public String longitude;
            public String latitude;
            public String countries;
            public String province;
            public String city;
            public String area;
            public String buystate;
        }
    }
}
