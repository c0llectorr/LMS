package service;

import model.Book;

import java.util.List;

public class BookManager {
    private Library library;

    public BookManager(Library library) {
        this.library = library;
    }

    public boolean borrowBook(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {
                book.borrow();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isBorrowed()) {
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    public List<Book> listAllBooks() {
        return library.getBooks();
    }
}
