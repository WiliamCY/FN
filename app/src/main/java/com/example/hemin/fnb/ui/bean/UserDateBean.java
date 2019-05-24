package com.example.hemin.fnb.ui.bean;

public class UserDateBean {


    /**
     * code : 0
     * msg : 请求成功
     * data : {"Authorization":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzA4MTk2Mjk2OSIsImV4cCI6MzM1ODY4ODEzOH0.aKDkU-hosX7I2G6Lyby20-sPa6vqop4ARhcEiZDy3lWVqD6jzneMYpWYBRFUzrGKGzViY6uLX-uMfVh0VWGZyg","token_type":"usERa","expires_in":1800000000,"user":{"userId":9,"nickname":"130****2969","url":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","mobile":"130****2969","birthday":"","signature":"","sex":"男"}}
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
         * Authorization : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzA4MTk2Mjk2OSIsImV4cCI6MzM1ODY4ODEzOH0.aKDkU-hosX7I2G6Lyby20-sPa6vqop4ARhcEiZDy3lWVqD6jzneMYpWYBRFUzrGKGzViY6uLX-uMfVh0VWGZyg
         * token_type : usERa
         * expires_in : 1800000000
         * user : {"userId":9,"nickname":"130****2969","url":"https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg","mobile":"130****2969","birthday":"","signature":"","sex":"男"}
         */

        private String Authorization;
        private String token_type;
        private String expires_in;
        private UserBean user;

        public String getAuthorization() {
            return Authorization;
        }

        public void setAuthorization(String Authorization) {
            this.Authorization = Authorization;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * userId : 9
             * nickname : 130****2969
             * url : https://funwl.oss-cn-hangzhou.aliyuncs.com/images/u%3D1229021758%2C2220622610%26fm%3D27%26gp%3D0.jpg
             * mobile : 130****2969
             * birthday :
             * signature :
             * sex : 男
             */

            private String userId;
            private String nickname;
            private String url;
            private String mobile;
            private String birthday;
            private String signature;
            private String sex;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
