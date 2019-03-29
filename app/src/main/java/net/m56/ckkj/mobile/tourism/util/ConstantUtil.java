package net.m56.ckkj.mobile.tourism.util;

/**
 * ClassName: ConstantUtil
 * Description: 全局设置
 * author junli Lee mingyangnc@163.com
 * date 2015/11/9 19:03
 */
public class ConstantUtil {

    /**
     * fh
     * cache time
     */
    public static final Long CACHE_TIME = 60 * 60 * 1000L;




    /**
     * base url
     */
    public static String BASE_URL = "";
    public static String TAKE_PHOTO = "take_photo.jpg";
    public static String CROP_PATH = "/crop.jpg";

    public static final int DEFAULT_DISK_USAGE_BYTES = 20 * 1024 * 1024;


    public static class USER {
        public static final String USERID = "userId";
        public static final String TOKENS = "tokens";
        public static final String USERNAME = "nickname";
        public static final String LOGINTIME = "loginTime";
        public static final String PASSWORD = "password";
        public static final String ACCOUNT = "account";
        public static final String PHONE = "phone";
        public static final String LAT = "latitude";
        public static final String LNG = "longitude";
        public static final String CITY = "city";
        public static final String SEX = "sex";
        public static final String USERHEAD = "userHead";
        public static final String ACCOUNTNUM = "account_num";
        public static final String LOGIN_ACCOUNT = "account";
        public static final String ISHELP = "isShowHelp";
        public static final String AGE = "age";
    }
}
