package flightInfo;
import role.*;
import java.util.ArrayList;

public class Flight {


    private Location destination;
    private String flightNum;
    private Location origin;
    private String departureTime;
    private String arrivalTime;
    private String flightTime;
    private String flightDate;
    private Aircraft aircraft; 
    private ArrayList<CrewMember> crewMembers;
    private ArrayList<RegisteredCustomer> passengers;

    // Constructor
    public Flight(Location origin, Location destination, String flightNum,String date, String departureTime, String arrivalTime, String flightTime,Aircraft aircraft, ArrayList<CrewMember> crewMembers, ArrayList<RegisteredCustomer> passengers) {
        this.origin = origin;
        this.destination = destination;
        this.flightNum = flightNum;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightTime = flightTime;
        this.crewMembers = crewMembers;
        this.passengers = passengers;
        this.aircraft = aircraft; 
        this.flightDate = date; 
    }
    public Flight(Location origin, Location destination, String flightNum, String date, String departureTime, String arrivalTime, String flightTime, Aircraft aircraft) {
        this.origin = origin;
        this.destination = destination;
        this.flightNum = flightNum;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightTime = flightTime;
        this.aircraft = aircraft; 
        this.flightDate = date; 
    }

    // Getters
    public String getFlightDate() {
        return flightDate; 
    }

    public Aircraft getAircraft() {
        return aircraft; 
    }
    
    public Flight getFlight(){
        return this;
    }
     public Location getDestination() {
        return destination;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public Location getOrigin() {
        return origin;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public ArrayList<RegisteredCustomer> getPassengers() {
        return passengers;
    }

    // Setters
    public void setAircraft(Aircraft a) {
        this.aircraft = a; 
    }
    
    public void setFlightDate(String date) {
        this.flightDate = date;
    }
    
    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }


    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public void setCrewMembers(ArrayList<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }
    public void addCrewMember(CrewMember crewMember){
        this.crewMembers.add(crewMember);
    }
    public void setPassengers(ArrayList<RegisteredCustomer> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(RegisteredCustomer passenger){
        if(this.passengers == null){
            passengers = new ArrayList<>();
        }
        this.passengers.add(passenger);
    }

    public String toString() {
        return "<html><u>Flight Number:</u> " + getFlightNum() +
           "<br><u>Date:</u> " + getFlightDate() +
           "<br><u>Origin:</u> " + getOrigin().toString() +
           "<br><u>Destination:</u> " + getDestination().toString() +
           "<br><u>Departure Time:</u> " + getDepartureTime() +
           "<br><u>Arrival Time:</u> " + getArrivalTime() +
           "<br><u>Flight Duration:</u> " + getFlightTime() +
           "<br></html>";
    }
}
