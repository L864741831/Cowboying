package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.ModifyHeadParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickParamBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
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
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 个人信息界面
 */
public class PersonalInformationActivity extends BaseActivity implements UserInfoBase.IView ,FeedbackBase.IView{

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
    @Bind(R.id.code_txt_id)
    TextView codeTxtId;
    @Bind(R.id.name_txt_id)
    TextView nameTxtId;
    @Bind(R.id.real_info_rv)
    RelativeLayout realInfoRv;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;
    @Bind(R.id.et_write_id)
    EditText etWriteId;
    @Bind(R.id.et_write_id1)
    EditText etWriteId1;
    @Bind(R.id.cancle_img)
    ImageView cancleImg;
    @Bind(R.id.cancle_img1)
    ImageView cancleImg1;
    @Bind(R.id.modify_nickname_rv)
    RelativeLayout modifyNicknameRv;
    @Bind(R.id.show_realinfo_rv)
    RelativeLayout showRealinfoRv;
    @Bind(R.id.search2_id)
    LinearLayout search2Id;
    private boolean isNickname=false;
    private   File file;
    private boolean isTakePhoto=true;
    private UserInfoPresenter userInfoPresenter;
    private String token,userId;
    private UserInfoResultBean userInfoResultBean;
    private Uri uritempFile;
    private Bitmap head;
    private FileOutputStream b;
    private String imgPath;
    @SuppressLint("SdCardPath")
    private static String path = "/storage/emulated/0/myHead/";
    private boolean isCheck=false,isBindPhone=false;
    private FeedbackPresenter feedbackPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        init();
        initDialog();
    }

    private void init() {
        info.setText("个人信息");
        token= Hawk.get(HawkKey.TOKEN);
        userId= Hawk.get(HawkKey.userId);
        userInfoPresenter=new UserInfoPresenter(this);
        feedbackPresenter=new FeedbackPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        userInfoPresenter.getUserInfo(reqData);
    }

    @OnClick({R.id.back_id, R.id.iv_icon, R.id.nickname_rv, R.id.bind_phone_rv, R.id.real_info_rv, R.id.modify_pwd_rv,R.id.modify_btn,R.id.cancle_img,R.id.cancle_img1,R.id.modify_nickname_rv,R.id.show_realinfo_rv})
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
                search2Id.setVisibility(View.GONE);
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
                if(isCheck){
                    if(!"0".equals(userInfoResultBean.getBizData().getIsValidate())){
                        //已实名认证
                        showRealinfoRv.setVisibility(View.VISIBLE);
                        nameTxtId.setText("姓名："+userInfoResultBean.getBizData().getRealName());
                        codeTxtId.setText("身份证号："+userInfoResultBean.getBizData().getRealCardNo());
                    }else {
                        //未实名认证
                        isNickname=false;
                        etWriteId.setHint("请输入真实姓名");
                        etWriteId1.setHint("请输入真实身份证号");
                        etWriteId.setText("");
                        etWriteId1.setText("");
                        modifyNicknameRv.setVisibility(View.VISIBLE);
                        titleTxtId.setText("实名认证");
                        search2Id.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.modify_pwd_rv:
                startActivity(ModifyPwdActivity.class);
                break;
            case R.id.modify_btn:
                if(isNickname){
                    // 昵称
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    ModifyNickParamBean modifyNickParamBean=new ModifyNickParamBean();
                    modifyNickParamBean.setNiceName(etWriteId.getText().toString().trim());
                    userInfoPresenter.getModifNick(reqData,modifyNickParamBean);
                }else {
                    // 实名认证
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    RealNameParamBean realNameParamBean=new RealNameParamBean();
                    realNameParamBean.setUserId(userInfoResultBean.getBizData().getUserId()+"");
                    if(!SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getMobile())){
                        realNameParamBean.setUserMobile(userInfoResultBean.getBizData().getMobile());
                    }
                    realNameParamBean.setRealName(etWriteId.getText().toString().trim());
                    realNameParamBean.setRealCardNo(etWriteId1.getText().toString().trim());
                    userInfoPresenter.getRealName(reqData,realNameParamBean);
                }
                modifyNicknameRv.setVisibility(View.GONE);
                break; 
            case R.id.cancle_img:
                etWriteId.setText("");
                break;
            case R.id.cancle_img1:
                etWriteId1.setText("");
                break;
            case R.id.modify_nickname_rv:
                modifyNicknameRv.setVisibility(View.GONE);
                break;
            case R.id.show_realinfo_rv:
                showRealinfoRv.setVisibility(View.GONE);
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

    @Override
    public void showMsg(String msg) {
        if(!TextUtils.isEmpty(msg)){
            if(msg.contains("401")){
                Hawk.put(HawkKey.TOKEN, "");
                Toast.makeText(this,"Authorization失效，请重新登录",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {

    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {

    }

    @Override
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {
        if("000000".equals(modifyHeadResultBean.getCode())){
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
            Glide.with(this).load(userInfoResultBean.getBizData().getHeadImage()).apply(options).into(ivIcon);
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
                bindPhoneTxt.setText(userInfoResultBean.getBizData().getMobile());
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
        Log.e(Constant.TAG,imgPath+"??????????头像");
        //文件上传图片
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        List<MultipartBody.Part> parts = new ArrayList<>(1);
        File file = new File(imgPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getName(), requestBody);
        parts.add(part);

        Log.e(Constant.TAG,file.getName()+"??????????头像" + file);
        feedbackPresenter.getUploadImg(reqData,parts);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    uritempFile=data.getData();
                    Intent intent = userInfoPresenter.cropHeadPhoto(data.getData());
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
                        startActivityForResult(intent1,3);
                    }else {
                        Uri photoURI = FileProvider.getUriForFile(PersonalInformationActivity.this, "com.ibeef.cowboying.fileprovider", file);
                        Intent intent1 = userInfoPresenter.cropHeadPhoto(photoURI);
                        // 裁剪图片
                        uritempFile=photoURI;
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

                        userInfoPresenter.setPicToView(head,path,b);
                        // 保存在SD卡中

                    }
                }
                break;
            default:
                break;
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
        if("000000".equals(mdUploadImgBean.getCode())){
            Toast.makeText(PersonalInformationActivity.this,"图片上传成功！",Toast.LENGTH_SHORT).show();

            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            ModifyHeadParamBean modifyHeadParamBean=new ModifyHeadParamBean();
            modifyHeadParamBean.setImageUrl(mdUploadImgBean.getBizData().get(0).getFilePath());
            userInfoPresenter.getModifyHead(reqData,modifyHeadParamBean);
        }else {
            Toast.makeText(PersonalInformationActivity.this,mdUploadImgBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if(feedbackPresenter!=null){
            feedbackPresenter.detachView();
        }
        super.onDestroy();
    }

}
