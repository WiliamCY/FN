package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.ImageViewAdapter;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.zzti.fengyongge.imagepicker.PhotoPreviewActivity;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;
import com.zzti.fengyongge.imagepicker.model.PhotoModel;
import com.zzti.fengyongge.imagepicker.util.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.lwkandroid.imagepicker.ImagePicker;
//import com.lwkandroid.imagepicker.data.ImageBean;
//import com.lwkandroid.imagepicker.data.ImagePickType;
//import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
//import com.lwkandroid.imagepicker.ImagePicker;
//import com.lwkandroid.imagepicker.data.ImageBean;
//import com.lwkandroid.imagepicker.data.ImagePickType;
//import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;

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
    @BindView(R.id.imageview_recyclerview)
    RecyclerView imageviewRecyclerview;
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

    @OnClick({R.id.pc_photo, R.id.pc_button, R.id.submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pc_photo:
                //发起图片选择
                Intent intent = new Intent(this, PhotoSelectorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("limit", 5);//number是选择图片的数量
                startActivityForResult(intent, 0);

                break;
            case R.id.pc_button:
                break;
            case R.id.submission:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    final List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    //处理代码
                    Log.d("photoPath", paths.toString());
                    if(paths.size()>5){
                        pcPhoto.setVisibility(View.GONE);
                        pcAdd.setVisibility(View.GONE);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    imageviewRecyclerview.setLayoutManager(linearLayoutManager);
                    linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                    final ImageViewAdapter adapter = new ImageViewAdapter(paths,this);
                    imageviewRecyclerview.setAdapter(adapter);
                    adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                        @Override
                        public void onItemClick(int position, String path) {
//                            List<PhotoModel> single_photos = new ArrayList<PhotoModel>();
////PhotoModel 开发者将自己本地bean的list封装成PhotoModel的list，PhotoModel属性源码可查看
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("photos",(Serializable)single_photos);
//                            bundle.putInt("position", position);//position预览图片地址
//                            Log.d("positions", String.valueOf(position));
//                            bundle.putBoolean("isSave",false);//isSave表示是否可以保存预览图片，建议只有预览网络图片时设置true
//                            CommonUtils.launchActivity(PublishingCollections.this, PhotoPreviewActivity.class, bundle);

                        }
                    });
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
