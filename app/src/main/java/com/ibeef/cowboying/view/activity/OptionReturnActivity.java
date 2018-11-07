package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.OptionReturnImgAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.GetOssImgBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.OssResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.ibeef.cowboying.presenter.GetOssImgPresenter;
import com.ibeef.cowboying.utils.Md5Utils;
import com.ibeef.cowboying.utils.SDCardUtil;
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
public class OptionReturnActivity extends BaseActivity implements FeedbackBase.IView ,GetOssImgBase.IView {
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
    private GetOssImgPresenter getOssImgPresenter;
    private ClientConfiguration conf = null;
    private OSS oss = null;
    private OssResultBean ossResultBean;
    private  List<String> photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_return);
        ButterKnife.bind(this);
        initDialog();
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("意见反馈");
        ryImgId.setLayoutManager(new GridLayoutManager(this, 4));
        stringList = new ArrayList<>();
        stringList.add("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
        optionReturnImgAdapter = new OptionReturnImgAdapter(this, stringList, R.layout.option_return_item);
        ryImgId.setAdapter(optionReturnImgAdapter);
        optionReturnImgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.upload_img:
                        if (position == 0) {
                            if (stringList.size() >= 6) {
                                showToast("最多只能上传五张图片");
                                return;
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                            } else {
                                callGallery();
                            }
                        } else {
                            return;
                        }
                        break;
                    case R.id.close_id:
                        Toast.makeText(OptionReturnActivity.this, "删除成功~", Toast.LENGTH_SHORT).show();
                        stringList.remove(position);
                        showNumId.setText("上传图片（" + (stringList.size() - 1) + "/5）");
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
        getOssImgPresenter=new GetOssImgPresenter(this);
        getOssImgPresenter.getOssImg(getVersionCodes());

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
                if(stringList.size()>0){
                    stringList.remove(0);
                }
                submitFeedbackParamBean.setImageList(stringList);
                if (!TextUtils.isEmpty(token)) {
                    feedbackPresenter.getSubmitFeedback(reqData,submitFeedbackParamBean);
                }
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
    private void callGallery() {
        PhotoPicker.builder()
                .setPhotoCount(5)
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
                if (requestCode == 1) {
                    //处理调用系统图库
                } else if (requestCode == PhotoPicker.REQUEST_CODE) {
                    //异步方式插入图片
                    stringList.clear();
                    stringList.add("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
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
        photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
        showLoadings();
        if(TextUtils.isEmpty(token)){
            startActivity(new Intent(OptionReturnActivity.this,LoginActivity.class));
        }else {
            if(!SDCardUtil.isNullOrEmpty(ossResultBean)){
                if(!SDCardUtil.isNullOrEmpty(ossResultBean.getBizData())){
                    ossUpload(photos,ossResultBean.getBizData().getBucketName(),ossResultBean.getBizData().getFolder());
                }
            }
        }
    }

    /**
     * 调用这个方法之前必须先设置accessKeyId，accessKeySecret，securityToken;
     */
    public  void initOss( String accessKeyId,
                          String accessKeySecret,
                          String securityToken,String Endpoint){
        conf = new ClientConfiguration();
        conf.setConnectionTimeout(5*60*1000);
        conf.setSocketTimeout(5*60*1000);
        conf.setMaxConcurrentRequest(5);
        conf.setMaxErrorRetry(2);
        OSSLog.enableLog();

        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                accessKeyId, accessKeySecret,
                securityToken
        );

        oss = new OSSClient(OptionReturnActivity.this,Endpoint,credentialProvider,conf);
    }

    /**
     * 阿里云OSS上传（默认是异步多文件上传）
     * @param urls
     */
    private void ossUpload(final List<String> urls, final String BucketName, final String Folder) {
        if (urls.size() <= 0) {
            handler.sendEmptyMessage(0);
            // 文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
            return;// 这个return必须有，否则下面报越界异常，原因自己思考下哈
        }
        final String url = urls.get(0);
        if (TextUtils.isEmpty(url)) {
            urls.remove(0);
            // url为空就没必要上传了，这里做的是跳过它继续上传的逻辑。
            ossUpload(urls,BucketName,Folder);
            return;
        }

        File file = new File(url);
        if (null == file || !file.exists()) {
            urls.remove(0);
            // 文件为空或不存在就没必要上传了，这里做的是跳过它继续上传的逻辑。
            ossUpload(urls,BucketName,Folder);
            return;
        }
        // 文件后缀
        String fileSuffix = "";
        if (file.isFile()) {
            // 获取文件后缀名
            fileSuffix = file.getName().substring(file.getName().lastIndexOf("."));
        }
        // 文件标识符objectKey
        final String objectKey = Folder + Md5Utils.stringToMD5(Md5Utils.getFileName(url)) + fileSuffix;
        Log.e(Constant.TAG,objectKey+"?????????");
        // 下面3个参数依次为bucket名，ObjectKey名，上传文件路径
        PutObjectRequest put = new PutObjectRequest(BucketName, objectKey, url);

        // 设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                // 进度逻辑
            }
        });
        // 异步上传
        oss.asyncPutObject(put,
                new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                    @Override
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) { // 上传成功
                        urls.remove(0);
                        stringList.add(objectKey);
                        // 递归同步效果
                        ossUpload(urls,BucketName,Folder);
                    }

                    @Override
                    public void onFailure(PutObjectRequest request, ClientException clientExcepion,
                                          ServiceException serviceException) { // 上传失败
                        // 请求异常
                        if (clientExcepion != null) {
                            // 本地异常如网络异常等
                            clientExcepion.printStackTrace();
                        }
                        if (serviceException != null) {
                            // 服务异常
                            Log.e("ErrorCode", serviceException.getErrorCode());
                            Log.e("RequestId", serviceException.getRequestId());
                            Log.e("HostId", serviceException.getHostId());
                            Log.e("RawMessage", serviceException.getRawMessage());
                        }
                    }
                });
    }
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Toast.makeText(OptionReturnActivity.this,"图片上传成功！",Toast.LENGTH_SHORT).show();
                showNumId.setText("上传图片（"+(stringList.size()-1)+"/5）");
                optionReturnImgAdapter.notifyDataSetChanged();
                dismissLoading();
            }

        }
    };


    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getOssImg(OssResultBean ossResultBean) {
        if("000000".equals(ossResultBean.getCode())){
            this.ossResultBean=ossResultBean;
            initOss(ossResultBean.getBizData().getAccessKeyId(),ossResultBean.getBizData().getAccessKeySecret(),ossResultBean.getBizData().getSecurityToken(),ossResultBean.getBizData().getEndpoint());
        }else {
            showToast(ossResultBean.getMessage());
        }

    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {
    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {
        if("000000".equals(submitFeedbackResultBean.getCode())){
            showToast("提交成功");
        }else {
            showToast(submitFeedbackResultBean.getMessage());
        }
        finish();

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void getUploadImg(MdUploadImgBean mdUploadImgBean) {
    }

    @Override
    protected void onDestroy() {
        if (feedbackPresenter != null) {
            feedbackPresenter.detachView();
        }
        super.onDestroy();
    }
}