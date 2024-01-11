package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;

    private JTextField houseNumField;
    private JTextField streetNameField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField postalCodeField;

    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JButton loginLink;

    public Register(ActionListener registerListener, ActionListener backListener, ActionListener loginListener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        initializeComponents(registerListener, backListener, loginListener);
    }

    private void initializeComponents(ActionListener registerListener, ActionListener backListener, ActionListener loginListener) {
        add(Box.createVerticalStrut(20));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 5, 50);
        gbc.weightx = 1;
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Welcome! Create Your Account Below");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Name
        firstNameField = new JTextField(15);
        JPanel firstNamePanel = createInputPanel("First Name:", firstNameField);
        add(firstNamePanel, gbc);

        lastNameField = new JTextField(15);
        JPanel lastNamePanel = createInputPanel("Last Name:", lastNameField);
        add(lastNamePanel, gbc);

        // Email
        emailField = new JTextField(15);
        JPanel emailPanel = createInputPanel("Email:", emailField);
        gbc.anchor = GridBagConstraints.WEST;
        add(emailPanel, gbc);

        // Address
        houseNumField = new JTextField(15);
        JPanel houseNumPanel = createInputPanel("House Number:", houseNumField);
        add(houseNumPanel, gbc);

        streetNameField = new JTextField(15);
        JPanel streetNamePanel = createInputPanel("Street Name:", streetNameField);
        add(streetNamePanel, gbc);

        cityField = new JTextField(15);
        JPanel cityPanel = createInputPanel("City:", cityField);
        add(cityPanel, gbc);

        countryField = new JTextField(15);
        JPanel countryPanel = createInputPanel("Country:", countryField);
        add(countryPanel, gbc);

        postalCodeField = new JTextField(15);
        JPanel postalCodePanel = createInputPanel("Postal Code:", postalCodeField);
        add(postalCodePanel, gbc);

        // Password
        passwordField = new JPasswordField(15);
        JPanel passwordPanel = createInputPanel("Password:", passwordField);
        add(passwordPanel, gbc);
    
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(e -> passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? '\u0000' : '*'));
        gbc.anchor = GridBagConstraints.CENTER;
        add(showPasswordCheckBox, gbc);

        gbc.anchor = GridBagConstraints.CENTER;

        // Buttons
        registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.addActionListener(registerListener);
        add(registerButton, gbc);
    
        backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(backListener);
        add(backButton, gbc);

        loginLink = new JButton("Already have an account? Log in");
        styleLinkButton(loginLink);
        loginLink.addActionListener(loginListener);
        add(loginLink, gbc);
    
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);

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
        button.setForeground(new Color(0, 102, 204));
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

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getHouseNum() {
        return houseNumField.getText();
    }
    
    public String getStreetName() {
        return streetNameField.getText();
    }
    
    public String getCity() {
        return cityField.getText();
    }
    
    public String getCountry() {
        return countryField.getText();
    }
    
    public String getPostalCode() {
        return postalCodeField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        houseNumField.setText("");
        streetNameField.setText("");
        cityField.setText("");
        countryField.setText("");
        postalCodeField.setText("");
        passwordField.setText("");
    }
}
