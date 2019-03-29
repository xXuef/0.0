package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/17 15:16
 * 非遗bean
 */

public class InTangBean {
    /**
     * code : 0
     * message : {"count":3,"rows":[{"id":"1","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang1.html","addTime":"2017-10-12 14:59:31"},{"id":"2","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang2.html","addTime":"2017-10-12 14:59:34"},{"id":"3","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang3.html","addTime":"2017-10-12 14:59:41"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 3
         * rows : [{"id":"1","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang1.html","addTime":"2017-10-12 14:59:31"},{"id":"2","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang2.html","addTime":"2017-10-12 14:59:34"},{"id":"3","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/intang3.html","addTime":"2017-10-12 14:59:41"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 1
             * webUrl : http://123.57.247.239/android/smart_tourism/banner/web_view/intang1.html
             * addTime : 2017-10-12 14:59:31
             */

            public String id;
            public String webUrl;
            public String addTime;
        }
    }
}
