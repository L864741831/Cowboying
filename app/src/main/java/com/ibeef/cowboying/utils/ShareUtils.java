package com.ibeef.cowboying.utils;

import android.content.Context;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;


public class ShareUtils {

    /**
     * 演示调用ShareSDK执行分享
     * @param context
     * @param platformToShare 指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit 是否显示编辑页
     */
    public static void toShare(final Context context, String platformToShare, boolean showContentEdit, String title, String videoUrl, String remark,String imageUrl) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode(false);
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址

        oks.setTitle(title);

        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(videoUrl);

        // text是分享文本，所有平台都需要这个字段
        oks.setText(remark);

        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片

        oks.setImageUrl(imageUrl);

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(videoUrl);
        //微信不绕过审核分享链接

        //oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供

        oks.setComment("分享");
        //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供

        oks.setSite("丫贝");
        //QZone分享完之后返回应用时提示框上显示的名称

        oks.setSiteUrl("http://www.handtv.cn/downLoad/download.jsp");
        //QZone分享参数

        oks.setVenueName("丫贝");

        oks.setVenueDescription("This is a beautiful place!");

        oks.show(context);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //ToastUtils.showToast(context,"成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //ToastUtils.showToast(context,"错误");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //ToastUtils.showToast(context,"取消");
            }
        });
    }
}
