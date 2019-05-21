package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.example.hemin.fnb.ui.activity.PublishingCollections;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;
import com.example.hemin.fnb.ui.bean.TypeBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.model.GetTypeModel;
import com.example.hemin.fnb.ui.model.ImageUpdateModel;
import com.example.hemin.fnb.ui.model.SubImageModel;
import com.example.hemin.fnb.ui.net.RxScheduler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GetTypePresenter extends BasePresenter<PublishingCollections> implements GetTypeContract.GetTytpePresebter {
    private GetTypeContract.GetTypeModle modle;
    private GetTypeContract.Model modle2;
    private GetTypeContract.AddImageButton modle3;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private String typeName, typeId, typeUpdate, typeCreate;


    public GetTypePresenter() {
        modle = new GetTypeModel();
        modle2 = new ImageUpdateModel();
      modle3 = new SubImageModel();
    }

    @Override
    public void getType(final Context context, Map token) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        modle.getType(context, token)
                .compose(RxScheduler.<BaseObjectBean<List<TypeBean.DataBean>>>Flo_io_main())
                .as(mView.<BaseObjectBean<List<TypeBean.DataBean>>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                               @Override
                               public void accept(BaseObjectBean bean) throws Exception {
//                        mView.onSuccess(bean);
                                   mView.hideLoading();
                                   List<TypeBean.DataBean> typeList = (List<TypeBean.DataBean>) bean.getResult();
                                   names.clear();
                                   if (typeList.size() > 0) {
                                       for (int i = 0; i < typeList.size(); i++) {
                                           typeId = typeList.get(i).getCtId();
                                           typeName = typeList.get(i).getCtName();
                                           typeCreate = typeList.get(i).getCreateTime();
                                           typeUpdate = typeList.get(i).getUpdateTime();
                                           names.add(typeName);
                                           ids.add(typeId);
                                       }

                                       mView.getDate(names,ids);

                                   }

                               }

                           }
                );

    }

    //    @Override
    public void postImage(Context context, Map token, MultipartBody.Part partList) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        modle2.postImage(context, token, partList)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        ImageUrlBean.DataBean url = (ImageUrlBean.DataBean) bean.getResult();
                        String urls = url.getUrl();
                       mView.getPostImageUrls(urls);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void submitImage(Context context,Map token, RequestBody body) {
           if(!isViewAttached()){
               return;
           }
           mView.showLoading();
           modle3.submitImage(context,token,body)
                   .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                   .as(mView.<BaseObjectBean>bindAutoDispose())
                   .subscribe(new Consumer<BaseObjectBean>() {
                       @Override
                       public void accept(BaseObjectBean bean) throws Exception {
                           mView.onSuccess(bean);
                           mView.hideLoading();



                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           mView.onError(throwable);
                           mView.hideLoading();
                       }
                   });

    }

}
