package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.CowManInfoBase;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.CowManInfosResultBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CowManInfoPresenter;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.AdWebviewActivity;
import com.ibeef.cowboying.view.activity.AfterSaleActivity;
import com.ibeef.cowboying.view.activity.BeefStoreHouseActivity;
import com.ibeef.cowboying.view.activity.DiscountCouponActivity;
import com.ibeef.cowboying.view.activity.InviteFriendActivity;
import com.ibeef.cowboying.view.activity.LoginActivity;
import com.ibeef.cowboying.view.activity.MessegeChooseActivity;
import com.ibeef.cowboying.view.activity.MyAllMoneyActivity;
import com.ibeef.cowboying.view.activity.MyContractActivity;
import com.ibeef.cowboying.view.activity.MyCowsActivity;
import com.ibeef.cowboying.view.activity.MyOrderActivity;
import com.ibeef.cowboying.view.activity.OfflineStoreActivity;
import com.ibeef.cowboying.view.activity.PayActivity;
import com.ibeef.cowboying.view.activity.PersonalInformationActivity;
import com.ibeef.cowboying.view.activity.PickUpCodeActivity;
import com.ibeef.cowboying.view.activity.VipCardActivity;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rxfamily.view.BaseFragment;

/**
 * 牛人界面
 */
public class ThreeFragment extends BaseFragment  implements SuperSwipeRefreshLayout.OnPullRefreshListener,UserInfoBase.IView,CowManInfoBase.IView {

    @Bind(R.id.messege_id)
    ImageView messegeId;
    @Bind(R.id.setting_id)
    ImageView settingId;
    CircleImageView headImg;

    ImageView levelId;
    TextView nameId;
    @Bind(R.id.pay_money_codeId)
    LinearLayout payMoneyCodeId;
    @Bind(R.id.please_codeId)
    LinearLayout pleaseCodeId;
    @Bind(R.id.get_goods_codeId)
    LinearLayout getGoodsCodeId;
    @Bind(R.id.all_order_id)
    RelativeLayout allOrderId;
    @Bind(R.id.wait_to_pay_id)
    LinearLayout waitToPayId;
    @Bind(R.id.wait_to_delevery_id)
    LinearLayout waitToDeleveryId;
    @Bind(R.id.wait_get_goods_id)
    LinearLayout waitGetGoodsId;
    @Bind(R.id.wait_push_goods_id)
    LinearLayout waitPushGoodsId;
    @Bind(R.id.offline_store_lv)
    LinearLayout offlineStoreLv;

    TextView cattleNumId;
    @Bind(R.id.me_cattle_rv)
    RelativeLayout meCattleRv;
    @Bind(R.id.cattle_oder_rv)
    RelativeLayout cattleOderRv;

    TextView allMoneyId;
    @Bind(R.id.all_money_rv)
    RelativeLayout allMoneyRv;
    @Bind(R.id.beef_house_rv)
    RelativeLayout beefHouseRv;
    TextView writeMoneyId;
    TextView txt_num_id;

    @Bind(R.id.write_money_rv)
    RelativeLayout writeMoneyRv;

    TextView couponNumId;
    @Bind(R.id.coupon_num_rv)
    RelativeLayout couponNumRv;
    @Bind(R.id.contract_id_rv)
    RelativeLayout contractIdRv;
    @Bind(R.id.invite_friend_id_rv)
    RelativeLayout inviteFriendIdRv;
    @Bind(R.id.tell_us_id_rv)
    RelativeLayout tellUsIdRv;
    @Bind(R.id.nomal_question_rv)
    RelativeLayout nomalQuestionRv;

    private String token;
    private UserInfoPresenter userInfoPresenter;
    private CowManInfoPresenter cowManInfoPresenter;
    SuperSwipeRefreshLayout swipeLy;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        nameId=view.findViewById(R.id.name_id);
        headImg=view.findViewById(R.id.head_img);
        swipeLy=view.findViewById(R.id.swipe_ly);
        writeMoneyId=view.findViewById(R.id.write_money_id);
        cattleNumId=view.findViewById(R.id.cattle_num_id);
        allMoneyId=view.findViewById(R.id.all_money_id);
        couponNumId=view.findViewById(R.id.coupon_num_id);
        levelId=view.findViewById(R.id.level_id);
        txt_num_id=view.findViewById(R.id.txt_num_id);

        swipeLy.setHeaderViewBackgroundColor(getHoldingActivity().getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        userInfoPresenter=new UserInfoPresenter(this);
        cowManInfoPresenter=new CowManInfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        token= Hawk.get(HawkKey.TOKEN);
        if(TextUtils.isEmpty(token)) {
            nameId.setText("口袋牧场");
            headImg.setImageResource(R.mipmap.defaulthead);
        }else {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            userInfoPresenter.getUserInfo(reqData);
            cowManInfoPresenter.getCowManInfos(reqData);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.messege_id, R.id.setting_id, R.id.head_img,R.id.nomal_question_rv,R.id.pay_money_codeId,
            R.id.please_codeId, R.id.get_goods_codeId, R.id.all_order_id, R.id.wait_to_pay_id, R.id.wait_to_delevery_id,
            R.id.wait_get_goods_id, R.id.wait_push_goods_id, R.id.me_cattle_rv, R.id.cattle_oder_rv, R.id.all_money_rv,
            R.id.beef_house_rv, R.id.write_money_rv, R.id.coupon_num_rv, R.id.contract_id_rv, R.id.invite_friend_id_rv,
            R.id.tell_us_id_rv,R.id.after_sale_goods_id,R.id.offline_store_lv})
    public void onViewClicked(View view) {
        Intent intent=new Intent(getHoldingActivity(),MyOrderActivity.class);
        switch (view.getId()) {
            case R.id.messege_id:
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(MessegeChooseActivity.class);
                }
                break;
            case R.id.setting_id:
                //设置界面
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(PersonalInformationActivity.class);
                }
                break;
            case R.id.head_img:
                //个人信息界面
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(PersonalInformationActivity.class);
                }
                break;
            case R.id.pay_money_codeId:
                //付款码
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(PayActivity.class);
                }
                break;
            case R.id.please_codeId:
                //邀请码
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(InviteFriendActivity.class);
                }
                break;
            case R.id.get_goods_codeId:
                //取货码
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(PickUpCodeActivity.class);
                }
                break;
            case R.id.all_order_id:
                //商城订单
                startActivity(MyOrderActivity.class);
                break;
            case R.id.wait_to_pay_id:
                //待付款
                intent.putExtra("index",1);
                startActivity(intent);
                break;
            case R.id.wait_to_delevery_id:
                //待发货
                intent.putExtra("index",2);
                startActivity(intent);
                break;
            case R.id.wait_get_goods_id:
                //待收货
                intent.putExtra("index",3);
                startActivity(intent);
                break;
            case R.id.wait_push_goods_id:
                //待提货
                intent.putExtra("index",4);
                startActivity(intent);
                break;
            case R.id.me_cattle_rv:
                //我的牛只
                startActivity(MyCowsActivity.class);
                break;
            case R.id.cattle_oder_rv:
                //拼牛订单

                break;
            case R.id.offline_store_lv:
                //线下门店
                startActivity(OfflineStoreActivity.class);
                break;
            case R.id.all_money_rv:
                //总资产
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(MyAllMoneyActivity.class);
                }
                break;
            case R.id.beef_house_rv:
                //牛肉仓库
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(BeefStoreHouseActivity.class);
                }
                break;
            case R.id.write_money_rv:
                //会员卡
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(VipCardActivity.class);
                }
                break;
            case R.id.coupon_num_rv:
                //优惠券
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(DiscountCouponActivity.class);
                }
                break;
            case R.id.contract_id_rv:
                //我的合同
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(MyContractActivity.class);
                }
                break;
            case R.id.invite_friend_id_rv:
                //邀请好友
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(InviteFriendActivity.class);
                }
                break;
            case R.id.tell_us_id_rv:
                //联系我们
                Intent intent1=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                intent1.putExtra("url","http://h5.ibeef.vip/aboutUs/index.html");
                intent1.putExtra("title","联系我们");
                startActivity(intent1);
                break;
            case R.id.nomal_question_rv:
                Intent intent2=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                intent2.putExtra("url","http://h5.ibeef.vip/question/faq_index.html");
                intent2.putExtra("title","常见问题");
                startActivity(intent2);
                break;
            case R.id.after_sale_goods_id:
                //售后列表
                startActivity(AfterSaleActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getCowManInfos(CowManInfosResultBean cowManInfosResultBean) {
        if("000000".equals(cowManInfosResultBean.getCode())){

            if (cowManInfosResultBean.getBizData().getMsgCount() > 0) {
                txt_num_id.setVisibility(View.VISIBLE);
                txt_num_id.setText(cowManInfosResultBean.getBizData().getMsgCount() + "");
            } else {
                txt_num_id.setVisibility(View.GONE);
            }

            if (cowManInfosResultBean.getBizData().getMyCattleCount() > 0) {
                cattleNumId.setText(cowManInfosResultBean.getBizData().getMyCattleCount() + "只");
            } else {
                cattleNumId.setText("");
            }

            if(cowManInfosResultBean.getBizData().getMyTotalAssets()>0){
                allMoneyId.setText(cowManInfosResultBean.getBizData().getMyTotalAssets()+"元");
            }else {
                allMoneyId.setText("");
            }

            if (cowManInfosResultBean.getBizData().isHaveVipCard()) {
                writeMoneyId.setText("");
            } else {
                writeMoneyId.setText("未办理");
            }

            if (cowManInfosResultBean.getBizData().getMyCouponCount() > 0) {
                couponNumId.setText(cowManInfosResultBean.getBizData().getMyCouponCount() + "张");
            } else {
                couponNumId.setText("");
            }


        }else {
            showToast(cowManInfosResultBean.getMessage());
        }
    }

    @Override
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {

    }

    @Override
    public void getModifNick(ModifyNickResultBean modifyNickResultBean) {

    }

    @Override
    public void getRealName(RealNameReaultBean realNameReaultBean) {

    }

    @Override
    public void getUserInfo(UserInfoResultBean userInfoResultBean) {
        if("000000".equals(userInfoResultBean.getCode())){
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.meheaddefalut)
                    //加载错误之后的错误图
                    .skipMemoryCache(true)
                    //跳过内存缓存
                    ;
            Hawk.put(HawkKey.userId, userInfoResultBean.getBizData().getUserId()+"");
            Glide.with(this).load(Constant.imageDomain+userInfoResultBean.getBizData().getHeadImage()).apply(options).into(headImg);
            if(SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getNickName())){
                nameId.setText("口袋牧场");
            }else {
                nameId.setText(userInfoResultBean.getBizData().getNickName());
            }

            if ("1".equals(userInfoResultBean.getBizData().getVipLevel())){
                 levelId.setImageResource(R.mipmap.level_1);
            }else if ("2".equals(userInfoResultBean.getBizData().getVipLevel())){
                levelId.setImageResource(R.mipmap.level_2);
            }else if ("3".equals(userInfoResultBean.getBizData().getVipLevel())){
                levelId.setImageResource(R.mipmap.level_3);
            }else if ("4".equals(userInfoResultBean.getBizData().getVipLevel())){
                levelId.setImageResource(R.mipmap.level_4);
            }else if ("5".equals(userInfoResultBean.getBizData().getVipLevel())){
                levelId.setImageResource(R.mipmap.level_5);
            }

        }else {
            Toast.makeText(getHoldingActivity(),userInfoResultBean.getMessage(),Toast.LENGTH_LONG).show();
        }
        swipeLy.setRefreshing(false);
    }

    @Override
    public void isTakePhoeto(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
        if(cowManInfoPresenter!=null){
            cowManInfoPresenter.detachView();
        }
    }

    @Override
    public void onRefresh() {
        token= Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        userInfoPresenter.getUserInfo(reqData);
        cowManInfoPresenter.getCowManInfos(reqData);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
