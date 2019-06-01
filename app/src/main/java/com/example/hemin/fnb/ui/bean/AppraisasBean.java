package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class AppraisasBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":1,"size":10,"pages":1,"current":1,"records":[{"collectionId":15,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/zkafctvy74","certificateNum":"","insuranceNum":"","collectionAht":"真品无误","caId":1,"collectionCost":"千万以上","collectionTime":"秦代","collectionAppearance":"全品","collectionType":"竹器"}]}
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
         * total : 1
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"collectionId":15,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/zkafctvy74","certificateNum":"","insuranceNum":"","collectionAht":"真品无误","caId":1,"collectionCost":"千万以上","collectionTime":"秦代","collectionAppearance":"全品","collectionType":"竹器"}]
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
             * collectionId : 15
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/zkafctvy74
             * certificateNum :
             * insuranceNum :
             * collectionAht : 真品无误
             * caId : 1
             * collectionCost : 千万以上
             * collectionTime : 秦代
             * collectionAppearance : 全品
             * collectionType : 竹器
             */

            private int collectionId;
            private String imagesUrl;
            private String certificateNum;
            private String insuranceNum;
            private String collectionAht;
            private int caId;
            private String collectionCost;
            private String collectionTime;
            private String collectionAppearance;
            private String collectionType;

            public int getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(int collectionId) {
                this.collectionId = collectionId;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public String getCertificateNum() {
                return certificateNum;
            }

            public void setCertificateNum(String certificateNum) {
                this.certificateNum = certificateNum;
            }

            public String getInsuranceNum() {
                return insuranceNum;
            }

            public void setInsuranceNum(String insuranceNum) {
                this.insuranceNum = insuranceNum;
            }

            public String getCollectionAht() {
                return collectionAht;
            }

            public void setCollectionAht(String collectionAht) {
                this.collectionAht = collectionAht;
            }

            public int getCaId() {
                return caId;
            }

            public void setCaId(int caId) {
                this.caId = caId;
            }

            public String getCollectionCost() {
                return collectionCost;
            }

            public void setCollectionCost(String collectionCost) {
                this.collectionCost = collectionCost;
            }

            public String getCollectionTime() {
                return collectionTime;
            }

            public void setCollectionTime(String collectionTime) {
                this.collectionTime = collectionTime;
            }

            public String getCollectionAppearance() {
                return collectionAppearance;
            }

            public void setCollectionAppearance(String collectionAppearance) {
                this.collectionAppearance = collectionAppearance;
            }

            public String getCollectionType() {
                return collectionType;
            }

            public void setCollectionType(String collectionType) {
                this.collectionType = collectionType;
            }
        }
    }
}
