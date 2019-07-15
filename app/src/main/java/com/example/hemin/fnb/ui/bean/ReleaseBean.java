package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class ReleaseBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":1,"size":10,"pages":1,"current":1,"records":[{"friendId":25,"friendHead":"654465645645","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/rz2nsht3vp","userUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5","nickname":"130****2969","giveNum":0,"isGiveNum":null,"roleId":2}]}
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
         * records : [{"friendId":25,"friendHead":"654465645645","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/rz2nsht3vp","userUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5","nickname":"130****2969","giveNum":0,"isGiveNum":null,"roleId":2}]
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
             * friendId : 25
             * friendHead : 654465645645
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/rz2nsht3vp
             * userUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5
             * nickname : 130****2969
             * giveNum : 0
             * isGiveNum : null
             * roleId : 2
             */

            private int friendId;
            private String friendHead;
            private String imagesUrl;
            private String userUrl;
            private String nickname;
            private String giveNum;
            private int isGiveNum;
            private int roleId;


            public int getFriendId() {
                return friendId;
            }

            public void setFriendId(int friendId) {
                this.friendId = friendId;
            }

            public String getFriendHead() {
                return friendHead;
            }

            public void setFriendHead(String friendHead) {
                this.friendHead = friendHead;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
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

            public String  getGiveNum() {
                return giveNum;
            }

            public void setGiveNum(String giveNum) {
                this.giveNum = giveNum;
            }

            public int getIsGiveNum() {
                return isGiveNum;
            }

            public void setIsGiveNum(int isGiveNum) {
                this.isGiveNum = isGiveNum;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }
        }
    }
}
