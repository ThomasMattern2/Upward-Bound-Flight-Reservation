package flightInfo;
import util.*;

public class Ticket {
    private int seatNum;
    private double price;
    private String flightNumber;
    private String departureTime;
    private boolean hasCancellationInsurance = false;
    private String classSeat;

    public Ticket(int seatNum, double price, String flightNum, boolean insurance, String departureTime, String classSeat) {
        this.seatNum = seatNum;
        this.price = price;
        this.flightNumber = flightNum;
        this.hasCancellationInsurance = insurance;
        this.departureTime = departureTime;
        this.classSeat = classSeat;
    }
    
    public Ticket getTicket(){
        return this;
    }

    public double getPrice() {
        return price;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public boolean getHasCancellationInsurance() {
        return hasCancellationInsurance;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getClassSeat() {
        return classSeat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "<html><u>Seat Number:</u> " + seatNum + "<br><u>Price:</u> $" + String.format("%.2f", price) + "<br><u>Departure Time:</u> " + departureTime + "<br><u>Class:</u> " + classSeat + "<br><u>Cancellation Insurance:</u> " + (hasCancellationInsurance ? "Yes" : "No"+ "</html>");
    }
}

