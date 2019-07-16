package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.adapter.Message3Apdater;
import com.example.hemin.fnb.ui.adapter.MessageApdater;
import com.example.hemin.fnb.ui.adapter.MessagesApdater;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.contract.MessageContract;
import com.example.hemin.fnb.ui.presenter.MessagePresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageFragment extends BaseMvpFragment<MessagePresenter> implements MessageContract.View {
    @BindView(R.id.message_recyclerview)
    RecyclerView messageRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.title_1)
    TextView title1;
    private Map map = new HashMap();
    private String userId;
    private ArrayList<String> recordPaths = new ArrayList<>();
    private long finderid, finderids;
    private List<MessageBean1.DataBean.RecordsBean> data4 = new ArrayList<>();
    private List<MessageBean2.DataBean.RecordsBean> data = new ArrayList<>();
    private List<MessageBean3.DataBean.RecordsBean> data3 = new ArrayList<>();
    private MessageApdater apdater1 = new MessageApdater(R.layout.message_adapter, data4);
    private MessagesApdater adapter2 = new MessagesApdater(R.layout.message_adapters, data);
    private Message3Apdater adapter3 = new Message3Apdater(R.layout.message_adapter, data3);
    private int pageIndex = 1;

protected int getLayoutId() {
    return R.layout.message;
}


    @Override
    public void lazyInitView(View view) {
        mPresenter = new MessagePresenter();
        mPresenter.attachView(this);
        int index = this.getArguments().getInt("index");
        initDate(index);
    }

    public static MessageFragment getInstance(String status) {
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        if (status.equals("资料库")) {
            bundle.putInt("index", 0);
        } else if (status.equals("推荐"))
            bundle.putInt("index", 1);
        else {
            bundle.putInt("index", 2);
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initDate(int index) {
        Log.d("MessageIndex", String.valueOf(index));
        map = Utils.getAuthorization(getActivity());
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        if (index == 0) {
            mPresenter.getMaga(getActivity(), map, 1, 10, "1", 1);
        } else if (index == 1) {
            mPresenter.getTuiJian(getActivity(), map, 1, 10, Integer.parseInt(userId), 2);
        } else {
            mPresenter.getGuanZhu(getActivity(), map, 1, 10, Integer.parseInt(userId), 3);
        }
    }


    @Override
    public void Date(final Object object, int index) {
        Log.d("messageIndex", String.valueOf(index));
        if (index == 1) {
            initRecyclerview1((List<MessageBean1.DataBean.RecordsBean>) object);
        } else if (index == 2) {
            initRecyclerview2((List<MessageBean2.DataBean.RecordsBean>) object);
        } else if (index == 3) {
            initRecyclerview3((List<MessageBean3.DataBean.RecordsBean>) object);
        } else if (index == 21) {
            initRecyclerview2((List<MessageBean2.DataBean.RecordsBean>) object);
            refreshLayout.finishRefresh(100);
        } else if (index == 22) {
            if (((List<MessageBean2.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                adapter2.addData((List<MessageBean2.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        } else if (index == 31) {
//            adapter3.replaceData((List<MessageBean3.DataBean.RecordsBean>) object);
            initRecyclerview3((List<MessageBean3.DataBean.RecordsBean>) object);
            refreshLayout.finishRefresh(100);
        } else if (index == 32) {
            if (((List<MessageBean3.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                adapter3.addData((List<MessageBean3.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        } else if (index == 11) {
            initRecyclerview1((List<MessageBean1.DataBean.RecordsBean>) object);
            refreshLayout.finishRefresh(100);
        } else if (index == 12) {
            if (((List<MessageBean1.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                apdater1.addData((List<MessageBean1.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        }
    }

    @Override
    public void DateUserId(Object object, String userId, String content, String userUrl, String nickName,String isCollectionSum,String isGiveNum,String isFocus,int focusNum ,int giveNum  ) {

        Images((List<MessageImageBean.DataBean.ImagesBean>) object, userId, content, userUrl, nickName,isCollectionSum,isGiveNum,isFocus,focusNum,giveNum);

    }

    private void Images(List<MessageImageBean.DataBean.ImagesBean> object, String userId, String content, String userUrl, String nickName,String isCollectionSum,String isGiveNum,String isFocus,int focusNum ,int giveNum) {
        recordPaths.clear();
        for (int i = 0; i < object.size(); i++) {
            String path = object.get(i).getImagesUrl();
            Log.d("messageAdapter3", path);
            recordPaths.add(path);
        }

        Log.d("messaePaths", recordPaths.toString());
        Intent imgIntent = new Intent(getActivity(), TaskBigImgActivity.class);
        imgIntent.putStringArrayListExtra("paths", recordPaths);
        imgIntent.putExtra("title", "关注");
        imgIntent.putExtra("position", object.size());
        imgIntent.putExtra("finderid", finderid);
        imgIntent.putExtra("userId", userId);
        imgIntent.putExtra("StringContent", content);
        imgIntent.putExtra("userUrl", userUrl);
        imgIntent.putExtra("isFocus",isFocus);
        imgIntent.putExtra("nickName", nickName);
        imgIntent.putExtra("isCollectionSum",isCollectionSum);
        imgIntent.putExtra("isGiveNum",isGiveNum);
        imgIntent.putExtra("focusNum",focusNum);
        imgIntent.putExtra("giveNum",giveNum);
        startActivity(imgIntent);

    }

    private void initRecyclerview1(final List<MessageBean1.DataBean.RecordsBean> bean) {
        if (bean.size() == 0) {
            messageRecyclerview.setVisibility(View.GONE);
            image1.setVisibility(View.VISIBLE);
            title1.setText("暂无数据");
            title1.setVisibility(View.VISIBLE);
        } else {
            messageRecyclerview.setVisibility(View.VISIBLE);
            image1.setVisibility(View.GONE);
            title1.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        messageRecyclerview.setLayoutManager(layoutManager);
        apdater1 = new MessageApdater(R.layout.message_adapter, bean);
        Log.d("beanDate", bean.toString());
        messageRecyclerview.setAdapter(apdater1);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.getMaga(getActivity(), map, 1, 10, "1", 11);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.getMaga(getActivity(), map, pageIndex, 10, "1", 12);
            }
        });

        apdater1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), UserAbout.class);
                String paths = bean.get(position).getMagazineContent();
                intent.putExtra("path", paths);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerview2(final List<MessageBean2.DataBean.RecordsBean> bean) {
        if (bean.size() == 0) {
            messageRecyclerview.setVisibility(View.GONE);
            image1.setVisibility(View.VISIBLE);
            title1.setText("暂无数据");
            title1.setVisibility(View.VISIBLE);
        } else {
            messageRecyclerview.setVisibility(View.VISIBLE);
            image1.setVisibility(View.GONE);
            title1.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        messageRecyclerview.setLayoutManager(layoutManager);
        adapter2 = new MessagesApdater(R.layout.message_adapters, bean);
        messageRecyclerview.setAdapter(adapter2);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.getTuiJian(getActivity(), map, 1, 10, Integer.parseInt(userId), 21);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.getTuiJian(getActivity(), map, pageIndex, 10, Integer.parseInt(userId), 22);
            }
        });

        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                finderid = bean.get(position).getFriendId();
                mPresenter.getFidner(getActivity(), map, finderid, Integer.parseInt(userId));
            }
        });

    }

    private void initRecyclerview3(final List<MessageBean3.DataBean.RecordsBean> bean) {
        Log.d("beanRecyCledrSize",String.valueOf(bean.size()));
        if (bean.size() == 0) {
            messageRecyclerview.setVisibility(View.GONE);
            image1.setVisibility(View.VISIBLE);
            title1.setVisibility(View.VISIBLE);
        } else {
            messageRecyclerview.setVisibility(View.VISIBLE);
            image1.setVisibility(View.GONE);
            title1.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        messageRecyclerview.setLayoutManager(layoutManager);
        adapter3 = new Message3Apdater(R.layout.message_adapters, bean);
        messageRecyclerview.setAdapter(adapter3);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.getGuanZhu(getActivity(), map, 1, 10, Integer.parseInt(userId), 31);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.getGuanZhu(getActivity(), map, pageIndex, 10, Integer.parseInt(userId), 32);
            }
        });

        adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                finderid = bean.get(position).getFriendId();
                mPresenter.getFidner(getActivity(), map, finderid, Integer.parseInt(userId));
            }
        });
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() != 0) {
            Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
