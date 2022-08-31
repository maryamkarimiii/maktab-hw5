package hw5;

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
            System.out.println("chose 1:signUp \n 2:signIN \n 3:read published article \n 4:exit ");
            switch (scanner.nextInt()) {
                case 1:
                    User user = new User();
                    System.out.println("enter your user name without any space:\n note:your username can contain character and number");
                    String name=scanner.next();
                    if (userRepository.checkExistUserName(name) > 0)
                        System.out.println("this username is already exist try again");
                    user.setUserName(name);
                    System.out.println("enter your national code without any space or _:");
                    String nationalCode = scanner.next();
                    user.setNationalCode(nationalCode);
                    user.setPassword(nationalCode);
                    System.out.println("enter your birthday");
                    user.setBirthday(new Date(scanner.nextLong()));
                    int check = userRepository.register(user);
                    if (check > 0)
                        System.out.println("registering be successful \n" +
                                " your password is your national code you can change it in your profile after logIn");
                    break;
                case 2:
                    System.out.println("enter your user name:");
                    String userName = scanner.next();
                    System.out.println("enter your password");
                    String password = scanner.next();
                    if (userRepository.signIn(userName, password) != null) {
                        boolean signIn = true;
                        while (signIn) {
                            System.out.println("enter: \n *1:change your password \n *2:see your articles \n *3:edit your article \n " +
                                    "*4:submit new article \n *5:exit from profile");
                            switch (scanner.nextInt()) {
                                case 1:
                                    System.out.println("enter your new password");
                                    String newPassword = scanner.next();
                                    if (userRepository.updatePassword(newPassword, userName) > 0)
                                        System.out.println("password changes");
                                    System.out.println("try it again");
                                    break;
                                case 2:
                                    System.out.println("enter 1:see list of your article \n 2:read all parts of specific article ");
                                    switch (scanner.nextInt()) {
                                        case 1:
                                            articleRepository.ArticleList(userName);
                                            break;
                                        case 2:
                                            articleRepository.showArticle(scanner.nextInt());
                                    }
                                    break;
                                case 3:
                                    System.out.println("enter 1:change title \n 2:change brief \n 3:change content \n 4:change creatDate" +
                                            "\n 5:isPublished");
                                    switch (scanner.nextInt()) {
                                        case 1:
                                            System.out.println("enter your article id");
                                            System.out.println("enter your new title");
                                            String updateTitle = scanner.nextLine();
                                            if (articleRepository.updateArticle(scanner.nextInt(), "\"title\"", updateTitle) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 2:
                                            System.out.println("enter your article id");
                                            System.out.println("enter your new brief");
                                            String updateBrief = scanner.nextLine();
                                            if (articleRepository.updateArticle(scanner.nextInt(), "\"brief\"", updateBrief) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 3:
                                            System.out.println("enter your article id");
                                            System.out.println("enter your new content");
                                            String updateContent = scanner.nextLine();
                                            if (articleRepository.updateArticle(scanner.nextInt(), "\"content\"", updateContent) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 4:
                                            System.out.println("enter your article id");
                                            System.out.println("enter your new date");
                                            String updateDate = scanner.nextLine();
                                            if (articleRepository.updateArticle(scanner.nextInt(), "\"creatDate\"", updateDate) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                            break;
                                        case 5:
                                            System.out.println("enter article id");
                                            if (articleRepository.updateArticle(scanner.nextInt()) > 0)
                                                System.out.println("successfully update");
                                            System.out.println("unsuccessful update");
                                    }
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("enter \n *1:see list of published article \n *2:select specific article to read");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println(articleRepository.ArticleList());
                            break;
                        case 2:
                            System.out.println("enter the article id to read it");
                            System.out.println(articleRepository.showArticle(scanner.nextInt()));
                    }
                    break;
                case 4:
                    System.out.println("good by come back soon to read more");
                    flag = false;
            }
        }
    }
}
