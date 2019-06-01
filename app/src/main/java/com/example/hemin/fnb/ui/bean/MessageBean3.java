package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class MessageBean3 {


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 4
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"friendId":3,"friendHead":"123456","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/ygcnxwu528","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"jkl","giveNum":1,"isGiveNum":0,"roleId":2},{"friendId":4,"friendHead":"测试","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/6ra8sgwlqv","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"173****4861","giveNum":2,"isGiveNum":0,"roleId":2},{"friendId":1,"friendHead":"asdasd","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/u7b3h9r8sn","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"jkl","giveNum":2,"isGiveNum":0,"roleId":2},{"friendId":2,"friendHead":"dssad","imagesUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/y2nkmtl9ru","userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"jkl","giveNum":2,"isGiveNum":0,"roleId":2}]
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
             * friendId : 3
             * friendHead : 123456
             * imagesUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/ygcnxwu528
             * userUrl : https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg
             * nickname : jkl
             * giveNum : 1
             * isGiveNum : 0
             * roleId : 2
             */

            private int friendId;
            private String friendHead;
            private String imagesUrl;
            private String userUrl;
            private String nickname;
            private String giveNum;
            private String isGiveNum;
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

            public String getGiveNum() {
                return giveNum;
            }

            public void setGiveNum(String giveNum) {
                this.giveNum = giveNum;
            }

            public String getIsGiveNum() {
                return isGiveNum;
            }

            public void setIsGiveNum(String isGiveNum) {
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
