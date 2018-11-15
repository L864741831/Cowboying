package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/14 17:01
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PayInitResultBean {

    /**
     * code : 000000
     * message : ""
     * bizData : alipay_sdk=alipay-sdk-java-3.4.27.ALL&app_id=2018102761793917&biz_content=%7B%22disable_pay_channels%22%3A%22pcredit%2CpcreditpayInstallment%22%2C%22out_trade_no%22%3A%22112684210651264%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E7%89%9B%E5%8F%AA%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%225000.00%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F181j970a19.imwork.net%3A23558%2Ftryout%2Falipay%2Fnotify&sign=DCz48etmzTUS2zSTADeTQFzvDXfW1icp3GurFi1TRuOgM5bHEvkmcIF1PKFRazTIxuvGu6i%2FaEYCljiTdU61wRY5wLC%2BEVICBWtgjgbe6AAXT9VeyKHhnoMFHAvi%2FRUTkJYG9vbKebVSo%2FqaetgM7PGL8oxxr%2FV8%2BEr52gU6a9A7ci2KuOLinE5DN6C4roORdfzQBh9a4%2FhgdURByHn%2Fyp54al7M8h1cQL0iA7MEBVmcl%2BhsF%2BE9hA8fq4R4W4sKE%2FAQ16UoGfe4Vz3eeqH0QxODxvxWRDEgKcDkZR5HQvnligVmhv%2FJNnyS1OBw1alGAxd8aWTsDkPeYQTdTU%2BS0Q%3D%3D&sign_type=RSA2&timestamp=2018-11-15+09%3A53%3A14&version=1.0
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
