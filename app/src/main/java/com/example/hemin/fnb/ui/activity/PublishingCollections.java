package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
//import com.lwkandroid.imagepicker.ImagePicker;
//import com.lwkandroid.imagepicker.data.ImageBean;
//import com.lwkandroid.imagepicker.data.ImagePickType;
//import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishingCollections extends BaseActivity {
    private final int REQUEST_CODE = 111;
    @BindView(R.id.pc_photo)
    ImageView pcPhoto;
    @BindView(R.id.pc_add)
    TextView pcAdd;
    @BindView(R.id.pc_add_rember)
    TextView pcAddRember;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.pc_button)
    TextView pcButton;
    @BindView(R.id.titl3)
    ImageView titl3;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.titl4)
    EditText titl4;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.title5)
    TextView title5;
    @BindView(R.id.Technological_process)
    ImageView TechnologicalProcess;
    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.title6)
    TextView title6;
    @BindView(R.id.title7)
    TextView title7;
    private Button button;
    private String cachePath;

    @Override
    public int getLayoutId() {
        return R.layout.publishing_collections;
    }

    @Override
    public void initView() {
        cachePath = getExternalFilesDir(null) + "/mypics/photos/";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
//
//    @OnClick({R.id.pc_photo, R.id.pc_button, R.id.submission})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.pc_photo:
//                //发起图片选择
//                new ImagePicker()
//                        .pickType(ImagePickType.MULTI) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
//                        .maxNum(9) //设置最大选择数量(此选项只对多选生效，拍照和单选都是1，修改后也无效)
//                        .needCamera(true) //是否需要在界面中显示相机入口(类似微信那样)
//                        .cachePath(cachePath) //自定义缓存路径(拍照和裁剪都需要用到缓存)
//                        .doCrop(1,1,300,300) //裁剪功能需要调用这个方法，多选模式下无效，参数：aspectX,aspectY,outputX,outputY
//                        .displayer(new GlideImagePickerDisplayer()); //自定义图片加载器，默认是Glide实现的,可自定义图片加载器
////                        .start(this, REQUEST_CODE); //自定义RequestCode
//                break;
//            case R.id.pc_button:
//                break;
//            case R.id.submission:
//                break;
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null)
//        {
//            List<ImageBean> resultList = data.getExtras().getParcelableArrayList(ImagePicker.INTENT_RESULT_DATA);
//            Log.i("ImagePickerDemo", "选择的图片：" + resultList.toString());
//            String content = "";
//            for (ImageBean imageBean : resultList)
//            {
//                content = content + imageBean.toString() + "\n";
//            }
////            mTvResult.setText(content);
//        }
//    }
}
