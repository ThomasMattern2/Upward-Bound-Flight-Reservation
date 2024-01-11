package boundary;

import controller.AircraftController;
import controller.SystemController;
import flightInfo.Flight;
import flightInfo.Seat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeatChart extends JPanel {

    private AircraftController aircraftController;
    private String flightNum;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private SystemController system;
    private String userEmail;
    private ActionListener logoutListener;
    private final Color takenSeatColor = new Color(255, 99, 71);
    private final Color availableColor = new Color(0x57A0D2);

    public SeatChart(String userEmail, ArrayList<Seat> seats, AircraftController aircraftController, String flightNum, SystemController system, JPanel cardPanel, CardLayout cardLayout, ActionListener logoutListener) {
        this.userEmail = userEmail;
        this.aircraftController = aircraftController;
        this.flightNum = flightNum;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.system = system;
        this.logoutListener = logoutListener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        add(Box.createVerticalStrut(20));

        JLabel seatChartLabel = new JLabel("Seat Chart");
        seatChartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seatChartLabel.setFont(new Font(seatChartLabel.getFont().getName(), Font.BOLD, 18));

        JPanel seatsPanel = new JPanel(new GridLayout(0, 4)); // 4 seats per row
        seatsPanel.setPreferredSize(new Dimension(900, 600));
        seatsPanel.setBackground(Color.WHITE);

        for (Seat seat : seats) {
            JButton seatButton = createSeatButton(seat);
            seatButton.addActionListener(e -> showSeatDetails(seat));
            seatsPanel.add(seatButton);
        }

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.add(seatsPanel);
        wrapperPanel.setBackground(Color.WHITE);

        add(seatChartLabel);
        add(Box.createVerticalStrut(20));
        add(wrapperPanel);
        addLegend();
        addNavigationButtons();
        add(Box.createVerticalStrut(10));
    }

    private void showSeatDetails(Seat seat) {
        Flight flight = system.getFlightByNum(flightNum);
        int response = JOptionPane.showConfirmDialog(this, 
            aircraftController.getSeatDetails(seat) + "\n\nWould you like to select this seat?", 
            "Seat Details", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION && seat.getAvailability()) {
            if (userEmail == "") {
                cardLayout.show(cardPanel, "login");
            } else {
                TicketPurchasePanel purchasePanel = new TicketPurchasePanel(userEmail, seat, aircraftController, flight, cardPanel, cardLayout, system);
                cardPanel.add(purchasePanel, "purchaseTicket");
                cardLayout.show(cardPanel, "purchaseTicket");
            }
        } else if (response == JOptionPane.YES_OPTION && !seat.getAvailability()) {
            JOptionPane.showMessageDialog(this, "Seat " + seat.getSeatNum() + " is not available!");
        }
    }

    public void addLegend() {
        JPanel legendPanel = new JPanel();
        legendPanel.setBackground(Color.WHITE);
        legendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel availableBox = createLegendBox("Available", availableColor);
        JPanel takenBox = createLegendBox("Taken", takenSeatColor);

        legendPanel.add(availableBox);
        legendPanel.add(takenBox);

        add(legendPanel);
    }

    private JButton createSeatButton(Seat seat) {
        JButton seatButton = new JButton(String.valueOf(seat.getSeatNum())) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Rounded corners
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        Color color = seat.getAvailability() ? availableColor : takenSeatColor;
        seatButton.setOpaque(false);
        seatButton.setContentAreaFilled(false);
        seatButton.setFocusPainted(false);
        seatButton.setBackground(color);
        seatButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        seatButton.setForeground(Color.WHITE);
        seatButton.setFont(new Font("Arial", Font.BOLD, 12));
        seatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        seatButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                seatButton.setBackground(color.darker());
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                seatButton.setBackground(color);
            }
        });

        return seatButton;
    }
    
    private JPanel createLegendBox(String labelText, Color backgroundColor) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
    
        JPanel box = new JPanel();
        box.setBackground(backgroundColor);
        box.add(label);
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return box;
    }
    
    private void addNavigationButtons() {
        JButton backButton = styleButton(new JButton("Back"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "flightInfo"));
    
        JButton logoutButton = styleButton(new JButton("Logout"));
        logoutButton.addActionListener(logoutListener);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
    
        add(buttonPanel);
    }
    
    private JButton styleButton(JButton button) {
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
        return button;
    }

}
