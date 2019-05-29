package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class MessageBean1 {
    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":1,"size":10,"pages":1,"current":1,"records":[{"magazineId":1,"magazineName":"玩瓷人群的三个层次","magazineStage":"第一期","magazineUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/65sfwkdgbx","updateTime":"2019-05-27 09:46:06","nickname":"test"}]}
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
         * records : [{"magazineId":1,"magazineName":"玩瓷人群的三个层次","magazineStage":"第一期","magazineUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/65sfwkdgbx","updateTime":"2019-05-27 09:46:06","nickname":"test"}]
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
             * magazineId : 1
             * magazineName : 玩瓷人群的三个层次
             * magazineStage : 第一期
             * magazineUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/65sfwkdgbx
             * updateTime : 2019-05-27 09:46:06
             * nickname : test
             */

            private int magazineId;
            private String magazineName;
            private String magazineStage;
            private String magazineUrl;
            private String updateTime;
            private String nickname;

            public int getMagazineId() {
                return magazineId;
            }

            public void setMagazineId(int magazineId) {
                this.magazineId = magazineId;
            }

            public String getMagazineName() {
                return magazineName;
            }

            public void setMagazineName(String magazineName) {
                this.magazineName = magazineName;
            }

            public String getMagazineStage() {
                return magazineStage;
            }

            public void setMagazineStage(String magazineStage) {
                this.magazineStage = magazineStage;
            }

            public String getMagazineUrl() {
                return magazineUrl;
            }

            public void setMagazineUrl(String magazineUrl) {
                this.magazineUrl = magazineUrl;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
