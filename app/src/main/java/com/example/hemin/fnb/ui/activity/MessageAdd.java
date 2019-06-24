package com.example.hemin.fnb.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.ImageViewAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.MessageFinderAddContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.MessageAddPresenter;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.ImageBean;
import com.example.hemin.fnb.ui.util.Loader;
import com.example.hemin.fnb.ui.util.MyGlideEngine;
import com.example.hemin.fnb.ui.util.Utils;
import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
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
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MessageAdd extends BaseMvpActivity<MessageAddPresenter> implements MessageFinderAddContract.View,EasyPermissions.PermissionCallbacks {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.user_toolbar)
    ConstraintLayout userToolbar;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.it_picker_view)
    ImageShowPickerView itPickerView;
    private static final int REQUEST_CODE_CHOOSE = 233;
    //    @BindView(R.id.r1)
//    RecyclerView r1;
//    @BindView(R.id.add_image)
//    ImageView addImage;
//    @BindView(R.id.imageViewNumber)
//    TextView imageViewNumber;
//    @BindView(R.id.c1)
//    ConstraintLayout c1;
    //    @BindView(R.id.user_logos)
//    ImageView userLogos;
    private String[] mPerms = {Manifest.permission.CAMERA};

    private static final int PERMISSIONS = 100;
    private ImageViewAdapter adapter = new ImageViewAdapter();
    private List<String> imagePath = new ArrayList<String>();
    private List<String> imageUrls = new ArrayList<>();
    List<ImageBean> list = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.message_add;
    }

    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mPresenter = new MessageAddPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        itPickerView.setImageLoaderInterface(new Loader());
        itPickerView.setNewData(list);
        //展示有动画和无动画
        itPickerView.setShowAnim(true);
        itPickerView.setMaxNum(9);
        itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
                startActivityForResult(intent2, 0);

            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
//                Toast.makeText(MessageAdd.this, list.size() + "========" + position + "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
//                Toast.makeText(MessageAdd.this, "delOnClickListenerremainNum" + remainNum, Toast.LENGTH_SHORT).show();
                imageUrls.remove(position);
            }
        });
        itPickerView.show();


    }

    @OnClick({R.id.back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                if (TextUtils.isEmpty(getEdittext())) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT), 400);
                    return;
                }else if(imageUrls.size()<1 || imageUrls.size()>9){
                    Utils.showMyToast(Toast.makeText(this, "图片在1-9张范围之内", Toast.LENGTH_SHORT), 400);

                }
//                Log.d("imageSizeSend", String.valueOf(imageUrls.size()));
                String dates = imageUrls.toString().trim();
                if (dates.trim().contains("[") || dates.trim().contains("]")) {
                    dates = dates.substring(1, dates.length() - 1);
                }
                SharedPreferences sp = this.getSharedPreferences("userDate", this.MODE_PRIVATE);
                String id = sp.getString("userId", "");
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("friendContent", getEdittext());
                Log.d("messageFinder", getEdittext());
                map2.put("friendUrl", dates);
                Log.d("messageFinder1", dates);
                map2.put("userId", id);
                Log.d("messageFinder2", id);
                HashMap<String, String> token = new HashMap<>();
                token.put("Authorization", "usERa" + getToken());
                Log.d("messageFdinder", String.valueOf(Utils.RetrofitHead(map2)));
                mPresenter.friendAdd(this, token, Utils.RetrofitHead(map2));

                break;

        }
    }

    private String getEdittext() {
        return title.getText().toString();
    }

    @Override
    public void Date(Object object, int index) {

    }

    @Override
    public void Status(int index) {
        if (index == 1) {
            finish();
        }
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    public void getPostImageUrls(String urils) {

        imageUrls.add(urils);
        Log.d("imageUrlsiZE", String.valueOf(imageUrls.size()));


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", "usERa" + getToken());
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    for (int i = 0; i < paths.size(); i++) {
                            itPickerView.addData(new ImageBean(Utils.getRealFilePath(MessageAdd.this, Uri.parse(paths.get(i)))));

                        String path = paths.get(i);
                        Log.d("pathsss:", path);
                        File file = new File(paths.get(i));
                        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
                        mPresenter.postImage(this, map, imageBodyPart);
                    }


                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(id = 4)
    public void printss(String message) {
        if (Integer.parseInt(message) < 10) {
            Log.d("printssSize", message);
//            c1.setVisibility(View.VISIBLE);
//            imageViewNumber.setText(String.valueOf(message) + "/9");
        } else {
//            c1.setVisibility(View.GONE);
        }

    }
    @AfterPermissionGranted(PERMISSIONS)
    private void requestPermission() {
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            //Log.d(TAG, "onClick: 获取读写内存权限,Camera权限和wifi权限");
        } else {
            EasyPermissions.requestPermissions(this, "上传朋友圈可能用到相机需要相机权限", PERMISSIONS, mPerms);

        }
    }
    protected void onResume() {
        super.onResume();
        requestPermission();
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale("上传朋友圈可能用到相机需要相机权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }
    }
    private String getToken() {
        SharedPreferences sp = this.getSharedPreferences("userDate", MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        System.out.println(c);
        return sp.getString("Authorization", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
