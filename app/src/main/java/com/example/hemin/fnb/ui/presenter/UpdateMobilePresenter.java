package com.example.hemin.fnb.ui.presenter;

import com.example.hemin.fnb.ui.activity.MobileRegister;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;
import com.example.hemin.fnb.ui.contract.MobileRegisterContract;
import com.example.hemin.fnb.ui.model.UpdateMobileModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class UpdateMobilePresenter extends BasePresenter<MobileRegister> implements MobileRegisterContract.persenter {
    private MobileRegisterContract.mobile mobiles;
    private MobileRegisterContract.mobile2 mobile2;

    public UpdateMobilePresenter() {
        mobiles = new UpdateMobileModel();
        mobile2 = new UpdateMobileModel();
    }

    @Override
    public void getCode(String mobile, final int status) {
        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();
        mobiles.getCode(mobile)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean,status);
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


    @Override
    public void updateMobile(Map map, RequestBody body, final int status) {
        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();
        mobile2.getUpdateMobile(map, body)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean,status);
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
