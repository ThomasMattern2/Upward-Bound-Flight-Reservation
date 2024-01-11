package controller;

import util.*;

import java.util.ArrayList;

import role.RegisteredCustomer;
import flightInfo.Ticket;
import flightInfo.Seat;

import com.owen_guldberg.gmailsender.GMailer;

import boundary.Database;

public class PaymentController {

    private RegisteredCustomer user;
    private Seat seat;
    private String departureTime;
    ArrayList<RegisteredCustomer> registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();

    public PaymentController(String userEmail, Seat seat, String departureTime) {
        this.seat = seat;
        this.departureTime = departureTime;
        for (RegisteredCustomer customer : registeredCustomers) {
            if (customer.getEmail().equals(userEmail)) {
                this.user = customer;
            }
        }
    }

    public boolean paymentAvailable() {
        if (user.getPayment().getCreditCardNumber() != null && user.getPayment().getCVV() != 0) {
            return true;
        }
        return false;
    }

    public String getPaymentCardNum() {
        if (paymentAvailable()) {
            return user.getPayment().getCreditCardNumber();
        }
        return null;
    }

    public String getPaymentCVV() {
        if (paymentAvailable()) {
            return String.valueOf(user.getPayment().getCVV());
        }
        return null;
    }

    public void setPaymentInfo(String cardNum, String cvv) {
        if (cardNum.length() != 16 || cvv.length() != 3) {
            return;
        }
        else if (!paymentAvailable()){
            Payment newPayment = new Payment(cardNum, Integer.parseInt(cvv));
            System.out.println(newPayment.getCreditCardNumber());
            System.out.println(newPayment.getCVV());
            this.user.setPayment(newPayment);
        }
        System.out.println(user.getPayment().getCreditCardNumber());
        System.out.println(user.getPayment().getCVV());
        Database.getOnlyInstance().updateUser(user);
    }

    public void setStrat(String promoCode) {
        Payment payment = user.getPayment();
        if ("MEMBER15".equals(promoCode)) {
            fifteenDiscount disc = new fifteenDiscount();
            double discountedPrice = disc.applyDiscount(payment.getAmountOwed());
            payment.setOwed(discountedPrice);
        }
        else if("MEMBER50".equals(promoCode)){
            fiftyDiscount disc = new fiftyDiscount();
            double discountedPrice = disc.applyDiscount(payment.getAmountOwed());
            payment.setOwed(discountedPrice);
        }
    }

    public void setTicketPrice(double price, boolean insurance) {
        if (insurance) {
            price += 20;
        }
        Payment payment = user.getPayment();
        payment.setOwed(price);
    }

    public double getTicketPrice() {
        return user.getPayment().getAmountOwed();
    }

    public boolean createTicket(boolean insurance, String flightNum) {
        Ticket ticket = new Ticket(seat.getSeatNum(), user.getPayment().getAmountOwed(), flightNum, insurance, departureTime, seat.getSeatClass());
        user.addTicket(ticket);
        seat.setAvailable(false);
        boolean success = Database.getOnlyInstance().addTicketToDB(ticket, user.getEmail());
        if (!success) {
            return false;
        }
        return true;
    }

    public String printTicket(String userEmail, int seatNum) {
        ArrayList<Ticket> tickets = user.getTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getSeatNum() == seatNum) {
                return ticket.toString();
            }
        }
        return null;
    }

    public boolean deleteTicket(String userEmail, int seatNum) {
        ArrayList<Ticket> tickets = user.getTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getSeatNum() == seatNum) {
                String emailSubject = "Flight Cancellation Notification";
                String emailBody = "Hello, " + user.getName().getFirstName() + " " + user.getName().getLastName() + "\n" +
                        "You have canceled flight " + ticket.getFlightNumber();
    
                if (ticket.getHasCancellationInsurance()) {
                    emailBody += "\nSince you have cancellation insurance, you will get a full refund.";
                    double price = ticket.getPrice();
                    user.getPayment().setOwed(price);
                }
    
                emailBody += "\nWe look forward to serving you on board and providing you with a great travel experience.\n\n" +
                        "Best regards,\n" +
                        "The Skyward Bound Team";
                try {
                    GMailer gMailer = new GMailer();
                
                    System.out.println(emailBody);

                    gMailer.sendMail(userEmail, emailSubject, emailBody);
             } catch (Exception e) {
                        e.printStackTrace();
                    }

                Database.getOnlyInstance().deleteTicketFromDB(ticket, userEmail);

                return true;
            }
        }

        return false;
    }
}

