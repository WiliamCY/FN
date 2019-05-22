package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class AppraisaBean {
    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":2,"size":10,"pages":1,"current":1,"records":[{"collectionId":5,"collectionNum":"432260195","imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","collectionAudit":"0","createTime":"2019-05-21"},{"collectionId":6,"collectionNum":"432287330","imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","collectionAudit":"0","createTime":"2019-05-21"}]}
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
         * total : 2
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"collectionId":5,"collectionNum":"432260195","imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","collectionAudit":"0","createTime":"2019-05-21"},{"collectionId":6,"collectionNum":"432287330","imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","collectionAudit":"0","createTime":"2019-05-21"}]
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
             * collectionId : 5
             * collectionNum : 432260195
             * imagesUrl : [https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast
             * collectionAudit : 0
             * createTime : 2019-05-21
             */

            private int collectionId;
            private String collectionNum;
            private String imagesUrl;
            private String collectionAudit;
            private String createTime;

            public int getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(int collectionId) {
                this.collectionId = collectionId;
            }

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public String getCollectionAudit() {
                return collectionAudit;
            }

            public void setCollectionAudit(String collectionAudit) {
                this.collectionAudit = collectionAudit;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
