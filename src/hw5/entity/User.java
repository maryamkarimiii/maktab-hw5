package hw5.entity;

import com.sun.jdi.Value;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class User {
    private int userId;
    private String userName;
    private String nationalCode;
    private Date birthday;
    private String password;

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
        System.out.println("error:your national code is not true");
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 5 && password.length() <= 10)
            this.password = password;
        System.out.println("error:password must be between 5 to 10 character");
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
