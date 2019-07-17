package com.example.hemin.fnb.ui.presenter;

import com.example.hemin.fnb.ui.activity.MyFans;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.contract.FanContract;
import com.example.hemin.fnb.ui.model.FansModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.Map;

import io.reactivex.functions.Consumer;

public class FansPresenter extends BasePresenter<MyFans> implements FanContract.Presenter {
  private   FanContract.modle modle;
  private FanContract.modle2 modle2;
  public  FansPresenter(){
      modle = new FansModel();
      modle2 = new FansModel();
  }
    @Override
    public void GZ(Map token, int current, int size, int userId, final int status) {
      if(!isViewAttached()){
        return;
      }

//      mView.showLoading();
      modle.GZ(token,current,size,userId)
              .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
              .as(mView.<BaseObjectBean>bindAutoDispose())
              .subscribe(new Consumer<BaseObjectBean>() {
                @Override
                public void accept(BaseObjectBean bean) throws Exception {

//                  mView.hideLoading();
                  if(bean.getErrorCode() == 0){
                      GuanZhuBean.DataBean bean1 = (GuanZhuBean.DataBean) bean.getData();
                      mView.date(bean1.getRecords(),status);
                  }
                }
              }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                  mView.onError(throwable);
//                  mView.hideLoading();
                }
              });
    }

    @Override
    public void fs(Map token, int current, int size, final int status) {
      if (!isViewAttached()) {
        return;
      }

//      mView.showLoading();
      modle2.fs(token, current, size)
              .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
              .as(mView.<BaseObjectBean>bindAutoDispose())
              .subscribe(new Consumer<BaseObjectBean>() {
                @Override
                public void accept(BaseObjectBean bean) throws Exception {

//                  mView.hideLoading();
                  if (bean.getErrorCode() == 0) {
                      GuanZhuBean.DataBean bean1 = (GuanZhuBean.DataBean) bean.getData();
                      mView.date(bean1.getRecords(),status);
                  }
                }
              }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                  mView.onError(throwable);
//                  mView.hideLoading();
                }
              });
    }
}
