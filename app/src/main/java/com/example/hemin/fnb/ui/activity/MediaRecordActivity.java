package com.example.hemin.fnb.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaRecordActivity extends AppCompatActivity {
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    public static final int RESULT_CODE_RETURN_PHOTO = 101;
    public static final int RESULT_CODE_RETURN_VIDEO = 102;
    private JCameraView jCameraView;
    private boolean granted = false;
    private String type;
    private int StatusType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
         Intent intent = getIntent();
         type = intent.getStringExtra("type");
        StatusType = intent.getIntExtra("StatusType",0);
        jCameraView = (JCameraView) findViewById(R.id.jcameraview);

        //设置视频保存路径
//        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        jCameraView.setSaveVideoPath("storage/emulated/0/imagepicker");
        //设置只能录像或只能拍照或两种都可以（默认两种都可以）
        if(StatusType == 0){
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        }else if(StatusType == 1){
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_CAPTURE);
        jCameraView.setTip("轻触拍照");
        }else if(StatusType == 2){
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER);
            jCameraView.setTip("长按摄像");
        }

        //设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                Log.i("CJT", "AudioPermissionError");
            }
        });
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = FileUtil.saveBitmap("storage/emulated/0/imagepicker", bitmap);
                if(type != null && type.equals("1")){
                    Intent intent = new Intent(MediaRecordActivity.this,MessageAdd.class);
                    intent.putExtra("path", path);
                        startActivity(intent);
                        finish();
                }else {
                    Intent intent = new Intent(MediaRecordActivity.this,MessageAdd.class);
                    intent.putExtra("path", path);
                    setResult(RESULT_CODE_RETURN_PHOTO, intent);
                    finish();
                }

//                startActivity(intent);
//                onActivityResult(100,RESULT_CODE_RETURN_PHOTO,intent);
//               startActivityForResult(intent,RESULT_CODE_RETURN_PHOTO);
//                startActivity(intent);


//
//                finish();

            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取视频路径
                String path = FileUtil.saveBitmap("imagePicker", firstFrame);//FileUtil是本库自带的

                if(type != null && type.equals("1")){
                    Intent intent = new Intent(MediaRecordActivity.this,MessageAdd.class);
                    intent.putExtra("videopath", path);
                    intent.putExtra("videoUrl", url);
                    startActivity(intent);
                }
                Intent intent = new Intent(MediaRecordActivity.this,MessageAdd.class);
                intent.putExtra("videopath", path);
                intent.putExtra("videoUrl", url);
                setResult(RESULT_CODE_RETURN_VIDEO, intent);
                finish();
            }
        });
//左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                MediaRecordActivity.this.finish();
            }
        });
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MediaRecordActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
        //6.0动态权限获取
        getPermissions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (granted) {
            jCameraView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                //具有权限
                granted = true;
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(MediaRecordActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
                granted = false;
            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    granted = true;
                    jCameraView.onResume();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
