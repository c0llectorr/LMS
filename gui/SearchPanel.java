package gui;

import model.Book;
import service.BookManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private BookManager bookManager;

    public SearchPanel(BookManager bookManager) {
        this.bookManager = bookManager;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(30);
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "Genre", "Status"}, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                List<Book> results = bookManager.listAllBooks().stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchField.getText().toLowerCase()))
                    .toList();
                updateTable(results);
            }
        });
    }

    private void updateTable(List<Book> books) {
        tableModel.setRowCount(0);
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
