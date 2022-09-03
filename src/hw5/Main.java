package hw5;

import hw5.entity.Article;
import hw5.entity.User;
import hw5.repository.ArticleRepository;
import hw5.repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            menu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menu() throws SQLException {
        UserRepository userRepository = new UserRepository();
        ArticleRepository articleRepository = new ArticleRepository();
        boolean flag = true;
        System.out.println("hello welcome");
        while (flag) {
            System.out.println("chose 1:signUp \n 2:signIN \n 3:published article \n 4:exit ");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    User user = new User();
                    System.out.println("enter your user name without any space:\n note:your username can contain character and number");
                    String name = scanner.nextLine();
                    while (userRepository.checkExistUserName(name) > 0) {
                        System.out.println("this username is already exist try again");
                        name = scanner.nextLine();
                    }
                    user.setUserName(name);
                    System.out.println("enter your national code without any space or _:");
                    String nationalCode = scanner.nextLine();
                    while (nationalCode.equals("0000000000")) {
                        System.out.println("the nationalCode is not valid");
                    }
                    user.setNationalCode(nationalCode);
                    user.setPassword(nationalCode);
                    System.out.println("enter your birthday like : 1376_02_01 ");
                    user.setBirthday(scanner.nextLine());
                    int check = userRepository.register(user);
                    if (check > 0)
                        System.out.println("registering be successful \n" +
                                " your password is your national code you can change it in your profile after logIn");
                    break;
                case 2:
                    boolean chek = true;
                    boolean signIn = false;
                    String userName = "";
                    while (chek) {
                        System.out.println("enter your user name:");
                        userName = scanner.nextLine();
                        System.out.println("enter your password");
                        String password = scanner.nextLine();
                        if ((userRepository.signIn(userName, password)) == null)
                            System.out.println("userName or password is not correct try again");
                        else {
                            signIn = true;
                            chek = false;
                        }
                    }
                    while (signIn) {
                        System.out.println("enter: \n *1:change your password \n *2:see your articles \n *3:edit your article \n " +
                                "*4:submit new article \n *5:exit from profile");
                        switch (Integer.parseInt(scanner.nextLine())) {
                            case 1:
                                System.out.println("enter your new password , the password must be 5 to 10 character");
                                String newPassword = scanner.nextLine();
                                while (newPassword.equals("0")) {
                                    System.out.println("not valid password try again");
                                }
                                if (userRepository.updatePassword(newPassword, userName) > 0)
                                    System.out.println("password changes");
                                else System.out.println("not successful,try another time");
                                break;
                            case 2:
                                System.out.println("enter 1:see list of your article \n 2:read all parts of specific article ");
                                switch (Integer.parseInt(scanner.nextLine())) {
                                    case 1:
                                        int id = userRepository.getUserId(userName);
                                        System.out.println(articleRepository.ArticleList(id));
                                        break;
                                    case 2:
                                        System.out.println("enter article id");
                                        int articleId = Integer.parseInt(scanner.nextLine());
                                        if (articleRepository.showArticle(articleId) != null)
                                            System.out.println("not found");
                                        else System.out.println(articleRepository.showArticle(articleId));
                                }
                                break;
                            case 3:
                                System.out.println("enter article title you want update");
                                String titleName = scanner.nextLine();
                                if (articleRepository.checkExistTitle(titleName) == 1) {
                                    System.out.println("enter 1:change title \n 2:change brief \n 3:change content \n 4:change creatDate" +
                                            "\n 5:isPublished");
                                    switch (Integer.parseInt(scanner.nextLine())) {
                                        case 1:
                                            System.out.println("enter your new title");
                                            String updateTitle = scanner.nextLine();
                                            if (articleRepository.updateArticleTitle(titleName, updateTitle) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 2:
                                            System.out.println("enter your new brief");
                                            String updateBrief = scanner.nextLine();
                                            if (articleRepository.updateArticleBrief(titleName, updateBrief) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 3:
                                            System.out.println("enter your new content");
                                            String updateContent = scanner.nextLine();
                                            if (articleRepository.updateArticleContent(titleName, updateContent) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 4:
                                            System.out.println("enter your new date");
                                            Date updateDate = Date.valueOf(scanner.nextLine());
                                            if (articleRepository.updateArticleDate(titleName, updateDate) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 5:
                                            if (articleRepository.updateArticlePublish(titleName) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                    }
                                }
                                break;
                            case 4:
                                Article article = new Article();
                                System.out.println("enter your article title it can have maximum 100 character:");
                                String title = scanner.nextLine();
                                while (articleRepository.checkExistTitle(title) > 0 && title.equals("0.0")) {
                                    System.out.println("this title not valid enter another title");
                                    title = scanner.nextLine();
                                }
                                article.setTitle(title);
                                System.out.println("enter article brief");
                                article.setBrief(scanner.nextLine());
                                System.out.println("enter article content");
                                article.setContent(scanner.nextLine());
                                System.out.println("enter article creatDate");
                                String creat = scanner.nextLine();
                                article.setCreateDate(creat);
                                System.out.println("enter true if you want published your article");
                                article.setPublished(scanner.nextBoolean());
                                if (articleRepository.addArticle(article, userRepository.getUserId(userName)) == 1) {
                                    System.out.println("add article be successful");
                                } else System.out.println("not add try again");
                            case 5:
                                signIn = false;
                        }
                    }
                    break;
                case 3:
                    System.out.println("enter \n *1:see list of published article \n *2:select specific article to read");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            System.out.println(articleRepository.ArticleList());
                            break;
                        case 2:
                            System.out.println("enter the article id to read it");
                            System.out.println(articleRepository.showArticle(Integer.parseInt(scanner.nextLine())));
                    }
                    break;
                case 4:
                    System.out.println("good by come back soon to read more");
                    flag = false;
            }
        }
    }
}

