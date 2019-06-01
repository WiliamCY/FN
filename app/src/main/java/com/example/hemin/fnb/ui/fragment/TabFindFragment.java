package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.adapter.FindAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.FindPresenter;
import com.example.hemin.fnb.ui.util.Utils;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabFindFragment extends BaseMvpFragment<FindPresenter> implements FindContract.View {
    @BindView(R.id.find_recyclerview)
    RecyclerView findRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    Unbinder unbinder1;
    @BindView(R.id.find_logo)
    ImageView findLogo;
    @BindView(R.id.lay1)
    LinearLayout lay1;
    private int sendPost = 0;
    private Map Authorization = new HashMap();
    private PopupWindow pw;
    private View myView;
    private TextView userTitles, userAbouts;
    private Button userUpdates;

    @Override
    protected void initView(View view) {
        mPresenter = new FindPresenter();
        mPresenter.attachView(this);
        AlertDialogUpdateStyle();
        initDate();
    }

    private void initDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        Authorization = Utils.getAuthorization(getActivity());
        String userId = sp.getString("userId", "");
        mPresenter.pageListHuo(getActivity(), Authorization, 1, 3);

//        mPresenter.getDaily(getActivity(), Authorization, Long.parseLong(userId));
//        mPresenter.guessLove(getActivity(), Authorization);
//        mPresenter.getRankingList(getActivity(), Authorization, 1, 10);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.find_view;
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
        if(bean.getErrorCode() != 0){
                    Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        sendPost++;

        initNetWork(sendPost);

    }

    private void initNetWork(int sendPost) {
        Log.d("sendPost", String.valueOf(sendPost));
        if (sendPost <= 1) {
            mPresenter.addHua(getActivity(), Authorization, 1, 1);
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Date(List<FindBean> bean, int status) {
        initRecyclerView(bean);

    }


    private void initRecyclerView(final List<FindBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        FindAdapter adapter = new FindAdapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        findRecyclerview.setAdapter(adapter);
        final int size = adapter.getItemCount();
        Log.d("findSize1:", String.valueOf(size));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), UserAbout.class);
                String paths = bean.get(position).getPathUrl();
                intent.putExtra("path", paths);
                startActivity(intent);

            }
        });


    }


    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        unbinder1.unbind();
    }

//    /**
//     * layout_parent = HorizontalScrollView
//     *
//     * @param
//     * @param index 代表这个数据源插入的位置 比如你一个数据源有3条数据 你的addView_2中的index就应该是3
//     */
//    private void addView_1(List<FindFirstBean.DataBean.RecordsBean> beans, int index) {
//        for (int i = 0; i < beans.size(); i++) {
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card_view, null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
//            view.setLayoutParams(params);
//            //这里写对应的控件数据
////            TextView tv = view.findViewById(R.id.tv);
//
//            //添加进父布局
//            layoutParent.addView(view, index);
//            index++;
//        }
//    }
//
//    /**
//     * layout_parent = HorizontalScrollView
//     *
//     * @param
//     */
//    private void addView_2(List<Find2Bean.DataBean.RecordsBean> beans, int index) {
//        for (int i = 0; i < beans.size(); i++) {
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card2_view, null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
//            view.setLayoutParams(params);
//            //这里写对应的控件数据
////            TextView tv = view.findViewById(R.id.tv);
//
//            //添加进父布局
//            layoutParent.addView(view);
//            index++;
//        }
//    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder1 = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
    private void AlertDialogUpdateStyle() {
        new PgyUpdateManager.Builder()
                .setForced(true)                //设置是否强制更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk
                .setUpdateManagerListener(new UpdateManagerListener() {

                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is no new version");
//                        Toast.makeText(getActivity(), "当前为最新版本", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateAvailable(final AppBean appBean) {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());

                        //调用以下方法，DownloadFileListener 才有效；如果完全使用自己的下载方法，不需要设置DownloadFileListener
//                        new AlertDialog(PersionActivity.this).builder().setMsg("发现新版本是否更新?")
//                                .setPositiveButton("确定", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
//                                    }
//                                }).setNegativeButton("取消", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getApplication(), "取消更新", Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
                        update();
                    }


                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        Log.e("pgyer", "check update failed ", e);
//                        Toast.makeText(getApplicationContext(),"检查更新失败",Toast.LENGTH_SHORT).show();

                    }
                })
                //注意 ：下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                .setDownloadFileListener(new DownloadFileListener() {   // 使用蒲公英提供的下载方法，这个接口才有效。
                    @Override
                    public void downloadFailed() {
                        //下载失败
                        Log.e("pgyer", "download apk failed");
//                        Toast.makeText(getApplicationContext(),"下载失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        Log.e("pgyer", "download apk failed");
//                        Toast.makeText(getApplicationContext(),"下载成功",Toast.LENGTH_SHORT).show();
                        PgyUpdateManager.installApk(uri);  // 使用蒲公英提供的安装方法提示用户 安装apk

                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        Log.e("pgyer", "update download apk progress : " + integers[0]);

                    }
                })
                .register();

    }

    private void update() {
        Toast.makeText(getActivity(),"请更新",Toast.LENGTH_SHORT).show();
        myView = LayoutInflater.from(getActivity()).inflate(R.layout.user_about_popwindow, null, true);
        userTitles = myView.findViewById(R.id.user_update_title);
        userAbouts = myView.findViewById(R.id.tv_update_content);
//        bankcard = myView.findViewById(R.id.bankcard);
        pw = new PopupWindow(getActivity());
        pw.setContentView(myView);
        int popwidth = getResources().getDisplayMetrics().widthPixels;
        int popheight = getResources().getDisplayMetrics().heightPixels;
        pw.setWidth(Math.round(popwidth * 0.8f));
        pw.setHeight(Math.round(popheight * 0.45f));
        pw.setOutsideTouchable(false);
        pw.setFocusable(false);
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        darkenBackground(0.8f);
        pw.setBackgroundDrawable(dw);
        pw.showAsDropDown(lay1);
//        pw.showAtLocation(lay1, Gravity.CENTER | Gravity.CENTER, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                darkenBackground(1f);
//                pw.dismiss();
            }
        });
//        wthdrawColse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pw.dismiss();
//            }
//        });
//        Alipay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent alipay = new Intent(getActivity(), FragmentAlipay.class);
//                startActivity(alipay);
//            }
//        });
//        bankcard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent backCadrk = new Intent(getActivity(), FragmentBankWi.class);
//                startActivity(backCadrk);
//            }
//        });
    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams ip = getActivity().getWindow().getAttributes();
        ip.alpha = bgcolor;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(ip);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
