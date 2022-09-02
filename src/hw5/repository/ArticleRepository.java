package hw5.repository;

import hw5.entity.Article;
import hw5.service.ApplicationConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    UserRepository userRepository = new UserRepository();

    public int checkExistTitle(String title) {
        String selectQuery = "SELECT count(\"title\") from \"article\" WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(selectQuery);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            int result = resultSet.getRow();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addArticle(Article article,int userId) {
        String insertQuery = "INSERT INTO \"article\" (\"title\",\"brief\",\"content\",\"creatDate\",\"isPublished\",\"user_id\") VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBrief());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setString(4, article.getCreateDate());
            preparedStatement.setBoolean(5, article.isPublished());
            preparedStatement.setInt(6, userId);
            int result = preparedStatement.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Article showArticle(int articleId) {
        String selectQuery = "SELECT from \"article\" where \"article_id\"=?";
        try {
            PreparedStatement selectArticle = ApplicationConstant.getConnection().prepareStatement(selectQuery);
            selectArticle.setInt(1, articleId);
            ResultSet resultSet = selectArticle.executeQuery();
            Article article = new Article(resultSet.getInt("article_id"), resultSet.getString("title"),
                    resultSet.getString("brief"), resultSet.getString("content"), resultSet.getString("createDate"),
                    resultSet.getBoolean("isPublished"));
            return article;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> ArticleList() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT \"title\",\"brief\" from \"article\" WHERE \"isPublished\"=true";
        try {
            Statement statement = ApplicationConstant.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Article article = new Article(resultSet.getString("title"),
                        resultSet.getString("brief"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> ArticleList(int userId) {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT \"title\",\"brief\" from \"article\" WHERE \"user_id\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article(resultSet.getString("title"),
                        resultSet.getString("brief"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticlePublish(String articleTitle) {
        String updateQuery = "UPDATE \"article\" set \"isPublished\"=true WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, articleTitle);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticleTitle(String title, String updateTitle) {
        String updateQuery = "UPDATE \"article\" set \"title\"=? WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, updateTitle);
            preparedStatement.setString(2, title);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticleBrief(String title, String updateBrief) {
        String updateQuery = "UPDATE \"article\" set \"brief\"=? WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, updateBrief);
            preparedStatement.setString(2, title);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticleContent(String title, String updateContent) {
        String updateQuery = "UPDATE \"article\" set \"content\"=? WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, updateContent);
            preparedStatement.setString(2, title);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateArticleDate(String title, Date updateDate) {
        String updateQuery = "UPDATE \"article\" set \"createDate\"=? WHERE \"title\"=?";
        try {
            PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(updateQuery);
            preparedStatement.setDate(1, updateDate);
            preparedStatement.setString(2, title);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
