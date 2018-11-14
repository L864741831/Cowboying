package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/14 17:01
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PayInitResultBean {
    /**
     * status : 000000
     * message :
     * data : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082708412835&biz_content=%7B%22out_trade_no%22%3A%22201804030060%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%25E9%259F%25A9%25E7%2589%2588%25E6%25B8%2585%25E6%2596%25B0%25E5%25A4%25A9%25E7%2584%25B6%25E8%25B4%259D%25E5%25A3%25B3%25E6%2589%2593%25E7%25A3%25A8%25E5%25B0%258F%25E9%259B%258F%25E8%258F%258A%25E8%258A%25B1%25E7%2593%25A3%25E7%2594%259C%25E7%25BE%258E%25E8%258A%25B1%25E6%259C%25B5%25E9%2595%2580%25E9%2593%25B6%25E9%2592%2588%25E8%2580%25B3%25E9%2592%2589%25E8%2580%25B3%25E9%25A5%25B0%25E5%25A5%25B3%25E9%2585%258D%25E9%25A5%25B0%25E5%2593%2581%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2212.50%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.27.12.205%3A9090%2Frest%2Forder%2Fpay%2Falipay%2Fnotify&sign=OG%2BRbmAl7LNUplo%2FssBA86L2e%2Fe6iSGcUp0TBPdum3IM7Oy%2BhHVjLcI3BRLqTTQq3k26gvnEqFnilRgEM%2FybHWyzu4QCig6KSOc61dcSSODXOAOHVkjy95FvRq70kG3pLttQ6WcyLhyTq9J4jvM6gqx1xD0EtTH9FJUfTVtFW7Q%3D&sign_type=RSA&timestamp=2018-04-03+11%3A37%3A48&version=1.0
     */

    private String code;
    private String message;
    private String bizData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }
}
