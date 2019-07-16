package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class MessageImageBean {
    /**
     * code : 0
     * msg : 请求成功
     * data : {"friendId":null,"userId":"17","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"137****0107","friendContent":"啦啦啦","images":[{"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/20190529111710.jpeg"}],"giveNum":1,"isGiveNum":2,"isCollectionSum":2}
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
         * friendId : null
         * userId : 17
         * userUrl : https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg
         * nickname : 137****0107
         * friendContent : 啦啦啦
         * images : [{"imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/20190529111710.jpeg"}]
         * giveNum : 1
         * isGiveNum : 2
         * isCollectionSum : 2
         */

        private Object friendId;
        private String userId;
        private String userUrl;
        private String nickname;
        private String friendContent;
        private int giveNum;
        private String isGiveNum;
        private String isCollectionSum;
        private List<ImagesBean> images;
       private String isFocus;
       private int focusNum;

        public int getFocusNum() {
            return focusNum;
        }

        public void setFocusNum(int focusNum) {
            this.focusNum = focusNum;
        }

        public String getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(String isFocus) {
            this.isFocus = isFocus;
        }

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

        public String getIsGiveNum() {
            return isGiveNum;
        }

        public void setIsGiveNum(String isGiveNum) {
            this.isGiveNum = isGiveNum;
        }

        public String getIsCollectionSum() {
            return isCollectionSum;
        }

        public void setIsCollectionSum(String isCollectionSum) {
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
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/20190529111710.jpeg
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
