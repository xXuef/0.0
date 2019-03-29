package net.m56.ckkj.mobile.tourism.bean;

import java.util.List;

/**
 * 2017/10/24 13:42
 * 评论
 */

public class CommentBean {


    /**
     * code : 0
     * message : {"count":5,"rows":[{"id":28,"commentid":"B5B4ED22-135D-1EE9-8182-629363FD9EC9","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:22"},{"id":29,"commentid":"EB172E5F-19BC-2D22-1659-3B9B031FD12C","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:55"},{"id":30,"commentid":"42572E53-EA85-2DBB-535C-166B26F3BF5C","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:56"},{"id":31,"commentid":"CCD1CCBC-6344-9876-BCDD-38C25CBAD763","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:57"},{"id":32,"commentid":"F913E07E-0B04-0911-A5E9-EF446C75F876","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:08:26"}]}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 5
         * rows : [{"id":28,"commentid":"B5B4ED22-135D-1EE9-8182-629363FD9EC9","shopid":"2",
         * "userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝",
         * "commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:22"},{
         * "id":29,"commentid":"EB172E5F-19BC-2D22-1659-3B9B031FD12C","shopid":"2","
         * userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝",
         * "commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:55"},
         * {"id":30,"commentid":"42572E53-EA85-2DBB-535C-166B26F3BF5C","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:56"},{"id":31,"commentid":"CCD1CCBC-6344-9876-BCDD-38C25CBAD763","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:07:57"},{"id":32,"commentid":"F913E07E-0B04-0911-A5E9-EF446C75F876","shopid":"2","userid":"56A62151-CFAF-8FAA-DF5A-DAE454B68B08","usericon":null,"nickname":"上帝","commentmsg":"这个景区很扯淡","imgUrl":null,"commenttime":"2017-10-19 17:08:26"}]
         */

        public int count;
        public List<RowsItem> rows;

        public static class RowsItem {
            /**
             * id : 28
             * commentid : B5B4ED22-135D-1EE9-8182-629363FD9EC9
             * shopid : 2
             * userid : 56A62151-CFAF-8FAA-DF5A-DAE454B68B08
             * usericon : null
             * nickname : 上帝
             * commentmsg : 这个景区很扯淡
             * imgUrl : null
             * commenttime : 2017-10-19 17:07:22
             */

            public int id;
            public String commentid;
            public String shopid;
            public String userid;
            public Object usericon;
            public String nickname;
            public String commentmsg;
            public Object imgUrl;
            public String commenttime;
        }
    }
}
