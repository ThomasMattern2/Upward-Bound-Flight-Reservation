package controller;

import java.util.ArrayList;

import boundary.Database;
import flightInfo.*;
import role.RegisteredCustomer;


public class FlightController{

    public FlightController(){ }

    public ArrayList<String> browseFlightNums(ArrayList<Flight> flights){
        ArrayList<String> flightDetails = new ArrayList<>();
        for(Flight f : flights){
            flightDetails.add(f.getFlightNum());
        }
        return flightDetails;

    }

    public ArrayList<String> browseFlightNums(Location origin,Location location){
        ArrayList<Flight> flights = flightsByLocation(origin, location);
        ArrayList<String> flightDetails = new ArrayList<>();
        for(Flight f : flights){
            flightDetails.add(f.getFlightNum());
        }
        return flightDetails;

    }

    public ArrayList<Flight> flightsByLocation(Location origin,Location location){
        ArrayList<Flight> allFlights = Database.getOnlyInstance().getFlightData();
        ArrayList<Flight> locationFlights = new ArrayList<>();
        for(Flight f : allFlights){
            if(f.getOrigin() == origin && f.getDestination() == location){
                locationFlights.add(f);
            }
        }
        return locationFlights;
    }

    

    public ArrayList<Flight> allFlights(){
        ArrayList<Flight> allFlights = Database.getOnlyInstance().getFlightData();
        return allFlights;
    }
    public Flight flightByPassenger(RegisteredCustomer passenger){
        ArrayList<Flight> allFlights = Database.getOnlyInstance().getFlightData();

        for(Flight f: allFlights){
            for(int i = 0; i < f.getPassengers().size(); i++){
                if(f.getPassengers().get(i) == passenger){
                    return f;
                }

            }
        }
        return null;
    }
    public void bookFlightByCustomer(RegisteredCustomer customer, Flight flight, int seatNum){
        flight.addPassenger(customer);
        flight.getAircraft().getSeats().get(seatNum).reserveSeat();
    }
}
