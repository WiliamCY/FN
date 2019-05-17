package com.example.hemin.fnb.ui.bean;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.security.Timestamp;

public class BaseObjectBean<T> {



    private int code;

    private String msg;

    private T data;

    public int getErrorCode() {
        return code;
    }

    public void setErrorCode(int errorCode) {
        this.code = errorCode;
    }

    public String getErrorMsg() {
        return msg;
    }

    public void setErrorMsg(String errorMsg) {
        this.msg = errorMsg;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }


}
