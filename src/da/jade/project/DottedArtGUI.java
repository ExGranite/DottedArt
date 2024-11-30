package da.jade.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//frame.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));
public class DottedArtGUI extends JFrame {
    private DatabaseManager dbManager;

    public DottedArtGUI() {
        dbManager = new DatabaseManager();
        setTitle("DottedArt Gallery");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Registration Panel
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(3, 2));
        registrationPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        registrationPanel.add(usernameField);
        registrationPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        registrationPanel.add(passwordField);
        JButton registerButton = new JButton("Register");
        registrationPanel.add(registerButton);
        add(registrationPanel, BorderLayout.NORTH);

        // Artwork Submission Panel
        JPanel artworkPanel = new JPanel();
        artworkPanel.setLayout(new GridLayout(4, 2));
        artworkPanel.add(new JLabel("Artwork Title:"));
        JTextField titleField = new JTextField();
        artworkPanel.add(titleField);
        artworkPanel.add(new JLabel("Artist:"));
        JTextField artistField = new JTextField();
        artworkPanel.add(artistField);
        artworkPanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        artworkPanel.add(descriptionField);
        JButton submitArtworkButton = new JButton("Submit Artwork");
        artworkPanel.add(submitArtworkButton);
        add(artworkPanel, BorderLayout.CENTER);

        // Action Listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (dbManager.registerUser (username, password)) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Try again.");
                }
            }
        });

        submitArtworkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String artist = artistField.getText();
                String description = descriptionField.getText();
                if (dbManager.addArtwork(title, artist, description)) {
                    JOptionPane.showMessageDialog(null, "Artwork submitted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Artwork submission failed. Try again.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DottedArtGUI gui = new DottedArtGUI();
            gui.setVisible(true);
        });
    }
    public void display() {
        SwingUtilities.invokeLater(() -> {
            DottedArtGUI gui = new DottedArtGUI();
            gui.setVisible(true);
        });
    }
}