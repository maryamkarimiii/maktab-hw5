package hw5.entity;

import java.sql.Date;

public class User {
    private int userId;
    private String userName;
    private String nationalCode;
    private String birthday;
    private String password;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        if (nationalCode.length() == 10)
            this.nationalCode = nationalCode;
        else this.nationalCode = "0000000000";
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        String [] birthdays=birthday.split("_");
        for (int i=0 ; i < birthdays.length ;i++){
            if(birthdays[0].length()==4 && Integer.parseInt(birthdays[1]) <=12 && Integer.parseInt(birthdays[2]) <=31){
                this.birthday=birthday;
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 5 && password.length() <= 10)
            this.password = password;
        else this.password = "0";
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                "\n" + '}';
    }
}
