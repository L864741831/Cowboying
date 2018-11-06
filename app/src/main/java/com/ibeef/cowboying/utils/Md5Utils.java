package com.ibeef.cowboying.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ls
 * @date on 2018/11/5 17:49
 * @describe
 * @package com.ibeef.cowboying.utils
 **/
public class Md5Utils {

        //通过图片本地路径拿到MD5
        public static String getMD5(String imagePath) throws NoSuchAlgorithmException, IOException {

            InputStream in = new FileInputStream(new File(imagePath));

            StringBuffer md5 = new StringBuffer();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dataBytes = new byte[1024];

            int nread = 0;
            while ((nread = in.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();

            // convert the byte to hex format
            for (int i = 0; i < mdbytes.length; i++) {
                md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return md5.toString().toLowerCase();
        }

        //通过字符串拿到md5
        public static String stringToMD5(String string) {
            byte[] hash;

            try {
                hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }

            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }

            return hex.toString().toLowerCase();
        }

    public static String getFileName(String pathandname){

        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return pathandname.substring(start+1,end);
        }else{
            return null;
        }

    }
}
