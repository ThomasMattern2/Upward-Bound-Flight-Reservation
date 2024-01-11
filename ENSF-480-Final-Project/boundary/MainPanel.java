package boundary;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
    private JButton registerButton;
    private JButton loginButton;
    private JButton guestLink;

    public MainPanel(ActionListener registerListener, ActionListener loginListener, ActionListener guestListener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        initializeComponents(registerListener, loginListener, guestListener);
    }

    private void initializeComponents(ActionListener registerListener, ActionListener loginListener, ActionListener guestListener) {
        
        JLabel welcome = new JLabel("Welcome to Skyward Bound!");
        welcome.setFont(new Font(welcome.getFont().getName(), Font.BOLD, 24));
        welcome.setForeground(new Color(0, 102, 204));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("airline.png"));
        JLabel label = new JLabel(imageIcon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sloganLabel = new JLabel("Elevating Your Journey!");
        sloganLabel.setFont(new Font(sloganLabel.getFont().getName(), Font.ITALIC, 18));
        sloganLabel.setForeground(new Color(102, 153, 255));
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("Please register or log in to continue.");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(new Color(255, 255, 255));

        Dimension buttonSize = new Dimension(150, 30);

        registerButton = createStyledButton("Register", new Color(255, 215, 0), new Color(0, 102, 204), buttonSize); // Yellow button with blue text
        registerButton.addActionListener(registerListener);
        loginButton = createStyledButton("Log In", new Color(0, 102, 204), Color.WHITE, buttonSize); // Blue button with white text
        loginButton.addActionListener(loginListener);

        // Continue as Guest Link
        guestLink = new JButton("Continue as Guest");
        styleLinkButton(guestLink);

        guestLink.addActionListener(guestListener);
        guestLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonsPanel.add(registerButton);
        buttonsPanel.add(loginButton);

        add(Box.createVerticalStrut(20));
        add(welcome);
        add(label);
        add(sloganLabel);
        add(Box.createVerticalStrut(40));
        add(instructions);
        add(Box.createVerticalStrut(20));
        add(buttonsPanel);
        add(guestLink);
        add(Box.createVerticalStrut(30));
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getGuestLink() {
        return guestLink;
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 16));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
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
}
