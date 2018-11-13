package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.IncomeInfoResultBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;

import java.util.Map;

import retrofit2.http.Query;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 总资产详情 钱包流水
 * @package com.ranhan.cowboying.base
 **/
public class IncomeInfoBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getMoneyInfo(IncomeInfoResultBean incomeInfeResultBean);
        void getWalletRecord(WalletRecordResultBean walletRecordResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getMoneyInfo(Map<String, String> headers);
        void getWalletRecord(Map<String, String> headers,int currentPage, String amountType);
    }

    public interface IModel {
        Subscription getMoneyInfo(Map<String, String> headers, ResponseCallback<IncomeInfoResultBean> callback);
        Subscription getWalletRecord(Map<String, String> headers, int currentPage, String amountType,ResponseCallback<WalletRecordResultBean> callback);
    }
}
