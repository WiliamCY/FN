package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class GuanZhuBean {

    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":1,"size":10,"pages":1,"current":1,"records":[{"fuId":19,"userId":22,"userUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5","nickname":"130****2969","focusSum":1,"roleId":2}]}
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
         * records : [{"fuId":19,"userId":22,"userUrl":"https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5","nickname":"130****2969","focusSum":1,"roleId":2}]
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
             * fuId : 19
             * userId : 22
             * userUrl : https://wan-jian.oss-cn-hangzhou.aliyuncs.com/images/w3kxbpgcn5
             * nickname : 130****2969
             * focusSum : 1
             * roleId : 2
             */

            private String fuId;
            private String userId;
            private String userUrl;
            private String nickname;
            private String focusSum;
            private int roleId;

            public String getFuId() {
                return fuId;
            }

            public void setFuId(String fuId) {
                this.fuId = fuId;
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

            public String getFocusSum() {
                return focusSum;
            }

            public void setFocusSum(String focusSum) {
                this.focusSum = focusSum;
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
