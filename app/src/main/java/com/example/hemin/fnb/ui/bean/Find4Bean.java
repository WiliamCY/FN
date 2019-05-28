package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class Find4Bean {

    /**
     * code : 0
     * msg : 请求成功
     * data : [{"collectionId":20,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/tgylkvsc43","certificateNum":"","insuranceNum":"","collectionAht":"真品无误","caId":1,"collectionCost":"无价之宝","collectionTime":"新石器时代","collectionAppearance":"残品（补）","collectionType":"玉器"},{"collectionId":1,"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/wpn89sy3gk","certificateNum":"","insuranceNum":"","collectionAht":"真品无误","caId":1,"collectionCost":"无价之宝","collectionTime":"旧石器时代","collectionAppearance":"全品","collectionType":"石器"}]
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
         * collectionId : 20
         * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/tgylkvsc43
         * certificateNum :
         * insuranceNum :
         * collectionAht : 真品无误
         * caId : 1
         * collectionCost : 无价之宝
         * collectionTime : 新石器时代
         * collectionAppearance : 残品（补）
         * collectionType : 玉器
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
