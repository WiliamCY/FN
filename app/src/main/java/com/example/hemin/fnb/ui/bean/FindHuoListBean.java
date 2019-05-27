package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FindHuoListBean {


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 2
         * size : 3
         * pages : 1
         * current : 1
         * records : [{"activityId":1,"activityName":null,"activityContentUrl":"http://h5.eqxiu.com/ls/7gRL4YtE","activityUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wcqevg9psf","activityType":null,"activityNum":2,"userId":null,"activitySort":null,"createTime":null,"updateTime":null},{"activityId":2,"activityName":null,"activityContentUrl":"http://h5.eqxiu.com/ls/5cEJGBE2","activityUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/d89yzu7enx","activityType":null,"activityNum":3,"userId":null,"activitySort":null,"createTime":null,"updateTime":null}]
         */

        private int total;
        private int size;
        private int pages;
        private int current;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * activityId : 1
             * activityName : null
             * activityContentUrl : http://h5.eqxiu.com/ls/7gRL4YtE
             * activityUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wcqevg9psf
             * activityType : null
             * activityNum : 2
             * userId : null
             * activitySort : null
             * createTime : null
             * updateTime : null
             */

            private String activityId;
            private Object activityName;
            private String activityContentUrl;
            private String activityUrl;
            private Object activityType;
            private String activityNum;
            private Object userId;
            private Object activitySort;
            private Object createTime;
            private Object updateTime;

            public String getActivityId() {
                return activityId;
            }

            public void setActivityId(String activityId) {
                this.activityId = activityId;
            }

            public Object getActivityName() {
                return activityName;
            }

            public void setActivityName(Object activityName) {
                this.activityName = activityName;
            }

            public String getActivityContentUrl() {
                return activityContentUrl;
            }

            public void setActivityContentUrl(String activityContentUrl) {
                this.activityContentUrl = activityContentUrl;
            }

            public String getActivityUrl() {
                return activityUrl;
            }

            public void setActivityUrl(String activityUrl) {
                this.activityUrl = activityUrl;
            }

            public Object getActivityType() {
                return activityType;
            }

            public void setActivityType(Object activityType) {
                this.activityType = activityType;
            }

            public String getActivityNum() {
                return activityNum;
            }

            public void setActivityNum(String activityNum) {
                this.activityNum = activityNum;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getActivitySort() {
                return activitySort;
            }

            public void setActivitySort(Object activitySort) {
                this.activitySort = activitySort;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
