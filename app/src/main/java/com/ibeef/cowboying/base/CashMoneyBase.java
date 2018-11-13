package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CashMoneyParamBean;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;
import com.ibeef.cowboying.bean.CashMoneyResultBean;

import java.util.Map;

import retrofit2.http.Body;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 提现
 * @package com.ranhan.cowboying.base
 **/
public class CashMoneyBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCashMoney(CashMoneyResultBean cashMoneyResultBean);
        void getCashMoneyRecord(CashMoneyRecordResultBean cashMoneyRecordResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getCashMoney(Map<String, String> headers, CashMoneyParamBean cashMoneyParamBean);
        void getCashMoneyRecord(Map<String, String> headers,int currentPage);
    }

    public interface IModel {
        Subscription getCashMoney(Map<String, String> headers, CashMoneyParamBean cashMoneyParamBean, ResponseCallback<CashMoneyResultBean> callback);
        Subscription getCashMoneyRecord(Map<String, String> headers, int currentPage,ResponseCallback<CashMoneyRecordResultBean> callback);
    }
}
