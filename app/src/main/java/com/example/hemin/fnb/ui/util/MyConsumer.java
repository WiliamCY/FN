package com.example.hemin.fnb.ui.util;

import android.util.Log;
import android.widget.Toast;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.functions.Consumer;

/**
 * Created by DK
 * on 2019/05/17
 *
 * @description
 */
public abstract class MyConsumer<T> implements Consumer<BaseObjectBean> {
    private BaseObjectBean baseObjectBean = new BaseObjectBean();

    @Override
    public void accept(BaseObjectBean baseObjectBean) throws Exception {
        Gson gson = new Gson();
        String bean2 = gson.toJson(baseObjectBean);
        if (baseObjectBean.getErrorCode() == 0) {
            try {
                JSONObject jsonObject = new JSONObject(bean2);
                Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                T result = gson.fromJson(jsonObject.getJSONObject("data").toString(), type);
                accept(result, true, baseObjectBean);
            } catch (Exception e) {
                Log.d("filess",e.toString());
            }

        } else {
            accept_error(bean2, false, baseObjectBean.getErrorMsg());
        }

    }

    public abstract void accept(T bean, boolean success, BaseObjectBean beans);

    public abstract void accept_error(String bean, boolean success, String message);
}
