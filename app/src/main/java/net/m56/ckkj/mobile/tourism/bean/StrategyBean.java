package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/18 11:35
 */

public class StrategyBean {


    /**
     * code : 0
     * message : {"count":6,"rows":[{"id":"6","stratetitle":"英雄广场","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl6.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view6.html","addTime":"2017-10-18 09:32:25"},{"id":"5","stratetitle":"翠丰庄园","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl5.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view5.html","addTime":"2017-10-18 09:31:07"},{"id":"4","stratetitle":"玄中寺参观路线","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl4.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view4.html","addTime":"2017-10-18 09:27:23"},{"id":"3","stratetitle":"怎么避开卦山高峰期","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl3.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view3.html","addTime":"2017-10-18 09:25:48"},{"id":"2","stratetitle":"交城避暑","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl2.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view2.html","addTime":"2017-10-18 09:22:54"},{"id":"1","stratetitle":"庞泉沟旅游攻略","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl1.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view1.html","addTime":"2017-10-18 09:22:51"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 6
         * rows : [{"id":"6","stratetitle":"英雄广场","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl6.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view6.html","addTime":"2017-10-18 09:32:25"},{"id":"5","stratetitle":"翠丰庄园","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl5.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view5.html","addTime":"2017-10-18 09:31:07"},{"id":"4","stratetitle":"玄中寺参观路线","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl4.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view4.html","addTime":"2017-10-18 09:27:23"},{"id":"3","stratetitle":"怎么避开卦山高峰期","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl3.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view3.html","addTime":"2017-10-18 09:25:48"},{"id":"2","stratetitle":"交城避暑","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl2.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view2.html","addTime":"2017-10-18 09:22:54"},{"id":"1","stratetitle":"庞泉沟旅游攻略","imgUrl":"http://123.57.247.239/android/smart_tourism/strategy/img/gl1.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/strategy/web_view/view1.html","addTime":"2017-10-18 09:22:51"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 6
             * stratetitle : 英雄广场
             * imgUrl : http://123.57.247.239/android/smart_tourism/strategy/img/gl6.jpg
             * webUrl : http://123.57.247.239/android/smart_tourism/strategy/web_view/view6.html
             * addTime : 2017-10-18 09:32:25
             */
            public String id;
            public String stratetitle;
            public String imgUrl;
            public String webUrl;
            public String addTime;
        }
    }
}
