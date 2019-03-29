package net.m56.ckkj.mobile.tourism.bean;

/**
 * 2017/11/2 14:30
 */

public class MessageSend {
    /**
     * code : 0
     * message : {"mag_succ":"登陆成功","userid":"2ED144BC-2308-4B0A-58F4-1107800C2CE0"}
     */

    public int code;
    public MessageItem message;

    public static class MessageItem {
        /**
         * mag_succ : 登陆成功
         * userid : 2ED144BC-2308-4B0A-58F4-1107800C2CE0
         */

        public String mag_succ;
        public String userid;
    }

    /**
     * code : -1
     * message : 您输入的手机号还未注册，请先注册
     */


}
