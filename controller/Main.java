package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.User;

import java.time.LocalDate;

public class Main extends Application {

    public static Library library;

    @Override
    public void start(Stage primaryStage) throws Exception{

        User u1 = new User("John Stones","jstones@gmail.com","211 Ryder St. Tallahassee, FL 32304", LocalDate.of(1989,06,13), true);
        User u2 = new User("Jack Bauer","jack24@gmail.com","7400 Bay Rd. University Center, MI 48710",LocalDate.of(1988,11,15),false);
        User u3 = new User("Harry Kane","hkane@gmail.com","123 James Boyd Rd. Scranton, PA 28410",LocalDate.of(1988,2,1), false);
        User u4 = new User("Tim Arnold","ta123@gmail.com","3412 Dinsmore Ave, MA 01710",LocalDate.of(1999,1,15), true);

        Book b1 = new Book("Programming with Java","Daniel Liang","Pearson","Education","1234568924",2020);
        Book b2 = new Book("Data Structures and Algorithms","Robert Lafore","Pearson","Education","98726213",2001);
        Book b3 = new Book("Harry Potter and The Chamber of Secrets","J.K. Rowling","Scholastic","Adventure","343255323",1998);
        Book b4 = new Book("Lord of the Rings - The Two Towers","Tolkien","Wiley","Thriller","989636362",1945);

        library = new Library();
        library.addUser(u1);
        library.addUser(u2);
        library.addUser(u3);
        library.addUser(u4);


        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);

        Parent root = FXMLLoader.load(getClass().getResource("MainMenuPage.fxml"));
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
