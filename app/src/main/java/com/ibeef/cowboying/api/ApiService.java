package com.ibeef.cowboying.api;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.BindMobileParamBean;
import com.ibeef.cowboying.bean.BindMobileResultBean;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.bean.ModifyHeadParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickParamBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.bean.QiniuUploadImg;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.RestLoginParamBean;
import com.ibeef.cowboying.bean.RestLoginPwdResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdCountLoginResultBean;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
import com.ibeef.cowboying.bean.UpdateMobileParamBean;
import com.ibeef.cowboying.bean.UpdateMobileResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.WeixinAuthFirstBean;
import com.ibeef.cowboying.bean.WeixinAuthSecondeBean;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Observable<HomeBannerResultBean> getHomeBanner(@Header("version") String version);

    /**
     *首页视频
     * @return
     */
    @GET("pasture/video/home")
    Observable<HomeVideoResultBean> getHomeVideo(@Header("version") String version);

    /**
     *全部视频
     * @return
     */
    @GET("pasture/video/all")
    Observable<HomeAllVideoResultBean> getAllVideo(@Header("version") String version, @Query("currentPage") int currentPage);

    /**
     *牧场列表
     * @return
     */
    @GET("pasture/all")
    Observable<PastureAllResultBean> getPastureAllVideo(@Header("version") String version);

    /**
     *牧场详情
     * @return
     */
    @GET("pasture/detail")
    Observable<PastureDetelResultBean> getPastureDetelVideo(@Header("version") String version,@Query("pastureId") int pastureId);

    /**
     *提交反馈
     * @return
     */
    @POST("feedback/submit")
    Observable<SubmitFeedbackResultBean> getSubmitFeedback(@HeaderMap Map<String, String> headers, @Body SubmitFeedbackParamBean submitFeedbackParamBean);

    /**
     *我的反馈
     * @return
     */
    @GET("feedback/my")
    Observable<MyFeedbackResultBean> getMyFeedback(@HeaderMap Map<String, String> headers,@Query("currentPage") int currentPage);

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
    Observable<EditLoginPwdResultBean> getEditLoginPwd(@HeaderMap Map<String, String> headers, @Body EditLoginPwdParamBean editLoginPwdParamBean);

    /**
     * 重置密码
     * @return
     */
    @POST("account/reSetPassword")
    Observable<RestLoginPwdResultBean> getRestLoginPwd(@HeaderMap Map<String, String> headers, @Body RestLoginParamBean editLoginPwdParamBean);

    /**
     * 更新头像
     * @return
     */
    @POST("account/modifyHeadImage")
    Observable<ModifyHeadResultBean> getModifyHead(@HeaderMap Map<String, String> headers,  @Body ModifyHeadParamBean modifyHeadParamBean);

    /**
     * 更新昵称
     * @return
     */
    @POST("account/modifyNickName")
    Observable<ModifyNickResultBean> getModifNick(@HeaderMap Map<String, String> headers, @Body ModifyNickParamBean modifyNickParamBean);

    /**
     * 获取个人信息
     * @return
     */
    @GET("account/getUserInfo")
    Observable<UserInfoResultBean> getUserInfo(@HeaderMap Map<String, String> headers);

    /**
     * 用户实名认证
     * @return
     */
    @POST("account/realNameValidate")
    Observable<RealNameReaultBean> getRealName(@HeaderMap Map<String, String> headers, @Body RealNameParamBean realNameParamBean);

    /**
     * 绑定手机号
     * @return
     */
    @POST("account/setUserMobile")
    Observable<BindMobileResultBean> getBindMobile(@HeaderMap Map<String, String> headers, @Body BindMobileParamBean bindMobileParamBean);

    /**
     * 获取账户安全信息
     * @return
     */
    @GET("account/getSafeInfo")
    Observable<SafeInfoResultBean> getSafeInfo(@HeaderMap Map<String, String> headers);

    /**
     * 绑定第三方账号
     * @return
     */
    @POST("user/setThirdUser")
    Observable<BindThirdCountResultBean> getBindThidCount(@HeaderMap Map<String, String> headers, @Body BindThirdCountParamBean bindThirdCountParamBean);

    /**
     * 绑解除第三方账户绑定
     * @return
     */
    @POST("user/removeThirdUserInfo/{thirdUserId}")
    Observable<BindThirdCountResultBean> getUnBindThidCount(@HeaderMap Map<String, String> headers,@Path("thirdUserId") String thirdUserId);

    /**
     * 更新账户关联的手机号
     * @return
     */
    @POST("account/modifyMobile")
    Observable<UpdateMobileResultBean> getUpdateMobile(@HeaderMap Map<String, String> headers, @Body UpdateMobileParamBean updateMobileParamBean);

    /**
     * 第三方登录
     * @return
     */
    @POST("user/thirdLogin")
    Observable<ThirdCountLoginResultBean> getThirdCountLogin(@Header("version") String version, @Body ThirdCountLoginParamBean thirdCountLoginParamBean);

    /**
     * 检查版本号
     * @return
     */
    @POST("app/check/version")
    Observable<CheckVersionBean> getCheckVersion(@Header("version") String version, @Body CheckVersionParamBean checkVersionParamBean);

    /**
     * 第三方账号初始化
     * @return
     */
    @GET("user/thirdLogin/init")
    Observable<ThirdLoginResultBean> getInitThirdLogin(@Header("version") String version, @Query("loginType") String loginType);

    /**
     * 七牛上传图片
     * @return
     */
    @GET("token/upload")
    Observable<QiniuUploadImg> qiNiuUpload(@Query("bucket") String bucket);
}
