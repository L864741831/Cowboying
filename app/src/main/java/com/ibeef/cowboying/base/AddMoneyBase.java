package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.YesterdayIncomeResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 累计收益
 * @package com.ranhan.cowboying.base
 **/
public class AddMoneyBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getAddMoney(AddMoneyResultBean accountRegisterResultBean);
        void getYesterdayIncome(YesterdayIncomeResultBean yesterdayIncomeResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getAddMoney(Map<String, String> headers,int currentPage,String interestType,String incomeType);
        void getYesterdayIncome(Map<String, String> headers,String incomeType);
    }

    public interface IModel {
        Subscription getAddMoney(Map<String, String> headers,int currentPage, String interestType,String incomeType,ResponseCallback<AddMoneyResultBean> callback);
        Subscription getYesterdayIncome(Map<String, String> headers,String incomeType, ResponseCallback<YesterdayIncomeResultBean> callback);
    }
}
