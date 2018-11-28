package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseActivity;


public class MyCowsProgressDialog extends AppCompatActivity implements View.OnClickListener, MyCowsOrderBase.IView {

    @Bind(R.id.show_title_id)
    TextView showTitleId;
    @Bind(R.id.iv_circle)
    ImageView ivCircle;
    @Bind(R.id.tv_detail)
    TextView tvDetail;
    @Bind(R.id.vw_1)
    View vw1;
    @Bind(R.id.iv_3)
    ImageView iv3;
    @Bind(R.id.vw_2)
    View vw2;
    @Bind(R.id.iv4)
    ImageView iv4;
    @Bind(R.id.vw_3)
    View vw3;
    @Bind(R.id.vw_4)
    View vw4;
    @Bind(R.id.iv_5)
    ImageView iv5;
    @Bind(R.id.iv_6)
    ImageView iv6;
    @Bind(R.id.ll_dingqi)
    LinearLayout llDingqi;
    @Bind(R.id.rvs_id)
    LinearLayout rvsId;
    @Bind(R.id.tv_lock_day)
    TextView tvLockDay;
    @Bind(R.id.tv_lock_month)
    TextView tvLockMonth;
    private String status;
    private int LockMonths;
    private String UnlockTime;
    private String token;
    private LinearLayout linearLayout;
    private MyCowsOrderPresenter myCowsOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows_progress_dialog);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        status = getIntent().getStringExtra("status");
        LockMonths = getIntent().getIntExtra("LockMonths",0);
        UnlockTime = getIntent().getStringExtra("UnlockTime");
        Log.i("htht", "status:::::::: " + status);
        linearLayout = findViewById(R.id.rvs_id);
        linearLayout.setOnClickListener(this);

//    订单状态（1:未付款；2：已付款未分配--；3：已分配--；4：已分配锁定期中---；5：出售中----；6:交易完成-----；9；交易关闭）
        if ("2".equals(status)) {

        } else if ("3".equals(status)) {
            vw1.setBackgroundResource(R.color.colorGold_1);
            vw2.setBackgroundResource(R.color.colorGold_1);
            vw3.setBackgroundResource(R.color.colorGold_1);
            iv3.setBackgroundResource(R.mipmap.ischecked);
            iv4.setBackgroundResource(R.mipmap.ischecked);
            iv5.setBackgroundResource(R.mipmap.ischecked_now);
        } else if ("4".equals(status)) {
            tvLockDay.setText(LockMonths+"个月锁定期");
            tvLockMonth.setText("预计"+UnlockTime+"锁定结束");
            vw1.setBackgroundResource(R.color.colorGold_1);
            vw2.setBackgroundResource(R.color.colorGold_1);
            vw3.setBackgroundResource(R.color.colorGold_1);
            vw4.setVisibility(View.VISIBLE);
            iv3.setBackgroundResource(R.mipmap.ischecked);
            vw4.setBackgroundResource(R.color.colorGold_1);
            iv4.setBackgroundResource(R.mipmap.ischecked);
            iv5.setBackgroundResource(R.mipmap.ischecked);
            llDingqi.setVisibility(View.VISIBLE);
        } else if ("5".equals(status)) {
            vw1.setBackgroundResource(R.color.colorGold_1);
            vw2.setBackgroundResource(R.color.colorGold_1);
            vw3.setBackgroundResource(R.color.colorGold_1);
            iv3.setBackgroundResource(R.mipmap.ischecked);
            iv4.setBackgroundResource(R.mipmap.ischecked);
            iv5.setBackgroundResource(R.mipmap.ischecked_now);
        } else if ("6".equals(status)) {
            vw1.setBackgroundResource(R.color.colorGold_1);
            vw2.setBackgroundResource(R.color.colorGold_1);
            vw3.setBackgroundResource(R.color.colorGold_1);
            iv3.setBackgroundResource(R.mipmap.ischecked);
            iv4.setBackgroundResource(R.mipmap.ischecked);
            iv5.setBackgroundResource(R.mipmap.ischecked_now);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rvs_id:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {

    }

    @Override
    public void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean) {

    }

    @Override
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean msg) {
        if ("000000".equals(msg.getCode())) {
            finish();
            Toast.makeText(this, "取消订单成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("/adopt/order/cancel", "msg.getMessage()" + msg.getMessage());
        }
    }

    @Override
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {

    }
}