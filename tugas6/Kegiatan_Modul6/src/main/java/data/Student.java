package data;

import books.Book;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.iMenu;

import java.util.ArrayList;

public class Student extends User implements iMenu {
    public String nim;
    public String faculty;
    public String program;

    public Student(String nama, String nim, String faculty, String program) {
        super("User");
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public void borrowBook(ArrayList<Book> bookList) {
        Stage stage = new Stage();
        stage.setTitle("Borrow Book");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField bookIdField = new TextField();
        TextField durationField = new TextField();

        grid.add(new Label("Enter Book ID to borrow:"), 0, 0);
        grid.add(bookIdField, 1, 0);
        grid.add(new Label("Enter the duration in days:"), 0, 1);
        grid.add(durationField, 1, 1);

        Button borrowButton = new Button("Borrow Book");
        borrowButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            int durationDays;

            try {
                durationDays = Integer.parseInt(durationField.getText());
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Duration must be a number.");
                return;
            }

            boolean found = false;
            for (Book book : bookList) {
                if (book.getBookId().equals(bookId)) {
                    found = true;
                    book.borrowBook(durationDays);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Book borrowed successfully.");
                    stage.close();
                    break;
                }
            }
            if (!found) {
                showAlert(Alert.AlertType.ERROR, "Error", "Book with the given ID not found.");
            }
        });

        grid.add(borrowButton, 1, 2);

        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void returnBook(ArrayList<Book> bookList) {
        Stage stage = new Stage();
        stage.setTitle("Return Book");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField bookIdField = new TextField();

        grid.add(new Label("Enter Book ID to return:"), 0, 0);
        grid.add(bookIdField, 1, 0);

        Button returnButton = new Button("Return Book");
        returnButton.setOnAction(e -> {
            String bookId = bookIdField.getText();

            boolean found = false;
            for (Book book : bookList) {
                if (book.getBookId().equals(bookId)) {
                    found = true;
                    book.returnBook();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully.");
                    stage.close();
                    break;
                }
            }
            if (!found) {
                showAlert(Alert.AlertType.ERROR, "Error", "Book with the given ID not found or not borrowed by you.");
            }
        });

        grid.add(returnButton, 1, 1);

        Scene scene = new Scene(grid, 400, 150);
        stage.setScene(scene);
        stage.show();
    }

    public void showBorrowedBook(ArrayList<Book> bookList) {
        Stage stage = new Stage();
        stage.setTitle("Borrowed Books");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        boolean hasBorrowedBooks = false;
        int row = 0;
        for (Book book : bookList) {
            if (book.getBorrowedCount() > 0) {
                hasBorrowedBooks = true;
                grid.add(new Label("Book ID: " + book.getBookId()), 0, row);
                grid.add(new Label("Title: " + book.getTitle()), 1, row);
                grid.add(new Label("Author: " + book.getAuthor()), 2, row);
                grid.add(new Label("Category: " + book.getCategory()), 3, row);
                grid.add(new Label("Borrowed Date: " + book.getBorrowedDate()), 4, row);
                grid.add(new Label("Due in: " + book.getDurationDays() + " days"), 5, row);
                row++;
            }
        }
        if (!hasBorrowedBooks) {
            grid.add(new Label("You haven't borrowed any books yet."), 0, 0);
        }

        Scene scene = new Scene(grid, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void menu() {
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
