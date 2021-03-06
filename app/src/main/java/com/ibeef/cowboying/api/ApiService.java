package com.ibeef.cowboying.api;

import com.ibeef.cowboying.base.GetApplyReturnParameterBean;
import com.ibeef.cowboying.base.GetEditApplyReturnParameterBean;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.*;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    Observable<HomeAdResultBean> getHomeAd(@HeaderMap Map<String, String> headers);

    /**
     *首页banner
     * @return
     */
    @GET("banner/home")
    Observable<HomeBannerResultBean> getHomeBanner(@HeaderMap Map<String, String> headers);

    /**
     *首页视频
     * @return
     */
    @GET("pasture/video/home")
    Observable<HomeVideoResultBean> getHomeVideo(@HeaderMap Map<String, String> headers);

    /**
     *全部视频
     * @return
     */
    @GET("pasture/video/all")
    Observable<HomeAllVideoResultBean> getAllVideo(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     *牧场列表
     * @return
     */
    @GET("pasture/all")
    Observable<PastureAllResultBean> getPastureAllVideo(@HeaderMap Map<String, String> headers);

    /**
     *牧场详情
     * @return
     */
    @GET("pasture/detail")
    Observable<PastureDetelResultBean> getPastureDetelVideo(@HeaderMap Map<String, String> headers,@Query("pastureId") int pastureId);

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
     *上传图片
     * @return
     */
    @Multipart
    @POST("file/image/upload")
    Observable<MdUploadImgBean> getUploadImg(@HeaderMap Map<String, String> headers, @Part() List<MultipartBody.Part> parts);

    /**
     * 牧场详情推荐视频
     * @return
     */
    @GET("pasture/video/recommend")
    Observable<RanchBottomVideoResultBean> getRanchBottomVideo(@HeaderMap Map<String, String> headers);

    /**
     * oss图片上传
     * @return
     */
    @GET("file/appUploadInit")
    Observable<OssResultBean> getOssImg(@HeaderMap Map<String, String> headers);

    /**
     * 个人中心数据
     * @return
     */
    @GET("assets/home/data")
    Observable<CowManInfosResultBean> getCowManInfos(@HeaderMap Map<String, String> headers);

    /**
     * 资产详细数据
     * @return
     */
    @GET("assets/detail")
    Observable<IncomeInfoResultBean> getMoneyInfo(@HeaderMap Map<String, String> headers);

    /**
     * 钱包流水
     * @return
     */
    @GET("assets/wallet/logs")
    Observable<WalletRecordResultBean> getWalletRecord(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage, @Query("amountType") String amountType);

    /**
     * 累计收益记录
     * @return
     */
    @GET("assets/income/log")
    Observable<AddMoneyResultBean> getAddMoney(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage,@Query("interestType") String interestType,@Query("incomeType") String incomeType );

    /**
     * 昨日收益
     * @return
     */
    @GET("assets/income/data")
    Observable<YesterdayIncomeResultBean> getYesterdayIncome(@HeaderMap Map<String, String> headers, @Query("incomeType") String incomeType);

    /**
     * 提现
     * @return
     */
    @POST("assets/cash")
    Observable<CashMoneyResultBean> getCashMoney(@HeaderMap Map<String, String> headers, @Body CashMoneyParamBean cashMoneyParamBean);

    /**
     * 用户支付宝昵称
     * @return
     */
    @GET("account/alipayName")
    Observable<CashMoneyUserInfoResultBean> getCashMoneyUserInfo(@HeaderMap Map<String, String> headers);

    /**
     * 提现记录
     * @return
     */
    @GET("assets/cash/logs")
    Observable<CashMoneyRecordResultBean> getCashMoneyRecord(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     * 我的牛只订单列表
     * @return
     */
    @GET("adopt/order/list")
    Observable<MyCowsOrderListBean> geMyCowsOrderList(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage, @Query("status") String status);

    /**
     * 我的牛只订单详情
     * @return
     */
    @GET("adopt/order/detail")
    Observable<MyCowsOrderListDetailBean> geMyCowsOrderListDetail(@HeaderMap Map<String, String> headers, @Query("orderId") String orderCode);



    /**
     * 设置钱包密码
     * @return
     */
    @POST("wallet/setPayPassWord")
    Observable<SetPayPwdResultBean> getSetPayPwd(@HeaderMap Map<String, String> headers, @Body SetPayPwdParamBean setPayPwdParamBean);

    /**
     *重置支付密码
     * @return
     */
    @POST("wallet/reSetPayPassWord")
    Observable<ResetPayPwdResultBean> getResetPayPwd(@HeaderMap Map<String, String> headers, @Body ResetPayPwdParamBean resetPayPwdParamBean);

    /**
     * 查询进行中的方案列表
     * @return
     */
    @GET("adopt/scheme/activeSchemeInfo")
    Observable<ActiveSchemeResultBean> getActiveSchemeInfo(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     * 查询往期方案信息
     * @return
     */
    @GET("adopt/scheme/historySchemeInfo")
    Observable<HistorySchemeResultBean> getHistorySchemeInfo(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     * 获取方案参与者信息
     * @return
     */
    @GET("adopt/scheme/getParticipatorInfo")
    Observable<JionPersonInfoResultBean> getJionPersonInfo(@HeaderMap Map<String, String> headers, @Query("schemeId") int schemeId, @Query("currentPage") int currentPage);

    /**
     * 查询养牛方案详情
     * @return
     */
    @GET("adopt/scheme/schemeDetail")
    Observable<SchemeDetailReultBean> getSchemeDetail(@HeaderMap Map<String, String> headers, @Query("schemeId") int schemeId);

    /**
     * 获取牛只详情
     * @return
     */
    @GET("cattle/detail")
    Observable<CattleDetailResultBean> getCattleDetail(@HeaderMap Map<String, String> headers, @Query("cattleId") int cattleId);

    /**
     * 获取方案关联的牛只信息
     * @return
     */
    @GET("cattle/adopt/infos")
    Observable<AdoptInfosResultBean> getAdoptInfos(@HeaderMap Map<String, String> headers, @Query("code") String code, @Query("schemeId") Integer schemeId, @Query("currentPage") Integer currentPage);


    /**
     *生成养牛订单
     * @return
     */
    @POST("adopt/order/create")
    Observable<CreatOderResultBean> getCreatOder(@HeaderMap Map<String, String> headers, @Body CreatOrderParamBean creatOrderParamBean);

    /**
     *养牛订单支付
     * @return
     */
    @POST("adopt/order/payInit")
    Observable<PayInitResultBean> getPayInit(@HeaderMap Map<String, String> headers, @Body PayInitParamBean payInitParamBean);

    /**
     *商城订单支付
     * @return
     */
    @POST("shop/order/pay")
    Observable<PayInitResultBean> getStorePayInit(@HeaderMap Map<String, String> headers, @Body PayInitParamBean payInitParamBean);

    /**
     *获取卖牛信息
     * @return
     */
    @GET("sell/getSellInfo")
    Observable<SellCowsResultBean> getSellCows(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     *生成卖牛信息
     * @return
     */
    @POST("sell/pasture")
    Observable<CreatSellCowsResultBean> getCreatSellCows(@HeaderMap Map<String, String> headers, @Body CreatSellCowsParamBean creatSellCowsParamBean);

    /**
     * 我的牛只订单>删除订单
     * @return
     */
    @GET("adopt/order/delete")
    Observable<MyCowsOrderDeleteBean> getMyCowsOrderDelete(@HeaderMap Map<String, String> headers, @Query("orderId") String orderCode);

    /**
     * 我的牛只订单>删除订单
     * @return
     */
    @GET("adopt/order/cancel")
    Observable<MyCowsOrderDeleteBean> getMyCowsOrderCancel(@HeaderMap Map<String, String> headers, @Query("orderId") String orderCode);

    /**
     * 我的牛只订单>去支付》获取确认订单的界面详情信息
     * @return
     */
    @GET("adopt/order/nopay/detail")
    Observable<CreatOderResultBean> getMyCowsToPay(@HeaderMap Map<String, String> headers, @Query("orderId") String orderCode);

    /**
     * 累计销售数据
     * @return
     */
    @GET("index/total/data")
    Observable<HomeSellCowNumResultBean> getHomeSellCowsNum(@HeaderMap Map<String, String> headers);

    /**
     * 第三方登录再次验证
     * @return
     */
    @POST("user/third/reLogin")
    Observable<CheckThirdLoginResultBean> getCheckThirLogin(@HeaderMap Map<String, String> headers, @Body CheckThirdLoginParamBean checkThirdLoginParamBean);

    /**
     * 合同列表
     * @return
     */
    @GET("contract/list")
    Observable<MyContractListBean> getMyContrantList(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     * 合同域名
     * @return
     */
    @GET("file/pdfUrl")
    Observable<MyContractURLBean> getMyContrantURL(@HeaderMap Map<String, String> headers, @Query("type") String type, @Query("fileName") String fileName);

    /**
     * 可用优惠券数量
     * @return
     */
    @POST("coupon/available/count")
    Observable<CouponNumResultBean> getCouponNum(@HeaderMap Map<String, String> headers,  @Body CouponNumParamBean couponNumParamBean);

    /**
     * 获取当前方案中所有可用的优惠券信息
     * @return
     */
    @POST("coupon/available/list")
    Observable<UseCouponListResultBean> getUseCouponList(@HeaderMap Map<String, String> headers, @Body CouponNumParamBean couponNumParamBean);

    /**
     * 我的优惠券列表
     * @return
     */
    @GET("coupon/self/info")
    Observable<MyDiscountCouponListBean> getMyDiscountCouponList(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage, @Query("pageSize") int pageSize,@Query("findType") String findType);

    /**
     * 获取商城商品信息
     * @return
     */
    @POST("shop/product/getAll/info")
    Observable<StoreInfoListResultBean> getStoreInfoList(@HeaderMap Map<String, String> headers, @Body StorePriductIdParamBean storePriductIdParamBean);

    /**
     * 获取当前用户购车商品数量
     * @return
     */
    @GET("cart/get/productNum")
    Observable<StoreCarNumResultBean> getStoreCarNum(@HeaderMap Map<String, String> headers);

    /**
     * 新增购物车商品
     * @return
     */
    @POST("cart/add/products")
    Observable<AddStoreCarResultBean> addStoreCar(@HeaderMap Map<String, String> headers, @Body AddShopCarResultBean addShopCarResultBean);

    /**
     * 批量删除
     * @return
     */
    @POST("cart/delete/products")
    Observable<DeleteCarResultBean> deleteStoreCar(@HeaderMap Map<String, String> headers, @Body AddShopCarResultBean addShopCarResultBean);

    /**
     * 门店列表
     * @return
     */
    @GET("shop/store/list")
    Observable<StoreAddrResultBean> storeAddrList(@HeaderMap Map<String, String> headers);


    /**
     * 立即购买(确认订单页面)
     * @return
     */
    @POST("shop/order/buyNow")
    Observable<NowBuyOrderResultBean> nowBuyOrder(@HeaderMap Map<String, String> headers, @Body AddShopCarResultBean addShopCarResultBean);

    /**
     * 创建订单（立即付款）
     * @return
     */
    @POST("shop/order/create")
    Observable<NowPayOrderResultBean> nowPayOrder(@HeaderMap Map<String, String> headers, @Body NowPayOrderParamBean noPayOrderParamBean);

    /**
     * 购物车商品列表
     * @return
     */
    @GET("cart/product/list")
    Observable<CarListResultBean> getCarList(@HeaderMap Map<String, String> headers,@Query("currentPage") int currentPage);

    /**
     *地址 新增
     * @return
     */
    @POST("shop/address/add")
    Observable<DeleteCarResultBean> addAddress(@HeaderMap Map<String, String> headers,@Body AddAddressParamBean addAddressResultBean);

    /**
     * 地址列表
     * @return
     */
    @GET("shop/address/list")
    Observable<ShowAddressResultBean> showAddressList(@HeaderMap Map<String, String> headers, @Query("currentPage") int currentPage);

    /**
     * 地址修改
     * @return
     */
    @POST("shop/address/update")
    Observable<DeleteCarResultBean> updateAddress(@HeaderMap Map<String, String> headers,@Body AddAddressParamBean addAddressResultBean);

    /**
     * 地址删除
     * @return
     */
    @GET("shop/address/delete")
    Observable<DeleteCarResultBean> deleteAddress(@HeaderMap Map<String, String> headers,@Query("addressId") int addressId);

    /**
     * 我的商城订单列表
     * @return
     */
    @GET("shop/order/get/orderList")
    Observable<MyOrderListBean> getMyOrderList(@HeaderMap Map<String, String> headers, @Query("curPage") int curPage, @Query("status") String status);

    /**
     * 我的商城订单详情
     * @return
     */
    @GET("shop/order/get/orderDetail")
    Observable<MyOrderListDetailBean> getMyOrderListDetail(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     * 取消商城订单
     * @return
     */
    @GET("shop/order/cancel")
    Observable<MyOrderListCancelBean> getMyOrderListCancel(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     * 删除商城订单
     * @return
     */
    @GET("shop/order/remove")
    Observable<MyOrderListCancelBean> getMyOrderListDelete(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     * 商城订单确认收货
     * @return
     */
    @GET("shop/order/confirm/receive")
    Observable<MyOrderListCancelBean> getMyOrderListOk(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     * 我的售后列表
     * @return
     */
    @GET("shop/refund/list")
    Observable<MyAfterSaleListBean> getAfterSaleList(@HeaderMap Map<String, String> headers, @Query("pageSize") int pageSize, @Query("curPage") int curPage);

    /**
     * 售后详情
     * @return
     */
    @GET("shop/refund/get/detail")
    Observable<MyAfterSaleDetailBean> getAfterSaleDetail(@HeaderMap Map<String, String> headers, @Query("refundId") String refundId);

    /**
     * 申请退款
     * @return
     */
    @POST("shop/refund/apply")
    Observable<MyOrderListCancelBean> getApplyReturn(@HeaderMap Map<String, String> headers, @Body GetApplyReturnParameterBean getApplyReturnParameterBean);

    /**
     * 撤销申请退款
     * @return
     */
    @GET("shop/refund/cancel")
    Observable<MyOrderListCancelBean> getCancelApplyReturn(@HeaderMap Map<String, String> headers,  @Query("refundId") String refundId);

    /**
     * 修改申请退款
     * @return
     */
    @POST("shop/refund/modify")
    Observable<MyOrderListCancelBean> getEditApplyReturn(@HeaderMap Map<String, String> headers, @Body GetEditApplyReturnParameterBean getEditApplyReturnParameterBean);

    /**
     * 是否设置支付密码
     * @return
     */
    @GET("wallet/have/password")
    Observable<PayPwdResultBean> isPayPwd(@HeaderMap Map<String, String> headers);

    /**
     * 查询订单快递详情
     * @return
     */
    @GET("express/get/detail")
    Observable<ShowDeleveryResultBean> showDelevery(@HeaderMap Map<String, String> headers, @Query("orderId") String orderId);

    /**
     * 获取单个商品信息
     * @return
     */
    @GET("shop/product/getInfo/{productId}")
    Observable<StoreOneResultBean> getStoreOneInfo(@HeaderMap Map<String, String> headers, @Path("productId") int productId);

    /**
     *付款码
     * @return
     */
    @GET("appPay/code")
    Observable<PayCodeBean> showPayCode(@HeaderMap Map<String, String> headers, @Query("payType") String payType);

    /**
     *会员卡信息
     * @return
     */
    @GET("vip/card/info")
    Observable<VipCardBean> showVipCard(@HeaderMap Map<String, String> headers);

    /**
     *会员卡消费记录
     * @return
     */
    @GET("vip/card/consumer/log")
    Observable<VipCardListBean> showVipCardHistory(@HeaderMap Map<String, String> headers, @Query("curPage") int curPage);

    /**
     *更新极光注册id
     * @return
     */
    @POST("jiguang/update/code")
    Observable<JgResultBean> getJgRegisteId(@HeaderMap Map<String, String> headers, @Body JgParamBean jgParamBean);

    /**
     *获取消息列表
     * @return
     */
    @GET("message/get/infoList")
    Observable<MessegeListReslutBean> getMessegeList(@HeaderMap Map<String, String> headers, @Query("type") int type,@Query("curPage") int curPage);

    /**
     *获取各个类型未读消息数量
     * @return
     */
    @GET("message/get/noRead/count")
    Observable<MessegeNumResultBean> getMessegeNum(@HeaderMap Map<String, String> headers);

    /**
     *删除指定消息
     * @return
     */
    @GET("message/remove/info")
    Observable<DeleteMessegeResultBean> getMessegeDelete(@HeaderMap Map<String, String> headers,@Query("messageId") int messageId);

    /**
     *获取直播TOKEN
     * @return
     */
    @GET("yingshi/getToken")
    Observable<VideoAppkeyResultBean> getVideoAppKey(@HeaderMap Map<String, String> headers);

    /**
     *门店详情
     * @return
     */
    @GET("shop/store/detail")
    Observable<OfflineStoreInfoResultBean> getOfflinestoreInfo(@HeaderMap Map<String, String> headers);

    /**
     *首页我们的牧场列表
     * @return
     */
    @GET("pasture/home")
    Observable<HomeParstureResultBean> getHomeParsture(@HeaderMap Map<String, String> headers);

    /**
     *获取首页资讯信息接口
     * @return
     */
    @GET("information/firstPage/list")
    Observable<HomeParstureMessegeResultBean> getHomeParstureMessege(@HeaderMap Map<String, String> headers);

    /**
     *获取更多资讯信息
     * @return
     */
    @GET("information/moreInfo/list")
    Observable<HomeParstureMessegeResultBean> getHomeParstureMoreMessege(@HeaderMap Map<String, String> headers,@Query("currentPage") int currentPage);
    /**
     *获取首页养牛方案列表
     * @return
     */
    @GET("adopt/scheme/firstPage/list")
    Observable<HomeBuyCowPlanResultBean> getHomeBuyCowPlay(@HeaderMap Map<String, String> headers);

}
