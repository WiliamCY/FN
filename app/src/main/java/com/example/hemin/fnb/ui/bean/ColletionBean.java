package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class ColletionBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"collectionId":5,"collectionNum":"432260195","ctName":"玉器","collectionDetails":"完了","list":[{"imagesId":26,"imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","ieId":8,"ieName":"审核通过"},{"imagesId":27,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/pw7g5c2nsx","ieId":8,"ieName":"审核通过"},{"imagesId":28,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/926fbw5agr","ieId":8,"ieName":"审核通过"},{"imagesId":29,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/3arvxymn4t","ieId":8,"ieName":"审核通过"},{"imagesId":30,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/29vt6epg4q","ieId":8,"ieName":"审核通过"},{"imagesId":31,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/p32lt8gx4b","ieId":8,"ieName":"审核通过"},{"imagesId":32,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/qr526cbwpe","ieId":8,"ieName":"审核通过"},{"imagesId":33,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/ftl7364dsa]","ieId":8,"ieName":"审核通过"}]}
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
         * collectionId : 5
         * collectionNum : 432260195
         * ctName : 玉器
         * collectionDetails : 完了
         * list : [{"imagesId":26,"imagesUrl":"[https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast","ieId":8,"ieName":"审核通过"},{"imagesId":27,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/pw7g5c2nsx","ieId":8,"ieName":"审核通过"},{"imagesId":28,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/926fbw5agr","ieId":8,"ieName":"审核通过"},{"imagesId":29,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/3arvxymn4t","ieId":8,"ieName":"审核通过"},{"imagesId":30,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/29vt6epg4q","ieId":8,"ieName":"审核通过"},{"imagesId":31,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/p32lt8gx4b","ieId":8,"ieName":"审核通过"},{"imagesId":32,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/qr526cbwpe","ieId":8,"ieName":"审核通过"},{"imagesId":33,"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/ftl7364dsa]","ieId":8,"ieName":"审核通过"}]
         */

        private String collectionId;
        private String collectionNum;
        private String ctName;
        private String collectionDetails;
        private List<ListBean> list;

        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * imagesId : 26
             * imagesUrl : [https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/dyk6bgxast
             * ieId : 8
             * ieName : 审核通过
             */

            private String imagesId;
            private String imagesUrl;
            private String ieId;
            private String ieName;

            public String getImagesId() {
                return imagesId;
            }

            public void setImagesId(String imagesId) {
                this.imagesId = imagesId;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public String getIeId() {
                return ieId;
            }

            public void setIeId(String ieId) {
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
