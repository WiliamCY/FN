package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FindHuaTiBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":2,"size":1,"pages":2,"current":1,"records":[{"topicId":1,"topicName":"111","userId":14,"topicContent":"https://www.baidu.com","tContent":null,"topicSimple":"","topicNum":4,"topicSort":0,"topicType":1,"topicUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/lftswe3mph","createTime":"2019-05-27 17:17:19","updateTime":"2019-05-27 17:17:23"}]}
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
         * total : 2
         * size : 1
         * pages : 2
         * current : 1
         * records : [{"topicId":1,"topicName":"111","userId":14,"topicContent":"https://www.baidu.com","tContent":null,"topicSimple":"","topicNum":4,"topicSort":0,"topicType":1,"topicUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/lftswe3mph","createTime":"2019-05-27 17:17:19","updateTime":"2019-05-27 17:17:23"}]
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
             * topicId : 1
             * topicName : 111
             * userId : 14
             * topicContent : https://www.baidu.com
             * tContent : null
             * topicSimple :
             * topicNum : 4
             * topicSort : 0
             * topicType : 1
             * topicUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/lftswe3mph
             * createTime : 2019-05-27 17:17:19
             * updateTime : 2019-05-27 17:17:23
             */

            private int topicId;
            private String topicName;
            private int userId;
            private String topicContent;
            private Object tContent;
            private String topicSimple;
            private int topicNum;
            private int topicSort;
            private int topicType;
            private String topicUrl;
            private String createTime;
            private String updateTime;

            public int getTopicId() {
                return topicId;
            }

            public void setTopicId(int topicId) {
                this.topicId = topicId;
            }

            public String getTopicName() {
                return topicName;
            }

            public void setTopicName(String topicName) {
                this.topicName = topicName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getTopicContent() {
                return topicContent;
            }

            public void setTopicContent(String topicContent) {
                this.topicContent = topicContent;
            }

            public Object getTContent() {
                return tContent;
            }

            public void setTContent(Object tContent) {
                this.tContent = tContent;
            }

            public String getTopicSimple() {
                return topicSimple;
            }

            public void setTopicSimple(String topicSimple) {
                this.topicSimple = topicSimple;
            }

            public int getTopicNum() {
                return topicNum;
            }

            public void setTopicNum(int topicNum) {
                this.topicNum = topicNum;
            }

            public int getTopicSort() {
                return topicSort;
            }

            public void setTopicSort(int topicSort) {
                this.topicSort = topicSort;
            }

            public int getTopicType() {
                return topicType;
            }

            public void setTopicType(int topicType) {
                this.topicType = topicType;
            }

            public String getTopicUrl() {
                return topicUrl;
            }

            public void setTopicUrl(String topicUrl) {
                this.topicUrl = topicUrl;
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
}
