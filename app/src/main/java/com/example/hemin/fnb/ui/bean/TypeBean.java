package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class TypeBean {
    /**
     * code : 0
     * msg : 请求成功
     * data : [{"ctId":1,"ctName":"石器","createTime":"","updateTime":""},{"ctId":2,"ctName":"玉器","createTime":"","updateTime":""},{"ctId":3,"ctName":"骨器牙器","createTime":"","updateTime":""},{"ctId":4,"ctName":"木器","createTime":"","updateTime":""},{"ctId":5,"ctName":"竹器","createTime":"","updateTime":""},{"ctId":6,"ctName":"铜器","createTime":"","updateTime":""},{"ctId":7,"ctName":"铁器","createTime":"","updateTime":""},{"ctId":8,"ctName":"金器","createTime":"","updateTime":""},{"ctId":9,"ctName":"银器","createTime":"","updateTime":""},{"ctId":10,"ctName":"铅锌器","createTime":"","updateTime":""},{"ctId":11,"ctName":"瓷器","createTime":"","updateTime":""},{"ctId":12,"ctName":"漆器","createTime":"","updateTime":""},{"ctId":13,"ctName":"玻璃器","createTime":"","updateTime":""},{"ctId":14,"ctName":"珐琅器","createTime":"","updateTime":""},{"ctId":15,"ctName":"纺织品","createTime":"","updateTime":""},{"ctId":16,"ctName":"纸类文物","createTime":"","updateTime":""},{"ctId":17,"ctName":"其他","createTime":"","updateTime":""}]
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ctId : 1
         * ctName : 石器
         * createTime :
         * updateTime :
         */

        private String ctId;
        private String ctName;
        private String createTime;
        private String updateTime;

        public String getCtId() {
            return ctId;
        }

        public void setCtId(String ctId) {
            this.ctId = ctId;
        }

        public String getCtName() {
            return ctName;
        }

        public void setCtName(String ctName) {
            this.ctName = ctName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
