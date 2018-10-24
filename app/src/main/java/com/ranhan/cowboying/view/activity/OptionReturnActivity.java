package com.ranhan.cowboying.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kyleduo.switchbutton.SwitchButton;
import com.ranhan.cowboying.R;
import com.ranhan.cowboying.adapter.OptionReturnImgAdapter;
import com.ranhan.cowboying.utils.CleanDataUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 意见反馈
 */
public class OptionReturnActivity extends BaseActivity {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_opinion)
    EditText etOpinion;
    @Bind(R.id.show_num_id)
    TextView showNumId;
    @Bind(R.id.ry_img_id)
    RecyclerView ryImgId;
    @Bind(R.id.btn_id)
    ImageView btnId;
    @Bind(R.id.me_option_btn)
    ImageView meOptionBtn;
    private OptionReturnImgAdapter optionReturnImgAdapter;
    private List<BaseBean> beanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_return);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("意见反馈");
        ryImgId.setLayoutManager(new GridLayoutManager(this,4));
        ryImgId.setHasFixedSize(true);
        ryImgId.setNestedScrollingEnabled(false);
        beanList=new ArrayList<>();
        for (int i=0;i<10;i++){
            BaseBean baseBean=new BaseBean();
            baseBean.setMessage("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
            beanList.add(baseBean);
        }

        optionReturnImgAdapter=new OptionReturnImgAdapter(this,beanList,R.layout.option_return_item);
        ryImgId.setAdapter(optionReturnImgAdapter);
        optionReturnImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.upload_img:
                        if(position==0){
                            Toast.makeText(OptionReturnActivity.this,"位置一",Toast.LENGTH_SHORT).show();
                        }
                       break;
                    case R.id.close_id:

                        break;
                    default:
                        break;
                }
            }
        });

        etOpinion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.back_id, R.id.btn_id, R.id.me_option_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_id:
                if(TextUtils.isEmpty(etOpinion.getText().toString().trim())){
                    Toast.makeText(OptionReturnActivity.this,"请输入您的意见！",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.me_option_btn:
                startActivity(MyFeedbackActivity.class);
                break;
            default:
                break;
        }
    }

}
