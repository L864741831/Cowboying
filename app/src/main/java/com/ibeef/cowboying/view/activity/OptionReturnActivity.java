package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.OptionReturnImgAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 意见反馈
 */
public class OptionReturnActivity extends BaseActivity implements FeedbackBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_opinion)
    EditText etOpinion;
    @Bind(R.id.show_num_id)
    TextView showNumId;
    @Bind(R.id.txt_num_id)
    TextView txtNumId;
    @Bind(R.id.ry_img_id)
    RecyclerView ryImgId;
    @Bind(R.id.btn_id)
    ImageView btnId;
    @Bind(R.id.me_option_btn)
    ImageView meOptionBtn;
    private OptionReturnImgAdapter optionReturnImgAdapter;
    private List<String> stringList;
    private FeedbackPresenter feedbackPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_return);
        ButterKnife.bind(this);
        initDialog();
        init();
    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("意见反馈");
        ryImgId.setLayoutManager(new GridLayoutManager(this,4));
        stringList=new ArrayList<>();
        stringList.add("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
        optionReturnImgAdapter=new OptionReturnImgAdapter(this,stringList,R.layout.option_return_item);
        ryImgId.setAdapter(optionReturnImgAdapter);
        optionReturnImgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.upload_img:
                        if(position==0){
                            showLoadings();
                            stringList.add("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
                            optionReturnImgAdapter.notifyDataSetChanged();
                            dismissLoading();

                        }
                        break;
                    case R.id.close_id:
                        Toast.makeText(OptionReturnActivity.this,"删除",Toast.LENGTH_SHORT).show();
                        stringList.remove(position);
                        optionReturnImgAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });


        etOpinion.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp =charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionStart = etOpinion.getSelectionStart();
                selectionEnd = etOpinion.getSelectionEnd();
                txtNumId.setText(temp.length()+"/200");
                if(temp.length()>200){
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    etOpinion.setText(s);
                    //设置光标在最后
                    etOpinion.setSelection(tempSelection);
                    showToast("意见不能超过200个字");
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

        feedbackPresenter=new FeedbackPresenter(this);
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
                Map<String, String> reqData = new HashMap<>();
                reqData.put("token",token);
                reqData.put("version",getVersionCodes());
                SubmitFeedbackParamBean submitFeedbackParamBean=new SubmitFeedbackParamBean();
                submitFeedbackParamBean.setContent(etOpinion.getText().toString().trim());
                submitFeedbackParamBean.setImageList(stringList);
                feedbackPresenter.getSubmitFeedback(reqData,submitFeedbackParamBean);
                break;
            case R.id.me_option_btn:
                startActivity(MyFeedbackActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {

    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {
        if("000000".equals(submitFeedbackResultBean.getCode())){

        }else {
            showToast(submitFeedbackResultBean.getMessage());
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(feedbackPresenter != null){
            feedbackPresenter.detachView();
        }
        super.onDestroy();

    }
}
