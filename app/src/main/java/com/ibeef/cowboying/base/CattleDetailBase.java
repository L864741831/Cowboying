package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AdoptInfosResultBean;
import com.ibeef.cowboying.bean.CattleDetailResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 牛只详请
 * @package com.ranhan.cowboying.base
 **/
public class CattleDetailBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCattleDetail(CattleDetailResultBean cattleDetailResultBean);
        void getAdoptInfos(AdoptInfosResultBean adoptInfosResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getCattleDetail(Map<String, String> headers,int cattleId);
        void getAdoptInfos(Map<String, String> headers,String code, Integer schemeId,Integer currentPage);
    }

    public interface IModel {
        Subscription getCattleDetail(Map<String, String> headers,int cattleId, ResponseCallback<CattleDetailResultBean> callback);
        Subscription getAdoptInfos(Map<String, String> headers,String code, Integer schemeId,Integer currentPage, ResponseCallback<AdoptInfosResultBean> callback);
    }
}
