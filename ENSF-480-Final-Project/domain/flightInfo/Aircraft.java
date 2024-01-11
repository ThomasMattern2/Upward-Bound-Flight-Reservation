package flightInfo;
import java.util.ArrayList;



public class Aircraft{
    
    private int id;
    private String name;
    Flight assignedFlight;
    private int capacity;
    ArrayList<Seat> seats;

    
    public Aircraft(){}

    public Aircraft(int id, String name, Flight assignedFlight, int capacity) {
        this.id = id;
        this.name = name;
        this.assignedFlight = assignedFlight;
        this.capacity = capacity;
        this.seats = new ArrayList<>();

        for (int seatNum = 1; seatNum <= capacity; seatNum++) {
  
            if (seatNum < (capacity / 3)) {
                // Comfort class (40% more than Ordinary)
                int price = (int) (50 * 1.4);
                Seat seat = new Seat(price, seatNum, "Comfort");
                this.seats.add(seat);
            } else if (seatNum < (2 * capacity / 3)) {
                // Business class (double the Ordinary)
                int price = 50 * 2;
                Seat seat = new Seat(price, seatNum, "Business");
                this.seats.add(seat);
            } else {
                // Ordinary class
                Seat seat = new Seat(50, seatNum, "Ordinary");
                this.seats.add(seat);
            }
        }
        
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Flight getAssignedFlight() {
        return assignedFlight;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void assignFlight(Flight flight){
        assignedFlight = flight;
    }

    public void changeSeatStatus(boolean status, int seatNum){
        if (seatNum < 1 || seatNum > capacity) {
            System.out.println("Invalid seat number");
            return;
        }

        for (Seat seat : seats) {
            if (seat.getSeatNum() == seatNum) {
                seat.setAvailable(status);
                return;
            }
        }
    }
    public ArrayList<Seat> findAvailableSeats(){
        ArrayList<Seat> availableSeats = new ArrayList<>();
        for(Seat seat : seats){
            if(seat.getAvailability() == true){
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
}
