package com.example.hemin.fnb.ui.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import okhttp3.RequestBody;

public class Utils {

    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile(regex);
        return p.matches(regex, input);
    }

    public static class TimeCount extends CountDownTimer {
        private TextView btn_count;

        public TimeCount(long millisInFuture, long countDownInterval, TextView btn_count) {
            super(millisInFuture, countDownInterval);
            this.btn_count = btn_count;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_count.setEnabled(false);
            btn_count.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btn_count.setEnabled(true);
            btn_count.setText("获取验证码");

        }

    }

    public static RequestBody RetrofitHead(HashMap hashMap) {
        Gson gson = new Gson();
        String strEntity = gson.toJson(hashMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);
        return body;

    }

    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static boolean initLogin(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        if (sp.getString("Authorization", "").equals("")) {
            AlertDialog dialog = new AlertDialog(context).builder();
            dialog.setGone().setTitle("提示")
                    .setMsg("请先登录")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PasswordActivity.class);
                            context.startActivity(intent);
                        }
                    }).show();

        }
        return false;
    }

    public static boolean booleanisLogin(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        if (c.equals("")) {

            return true;

        }
        return false;
    }

    public static void initLogins(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        if (sp.getString("Authorization", "").equals("")) {
            AlertDialog dialog = new AlertDialog(context).builder();
            dialog.setGone().setTitle("提示")
                    .setNegativeButton("取消", null)
                    .setMsg("是否退出")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();

        }
    }

    public static Map getAuthorization(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String Authorization = sp.getString("Authorization", "");
        String token_type = sp.getString("tokenType", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", token_type + Authorization);
        return map;
    }
    public static String getAuthorizationHeard(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String Authorization = sp.getString("Authorization", "");
        String token_type = sp.getString("tokenType", "");
               String heard = token_type+Authorization;
               return heard;
    }

    public static String getUserId(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String useId = sp.getString("userId", "");
        String id = useId;
        return id;
    }
    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param filePath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    public static void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 2000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);
    }

    public static class ToastUtils {
        static Toast toast = null;

        public static void show(Context context, String text) {
            try {
                if (toast != null) {
                    toast.setText(text);
                } else {
                    toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                }
                toast.show();
            } catch (Exception e) {
                //解决在子线程中调用Toast的异常情况处理
                Looper.prepare();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    }

    public static void PwdHide(EditText pwds, ImageView hides, ImageView shows) {
        hides.setVisibility(View.GONE);
        shows.setVisibility(View.VISIBLE);

        //隐藏密码方法一
//        PasswordTransformationMethod method1 = PasswordTransformationMethod.getInstance();
        pwds.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //        //切换后将EditText光标置于末尾
        CharSequence charSequence = pwds.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    public static void PwdShow(EditText pwds, ImageView hides, ImageView shows) {
        shows.setVisibility(View.GONE);
        hides.setVisibility(View.VISIBLE);
        //显示密码方法一
//        HideReturnsTransformationMethod method2 = HideReturnsTransformationMethod.getInstance();
        pwds.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


        CharSequence charSequence1 = pwds.getText();
        if (charSequence1 instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence1;
            Selection.setSelection(spanText, charSequence1.length());
        }
    }
    public  static  void isLoginBoolean(Context context){
        SharedPreferences.Editor isLoginBoolean = context.getSharedPreferences("FirstLogin",Context.MODE_PRIVATE).edit();
        isLoginBoolean.putBoolean("isFirst",false);
        isLoginBoolean.commit();
    }
    /**
     *  根据Uri获取文件真实地址
     */
    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
    }

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static  File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    public static String bitmapToString(Bitmap bitmap){
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        string= Base64.encodeToString(bytes,Base64.DEFAULT);
        return string;
    }

//获取本地版本号
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取网络视频第一帧
     * @param
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
//    public  static Bitmap getVideoThumb(String path) {
//
//        MediaMetadataRetriever media = new MediaMetadataRetriever();
//
//        media.setDataSource("https://dy-frontend.video.ums.uc.cn/w/1563271834/video/wemedia/2d4108576c4841fba24829ca78b5cef3/d143e5255a4ca4aafdaaec30295e81e1-1685017267-6-0-3.mp4?auth_key=1563439068-bf9342495e1c4315aa6d9dd799f744a5-0-daed6cea98e37420243f8617125de83c", new HashMap());
//
//        return  media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
//
//    }
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
private static  Bitmap createVideoThumbnail(String url, int width, int height) {
    Bitmap bitmap = null;
    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
    int kind = MediaStore.Video.Thumbnails.MINI_KIND;
    try {
        if (Build.VERSION.SDK_INT >= 14) {
            retriever.setDataSource(url, new HashMap<String, String>());
        } else {
            retriever.setDataSource(url);
        }
        bitmap = retriever.getFrameAtTime();
    } catch (IllegalArgumentException ex) {
        // Assume this is a corrupt video file
    } catch (RuntimeException ex) {
        // Assume this is a corrupt video file.
    } finally {
        try {
            retriever.release();
        } catch (RuntimeException ex) {
            // Ignore failures while cleaning up.
        }
    }
    if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
    }
    return bitmap;
}

}
