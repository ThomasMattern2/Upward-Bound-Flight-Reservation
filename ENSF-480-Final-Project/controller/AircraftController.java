package controller;
import java.util.ArrayList;

import boundary.Database;

import flightInfo.*;

public class AircraftController {

    public AircraftController(){ }

    public ArrayList<Seat> seatByAircraft(Aircraft aircraft){
        ArrayList<Aircraft> ac = Database.getOnlyInstance().getAircraftData();
        for(Aircraft craft : ac){
            if(craft.getId() == aircraft.getId()){
                return craft.getSeats();
            }
        }
        return new ArrayList<>();
    }
    public String getSeatDetails(Seat seat) {
        return "Seat Number: " + seat.getSeatNum() + 
               "\nPrice: $" + seat.getPrice() + 
               "\nClass: " + seat.getSeatClass() +
               "\nAvailable: " + (seat.getAvailability() ? "Yes" : "No");
    }

    public void updateSeatAvailability(Seat seat, boolean availability) {
        seat.setAvailable(availability);
    }
}
