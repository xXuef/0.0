package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/17 13:36
 */

public class JcBean {


    /**
     * code : 0
     * message : {"count":3,"rows":[{"id":"1","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view1.html","addTime":"2017-10-12 13:51:10"},{"id":"2","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view2.html","addTime":"2017-10-12 13:51:59"},{"id":"3","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view3.html","addTime":"2017-10-12 13:52:10"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 3
         * rows : [{"id":"1","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view1.html","addTime":"2017-10-12 13:51:10"},{"id":"2","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view2.html","addTime":"2017-10-12 13:51:59"},{"id":"3","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view3.html","addTime":"2017-10-12 13:52:10"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 1
             * webUrl : http://123.57.247.239/android/smart_tourism/banner/web_view/jc_view1.html
             * addTime : 2017-10-12 13:51:10
             */

            public String id;
            public String webUrl;
            public String addTime;
        }
    }
}
