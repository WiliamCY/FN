package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class MessageImages {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"friendId":null,"userId":"9","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"130****2969","friendContent":"空","images":[{"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/32pgvrmqeh"},{"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/k6vcugf3n2"},{"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/l4kmv3yrzn"}],"giveNum":0,"isGiveNum":2,"isCollectionSum":2}
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
         * friendId : null
         * userId : 9
         * userUrl : https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg
         * nickname : 130****2969
         * friendContent : 空
         * images : [{"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/32pgvrmqeh"},{"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/k6vcugf3n2"},{"imagesUrl":" https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/l4kmv3yrzn"}]
         * giveNum : 0
         * isGiveNum : 2
         * isCollectionSum : 2
         */

        private Object friendId;
        private String userId;
        private String userUrl;
        private String nickname;
        private String friendContent;
        private int giveNum;
        private int isGiveNum;
        private int isCollectionSum;
        private List<ImagesBean> images;

        public Object getFriendId() {
            return friendId;
        }

        public void setFriendId(Object friendId) {
            this.friendId = friendId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFriendContent() {
            return friendContent;
        }

        public void setFriendContent(String friendContent) {
            this.friendContent = friendContent;
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

        public int getIsCollectionSum() {
            return isCollectionSum;
        }

        public void setIsCollectionSum(int isCollectionSum) {
            this.isCollectionSum = isCollectionSum;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/32pgvrmqeh
             */

            private String imagesUrl;

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }
        }
    }
}
