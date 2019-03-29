package net.m56.ckkj.mobile.tourism.Api;

/**
 * 2017/10/16 9:03
 */

public interface Url {
    //服务器地址
    String Url ="http://123.57.247.239/";

    //轮播图接口
    String BananUrl = Url+"smart_tourism/public/index.php/index/Index/banner";
    //景区接口
    String ListUrl = Url+"smart_tourism/public/index.php/index/Index/class_spot";
    //非遗
    String InTanUrl = Url+"smart_tourism/public/index.php/index/Index/class_intang";
    //交城接口
    String JcUrl = Url+"smart_tourism/public/index.php/index/Index/class_jcinfo";
    //攻略接口
    String StrategyUrl = Url+"smart_tourism/public/index.php/index/Strategy/sel_strategy";
    //周边热门推荐
    String NearUrl = Url+"smart_tourism/public/index.php/index/Surround/hot";
    //周边上面四个按钮
    String NearFourUrl = Url+"smart_tourism/public/index.php/index/Surround/shops";
    String ShopSortUrl = Url+"smart_tourism/public/index.php/index/Surround/shops_sort";
    //评论查询
    String CommitUrl = Url+"smart_tourism/public/index.php/index/Comment/sel_comment";
    //上传评论
    String PostCommitUrl = Url+"smart_tourism/public/index.php/index/Comment/post_comment";

    //注册
    String Register = Url+"smart_tourism/public/index.php/index/Register/register";
    //密码
    String SetPassword = Url+"smart_tourism/public/index.php/index/Register/password";
    //登录
    String Login = Url+"smart_tourism/public/index.php/index/Login/logincheck";
    //退出登录
    String OutLogin = Url+"smart_tourism/public/index.php/index/Login/loginout";
    //查询个人信息
    String userInfo = Url+"smart_tourism/public/index.php/index/UserInfo/info";
    //修改个人信息
    String EditUserInfo = Url+"smart_tourism/public/index.php/index/UserInfo/edit_info";
    //投诉建议
    String Suggest = Url+"smart_tourism/public/index.php/index/Suggest/suggest";
    //新闻公告
    String NewsInfo = Url+"smart_tourism/public/index.php/index/Notices/infos";
    String Three = Url+"smart_tourism/public/index.php/index/ThirdParty/third_party_reg";

}
