package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CreatSellCowsParamBean;
import com.ibeef.cowboying.bean.CreatSellCowsResultBean;
import com.ibeef.cowboying.bean.SellCowsResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 卖牛
 * @package com.ranhan.cowboying.base
 **/
public class SellCowsBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getSellCows(SellCowsResultBean sellCowsResultBean);
        void getCreatSellCows(CreatSellCowsResultBean creatSellCowsResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getSellCows(Map<String, String> headers,String orderId);
        void getCreatSellCows(Map<String, String> headers,CreatSellCowsParamBean creatSellCowsParamBean);
    }

    public interface IModel {
        Subscription getSellCows(Map<String, String> headers,String orderId, ResponseCallback<SellCowsResultBean> callback);
        Subscription getCreatSellCows(Map<String, String> headers, CreatSellCowsParamBean creatSellCowsParamBean, ResponseCallback<CreatSellCowsResultBean> callback);
    }
}
