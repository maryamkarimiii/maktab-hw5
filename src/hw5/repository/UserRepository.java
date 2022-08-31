package hw5.repository;

import hw5.entity.Article;
import hw5.entity.User;
import hw5.service.ApplicationConstant;

import java.sql.*;

public class UserRepository {
    public int register(User user) throws SQLException {
        String insertQuery = "INSERT INTO \"user\"(\"user_name\",\"nationalCode\",\"birthday\",\"password\") VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(insertQuery);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getNationalCode());
        preparedStatement.setDate(3,user.getBirthday());
        preparedStatement.setString(4,user.getNationalCode());
        return preparedStatement.executeUpdate();
    }

    public int updatePassword(String newPassword,String userName) {
        String updateQuery = "UPDATE \"user\" set \"password\"=? WHERE \"user_name\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,userName);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int checkExistUserName(String userName) {
        String selectQuery = "SELECT count(\"user_name\") from \"user\" WHERE \"user_name\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(selectQuery);
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            int result = resultSet.getRow();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet signIn(String userName,String password){
        String selectQuery="SELECT from \"user\" WHERE \"user_name\"=? AND \"password\"=?";
        try {
            PreparedStatement preparedStatement=ApplicationConstant.getConnection().prepareStatement(selectQuery);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
