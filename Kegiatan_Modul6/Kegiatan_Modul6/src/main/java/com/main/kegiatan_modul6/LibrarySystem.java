package com.main.kegiatan_modul6;

import data.User;
import data.Student;
import data.Admin;
import util.iMenu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LibrarySystem extends Application implements iMenu {
    private ArrayList<Student> userStudent;
    private Admin admin;
    private User user;

    static String adminusername = "admin", adminpassword = "admin123";

    public static void main(String[] args) {
        launch(args);
    }

    public LibrarySystem() {
        userStudent = new ArrayList<>();
        admin = new Admin("Admin");
        user = new User("User");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System by Azmi");
        primaryStage.setScene(createMainMenuScene(primaryStage));
        primaryStage.show();
    }

    private Scene createMainMenuScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("===== Library System by Azmi =====");
        Button studentLoginButton = new Button("Login as Student");
        Button adminLoginButton = new Button("Login as Admin");
        Button exitButton = new Button("Exit");

        studentLoginButton.setOnAction(e -> primaryStage.setScene(createStudentLoginScene(primaryStage)));
        adminLoginButton.setOnAction(e -> primaryStage.setScene(createAdminLoginScene(primaryStage)));
        exitButton.setOnAction(e -> primaryStage.close());

        layout.getChildren().addAll(titleLabel, studentLoginButton, adminLoginButton, exitButton);

        return new Scene(layout, 300, 200);
    }

    private Scene createStudentLoginScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label promptLabel = new Label("Enter your NIM (99 for Back):");
        TextField nimInput = new TextField();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
        submitButton.setOnAction(e -> {
            String nim = nimInput.getText();
            if (nim.equals("99")) {
                primaryStage.setScene(createMainMenuScene(primaryStage));
            } else if (checkNim(nim)) {
                Student currentStudent = getUserByNim(nim);
                primaryStage.setScene(createStudentMenuScene(primaryStage, currentStudent));
            } else {
                promptLabel.setText("Invalid NIM. Please try again.");
            }
        });

        layout.getChildren().addAll(promptLabel, nimInput, submitButton, backButton);

        return new Scene(layout, 300, 200);
    }

    private Scene createAdminLoginScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label promptLabel = new Label("Enter Username and Password:");
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
        submitButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (username.equals(adminusername) && password.equals(adminpassword)) {
                primaryStage.setScene(createAdminMenuScene(primaryStage));
            } else {
                promptLabel.setText("Invalid credentials. Please try again.");
            }
        });

        layout.getChildren().addAll(promptLabel, usernameInput, passwordInput, submitButton, backButton);

        return new Scene(layout, 300, 200);
    }

    private Scene createAdminMenuScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("===== Admin Menu =====");
        Button inputBookButton = new Button("Input Book");
        Button displayBooksButton = new Button("Display Books");
        Button addStudentButton = new Button("Add Student");
        Button displayStudentsButton = new Button("Display Registered Students");
        Button logoutButton = new Button("Logout");

        inputBookButton.setOnAction(e -> admin.inputBook());
        displayBooksButton.setOnAction(e -> admin.displayBooks());
        addStudentButton.setOnAction(e -> admin.addStudent(userStudent));
        displayStudentsButton.setOnAction(e -> displayStudent());
        logoutButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));

        layout.getChildren().addAll(titleLabel, inputBookButton, displayBooksButton, addStudentButton, displayStudentsButton, logoutButton);

        return new Scene(layout, 300, 300);
    }

    private Scene createStudentMenuScene(Stage primaryStage, Student currentStudent) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("===== Student Menu =====");
        Button displayBooksButton = new Button("Display Available Books");
        Button borrowBookButton = new Button("Borrow Book");
        Button borrowedBooksButton = new Button("Borrowed Books");
        Button returnBookButton = new Button("Return Book");
        Button logoutButton = new Button("Logout");

        displayBooksButton.setOnAction(e -> user.displayBooks());
        borrowBookButton.setOnAction(e -> currentStudent.borrowBook(user.bookList));
        borrowedBooksButton.setOnAction(e -> currentStudent.showBorrowedBook(user.bookList));
        returnBookButton.setOnAction(e -> currentStudent.returnBook(user.bookList));
        logoutButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));

        layout.getChildren().addAll(titleLabel, displayBooksButton, borrowBookButton, borrowedBooksButton, returnBookButton, logoutButton);

        return new Scene(layout, 300, 300);
    }

    private boolean checkNim(String nim) {
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return true;
            }
        }
        return false;
    }

    private Student getUserByNim(String nim) {
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return student;
            }
        }
        return null;
    }

    private void displayStudent() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("===== Registered Students =====");

        layout.getChildren().add(titleLabel);
        for (Student student : userStudent) {
            Label studentInfo = new Label("Name: " + student.nama + "\nNIM: " + student.nim + "\nFaculty: " + student.faculty + "\nProgram: " + student.program);
            layout.getChildren().add(studentInfo);
        }

        stage.setScene(new Scene(layout, 300, 300));
        stage.show();
    }

    @Override
    public void menu() {
        // This method is not used in JavaFX application
    }
}
