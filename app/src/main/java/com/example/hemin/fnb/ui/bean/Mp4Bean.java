package com.example.hemin.fnb.ui.bean;

public class Mp4Bean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"name":"video_1562750812262.mp4","url":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/MP4/7dgfkq2sw5"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : video_1562750812262.mp4
         * url : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/MP4/7dgfkq2sw5
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
