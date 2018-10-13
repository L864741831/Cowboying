package com.ranhan.cowboying.api;

import com.ranhan.cowboying.bean.WeixinAuthFirstBean;
import com.ranhan.cowboying.bean.WeixinAuthSecondeBean;

import retrofit2.http.GET;
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
}
