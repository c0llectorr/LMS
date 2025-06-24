package gui;

import service.BookManager;
import service.Library;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MainFrame() {
        super("Library Book Borrowing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Backend setup
        Library library = new Library();
        BookManager bookManager = new BookManager(library);

        // Layout
        setLayout(new BorderLayout());

        // Top Navigation Bar
        JPanel navBar = createNavBar(bookManager);
        add(navBar, BorderLayout.NORTH);

        // Card Panel Setup
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        BookTablePanel bookTablePanel = new BookTablePanel(bookManager);
        FormPanel formPanel = new FormPanel(bookManager, bookTablePanel);
        SearchPanel searchPanel = new SearchPanel(bookManager);

        cardPanel.add(bookTablePanel, "SHOW");
        cardPanel.add(formPanel, "FORM");
        cardPanel.add(searchPanel, "SEARCH");

        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "SHOW"); // Show default panel
        setVisible(true);
    }

    private JPanel createNavBar(BookManager bookManager) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnShow = new JButton("📚 Show Books");
        JButton btnForm = new JButton("✏️ Add / Borrow / Return");
        JButton btnSearch = new JButton("🔍 Search");

        btnShow.setFocusPainted(false);
        btnForm.setFocusPainted(false);
        btnSearch.setFocusPainted(false);

        btnShow.addActionListener(e -> cardLayout.show(cardPanel, "SHOW"));
        btnForm.addActionListener(e -> cardLayout.show(cardPanel, "FORM"));
        btnSearch.addActionListener(e -> cardLayout.show(cardPanel, "SEARCH"));

        panel.add(btnShow);
        panel.add(btnForm);
        panel.add(btnSearch);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
