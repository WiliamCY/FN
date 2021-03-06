package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.ImageViewAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.GetTypePresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
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
    @BindView(R.id.image_add_button)
    ImageView imageAddButton;
    @BindView(R.id.imageNumber)
    TextView imageViewNumber;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.c1)
    LinearLayout c1;
    private Button button;
    private String cachePath;
    private List<String> imagePath = new ArrayList<String>();
    private List<String> imageUrls = new ArrayList<>();
    private ImageViewAdapter adapter = new ImageViewAdapter();
    private String typeIds;
    private int i = 0;
    private Map<String, String> map = new HashMap<>();


    @Override
    public int getLayoutId() {
        return R.layout.publishing_collections;
    }

    @Override
    public void initView() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        map = Utils.getAuthorization(this);
        mPresenter = new GetTypePresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        title.setText("我的收藏");
        cachePath = getExternalFilesDir(null) + "/mypics/photos/";

    }


    @OnClick({R.id.pc_photo, R.id.pc_button, R.id.submission, R.id.image_add_button, R.id.lay_back})
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
                if (pcButton.getText().equals("选择分类") || TextUtils.isEmpty(getEdittect())) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT), 400);
                    return;
                } else if (adapter.getItemCount() < 5 || adapter.getItemCount() > 12) {
                    Utils.showMyToast(Toast.makeText(this, "图片在5-12张范围之内", Toast.LENGTH_SHORT), 400);
                    return;
                }
                Log.d("imageSizeSend", String.valueOf(imageUrls.size()));
                String dates = imageUrls.toString().trim();
                if (dates.trim().contains("[") || dates.trim().contains("]")) {
                    dates = dates.substring(1, dates.length() - 1);
                }
                SharedPreferences sp = this.getSharedPreferences("userDate", this.MODE_PRIVATE);
                String id = sp.getString("userId", "");
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("collectionType", typeIds);
                map2.put("imagesDetails", getEdittect());
                map2.put("imagesUrl", dates);
                Log.d("sendImage:", dates);
                map2.put("userId", id);
                HashMap<String, String> token = new HashMap<>();
                token.put("Authorization", "usERa" + getToken());
                mPresenter.submitImage(this, token, Utils.RetrofitHead(map2));
                break;
            case R.id.image_add_button:
                if (adapter.getItemCount() > 11) {
                    Utils.showMyToast(Toast.makeText(this, "放置图片已经最大值", Toast.LENGTH_SHORT), 400);
                    return;
                }
                Intent intent2 = new Intent(this, PhotoSelectorActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent2.putExtra("limit", 12 - adapter.getItemCount());//number是选择图片的数量
                startActivityForResult(intent2, 0);
                break;
            case R.id.lay_back:
//             Intent intent1 = new Intent(this,MainActivity.class);
//             startActivity(intent1);
                finish();
                break;
        }

    }

    @Subscribe(id = 4)
    public void printss(String message) {
        if (Integer.parseInt(message) < 13) {
            Log.d("printssSize", message);
            imageAddButton.setVisibility(View.VISIBLE);
            imageViewNumber.setVisibility(View.VISIBLE);
            imageViewNumber.setText(String.valueOf(message) + "/12");
        } else {
            imageAddButton.setVisibility(View.GONE);
            imageViewNumber.setVisibility(View.GONE);
        }
    }
//@Subscribe(id = 11)
// public  void photoResule(Bitmap bitmap){
//      File file = Utils.getFile(bitmap);
//    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//    MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
//    mPresenter.postImage(this, map, imageBodyPart);
//}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("Authorization", "usERa" + getToken());
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    addImageView(paths);
                    imageViewNumber.setText(adapter.getItemCount() + "/12");
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
                    if (adapter.getItemCount() > 11) {
//                       imageAddButton.setVisibility(View.GONE);
                        Utils.showMyToast(Toast.makeText(this, "添加的图片已经是最大值了", Toast.LENGTH_SHORT), 400);
                    }


                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addImageView(List<String> path) {
        for (int i = 0; i < path.size(); i++) {
            if (Arrays.asList(imagePath).contains(path.get(i))) {

                Utils.showMyToast(Toast.makeText(this, "已经存在", Toast.LENGTH_SHORT), 400);
            } else {

                imagePath.add(path.get(i));
                Log.d("imagePathc", path.toString());
                imageviewRecyclerview.setVisibility(View.VISIBLE);
                pcPhoto.setVisibility(View.GONE);
                pcAdd.setVisibility(View.GONE);
                imageViewNumber.setVisibility(View.VISIBLE);
                imageAddButton.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                imageviewRecyclerview.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                Log.d("imagePaths", imagePath.toString());
                adapter = new ImageViewAdapter(imagePath, this);
                imageviewRecyclerview.setAdapter(adapter);
                adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position, String path) {
                        String deleteImagePath = path;

                    }

                    @Override
                    public void onItemClick(int Position) {

                    }
                });
            }
        }
    }

    private String getToken() {
        SharedPreferences sp = this.getSharedPreferences("userDate", MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        System.out.println(c);
        return sp.getString("Authorization", "");
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

        if (bean.getErrorCode() != 0) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getEdittect() {
        return titl4.getText().toString();
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

    public void getPostImageUrls(String urils) {

        imageUrls.add(urils);
        Log.d("imageUrlsiZE", String.valueOf(imageUrls.size()));


    }

    public void getDate(List<String> date, List<String> ids) {
        initOptionPicker(date, ids);
    }

    private void initOptionPicker(final List<String> typeName, final List<String> ids) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                pcButton.setText(typeName.get(options1));
                typeIds = ids.get(options1);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
