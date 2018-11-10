package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class MyAllMoneyActivity extends BaseActivity {

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
    private final int fillColor = Color.argb(150, 51, 181, 229);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_money);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        integery = new ArrayList<>();
        //不显示边界
        chart.setDrawBorders(false);
        chart.getDescription().setEnabled(false);


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
                CharSequence format = DateFormat.format("MM/dd",
                        System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                return format.toString();
            }
        });

        //得到Y轴
        YAxis yAxis = chart.getAxisLeft();
        YAxis rightYAxis = chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(30);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        int min = 10;
        int max = 99;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        yAxis.setLabelCount(num, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(120);

        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });

        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        chart.invalidate();
        setData(45, 100);
    }

    private void setData(int count, float range) {

        //设置数据
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }
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
                startActivity(CashWithdrawActivity.class);
                break;
            case R.id.get_money_record_id:
                //提现记录
                startActivity(CashDepositActivity.class);
                break;
            default:
                break;
        }
    }
}
