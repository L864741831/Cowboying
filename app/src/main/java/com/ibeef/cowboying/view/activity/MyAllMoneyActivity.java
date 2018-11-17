package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.IncomeInfoBase;
import com.ibeef.cowboying.bean.IncomeInfoResultBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.IncomeInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class MyAllMoneyActivity extends BaseActivity implements IncomeInfoBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.all_money_id)
    TextView allMoneyId;
    @Bind(R.id.add_money_id)
    TextView addMoneyId;
    @Bind(R.id.my_wallet_show)
    TextView myWalletShow;
    @Bind(R.id.my_wallet_id)
    TextView myWalletId;
    @Bind(R.id.white_money_show)
    TextView whiteMoneyShow;
    @Bind(R.id.white_money_id)
    TextView whiteMoneyId;
    @Bind(R.id.get_money_id)
    TextView getMoneyId;
    @Bind(R.id.get_money_record_id)
    TextView getMoneyRecordId;
    @Bind(R.id.char_id)
    LineChart chart;
    @Bind(R.id.yesterday_money_rv)
    TextView yesterdayMoneyRv;
    @Bind(R.id.yesterday_money_id)
    TextView yesterdayMoneyId;
    @Bind(R.id.add_money_rv)
    TextView addMoneyRv;

    private List<Integer> integery;
    private IncomeInfoPresenter incomeInfoPresenter;
    private String token;
    private IncomeInfoResultBean incomeInfeResultBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_money);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        incomeInfoPresenter=new IncomeInfoPresenter(this);
        integery = new ArrayList<>();
        //不显示边界
        chart.setDrawBorders(false);
        chart.getDescription().setEnabled(false);

        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        chart.invalidate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        incomeInfoPresenter.getMoneyInfo(reqData);
    }

    private void setData(final IncomeInfoResultBean incomeInfeResultBean) {

        //设置数据
        ArrayList<Entry> values = new ArrayList<>();
        List<BigDecimal> doubleList=new ArrayList<>();

        for (int i = 0; i < incomeInfeResultBean.getBizData().getNearSevenDaysIncome().size(); i++) {
            values.add(new Entry(i, incomeInfeResultBean.getBizData().getNearSevenDaysIncome().get(i).getAmount().floatValue()));
            doubleList.add(incomeInfeResultBean.getBizData().getNearSevenDaysIncome().get(i).getAmount());
        }

        //得到X轴
        XAxis xAxis = chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(7, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                CharSequence format = incomeInfeResultBean.getBizData().getNearSevenDaysIncome().get(IValue).getDate();
                return format.toString();
            }
        });
        xAxis.setTextSize(8);

        //得到Y轴
        YAxis yAxis = chart.getAxisLeft();
        YAxis rightYAxis = chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
//        yAxis.setLabelCount(num, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        if(Collections.max(doubleList).floatValue()==0){
            yAxis.setAxisMaximum(1);
        }else {
            yAxis.setAxisMaximum(Collections.max(doubleList).floatValue());
        }


        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });
        //一个LineDataSet就是一条线
        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setDrawFilled(true);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setCircleRadius(1.5f);
            set1.setCircleColor(getResources().getColor(R.color.skyblue));
            set1.setHighLightColor(getResources().getColor(R.color.bule_7d));
            set1.setColor(getResources().getColor(R.color.skyblue));
            set1.setFillColor(getResources().getColor(R.color.bule_7d));
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            //是否绘制线条上的文字
            data.setDrawValues(false);
            // set data
            chart.setData(data);
        }
    }

    @OnClick({R.id.back_id, R.id.all_money_id, R.id.yesterday_money_rv,R.id.add_money_rv, R.id.my_wallet_show, R.id.white_money_show, R.id.get_money_id, R.id.get_money_record_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.all_money_id:
                //总资产
               startActivity(AssetDetailActivity.class);
                break;
            case R.id.add_money_rv:
                //累计收益
                Intent intent1=new Intent(MyAllMoneyActivity.this,YesterdayIncomeActivity.class);
                intent1.putExtra("isYesterday",false);
                startActivity(intent1);
                break;
            case R.id.yesterday_money_rv:
                //昨日收益
                Intent intent=new Intent(MyAllMoneyActivity.this,YesterdayIncomeActivity.class);
                intent.putExtra("isYesterday",true);
                startActivity(intent);
                break;
            case R.id.my_wallet_show:
                //我的钱包
                startActivity(WalletDetailActivity.class);
                break;
            case R.id.white_money_show:
                break;
            case R.id.get_money_id:
                //钱包提现
                if(!SDCardUtil.isNullOrEmpty(incomeInfeResultBean)){
                    Intent intent2=new Intent(MyAllMoneyActivity.this,CashWithdrawActivity.class);
                    intent2.putExtra("withdrawmoney","钱包余额："+incomeInfeResultBean.getBizData().getWalletBalance());
                    startActivity(intent2);
                }
                break;
            case R.id.get_money_record_id:
                //提现记录
                startActivity(CashDepositActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getMoneyInfo(IncomeInfoResultBean incomeInfeResultBean) {

        if("000000".equals(incomeInfeResultBean.getCode())){
            this.incomeInfeResultBean=incomeInfeResultBean;
            allMoneyId.setText(incomeInfeResultBean.getBizData().getTotalAssets()+">");
            yesterdayMoneyId.setText(incomeInfeResultBean.getBizData().getYesterdayIncome()+"");
            addMoneyId.setText(incomeInfeResultBean.getBizData().getCumulativeIncome()+"");
            myWalletId.setText("￥"+incomeInfeResultBean.getBizData().getWalletBalance());
            if(!SDCardUtil.isNullOrEmpty(incomeInfeResultBean.getBizData().getCridetBalance())){
                whiteMoneyId.setText("￥"+incomeInfeResultBean.getBizData().getCridetBalance());
            }
            setData(incomeInfeResultBean);
        }else {
            showToast(incomeInfeResultBean.getMessage());
        }
    }

    @Override
    public void getWalletRecord(WalletRecordResultBean walletRecordResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if (incomeInfoPresenter!=null){
            incomeInfoPresenter.detachView();
        }
        super.onDestroy();
    }
}
