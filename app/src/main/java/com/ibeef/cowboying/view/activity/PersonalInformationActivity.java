package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.GetOssImgBase;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.ModifyHeadParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickParamBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.OssResultBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.presenter.GetOssImgPresenter;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.CleanDataUtils;
import com.ibeef.cowboying.utils.Md5Utils;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.kyleduo.switchbutton.SwitchButton;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 个人信息界面
 */
public class PersonalInformationActivity extends BaseActivity implements UserInfoBase.IView ,GetOssImgBase.IView,AccountSecurityBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.iv_icon)
    CircleImageView ivIcon;
    @Bind(R.id.see_head)
    RelativeLayout seeHead;
    @Bind(R.id.nickname_txt)
    TextView nicknameTxt;
    @Bind(R.id.nickname_rv)
    RelativeLayout nicknameRv;
    @Bind(R.id.bind_phone_stadus)
    TextView bindPhoneStadus;
    @Bind(R.id.bind_phone_txt)
    TextView bindPhoneTxt;
    @Bind(R.id.bind_phone_rv)
    RelativeLayout bindPhoneRv;
    @Bind(R.id.real_info_stadus)
    TextView realInfoStadus;
    @Bind(R.id.real_info_txt)
    TextView realInfoTxt;
    @Bind(R.id.modify_btn)
    TextView modifyBtn;
    @Bind(R.id.title_txt_id)
    TextView titleTxtId;
    @Bind(R.id.real_info_rv)
    RelativeLayout realInfoRv;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;
    @Bind(R.id.et_write_id)
    EditText etWriteId;
    @Bind(R.id.cancle_img)
    ImageView cancleImg;
    @Bind(R.id.modify_nickname_rv)
    RelativeLayout modifyNicknameRv;
    @Bind(R.id.lv_info_show)
    LinearLayout lvInfoShow;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.switchButton)
    SwitchButton switchButton;
    @Bind(R.id.weixin_stadus)
    ImageView weixinStadus;
    @Bind(R.id.zfb_stadus)
    ImageView zfbStadus;
    @Bind(R.id.phone_stadus)
    ImageView phoneStadus;
    @Bind(R.id.station_txt)
    TextView stationTxt;
    @Bind(R.id.accout_securty_id)
    RelativeLayout accoutSecurtyId;
    @Bind(R.id.goods_addr_rv)
    RelativeLayout goodsAddrRv;
    @Bind(R.id.option_return_rv)
    RelativeLayout optionReturnRv;
    @Bind(R.id.version_code_txt)
    TextView versionCodeTxt;
    @Bind(R.id.release_cache_txt)
    TextView releaseCacheTxt;
    @Bind(R.id.release_cache_rv)
    RelativeLayout releaseCacheRv;
    @Bind(R.id.unlogin_rv)
    TextView unloginRv;
    private String token,isMessege;
    private AccountSecurityPresenter accountSecurityPresenter;
    private boolean isNickname=false;
    private   File file;
    private boolean isTakePhoto=true;
    private UserInfoPresenter userInfoPresenter;
    private String userId;
    private UserInfoResultBean userInfoResultBean;
    private Uri uritempFile;
    private Bitmap head;
    private FileOutputStream b;
    private String imgPath;
    @SuppressLint("SdCardPath")
    private static String path = "/storage/emulated/0/myHead/";
    private boolean isCheck=false,isBindPhone=false;
    private GetOssImgPresenter getOssImgPresenter;
    private ClientConfiguration conf = null;
    private OSS oss = null;
    private OssResultBean ossResultBean;
    private List<String> stringList;
    private String imgUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        init();
        initDialog();
    }

    private void init() {
        info.setText("账号设置");
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 点击恢复按钮后，极光推送服务会恢复正常工作
                    JPushInterface.resumePush(getApplicationContext());
                    Hawk.put(HawkKey.ISMESSEGE,"0");
                }else {
                    // 点击停止按钮后，极光推送服务会被停止掉
                    JPushInterface.stopPush(getApplicationContext());
                    Hawk.put(HawkKey.ISMESSEGE,"1");
                }

            }
        });
        getVersionCode();

        try {
            String totalCacheSize = CleanDataUtils.getTotalCacheSize(this);
            releaseCacheTxt.setText(totalCacheSize);
        } catch (Exception e) {
        }

        initDialog();
        token= Hawk.get(HawkKey.TOKEN);
        userId= Hawk.get(HawkKey.userId);
        userInfoPresenter=new UserInfoPresenter(this);
        getOssImgPresenter=new GetOssImgPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        getOssImgPresenter.getOssImg(reqData);
        stringList=new ArrayList<>();
        isMessege=Hawk.get(HawkKey.ISMESSEGE);
        accountSecurityPresenter=new AccountSecurityPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        userInfoPresenter.getUserInfo(reqData);

        accountSecurityPresenter.getSafeInfo(reqData);
        if("1".equals(isMessege)){
            switchButton.setChecked(false);
        }else {
            switchButton.setChecked(true);
        }
    }

    @OnClick({R.id.back_id, R.id.iv_icon, R.id.nickname_rv, R.id.bind_phone_rv, R.id.real_info_rv, R.id.modify_pwd_rv,R.id.modify_btn,R.id.cancle_img,R.id.modify_nickname_rv,R.id.accout_securty_id,  R.id.option_return_rv, R.id.release_cache_rv,R.id.unlogin_rv,R.id.goods_addr_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.iv_icon:
                ActionSheetDialog();
                break;
            case R.id.nickname_rv:
                etWriteId.setHint("请输入昵称");
                etWriteId.setText("");
                modifyNicknameRv.setVisibility(View.VISIBLE);
                titleTxtId.setText("昵称");
                isNickname=true;
                break;
            case R.id.bind_phone_rv:
                // 是否绑定手机号
                if(!isBindPhone){
                    if(!SDCardUtil.isNullOrEmpty(userInfoResultBean)){
                        if(!SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData())){
                            Intent intent=new Intent(PersonalInformationActivity.this,MobileLoginActivity.class);
                            intent.putExtra("stadus","7");
                            startActivity(intent);
                        }
                    }
                }
                break;
            case R.id.real_info_rv:
                    if(!"0".equals(userInfoResultBean.getBizData().getIsValidate())){
                        //已实名认证
                        Intent intent = new Intent(this, CertificationActivity.class);
                        intent.putExtra("realName",userInfoResultBean.getBizData().getRealName());
                        intent.putExtra("flag","1");
                        intent.putExtra("realCardNo",userInfoResultBean.getBizData().getRealCardNo());
                        startActivity(intent);
                    }else {
                        //未实名认证
                        Intent intent = new Intent(this, CertificationActivity.class);
                        intent.putExtra("flag","2");
                        intent.putExtra("userId",userInfoResultBean.getBizData().getUserId()+"");
                        intent.putExtra("mobile",userInfoResultBean.getBizData().getMobile());
                        startActivity(intent);
                    }
                break;
            case R.id.modify_pwd_rv:
                startActivity(ModifyPwdActivity.class);
                break;
            case R.id.modify_btn:
                if(isNickname){
                    // 昵称
                    if(TextUtils.isEmpty(etWriteId.getText().toString().trim())){
                        showToast("修改昵称不能为空~");
                        return;
                    }
                    if(etWriteId.getText().toString().trim().length()>12){
                        showToast("修改的昵称不能超过12个字哟~");
                        return;
                    }
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    ModifyNickParamBean modifyNickParamBean=new ModifyNickParamBean();
                    modifyNickParamBean.setNiceName(etWriteId.getText().toString().trim());
                    userInfoPresenter.getModifNick(reqData,modifyNickParamBean);
                }
                modifyNicknameRv.setVisibility(View.GONE);
                break; 
            case R.id.cancle_img:
                etWriteId.setText("");
                break;
            case R.id.modify_nickname_rv:
                modifyNicknameRv.setVisibility(View.GONE);
                break;
            case R.id.goods_addr_rv:
                startActivity(AddressActivity.class);
                break;
            case R.id.accout_securty_id:
                startActivity(AccoutSecurityActivity.class);
                break;
            case R.id.option_return_rv:
                startActivity(OptionReturnActivity.class);
                break;
            case R.id.release_cache_rv:
                try {
                    showLoadings();
                    if(CleanDataUtils.clearAllCache(PersonalInformationActivity.this)){
                        Toast.makeText(PersonalInformationActivity.this,"缓存清除成功~",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(PersonalInformationActivity.this,"缓存清除失败~",Toast.LENGTH_SHORT).show();
                    }
                    String size = CleanDataUtils.getTotalCacheSize(PersonalInformationActivity.this);
                    releaseCacheTxt.setText(size);
                    dismissLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unlogin_rv:
                Hawk.put(HawkKey.TOKEN, "");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private void ActionSheetDialog() {
        final String[] stringItems = {"拍照", "从相册选择"};

        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false)
                .titleTextSize_SP(14.5f)
                .itemTextColor(ContextCompat.getColor(PersonalInformationActivity.this,R.color.black)).cancelText(ContextCompat.getColor(PersonalInformationActivity.this,R.color.red))
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        file = new File(Environment.getExternalStorageDirectory(), "head.jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }

                        rx.Observable<Boolean> grantObservable = PermissionsUtils.getCameraGrant(PersonalInformationActivity.this);
                        grantObservable.subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {
                                    isTakePhoto=true;
                                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    /*获取当前系统的android版本号*/
                                    int currentapiVersion = Build.VERSION.SDK_INT;
                                    if (currentapiVersion<24){
                                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                        startActivityForResult(intent2, 2);
                                        // 采用ForResult打开
                                    }else {
                                        useCamera(intent2);
                                    }
                                } else {
                                    PermissionsUtils.showPermissionDeniedDialog(PersonalInformationActivity.this, false);
                                }
                            }
                        });
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            rx.Observable<Boolean> grantObservable1 = PermissionsUtils.getWrite(PersonalInformationActivity.this);
                            grantObservable1.subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean granted) {
                                    if (granted) {
                                        isTakePhoto=false;
                                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                        startActivityForResult(intent1, 1);
                                    } else {
                                        PermissionsUtils.showPermissionDeniedDialog(PersonalInformationActivity.this, false);
                                    }
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 获取版本号
     * @return
     */
    public void getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            versionCodeTxt.setText("版本 "+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用相机
     */
    private void useCamera(Intent intent2) {
        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.ibeef.cowboying.fileprovider", file);
        //添加权限
        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件

        intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent2, 2);
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

        oss = new OSSClient(PersonalInformationActivity.this,Endpoint,credentialProvider,conf);
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
                        stringList.clear();
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
                if(stringList.size()>0){
                    Log.e(Constant.TAG,stringList.get(0)+"objectKey????????????????");
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    ModifyHeadParamBean modifyHeadParamBean=new ModifyHeadParamBean();
                    modifyHeadParamBean.setImageUrl(stringList.get(0));
                    userInfoPresenter.getModifyHead(reqData,modifyHeadParamBean);
                }
                dismissLoading();
            }

        }
    };

    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {
        if("000000".equals(safeInfoResultBean.getCode())){
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getMobile())){
                phoneStadus.setImageResource(R.mipmap.setphoneh);
            }else {
                phoneStadus.setImageResource(R.mipmap.setphone);
            }

            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxId())){
                weixinStadus.setImageResource(R.mipmap.setweixinh);
            }else {
                weixinStadus.setImageResource(R.mipmap.setweixin);
            }
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbId())){
                zfbStadus.setImageResource(R.mipmap.setzfbh);
            }else {
                zfbStadus.setImageResource(R.mipmap.setzfb);
            }

        }else {
            showToast(safeInfoResultBean.getMessage());
        }
    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

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
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {
        if("000000".equals(modifyHeadResultBean.getCode())){
            stringList.clear();
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            showToast("更新头像成功~");
            userInfoPresenter.getUserInfo(reqData);
        }else {
            showToast(modifyHeadResultBean.getMessage());
        }
    }

    @Override
    public void getModifNick(ModifyNickResultBean modifyNickResultBean) {
        if("000000".equals(modifyNickResultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            showToast("更新昵称成功~");
            userInfoPresenter.getUserInfo(reqData);
        }else {
            showToast(modifyNickResultBean.getMessage());
        }
    }

    @Override
    public void getRealName(RealNameReaultBean realNameReaultBean) {
        if("000000".equals(realNameReaultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            showToast("实名认证成功~");
            userInfoPresenter.getUserInfo(reqData);
        }else {
            showToast(realNameReaultBean.getMessage());
        }
    }

    @Override
    public void getUserInfo(UserInfoResultBean userInfoResultBean) {
        if("000000".equals(userInfoResultBean.getCode())){
            this.userInfoResultBean=userInfoResultBean;
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.meheaddefalut)
                    //加载错误之后的错误图
                    .skipMemoryCache(true)
                    //跳过内存缓存
                    ;
            Hawk.put(HawkKey.userId, userInfoResultBean.getBizData().getUserId()+"");
            Glide.with(this).load(Constant.imageDomain+userInfoResultBean.getBizData().getHeadImage()).apply(options).into(ivIcon);
            nicknameTxt.setText("");
            if("0".equals(userInfoResultBean.getBizData().getIsValidate())){
                //未认证
                realInfoTxt.setText("未认证");
                realInfoTxt.setTextColor(getResources().getColor(R.color.red));
            }else {
                //认证
                realInfoTxt.setText(userInfoResultBean.getBizData().getRealName());
                realInfoTxt.setTextColor(getResources().getColor(R.color.gray));
            }
            isCheck=true;

            if(SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getMobile())){
                //绑定手机号
                bindPhoneStadus.setText("未绑定手机号");
                bindPhoneTxt.setText("去绑定");
                isBindPhone=false;
            }else {
                bindPhoneStadus.setText("手机号");
                String phoneNumber = userInfoResultBean.getBizData().getMobile().substring(0, 3) + "****" + userInfoResultBean.getBizData().getMobile().substring(7, userInfoResultBean.getBizData().getMobile().length());
                bindPhoneTxt.setText(phoneNumber);
                isBindPhone=true;
            }

            if(SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getNickName())){
                nicknameTxt.setText("设置昵称");
            }else {
                nicknameTxt.setText(userInfoResultBean.getBizData().getNickName());
            }
        }else {
            showToast(userInfoResultBean.getMessage());
        }

    }

    @Override
    public void isTakePhoeto(String msg) {
        imgPath=msg;
        showLoadings();
        List<String> photos=new ArrayList<>();
        photos.add(imgPath);
        if(!SDCardUtil.isNullOrEmpty(ossResultBean)){
            if(!SDCardUtil.isNullOrEmpty(ossResultBean.getBizData())){
                ossUpload(photos,ossResultBean.getBizData().getBucketName(),ossResultBean.getBizData().getFolder());
            }
        }
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        lvInfoShow.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        lvInfoShow.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    uritempFile=data.getData();
                    Intent intent = userInfoPresenter.cropHeadPhoto(data.getData());
                    imgUrl=intent.getStringExtra("cropImg");
                    // 裁剪图片
                    startActivityForResult(intent,3);
                }
                break;
            case 2:
                //拍照
                if (resultCode == RESULT_OK) {
                    /*获取当前系统的android版本号*/
                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion<24){
                        Intent intent1 = userInfoPresenter.cropHeadPhoto(Uri.fromFile(file));
                        // 裁剪图片
                        uritempFile=Uri.fromFile(file);
                        imgUrl=intent1.getStringExtra("cropImg");
                        startActivityForResult(intent1,3);
                    }else {
                        Uri photoURI = FileProvider.getUriForFile(PersonalInformationActivity.this, "com.ibeef.cowboying.fileprovider", file);
                        Intent intent1 = userInfoPresenter.cropHeadPhoto(photoURI);
                        // 裁剪图片
                        uritempFile=photoURI;
                        imgUrl=intent1.getStringExtra("cropImg");
                        startActivityForResult(intent1,3);
                    }

                    //在手机相册中显示刚拍摄的图片
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(file);
                    mediaScanIntent.setData(contentUri);
                    sendBroadcast(mediaScanIntent);

                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    try {
                        head = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(head!=null){
                        ivIcon.setImageBitmap(head);
                        // 用ImageView显示出来
//                         headImgUrl = bitmapToBase64(head);

                        userInfoPresenter.setPicToView(head,imgUrl,b);
                        // 保存在SD卡中

                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getOssImgPresenter!=null){
            getOssImgPresenter.detachView();
        }
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
        if(accountSecurityPresenter!=null){
            accountSecurityPresenter.detachView();
        }
    }

}
