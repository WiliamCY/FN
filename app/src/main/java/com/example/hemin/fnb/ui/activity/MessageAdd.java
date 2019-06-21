package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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
import com.example.hemin.fnb.ui.util.Utils;
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

public class MessageAdd extends BaseMvpActivity<MessageAddPresenter> implements MessageFinderAddContract.View {
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
    @BindView(R.id.r1)
    RecyclerView r1;
    @BindView(R.id.add_image)
    ImageView addImage;
    @BindView(R.id.imageViewNumber)
    TextView imageViewNumber;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    //    @BindView(R.id.user_logos)
//    ImageView userLogos;
    private ImageViewAdapter adapter = new ImageViewAdapter();
    private List<String> imagePath = new ArrayList<String>();
    private List<String> imageUrls = new ArrayList<>();
    private String cachePath;


    @Override
    public int getLayoutId() {
        return R.layout.message_add;
    }

    @Override
    public void initView() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mPresenter = new MessageAddPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        cachePath = this.getExternalFilesDir(null) + "/mypics/photos/";
    }

    @OnClick({R.id.back, R.id.add, R.id.add_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                if (TextUtils.isEmpty(getEdittext())) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT), 400);
                    return;
                } else if (adapter.getItemCount() < 1 || adapter.getItemCount() > 9) {
                    Utils.showMyToast(Toast.makeText(this, "图片在1-9张范围之内", Toast.LENGTH_SHORT), 400);
                    return;
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
            case R.id.add_image:
                if (adapter.getItemCount() > 9) {
                    Utils.showMyToast(Toast.makeText(this, "放置图片已经最大值", Toast.LENGTH_SHORT), 400);
                    return;
                }
                Intent intent2 = new Intent(this, PhotoSelectorActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
                startActivityForResult(intent2, 0);
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
//            Utils.showMyToast(Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT), 400);
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
                    addImageView(paths);
                    imageViewNumber.setText(adapter.getItemCount() + "/9");
                    for (int i = 0; i < paths.size(); i++) {
                        String path = paths.get(i);
                        Log.d("pathsss:", path);
                        File file = new File(paths.get(i));
                        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
                        mPresenter.postImage(this, map, imageBodyPart);
                    }
                    //处理代码
                    Log.d("photoPath", paths.toString());
                    if (adapter.getItemCount() >= 9) {
                        c1.setVisibility(View.GONE);
                        Utils.showMyToast(Toast.makeText(this, "添加的图片已经是最大值了", Toast.LENGTH_SHORT), 400);
                    }


                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Subscribe(id = 4)
    public void printss(String message){
        if(Integer.parseInt(message)<10) {
            Log.d("printssSize",message);
            c1.setVisibility(View.VISIBLE);
            imageViewNumber.setText(String.valueOf(message) + "/9");
        }else {
            c1.setVisibility(View.GONE);
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

    private void addImageView(List<String> path) {
        for (int i = 0; i < path.size(); i++) {
            if (Arrays.asList(imagePath).contains(path.get(i))) {

                Utils.showMyToast(Toast.makeText(
                        this, "已经存在", Toast.LENGTH_SHORT), 400);
            } else {

                imagePath.add(path.get(i));
                Log.d("imagePathc", path.toString());
                GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);
                r1.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
                Log.d("imagePaths", imagePath.toString());
                adapter = new ImageViewAdapter(imagePath, this);
                r1.setAdapter(adapter);
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
                        String deleteImagePath = path;

                    }

                    @Override
                    public void onItemClick(int Position) {

                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
