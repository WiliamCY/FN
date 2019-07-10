package com.example.hemin.fnb.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.ImageViewAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
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

public class MessageAdd extends BaseMvpActivity<MessageAddPresenter> implements MessageFinderAddContract.View, EasyPermissions.PermissionCallbacks, View.OnClickListener {
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
    private String[] mPerms = {Manifest.permission.CAMERA};
    private static final int PERMISSIONS = 100;
    private ImageViewAdapter adapter = new ImageViewAdapter();
    private List<String> imageUrls = new ArrayList<>();
    List<ImageBean> list = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    private static final String[] date2 = new String[]{"相册", "拍摄"};
    private List<String> mDataList2 = Arrays.asList(date2);
    private PopupWindow popupWindow;
    private ImageView imageView1, imageView2, imageView3, dissmissage;
    private boolean mIsVisibleToUser = false;
    ArrayList<String> infoList = new ArrayList<String>();


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
        map = Utils.getAuthorization(this);
        itPickerView.setImageLoaderInterface(new Loader());
        itPickerView.setNewData(list);
        //展示有动画和无动画
        itPickerView.setShowAnim(true);
        itPickerView.setMaxNum(9);
        itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                initOptionPicker(mDataList2);
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
                imageUrls.remove(position);
            }
        });
        itPickerView.show();
        String path = getIntent().getStringExtra("path");
        if(path != null){
            imageUrls.add(path);
            itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(path))));
            postFile(path,1);
        }
        infoList = getIntent().getStringArrayListExtra("paths");
        if(infoList != null) {
            for (int i = 0; i < infoList.size(); i++) {
                imageUrls.add(infoList.get(i));
                itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(infoList.get(i)))));
                postFile(infoList.get(i),1);
            }
        }
        String video = getIntent().getStringExtra("videoUrl");
        if(video != null){
            postFile(video,2);
        }
    }



//    private void initPopwdowns() {
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.upload_activity, null, false);
//        imageView1 = view.findViewById(R.id.image_1);
//        imageView2 = view.findViewById(R.id.image_2);
//        imageView3 = view.findViewById(R.id.image_3);
//        dissmissage = view.findViewById(R.id.upload_dissmiss);
//        imageView1.setOnClickListener(this);
//        imageView2.setOnClickListener(this);
//        imageView3.setOnClickListener(this);
//        dissmissage.setOnClickListener(this);
//        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable());
//        popupWindow.showAsDropDown(userToolbar);
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                                Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
//                startActivityForResult(intent2, 0);
//                popupWindow.dismiss();
//            }
//        });
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(getActivity(), MediaRecordActivity.class), 100);
//                popupWindow.dismiss();
//            }
//        });
//           dissmissage.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   getActivity().getSupportFragmentManager().popBackStack();
//                   popupWindow.dismiss();
//               }
//           });
//
//    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        mIsVisibleToUser = hidden;
//        if (hidden) {
//            Log.d("wdwaidhawlhdivs", String.valueOf(hidden));
//              popupWindow.dismiss();
//
//        } else {
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.message_add, null, false);
//            userToolbar = view.findViewById(R.id.user_toolbar);
////        userToolbar = getActivity().findViewById(R.id.view1);
//            popupWindow.showAsDropDown(userToolbar);
//            getActivity().getSupportFragmentManager().popBackStack();
//
////           popupWindow.show
//        }
//    }

    @OnClick({R.id.back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
//              Intent intent = new Intent(getActivity(),MainActivity.class);
//                  startActivity(intent);
                break;
            case R.id.add:
                if (TextUtils.isEmpty(getEdittext())) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT), 400);
                    return;
                } else if (imageUrls.size() < 1 || imageUrls.size() > 9) {
                    Utils.showMyToast(Toast.makeText(this, "图片在1-9张范围之内", Toast.LENGTH_SHORT), 400);

                }
                String dates = imageUrls.toString().trim();
                if (dates.trim().contains("[") || dates.trim().contains("]")) {
                    dates = dates.substring(1, dates.length() - 1);
                }
                SharedPreferences sp = this.getSharedPreferences("userDate", MODE_PRIVATE);
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

    private void initOptionPicker(final List<String> typeName) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String sexs = typeName.get(options1);
                if (sexs.equals("相册")) {
                    Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent2.putExtra("limit", 9 - imageUrls.size());//number是选择图片的数量
                    startActivityForResult(intent2, 0);

                } else if (sexs.equals("拍摄")) {
                    startActivityForResult(new Intent(AppUtils.getContext(), MediaRecordActivity.class), 100);
                }

            }
        }).setSubmitColor(ContextCompat.getColor(this, R.color.c4D6EEF))
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

    @Subscribe(id = 11)
    public void photoResule(String bitmap) {
        imageUrls.add(bitmap);
        itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(bitmap))));
        itPickerView.show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    for (int i = 0; i < paths.size(); i++) {
                        itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(paths.get(i)))));
                        String path = paths.get(i);
                        Log.d("pathsss:", path);
                        postFile(path,1);
                    }
                }
                break;
            case 100:
                if (resultCode == 101) {
                    String photoPath = data.getStringExtra("path");
                    imageUrls.add(photoPath);
                    itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(photoPath))));
                     postFile(photoPath,1);
                } else if (resultCode == 102) {
                    String firstVideoPicture = data.getStringExtra("path");
                    imageUrls.add(firstVideoPicture);
                    Log.d("wdwarhtrdf", firstVideoPicture);
                    //视频路径，该路径为已压缩过的视频路径
                    String videoPath = data.getStringExtra("videoUrl");
                    itPickerView.addData(new ImageBean(Utils.getRealFilePath(this, Uri.parse(firstVideoPicture))));
                   postFile(videoPath,2);

                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private  void postFile(String path,int type){
        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
        //1.相机  2视频
           if(type == 1){
               mPresenter.postImage(this, map, imageBodyPart);
           }else if(type == 2){
               mPresenter.postMp4(this,map,imageBodyPart);
           }
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

    //    protected void onResume() {
//        super.onResume();
//        requestPermission();
//    }
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
        SharedPreferences sp = getSharedPreferences("userDate", MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        System.out.println(c);
        return sp.getString("Authorization", "");
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    //
//    @Override
//    public void lazyInitView(View view) {
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
//        mPresenter = new MessageAddPresenter();
//        mPresenter.attachView(this);
////        ButterKnife.bind(this.getActivity());
//        map =  Utils.getAuthorization(getActivity());
//        itPickerView.setImageLoaderInterface(new Loader());
//        itPickerView.setNewData(list);
//        //展示有动画和无动画
//        itPickerView.setShowAnim(true);
//        itPickerView.setMaxNum(9);
//        Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
//        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
//        startActivityForResult(intent2, 0);
////        itPickerView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////            }
////        });
//        itPickerView.setPickerListener(new ImageShowPickerListener() {
//            @Override
//            public void addOnClickListener(int remainNum) {
////                Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
////                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
////                intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
////                startActivityForResult(intent2, 0);
//                initOptionPicker(mDataList2);
//            }
//
//            @Override
//            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
//                Toast.makeText(getActivity(),"111",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void delOnClickListener(int position, int remainNum) {
//                imageUrls.remove(position);
//            }
//        });
//        itPickerView.show();
//
//    }

    @Override
    public void onClick(View v) {

    }
}
