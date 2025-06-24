package gui;

import model.Book;
import service.BookManager;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    private JTextField titleField, authorField, genreField;
    private BookManager bookManager;
    private BookTablePanel tablePanel;

    public FormPanel(BookManager bookManager, BookTablePanel tablePanel) {
        this.bookManager = bookManager;
        this.tablePanel = tablePanel;
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        titleField = new JTextField();
        authorField = new JTextField();
        genreField = new JTextField();

        JButton addButton = new JButton("Add Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");

        add(new JLabel("Title:")); add(titleField);
        add(new JLabel("Author:")); add(authorField);
        add(new JLabel("Genre:")); add(genreField);
        add(addButton); add(borrowButton);
        add(returnButton); add(new JLabel("")); // empty

        addButton.addActionListener(e -> {
            Book book = new Book(titleField.getText(), authorField.getText(), genreField.getText());
            bookManager.listAllBooks().add(book);
            tablePanel.refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Book added.");
        });

        borrowButton.addActionListener(e -> {
            boolean result = bookManager.borrowBook(titleField.getText());
            JOptionPane.showMessageDialog(this, result ? "Book borrowed." : "Book not found or already borrowed.");
            tablePanel.refreshTable();
            clearFields();
        });

        returnButton.addActionListener(e -> {
            boolean result = bookManager.returnBook(titleField.getText());
            JOptionPane.showMessageDialog(this, result ? "Book returned." : "Book not found or not borrowed.");
            tablePanel.refreshTable();
            clearFields();
        });
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
    }
}
