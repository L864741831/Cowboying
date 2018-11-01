package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.OptionReturnImgAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;
import rxfamily.bean.BaseBean;
import rxfamily.utils.PermissionsUtils;
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
    private MdUploadImgBean mdUploadImgBean;

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
        optionReturnImgAdapter=new OptionReturnImgAdapter(this,stringList,R.layout.option_return_item);
        ryImgId.setAdapter(optionReturnImgAdapter);
        optionReturnImgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.upload_img:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                            rx.Observable<Boolean> grantObservable = PermissionsUtils.getCameraGrant(OptionReturnActivity.this);
                            grantObservable.subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean granted) {
                                    if (granted) {
                                        callGallery();
                                    } else {
                                        PermissionsUtils.showPermissionDeniedDialog(OptionReturnActivity.this, false);
                                    }
                                }
                            });
                        }else {
                            callGallery();
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
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                SubmitFeedbackParamBean submitFeedbackParamBean=new SubmitFeedbackParamBean();
                submitFeedbackParamBean.setContent(etOpinion.getText().toString().trim());
                submitFeedbackParamBean.setImageList(stringList);
                feedbackPresenter.getSubmitFeedback(reqData,submitFeedbackParamBean);
                break;
            case R.id.me_option_btn:
                if(!TextUtils.isEmpty(token)){
                    startActivity(MyFeedbackActivity.class);
                }else {
                    startActivity(LoginActivity.class);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 调用图库选择
     */
    private void callGallery(){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1){
                    //处理调用系统图库
                } else if (requestCode == PhotoPicker.REQUEST_CODE){
                    //异步方式插入图片
                    insertImagesSync(data);
                }
            }
        }
    }

    /**
     * 异步方式插入图片
     * @param data
     */
    private void insertImagesSync(final Intent data){
        ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
        ArrayList<File> files=new ArrayList<>();
        //可以同时插入多张图片
        for (String imagePath : photos) {
            //imagePath<File对象、或 文件路径、或 字节数组>
            File file = new File(imagePath);
            files.add(file);
//            multipartBody = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("imageFile",file.getName() , RequestBody.create(MediaType.parse("image/*"), file))
//                    .build();
        }
        Log.i("file/image/upload", "files: :::::"+files.size());
        MultipartBody multipartBody = filesToMultipartBody(files);
        showLoadings();
        if(TextUtils.isEmpty(token)){
            startActivity(new Intent(OptionReturnActivity.this,LoginActivity.class));
        }else {
            Map<String, String> reqData1 = new HashMap<>();
            reqData1.put("Authorization",token);
            reqData1.put("version",getVersionCodes());
            feedbackPresenter.getUploadImg(reqData1, multipartBody);
        }
    }

    private  MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("imageFile", file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
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
//        dismissLoading();
        if("000000".equals(submitFeedbackResultBean.getCode())){
            showToast("提交成功");
            finish();
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
    public void getUploadImg(MdUploadImgBean mdUploadImgBean) {
        this.mdUploadImgBean=mdUploadImgBean;
        dismissLoading();
        if("000000".equals(mdUploadImgBean.getCode())){
            Toast.makeText(OptionReturnActivity.this,"图片上传成功！",Toast.LENGTH_SHORT).show();
//            productImg=mdUploadImgBean.getData().getFileName();
//            RequestOptions options = new RequestOptions()
//                    .skipMemoryCache(true)
//                    //跳过内存缓存
//                    .error(R.mipmap.jzsb)
//                    ;
            stringList.add(mdUploadImgBean.getBizData().get(0).getFilePath());
            optionReturnImgAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(OptionReturnActivity.this,mdUploadImgBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if(feedbackPresenter != null){
            feedbackPresenter.detachView();
        }
        super.onDestroy();

    }
}
