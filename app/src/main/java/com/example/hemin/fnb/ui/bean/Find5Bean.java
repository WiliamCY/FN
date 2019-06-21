package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class Find5Bean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":1,"size":10,"pages":1,"current":1,"records":[{"collectionId":1,"giveNum":2,"collectionNum":2,"wantNum":2,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk"}]}
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
         * total : 1
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"collectionId":1,"giveNum":2,"collectionNum":2,"wantNum":2,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk"}]
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
             * collectionId : 1
             * giveNum : 2
             * collectionNum : 2
             * wantNum : 2
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk
             */

            private int collectionId;
            private String giveNum;
            private String collectionNum;
            private String wantNum;
            private String imagesUrl;

            public int getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(int collectionId) {
                this.collectionId = collectionId;
            }

            public String getGiveNum() {
                return giveNum;
            }

            public void setGiveNum(String giveNum) {
                this.giveNum = giveNum;
            }

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
            }

            public String getWantNum() {
                return wantNum;
            }

            public void setWantNum(String wantNum) {
                this.wantNum = wantNum;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }
        }
    }
}
