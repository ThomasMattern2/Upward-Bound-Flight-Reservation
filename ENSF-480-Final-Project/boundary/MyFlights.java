package boundary;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

import flightInfo.Ticket;
import role.RegisteredCustomer;
import controller.PaymentController;
import controller.SystemController;

public class MyFlights extends JPanel {
    private PaymentController paymentController;
    private String userEmail;
    private int seatNum;
    private SystemController systemController;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Color lightBlue = new Color(0xE1F5FE);

    public MyFlights(PaymentController paymentController, String userEmail, int seatNum, SystemController systemController, JPanel cardPanel, CardLayout cardLayout) {
        this.paymentController = paymentController;
        this.userEmail = userEmail;
        this.seatNum = seatNum;
        this.systemController = systemController;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        refreshPanel();
    }

    private void refreshPanel() {
        removeAll();
        initializeComponents();
        revalidate();
        repaint();
    }

    private void initializeComponents() {
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));


        JLabel titleLabel = new JLabel("My Flights");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createVerticalStrut(20));

        RegisteredCustomer customer = systemController.getUserByEmail(userEmail);
        ArrayList<Ticket> tickets = customer.getTickets();

        JPanel containerPanel = new JPanel();
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        if (tickets.isEmpty()) {
            JLabel noLabel = new JLabel("No flights booked.");
            noLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(noLabel);
        } else {
            for (Ticket ticket : tickets) {
                containerPanel.add(createTicketPanel(ticket));
                containerPanel.add(Box.createVerticalStrut(5));
            }
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(900, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane);

        add(Box.createVerticalStrut(40));
        JButton backButton = new JButton("Browse More Flights");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "user"));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(backButton);
        add(backButton);
        add(Box.createVerticalStrut(80));
        setVisible(true);
    }

    private JPanel createTicketPanel(Ticket ticket) {
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBackground(lightBlue);

        JLabel flightLabel = new JLabel("<html><b>Flight and Ticket Information</b></html>");
        flightLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ticketPanel.add(flightLabel);
        ticketPanel.add(Box.createVerticalStrut(10));

        String flightInfo = systemController.getFlightByNum(ticket.getFlightNumber()).toString();
        JLabel flightInfoLabel = new JLabel(flightInfo);
        flightInfoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ticketPanel.add(flightInfoLabel);

        JLabel ticketDetailsLabel = new JLabel(ticket.toString());
        ticketDetailsLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ticketPanel.add(ticketDetailsLabel);
        ticketPanel.add(Box.createVerticalStrut(10));

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        styleButton(cancelButton);
        cancelButton.addActionListener(e -> cancelTicket(ticket));
        ticketPanel.add(cancelButton);

        return ticketPanel;
    }

    private void cancelTicket(Ticket ticket) {

        for(int i = 0 ; i < systemController.getFlightByNum(ticket.getFlightNumber()).getAircraft().getSeats().size(); i++){
            if(systemController.getFlightByNum(ticket.getFlightNumber()).getAircraft().getSeats().get(i).getSeatNum() == ticket.getSeatNum()){
                systemController.getFlightByNum(ticket.getFlightNumber()).getAircraft().getSeats().get(i).setAvailable(true);
            }

        }
        paymentController.deleteTicket(userEmail, ticket.getSeatNum());
        systemController.getFlightByNum(ticket.getFlightNumber()).getPassengers().remove(systemController.getUserByEmail(userEmail));

        systemController.getUserByEmail(userEmail).removeTicket(ticket);

        JOptionPane.showMessageDialog(this, "Ticket cancelled successfully.");

        refreshPanel();
    }

    private void styleButton(JButton button) {
        Color color = new Color(0, 102, 204);
        button.setBackground(color); // Blue background
        button.setForeground(Color.WHITE); // White text
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


