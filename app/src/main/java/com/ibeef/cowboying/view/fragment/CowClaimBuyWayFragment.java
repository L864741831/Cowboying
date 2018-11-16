package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.SchemeDetailReultBean;

import java.text.NumberFormat;

import rxfamily.view.BaseFragment;

public class CowClaimBuyWayFragment extends BaseFragment {

    private String des;
    private SchemeDetailReultBean schemeDetailReultBean;
    private TextView vip_level_txt_id,level_vip_txt_id,percent_txt_id,percent_way_txt_id,minlevle_txt_id,minpercent_txt_id;
    private SeekBar seekbarId;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        vip_level_txt_id=view.findViewById(R.id.vip_level_txt_id);
        level_vip_txt_id=view.findViewById(R.id.level_vip_txt_id);
        percent_txt_id=view.findViewById(R.id.percent_txt_id);
        percent_way_txt_id=view.findViewById(R.id.percent_way_txt_id);
        minlevle_txt_id=view.findViewById(R.id.minlevle_txt_id);
        minpercent_txt_id=view.findViewById(R.id.minpercent_txt_id);
        seekbarId=view.findViewById(R.id.seekbar_id);

        vip_level_txt_id.setText("· 第"+schemeDetailReultBean.getBizData().getCode()+"期 ·");
        level_vip_txt_id.setText("· VIP"+schemeDetailReultBean.getBizData().getVipLevel()+" ·");
        percent_txt_id.setText(schemeDetailReultBean.getBizData().getExpectRate());
        if("1".equals(schemeDetailReultBean.getBizData().getType())){
            percent_way_txt_id.setText("T+7");
        }else   if("2".equals(schemeDetailReultBean.getBizData().getType())){
            percent_way_txt_id.setText(schemeDetailReultBean.getBizData().getLockMonths()+"月");
        }
        minlevle_txt_id.setText(schemeDetailReultBean.getBizData().getPrice()+"起投");

        int num1=(schemeDetailReultBean.getBizData().getTotalStock()-schemeDetailReultBean.getBizData().getStock());
        int num2=schemeDetailReultBean.getBizData().getTotalStock();
        // 创建一个数值格式化对象

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) num1 / (float) num2 * 100);
        minpercent_txt_id.setText("进度"+result+"%");

        seekbarId.setProgress(Integer.parseInt(result));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cow_cliam_bugway;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            schemeDetailReultBean = (SchemeDetailReultBean) args.getSerializable("schemeDetailReultBean");
        }
    }
    public static CowClaimBuyWayFragment newInstance(SchemeDetailReultBean schemeDetailReultBean) {

        CowClaimBuyWayFragment newFragment = new CowClaimBuyWayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("schemeDetailReultBean", schemeDetailReultBean);
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
