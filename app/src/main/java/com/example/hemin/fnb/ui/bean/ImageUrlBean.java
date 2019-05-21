package com.example.hemin.fnb.ui.bean;

public class ImageUrlBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"url":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/cdsthxq9u6"}
     */


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/cdsthxq9u6
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
