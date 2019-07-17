package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FansBean {


    /**
     * code : 0
     * msg : 请求成功
     * data : {"total":2,"size":10,"pages":1,"current":1,"records":[{"fuId":41,"userId":null,"userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"测试销售一号","signature":"没有就是个性","roleId":3},{"fuId":40,"userId":null,"userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"Boom ","signature":"啦啦啦","roleId":2}]}
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
         * total : 2
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"fuId":41,"userId":null,"userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"测试销售一号","signature":"没有就是个性","roleId":3},{"fuId":40,"userId":null,"userUrl":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","nickname":"Boom ","signature":"啦啦啦","roleId":2}]
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
             * fuId : 41
             * userId : null
             * userUrl : https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg
             * nickname : 测试销售一号
             * signature : 没有就是个性
             * roleId : 3
             */

            private int fuId;
            private Object userId;
            private String userUrl;
            private String nickname;
            private String signature;
            private int roleId;

            public int getFuId() {
                return fuId;
            }

            public void setFuId(int fuId) {
                this.fuId = fuId;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
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

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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
