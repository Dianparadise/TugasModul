package books;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Book {
    private StringProperty bookId;
    private StringProperty title;
    private StringProperty author;
    private StringProperty category;
    private IntegerProperty stock;
    private IntegerProperty borrowedCount;
    private ObjectProperty<LocalDate> borrowedDate;
    private IntegerProperty durationDays;

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = new SimpleStringProperty(bookId);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.stock = new SimpleIntegerProperty(stock);
        this.borrowedCount = new SimpleIntegerProperty(0);
        this.borrowedDate = new SimpleObjectProperty<>(null);
        this.durationDays = new SimpleIntegerProperty(0);
    }

    // Getters and Setters
    public String getBookId() {
        return bookId.get();
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public StringProperty bookIdProperty() {
        return bookId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getBorrowedCount() {
        return borrowedCount.get();
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount.set(borrowedCount);
    }

    public IntegerProperty borrowedCountProperty() {
        return borrowedCount;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate.get();
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate.set(borrowedDate);
    }

    public ObjectProperty<LocalDate> borrowedDateProperty() {
        return borrowedDate;
    }

    public int getDurationDays() {
        return durationDays.get();
    }

    public void setDurationDays(int durationDays) {
        this.durationDays.set(durationDays);
    }

    public IntegerProperty durationDaysProperty() {
        return durationDays;
    }

    public void borrowBook(int durationDays) {
        if (getStock() > 0) {
            setStock(getStock() - 1);
            setBorrowedCount(getBorrowedCount() + 1);
            setBorrowedDate(LocalDate.now());
            setDurationDays(durationDays);
            System.out.println("Book borrowed successfully for " + durationDays + " days.");
        } else {
            System.out.println("No more copies available for this book.");
        }
    }

    public void returnBook() {
        if (getBorrowedCount() > 0) {
            setStock(getStock() + 1);
            setBorrowedCount(getBorrowedCount() - 1);
            setBorrowedDate(null);
            setDurationDays(0);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("No copies borrowed for this book.");
        }
    }
}
