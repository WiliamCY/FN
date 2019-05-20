package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.hemin.fnb.ui.adapter.ImageViewAdapter;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.GetTypePresenter;
import com.example.hemin.fnb.ui.presenter.PasswordPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.zzti.fengyongge.imagepicker.PhotoPreviewActivity;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;
import com.zzti.fengyongge.imagepicker.model.PhotoModel;
import com.zzti.fengyongge.imagepicker.util.CommonUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

//import com.lwkandroid.imagepicker.ImagePicker;
//import com.lwkandroid.imagepicker.data.ImageBean;
//import com.lwkandroid.imagepicker.data.ImagePickType;
//import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
//import com.lwkandroid.imagepicker.ImagePicker;
//import com.lwkandroid.imagepicker.data.ImageBean;
//import com.lwkandroid.imagepicker.data.ImagePickType;
//import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;

public class PublishingCollections extends BaseMvpActivity<GetTypePresenter> implements GetTypeContract.View {
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
        mPresenter = new GetTypePresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        cachePath = getExternalFilesDir(null) + "/mypics/photos/";
    }


    @OnClick({R.id.pc_photo, R.id.pc_button, R.id.submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pc_photo:
                //发起图片选择
                Intent intent = new Intent(this, PhotoSelectorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("limit", 12);//number是选择图片的数量
                startActivityForResult(intent, 0);
             break;
            case R.id.pc_button:
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "usERa" + getToken());
                mPresenter.getType(this, map);

                break;
            case R.id.submission:
                if(getEdittect() == null ){
                    Toast.makeText(this,"请输入完整",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url1 = "https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/kap3glz7qy";
                String url2 = "https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/p6mknzgqs9";
                String url3 = "https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/p64zhv7w3a";
                String url4 = "https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/nlc4zbfuqx";
                String url5 = "https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/gk5y3buhmv";
                String url = url1+","+url2+","+url3+","+url4+","+url5;
                HashMap<String,String> map2 = new HashMap<>();
                  map2.put("collectionType ","1");
                  map2.put("imagesDetails",getEdittect());
                  map2.put("imagesUrl ",url);
                  map2.put("userId ","34");
                  HashMap<String,String> token = new HashMap<>();
                  token.put("Authorization","usERa"+getToken());
                  mPresenter.submitImage(this, token,Utils.RetrofitHead(map2));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", "usERa" + getToken());
                    final List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    for(int i = 0;i<paths.size();i++){
                        File file = new File(paths.get(i));
                        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
                        mPresenter.postImage(this,map,imageBodyPart);
                    }
                    //处理代码
                    Log.d("photoPath", paths.toString());
                    if (paths.size() > 5) {
                        pcPhoto.setVisibility(View.GONE);
                        pcAdd.setVisibility(View.GONE);
                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    imageviewRecyclerview.setLayoutManager(linearLayoutManager);
                    linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                    final ImageViewAdapter adapter = new ImageViewAdapter(paths, this);
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

    private String getToken() {
        SharedPreferences sp = this.getSharedPreferences("userDate", MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        System.out.println(c);
        return sp.getString("Authorization", "");
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() == 0) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
            ProgressDialog.getInstance().dismiss();
        }
    }
    private String  getEdittect(){
        return  titl4.getText().toString();
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }
    public void getDate(List<String> date){
        initOptionPicker(date);
    }
    private void  initOptionPicker(final List<String> typeName){
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                pcButton.setText(typeName.get(options1));

            }
        })                .setSubmitColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setCancelColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setDividerColor(Color.BLACK)
                .setCancelText("取消")
                .setSubmitText("确定")
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
         optionsPickerView.setPicker(typeName);

        optionsPickerView.show();
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }
}
