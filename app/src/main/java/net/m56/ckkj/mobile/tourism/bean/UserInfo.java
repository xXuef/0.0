package net.m56.ckkj.mobile.tourism.bean;

/**
 * 2017/11/15 10:45
 */

public class UserInfo {


    /**
     * code : 0
     * message : {"count":18,"rows":{"id":"15","userid":"2BFA1BA5-1FAB-6585-879B-59FC86250717","usericon":null,"nickname":null,"age":null,"gender":null,"phone":"13834878257","signature":null,"hobby":null,"countries":null,"province":null,"city":null,"area":null,"loginstate":"0","file_read_key":null,"integral":null,"file_source":null,"file_bucker":null}}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * count : 18
         * rows : {"id":"15","userid":"2BFA1BA5-1FAB-6585-879B-59FC86250717","usericon":null,"nickname":null,"age":null,"gender":null,"phone":"13834878257","signature":null,"hobby":null,"countries":null,"province":null,"city":null,"area":null,"loginstate":"0","file_read_key":null,"integral":null,"file_source":null,"file_bucker":null}
         */

        public int count;
        public RowsItem rows;

        public static class RowsItem {
            /**
             * id : 15
             * userid : 2BFA1BA5-1FAB-6585-879B-59FC86250717
             * usericon : null
             * nickname : null
             * age : null
             * gender : null
             * phone : 13834878257
             * signature : null//个性签名
             * hobby : null
             * countries : null
             * province : null
             * city : null
             * area : null
             * loginstate : 0
             * file_read_key : null
             * integral : null
             * file_source : null
             * file_bucker : null
             */

            public String id;
            public String userid;
            public Object usericon;
            public String nickname;
            public String age;
            public String gender;
            public String phone;
            public Object signature;//个性签名
            public Object hobby;
            public Object countries;
            public Object province;
            public Object city;
            public Object area;
            public String loginstate;
            public Object file_read_key;
            public Object integral;
            public Object file_source;
            public Object file_bucker;
        }
    }
}
