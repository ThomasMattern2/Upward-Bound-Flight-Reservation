package boundary;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.PaymentController;
import controller.SystemController;
import flightInfo.Seat;

import java.awt.*;

public class TicketConfirmationPanel extends JPanel {
    private PaymentController paymentController;
    private String userEmail;
    private String flightInfo;
    private Seat seat;
    private double price;
    private double origPrice;
    private boolean insurance;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private SystemController system;

    public TicketConfirmationPanel(PaymentController paymentController, String userEmail, String flightInfo, Seat seat, double price, double origPrice, boolean insurance, JPanel cardPanel, CardLayout cardLayout, SystemController system) {
        this.paymentController = paymentController;
        this.userEmail = userEmail;
        this.flightInfo = flightInfo;
        this.seat = seat;
        this.price = price;
        this.origPrice = origPrice;
        this.insurance = insurance;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.system = system;

        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));
        initializeComponents();
    }

    private void initializeComponents() {

        JLabel thankyouLabel = new JLabel("Thank you for using Skyward Bound!");
        thankyouLabel.setFont(new Font(thankyouLabel.getFont().getName(), Font.BOLD, 18));
        thankyouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(thankyouLabel);
        add(Box.createVerticalStrut(10));
        
        // Ticket Confirmation Title
        JLabel titleLabel = new JLabel("Your ticket and receipt have now been emailed to " + userEmail + ".");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createVerticalStrut(30));
    
        // Flight Information
        JPanel flightInfoPanel = createBorderedPanel("Flight Information", flightInfo);
        JPanel ticketPanel = createBorderedPanel("Ticket Information", 
                                                paymentController.printTicket(userEmail, seat.getSeatNum()));
    
        // Receipt Section
        double insurance_cost = 0;
        if(insurance) {
            insurance_cost = 20;
        }
        JPanel receiptPanel = createBorderedPanel("Receipt", "<html>" + "Seat Price: $" + String.format("%.2f", origPrice) + "<br>" +
                "Insurance: $" + String.format("%.2f", insurance_cost) +
                "<br>" + "Price after promos: $" + String.format("%.2f", price) + "</html>");
    

        add(Box.createVerticalStrut(30));
        // Buttons
        add(createButtonPanel());
    
        // Ensure components are aligned correctly
        alignComponents();
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);
    
        JButton viewFlightsButton = new JButton("View My Flights");
        styleButton(viewFlightsButton);
        viewFlightsButton.addActionListener(e -> viewFlights());
        buttonPanel.add(viewFlightsButton);
    
        JButton browseFlightsButton = new JButton("Browse More Flights");
        styleButton(browseFlightsButton);
        browseFlightsButton.addActionListener(e -> browseFlights());
        buttonPanel.add(browseFlightsButton);
    
        return buttonPanel;
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
    
    private void alignComponents() {
        Component[] components = getComponents();
        for (Component comp : components) {
            ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    private JPanel createBorderedPanel(String title, String content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(title), 
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contentLabel = new JLabel(content);
        contentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(contentLabel);

        add(panel);
        return panel;
    }

    private void logout() {
        cardLayout.show(cardPanel, "main");
    }

    private void viewFlights() {
        MyFlights myFlightsPanel = new MyFlights(paymentController, userEmail, seat.getSeatNum(), system, cardPanel, cardLayout); // Pass null or default values for now
        cardPanel.add(myFlightsPanel, "myFlights");
        cardLayout.show(cardPanel, "myFlights");
    }

    private void browseFlights() {
        cardLayout.show(cardPanel, "user");
    }
}
