package flightInfo;
public class Seat {

    private int price;
    private boolean isAvailable;
    private int seatNum;
    private String seatClass;

    // Parameterized constructor
    public Seat(int price, int seatNum, String seatClass) {
        this.price = price;
        isAvailable = true;
        this.seatNum = seatNum;
        this.seatClass = seatClass;
    }
    public Seat getSeat(){
        return this;
    }

    public void reserveSeat() {
        isAvailable = false;
    }
    
    public void setAvailable(boolean availability){
        isAvailable = availability;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public String getSeatClass() {
        return seatClass;
    }
}