package hw5.entity;

import java.sql.Date;

public class Article {
    private int articleId;
    private String title;
    private String brief;
    private String content;
    private Date createDate;
    private boolean isPublished;
    private int userID;
    private User user;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article(int articleId, String title, String brief, String content, Date createDate, boolean isPublished) {
        this.articleId = articleId;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
    }

    public Article(int articleId, String title, String brief) {
        this.articleId = articleId;
        this.title = title;
        this.brief = brief;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() <= 100)
            this.title = title;
        System.out.println("error:the title length is more than valid length");
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", isPublished=" + isPublished +
                ", userID=" + userID +
                ", user=" + user +
                "\n" + '}';
    }
}
