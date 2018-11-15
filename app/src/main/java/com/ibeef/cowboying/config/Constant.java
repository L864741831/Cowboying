package com.ibeef.cowboying.config;

/**
 * 用到的公共常量
 */
public class Constant {
    /**
     * 殷俊杰服务
     */
//    public static final String BASE_URL = "http://192.168.0.122:8082/";
    public static final String BASE_URL = "http://192.168.0.162:8090/";
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
    public static final String ALIPAYINFO = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082708412835&biz_content=%7B%22out_trade_no%22%3A%22201810120007780%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%9B%8A%E6%99%BA%E7%A7%AF%E6%9C%A8+%E5%B9%BC%E5%84%BF%E7%AB%A5%E7%8E%A9%E5%85%B7+%E4%B8%89%E5%90%88%E4%B8%80%E6%95%B0%E5%AD%97%E5%BD%A2%E7%8A%B6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmpi.yabei.shop%2Frest%2Forder%2Fpay%2Falipay%2Fnotify&sign=Q7LMn4rzkLgobupYFu1H91UnmUj8evYa46x4LICh36PqCi%2FD7D2qtiw2txi%2Fl2vIngi%2FmRn%2BfkTgwuBeSdpU21%2BIU3kYdNY184lQk9oERCGn%2Bf8hkQpsS3pSjiR5xsZF%2F%2Bq627Qi6OxmSaRcJCYI0G3OmkvVWpbNKjfBON0A9D8%3D&sign_type=RSA&timestamp=2018-10-12+12%3A00%3A16&version=1.0";

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

}
