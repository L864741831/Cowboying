package com.ibeef.cowboying.config;

/**
 * 用到的公共常量
 */
public class Constant {
    /**
     * 殷俊杰服务
     */
//    public static final String BASE_URL = "http://192.168.0.122:8090/";
//    public static final String BASE_URL = "http://192.168.0.162:9001/";
      public static final String BASE_URL = "http://192.168.0.162:8090/";
//    public static final String BASE_URL = "http://192.168.1.100:8090/";
//    public static final String BASE_URL = "http://120.27.12.205:9001/";
    /**
     * 阿里云应用服务器地址
     */
    public static final String BASE_URLAL = "http://120.27.12.205:9001";
    /**
     * 微信支付appid
     */
    public static final String APP_ID = "wx24d5c4c32ab90b65";
    /**
     * 错误日志 标签 tag
     */
    public static final String TAG= "--mian--";

    /**
     * 微信授权url
     */
    public static  final String weixinUrl= "https://api.weixin.qq.com/";

    /**
     * 短信验证码//Md5Key
     */
    public static  final String MD5KEY = "4f3ef09822b48ec28f0ff3dbf923344f116315d22f28017be39eb119555a9582";
    /**
     * //头像正式
     */
//    public static final String prodYbAvatarDomin = "http://p0vzdseok.bkt.clouddn.com/";

    /**
     * //测试头像
     */
    public static String prodYbAvatarDomin ="http://oy9pdbwo3.bkt.clouddn.com/";
    /**
     * //正式环境图片服务器域名
     */
//    public static final String imageDomain = "http://yabei.oss-cn-beijing.aliyuncs.com/";

    /**
     * //测试环境图片服务器域名
     */
//    public static String imageDomain = "http://lgf8953.oss-cn-beijing.aliyuncs.com/";
    public static String imageDomain = "http://pasture-center.oss-cn-beijing.aliyuncs.com/";
    /**
     * //正式七牛获取token
     */
    public static final String BASE_URL3 = "http://qiniu.yabei.shop/";
    /**
     * //测试访问七牛地址
     */
//    public static final String BASE_URL3="http://192.168.0.162:9101/";
    /**
     * 头像
     */
    public static final String ybAvatarBucket="ybAvatar";
    /**
     * 是否是绑定微信true,微信登录 false
     */
    public static  boolean isBindWeiXin=false;
    /**
     * 微信appid
     */
    public static  final String WeixinAppId="wx24d5c4c32ab90b65";

    /**
     * 微信appSecret
     */
    public static  final String appappSecret="9780986f8ce29b0c6db809ecec0874a5";

    /**
     * 微信orderId
     */
    public static  int orderId;

    /**
     * VersionCodes
     */
    public static  final String VersionCodes="1.0";

    /**
     * 地址json文件
     */
    public static final String CITY_DATA = "china_city_data.json";

    /**
     * 支付成功跳转界面 判断是养牛还是商城支付成功
     * 0是养牛
     * 1是商城
     * 2是拼牛
     */
    public static  int PAY_RESULT_TYPE = 0;
    /**
     * 是否是商城商品id
     */
    public static  int PRODUCR_ID = 0;

    /**
     * 萤石云app key
     */
    public static  String APPKEYYHC = "6db46fefb0534ce494d1e148bccc7ec8";
}
