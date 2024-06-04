package data;

import books.Book;
import books.TextBook;
import books.HistoryBook;
import books.StoryBook;

import java.util.ArrayList;

public class User {
    public String nama;
    public ArrayList<Book> bookList;

    public User(String user) {
        this.nama = this.nama;
        bookList = new ArrayList<>();
    }

    public User(String nama, ArrayList<Book> bookList) {
        this.nama = nama;
        this.bookList = bookList;
    }

    public void displayBooks() {
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : bookList.toArray(new Book[0])) {
            System.out.println("|| " + index + "  || " + book.getBookId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }

    public void addBook(String bookId, String title, String author, String category, int stock, String bookType) {
        Book newBook;
        switch (bookType) {
            case "History":
                newBook = new HistoryBook(bookId, title, author, category, stock);
                break;
            case "Text":
                newBook = new TextBook(bookId, title, author, category, stock);
                break;
            case "Story":
                newBook = new StoryBook(bookId, title, author, category, stock);
                break;
            default:
                System.out.println("Invalid book type.");
                return;
        }
        bookList.add(newBook);
        System.out.println("Book added successfully.");
    }
}