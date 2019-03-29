package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/16 11:14
 */

public class DataBanner {


    /**
     * code : 0
     * message : {"count":3,"rows":[{"id":"1","advtitle":"褐小美带你游交城","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner0.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view0.html","addTime":"2017-10-11 14:55:04"},{"id":"2","advtitle":"广场","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner1.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view1.html","addTime":"2017-10-11 14:57:03"},{"id":"3","advtitle":"新闻发布会","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner2.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view2.html","addTime":"2017-10-11 14:57:28"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 3
         * rows : [{"id":"1","advtitle":"褐小美带你游交城","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner0.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view0.html","addTime":"2017-10-11 14:55:04"},{"id":"2","advtitle":"广场","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner1.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view1.html","addTime":"2017-10-11 14:57:03"},{"id":"3","advtitle":"新闻发布会","imgUrl":"http://123.57.247.239/android/smart_tourism/banner/img/banner2.jpg","webUrl":"http://123.57.247.239/android/smart_tourism/banner/web_view/view2.html","addTime":"2017-10-11 14:57:28"}]
         */

        public int count;
        public List<RowsItem> rows;

        public MessageItem(int count, List<RowsItem> rows) {
            this.count = count;
            this.rows = rows;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<RowsItem> getRows() {
            return rows;
        }

        public void setRows(List<RowsItem> rows) {
            this.rows = rows;
        }

        public static class RowsItem {
            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdvtitle() {
                return advtitle;
            }

            public void setAdvtitle(String advtitle) {
                this.advtitle = advtitle;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(String webUrl) {
                this.webUrl = webUrl;
            }

            /**
             * id : 1
             * advtitle : 褐小美带你游交城
             * imgUrl : http://123.57.247.239/android/smart_tourism/banner/img/banner0.jpg
             * webUrl : http://123.57.247.239/android/smart_tourism/banner/web_view/view0.html
             * addTime : 2017-10-11 14:55:04
             */

            public String id;
            public String advtitle;
            public String imgUrl;
            public String webUrl;
            public String addTime;
        }
    }
}
