package hw5.repository;

import hw5.entity.Article;
import hw5.service.ApplicationConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    public Article addArticle(Article article){
        String insertQuery="INSERT INTO \"article\" (\"title\",\"brief\",\"content\",\"creatDate\",\"isPublished\",\"user_id\") VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=ApplicationConstant.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getBrief());
            preparedStatement.setString(3,article.getContent());
            preparedStatement.setDate(4,article.getCreateDate());
            preparedStatement.setBoolean(5,article.isPublished());
            preparedStatement.setInt(6,article.getUser().getUserId());
            return article;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Article showArticle(int id) {
        String selectQuery = "SELECT from \"article\" where \"article_id\"=?";
        try {
            PreparedStatement selectArticle = ApplicationConstant.getConnection().prepareStatement(selectQuery);
            ResultSet resultSet = selectArticle.executeQuery();
            Article article = new Article(resultSet.getInt("article_id"), resultSet.getString("title"),
                    resultSet.getString("brief"), resultSet.getString("content"), resultSet.getDate("createDate"),
                    resultSet.getBoolean("isPublished"));
            return article;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> ArticleList() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT \"article_id\",\"title\",\"brief\" from \"article\" WHERE \"isPublished\"=true";
        try {
            Statement statement = ApplicationConstant.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Article article = new Article(resultSet.getInt("article_id"), resultSet.getString("title"),
                        resultSet.getString("brief"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> ArticleList(String userName) {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT \"article_id\",\"title\",\"brief\" from \"article\" WHERE \"user_name\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article(resultSet.getInt("article_id"), resultSet.getString("title"),
                        resultSet.getString("brief"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticle(int id) {
        String updateQuery = "UPDATE \"article\" set \"isPublished\"=true WHERE \"article_id\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int updateArticle(int id, String columnName, String updateRecord) {
        String updateQuery = "UPDATE \"article\" set ?=? WHERE \"article_id\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, columnName);
            preparedStatement.setString(2, updateRecord);
            preparedStatement.setInt(3, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticle(int id, Date updateDate) {
        String updateQuery = "UPDATE \"article\" set \"createDate\"=? WHERE \"article_id\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setDate(1, updateDate);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
