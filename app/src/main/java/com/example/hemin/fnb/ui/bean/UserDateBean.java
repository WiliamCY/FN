package com.example.hemin.fnb.ui.bean;

public class UserDateBean {
    private String Authorization;
    private String token_type;
    private String usERa;
    private String expires_in;
    private User user;

  public class User {
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

      public String getBirthday() {
          return birthday;
      }

      public String getMobile() {
          return mobile;
      }

      public String getNickname() {
          return nickname;
      }

      public String getSignature() {
          return signature;
      }

      public String getUrl() {
          return url;
      }

      public void setBirthday(String birthday) {
          this.birthday = birthday;
      }

      public void setMobile(String mobile) {
          this.mobile = mobile;
      }

      public void setNickname(String nickname) {
          this.nickname = nickname;
      }

      public void setSignature(String signature) {
          this.signature = signature;
      }

      public void setUrl(String url) {
          this.url = url;
      }

      public void setUserId(String userId) {
          this.userId = userId;
      }

      public String getSex() {
          return sex;
      }

      public void setSex(String sex) {
          this.sex = sex;
      }

  }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getUsERa() {
        return usERa;
    }

    public void setUsERa(String usERa) {
        this.usERa = usERa;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
