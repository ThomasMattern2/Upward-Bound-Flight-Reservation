package boundary;

import controller.AircraftController;
import controller.PaymentController;
import controller.SystemController;
import flightInfo.*;
import role.RegisteredCustomer;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.owen_guldberg.gmailsender.GMailer;

import java.awt.*;
import java.util.ArrayList;

public class TicketPurchasePanel extends JPanel {
    private Seat selectedSeat;
    private Flight flight;
    private AircraftController aircraftController;
    private PaymentController paymentController;
    private String userEmail;
    private double totalPrice;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private SystemController system;

    private JTextField cardNumberField;
    private JTextField cvvField;
    private JTextField promoCodeField;

    public TicketPurchasePanel(String userEmail, Seat seat, AircraftController aircraftController, Flight flight, JPanel cardPanel, CardLayout cardLayout, SystemController system) {
        this.userEmail = userEmail;
        this.selectedSeat = seat;
        this.aircraftController = aircraftController;
        this.flight = flight;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.system = system;
        paymentController = new PaymentController(userEmail, seat, flight.getDepartureTime());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(Box.createVerticalStrut(20));

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 5, 50);
        gbc.weightx = 1;
        gbc.gridx = 0;

        JLabel ticketPurchaseLabel = new JLabel("Ticket Purchase Information");
        ticketPurchaseLabel.setFont(new Font(ticketPurchaseLabel.getFont().getName(), Font.BOLD, 18));
        ticketPurchaseLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.anchor = GridBagConstraints.CENTER;
        add(ticketPurchaseLabel, gbc);

        JLabel infoLabel2 = new JLabel(flight.toString());
        infoLabel2.setFont(new Font(infoLabel2.getFont().getName(), Font.PLAIN, 16));
        infoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel3 = new JLabel("<html><u>Selected Seat:</u> " + seat.getSeatNum() + "</html>");
        infoLabel3.setFont(new Font(infoLabel3.getFont().getName(), Font.PLAIN, 16));
        infoLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel4 = new JLabel("<html><u>Price:</u> $" + seat.getPrice() + "</html>");
        infoLabel4.setFont(new Font(infoLabel4.getFont().getName(), Font.PLAIN, 16));
        infoLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(30));
        add(infoLabel2, gbc);
        add(infoLabel3, gbc);
        add(infoLabel4, gbc);
        add(Box.createVerticalStrut(20));

        JCheckBox insuranceCheckBox = new JCheckBox("Add Ticket Cancellation Insurance ($20)");
        insuranceCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.anchor = GridBagConstraints.CENTER;
        add(insuranceCheckBox, gbc);

        cardNumberField = new JTextField(16);
        JPanel cardNumberPanel = createInputPanel("Card Number:", cardNumberField);
        add(cardNumberPanel, gbc);

        cvvField = new JTextField(4);
        JPanel cvvPanel = createInputPanel("CVV:", cvvField);
        add(cvvPanel, gbc);

        promoCodeField = new JTextField(10);
        JPanel promoCodePanel = createInputPanel("Promotion Code (optional):", promoCodeField);
        add(promoCodePanel, gbc);

        add(Box.createVerticalStrut(20));

        gbc.anchor = GridBagConstraints.CENTER;

        JButton purchaseButton = new JButton("Purchase Ticket");
        styleButton(purchaseButton);
        purchaseButton.addActionListener(e -> handlePurchase(insuranceCheckBox.isSelected(), cardNumberField.getText(), cvvField.getText(),
        promoCodeField.getText(), userEmail, flight));
        add(purchaseButton, gbc);
        add(Box.createVerticalStrut(10));

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "seatChart"));
        add(backButton, gbc);
        add(Box.createVerticalStrut(80));

        gbc.weighty = 1;
        add(Box.createGlue(), gbc);

        if (paymentController.paymentAvailable()) {
            cardNumberField.setText(paymentController.getPaymentCardNum());
            cvvField.setText(String.valueOf(paymentController.getPaymentCVV()));
        }
    }

    private void handlePurchase(boolean insuranceSelected, String cardNumber, String cvv, String promoCode, String username, Flight flight) {
        ArrayList<RegisteredCustomer> customers= system.getRegisteredCustomers();
        if (cardNumber.length() == 0 || cvv.length() == 0) {
            JOptionPane.showMessageDialog(this, "Credit card number and CVV are required.");
            return;
        }
        for(RegisteredCustomer a : customers){
            if(a.getEmail().equals(username)){
                flight.addPassenger(a);
            }
        }
        paymentController.setPaymentInfo(cardNumber, cvv);
        paymentController.setTicketPrice(selectedSeat.getPrice(), insuranceSelected);
        paymentController.setStrat(promoCode);
        totalPrice = paymentController.getTicketPrice();
        boolean success = paymentController.createTicket(insuranceSelected, flight.getFlightNum());
        if (!success) {
            JOptionPane.showMessageDialog(this, "Ticket purchase failed!");
            return;
        }
        aircraftController.updateSeatAvailability(selectedSeat, false);
        
        try {
            double insurance_cost = 0;
            if(insuranceSelected) {
                insurance_cost = 20;
            }
            GMailer gMailer = new GMailer();
            gMailer.sendMail(userEmail,"Skyward Bound Ticket & Receipt",
            "Flight Information: \n Date: " + flight.getFlightDate() + 
            "\nOrigin: " + flight.getOrigin() +
            "\nDestination: " + flight.getDestination() +
            "\nDeparture Time: " + flight.getDepartureTime() +
            "\nArrival Time " + flight.getArrivalTime() +
            "\nFlight Duration: " + flight.getFlightTime() +
            "\nCancellation Insurance: " + insuranceSelected +
            "\n\nSeat Information:" + 
            "\nClass: " + selectedSeat.getSeatClass() +
            "\nSelected Seat: " + selectedSeat.getSeatNum() +
            "\n\nReceipt:" +
            "\nSeat Cost: $" + String.format("%.2f", (double)selectedSeat.getPrice()) +
            "\nInsurance: $" + String.format("%.2f", insurance_cost) +
            "\nCost after promos: $" + String.format("%.2f", totalPrice));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TicketConfirmationPanel confirmationPanel = new TicketConfirmationPanel(paymentController, userEmail, flight.toString(), selectedSeat, totalPrice, selectedSeat.getPrice(), insuranceSelected, cardPanel, cardLayout, system);
        cardPanel.add(confirmationPanel, "ticketConfirmation");
        cardLayout.show(cardPanel, "ticketConfirmation");
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
}
