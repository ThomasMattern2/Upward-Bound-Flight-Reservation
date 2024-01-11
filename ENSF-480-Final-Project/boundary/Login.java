package boundary;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private JButton registerButton;

    public Login(ActionListener loginAction, ActionListener backAction, ActionListener registerAction) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        initializeComponents(loginAction, backAction, registerAction);
    }

    private void initializeComponents(ActionListener loginAction, ActionListener backAction, ActionListener registerAction) {
        add(Box.createVerticalStrut(20));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        gbc.weightx = 1;
        gbc.gridx = 0;
    
        JLabel titleLabel = new JLabel("Please Log In Below");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);
    
        emailField = new JTextField(15);
        JPanel emailPanel = createInputPanel("Email:", emailField);
        gbc.anchor = GridBagConstraints.WEST;
        add(emailPanel, gbc);
    
        passwordField = new JPasswordField(15);
        JPanel passwordPanel = createInputPanel("Password:", passwordField);
        add(passwordPanel, gbc);
    
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(e -> passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? '\u0000' : '*'));
        gbc.anchor = GridBagConstraints.CENTER;
        add(showPasswordCheckBox, gbc);
    
        loginButton = new JButton("Log In");
        styleButton(loginButton);
        loginButton.addActionListener(loginAction);
        add(loginButton, gbc);
    
        backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(backAction);
        add(backButton, gbc);
    
        registerButton = new JButton("Don't have an account? Register");
        styleLinkButton(registerButton);
        registerButton.addActionListener(registerAction);
        add(registerButton, gbc);
    
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
        clearFields();
    }
    
    private JPanel createInputPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
    
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
    
        field.setMinimumSize(new Dimension(900, 25));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);
    
        return panel;
    }

    private void styleButton(JButton button) {
        Color color = new Color(0, 102, 204);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 16));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }

    private void styleLinkButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setForeground(new Color(0, 102, 204)); // Blue color
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 16));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setText("<html><span style='text-decoration: none;'>" + button.getText() + "</span></html>");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText("<html><span style='text-decoration: underline;'>" + button.getText().replaceAll("<[^>]*>", "") + "</span></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setText("<html><span style='text-decoration: none;'>" + button.getText().replaceAll("<[^>]*>", "") + "</span></html>");
            }
        });
    }

    public String getEmail() {
        return emailField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void clearFields() {
        emailField.setText("");
        passwordField.setText("");
    }
}
