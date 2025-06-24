package gui;

import model.Book;
import service.BookManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private BookManager bookManager;

    public BookTablePanel(BookManager bookManager) {
        this.bookManager = bookManager;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "Genre", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Book> books = bookManager.listAllBooks();
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.isBorrowed() ? "Borrowed" : "Available"
            });
        }
    }
}
