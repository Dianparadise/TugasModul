package data;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.iMenu;

import java.util.ArrayList;

public class Admin extends User implements iMenu {
    public Admin(String name) {
        super("User");
    }

    public Admin() {
        super("User");
    }

    public void addStudent(ArrayList<Student> userStudent) {
        Stage stage = new Stage();
        stage.setTitle("Add Student");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        TextField nimField = new TextField();
        TextField facultyField = new TextField();
        TextField programField = new TextField();

        grid.add(new Label("Enter Student Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Enter NIM:"), 0, 1);
        grid.add(nimField, 1, 1);
        grid.add(new Label("Enter Faculty:"), 0, 2);
        grid.add(facultyField, 1, 2);
        grid.add(new Label("Enter Program:"), 0, 3);
        grid.add(programField, 1, 3);

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String program = programField.getText();

            if (nim.length() != 15) {
                showAlert(Alert.AlertType.ERROR, "Invalid NIM", "NIM must be 15 digits.");
            } else {
                Student newStudent = new Student(name, nim, faculty, program);
                userStudent.add(newStudent);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student added successfully.");
                stage.close();
            }
        });

        grid.add(addButton, 1, 4);

        Scene scene = new Scene(grid, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public void inputBook() {
        Stage stage = new Stage();
        stage.setTitle("Input Book");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField bookIdField = new TextField();
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField categoryField = new TextField();
        TextField stockField = new TextField();

        grid.add(new Label("Enter Book ID:"), 0, 0);
        grid.add(bookIdField, 1, 0);
        grid.add(new Label("Enter Title:"), 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(new Label("Enter Author:"), 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(new Label("Enter Category (History, Text, or Story):"), 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(new Label("Enter Stock:"), 0, 4);
        grid.add(stockField, 1, 4);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            int stock;

            try {
                stock = Integer.parseInt(stockField.getText());
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Stock must be a number.");
                return;
            }

            String bookType = category;
            super.addBook(bookId, title, author, category, stock, bookType);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully.");
            stage.close();
        });

        grid.add(addButton, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void menu() {
        // This method is not needed in JavaFX implementation
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
