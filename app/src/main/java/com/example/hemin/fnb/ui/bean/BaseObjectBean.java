package com.example.hemin.fnb.ui.bean;



/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：对象
 */
public class BaseObjectBean<T> {

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */

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
