package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FindDeilyBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"collectionId":1,"collectionNum":"430408875","ctName":"石器","collectionDetails":"啊 dasd 啊","list":[{"imagesId":1,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk","ieId":8,"ieName":"审核通过"},{"imagesId":2,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/95xwvyng7m","ieId":8,"ieName":"审核通过"},{"imagesId":3,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/4tkvmlgr2n","ieId":8,"ieName":"审核通过"},{"imagesId":4,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wg4xf5ukz9","ieId":8,"ieName":"审核通过"},{"imagesId":5,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/au9ygbfpqs","ieId":8,"ieName":"审核通过"}],"dailyId":1,"giveNum":1,"isGiveNum":0,"collectionSum":1,"isCollectionSum":0,"wantNum":1,"isWantNum":0}
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
         * collectionId : 1
         * collectionNum : 430408875
         * ctName : 石器
         * collectionDetails : 啊 dasd 啊
         * list : [{"imagesId":1,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk","ieId":8,"ieName":"审核通过"},{"imagesId":2,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/95xwvyng7m","ieId":8,"ieName":"审核通过"},{"imagesId":3,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/4tkvmlgr2n","ieId":8,"ieName":"审核通过"},{"imagesId":4,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wg4xf5ukz9","ieId":8,"ieName":"审核通过"},{"imagesId":5,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/au9ygbfpqs","ieId":8,"ieName":"审核通过"}]
         * dailyId : 1
         * giveNum : 1
         * isGiveNum : 0
         * collectionSum : 1
         * isCollectionSum : 0
         * wantNum : 1
         * isWantNum : 0
         */

        private int collectionId;
        private String collectionNum;
        private String ctName;
        private String collectionDetails;
        private int dailyId;
        private int giveNum;
        private int isGiveNum;
        private int collectionSum;
        private int isCollectionSum;
        private int wantNum;
        private int isWantNum;
        private List<ListBean> list;

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

        public String getCtName() {
            return ctName;
        }

        public void setCtName(String ctName) {
            this.ctName = ctName;
        }

        public String getCollectionDetails() {
            return collectionDetails;
        }

        public void setCollectionDetails(String collectionDetails) {
            this.collectionDetails = collectionDetails;
        }

        public int getDailyId() {
            return dailyId;
        }

        public void setDailyId(int dailyId) {
            this.dailyId = dailyId;
        }

        public int getGiveNum() {
            return giveNum;
        }

        public void setGiveNum(int giveNum) {
            this.giveNum = giveNum;
        }

        public int getIsGiveNum() {
            return isGiveNum;
        }

        public void setIsGiveNum(int isGiveNum) {
            this.isGiveNum = isGiveNum;
        }

        public int getCollectionSum() {
            return collectionSum;
        }

        public void setCollectionSum(int collectionSum) {
            this.collectionSum = collectionSum;
        }

        public int getIsCollectionSum() {
            return isCollectionSum;
        }

        public void setIsCollectionSum(int isCollectionSum) {
            this.isCollectionSum = isCollectionSum;
        }

        public int getWantNum() {
            return wantNum;
        }

        public void setWantNum(int wantNum) {
            this.wantNum = wantNum;
        }

        public int getIsWantNum() {
            return isWantNum;
        }

        public void setIsWantNum(int isWantNum) {
            this.isWantNum = isWantNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * imagesId : 1
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk
             * ieId : 8
             * ieName : 审核通过
             */

            private int imagesId;
            private String imagesUrl;
            private int ieId;
            private String ieName;

            public int getImagesId() {
                return imagesId;
            }

            public void setImagesId(int imagesId) {
                this.imagesId = imagesId;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public int getIeId() {
                return ieId;
            }

            public void setIeId(int ieId) {
                this.ieId = ieId;
            }

            public String getIeName() {
                return ieName;
            }

            public void setIeName(String ieName) {
                this.ieName = ieName;
            }
        }
    }
}
