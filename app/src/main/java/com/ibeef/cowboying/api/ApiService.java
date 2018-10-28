package com.ibeef.cowboying.api;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.WeixinAuthFirstBean;
import com.ibeef.cowboying.bean.WeixinAuthSecondeBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author ls
 * @date on 2018/10/7 14:03
 * @describe 接口api
 * @package com.ranhan.cowboying.api
 **/
public interface ApiService {
    /**
     *微信授权登录第一步，通过code获取access_token
     * @return
     */
    @GET("sns/oauth2/access_token")
    Observable<WeixinAuthFirstBean> getWeixinAuthFirst(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    /**
     *微信授权登录第二步，根据access_token再去获取UserInfo
     * @return
     */
    @GET("sns/userinfo")
    Observable<WeixinAuthSecondeBean> getWeixinAuthSeconde(@Query("access_token") String access_token, @Query("openid") String openid);

    /**
     *开屏广告
     * @return
     */
    @GET("banner/start")
    Observable<HomeAdResultBean> getHomeAd(@Header("version") String version);

    /**
     *首页banner
     * @return
     */
    @GET("banner/home")
    Observable<HomeBannerResultBean> getHomeBanner();

    /**
     *首页视频
     * @return
     */
    @GET("pasture/video/home")
    Observable<HomeVideoResultBean> getHomeVideo();

    /**
     *全部视频
     * @return
     */
    @GET("pasture/video/all")
    Observable<HomeVideoResultBean> getAllVideo();

    /**
     *牧场列表
     * @return
     */
    @GET("pasture/all")
    Observable<PastureAllResultBean> getPastureAllVideo();

    /**
     *牧场详情
     * @return
     */
    @GET("pasture/detail")
    Observable<PastureDetelResultBean> getPastureDetelVideo();

    /**
     *提交反馈
     * @return
     */
    @POST("feedback/submit")
    Observable<SubmitFeedbackResultBean> getSubmitFeedback(@Header("token") String jwt, @Body SubmitFeedbackParamBean submitFeedbackParamBean);

    /**
     *我的反馈
     * @return
     */
    @GET("feedback/my")
    Observable<MyFeedbackResultBean> getMyFeedback(@Header("token") String jwt);

    /**
     *用户注册
     * @return
     */
    @POST("account/register")
    Observable<AccountRegisterResultBean> getAccoutRegister(@Header("version") String version,@Body AccountRegisterParamBean accountRegisterParamBean);

    /**
     *发送短信验证码
     * @return
     */
    @POST("sms/sendCode")
    Observable<SmsCodeResultBean> getSms(@Header("version") String version,@Body SmsCodeParamBean smsCodeParamBean);

    /**
     *验证短信验证码
     * @return
     */
    @POST("sms/validateSmsCode")
    Observable<SmsCodeResultBean> getValidateSms(@Header("version") String version,@Body ValidateSmsCodeParamBean validateSmsCodeParamBean);

    /**
     * 用户登录
     * @return
     */
    @POST("user/login")
    Observable<LoginBean> getUserLogin(@Header("version") String version, @Body LoginParamBean loginParamBean);

    /**
     * 设置登录密码
     * @return
     */
    @POST("account/editLoginPassword")
    Observable<EditLoginPwdResultBean> getEditLoginPwd(@Header("version") String version, @Body EditLoginPwdParamBean editLoginPwdParamBean);
}
