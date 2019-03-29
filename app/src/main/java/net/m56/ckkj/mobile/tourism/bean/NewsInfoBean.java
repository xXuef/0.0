package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/11/23 9:28
 */

public class NewsInfoBean {

    /**
     * code : 0
     * message : {"count":2,"rows":[{"id":"3","title":"庞泉沟落水","cont":"庞泉沟有人落水，请就近搜救人员前往营救，避免出现不可挽回的后果","addTime":"2017-10-23 08:51:41","type":"2"},{"id":"4","title":"玄中寺拍照","cont":"玄中寺有游客不顾禁拍提醒，强行拍照，还触碰遗迹，请前去协商处理","addTime":"2017-10-24 08:53:31","type":"2"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 2
         * rows : [{"id":"3","title":"庞泉沟落水","cont":"庞泉沟有人落水，请就近搜救人员前往营救，避免出现不可挽回的后果","addTime":"2017-10-23 08:51:41","type":"2"},{"id":"4","title":"玄中寺拍照","cont":"玄中寺有游客不顾禁拍提醒，强行拍照，还触碰遗迹，请前去协商处理","addTime":"2017-10-24 08:53:31","type":"2"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 3
             * title : 庞泉沟落水
             * cont : 庞泉沟有人落水，请就近搜救人员前往营救，避免出现不可挽回的后果
             * addTime : 2017-10-23 08:51:41
             * type : 2
             */

            public String id;
            public String title;
            public String cont;
            public String addTime;
            public String type;
        }
    }

}
