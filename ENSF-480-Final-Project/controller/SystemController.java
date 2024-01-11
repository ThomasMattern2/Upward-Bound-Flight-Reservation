package controller;

import java.util.ArrayList;
import java.util.HashMap;

import boundary.Database;

import flightInfo.*;
import role.*;

public class SystemController {
	private ArrayList<Flight> flights = Database.getOnlyInstance().getFlightData();
	private ArrayList<Location> locations = Database.getOnlyInstance().getLocationData();
	private ArrayList<CrewMember> crewMembers = Database.getOnlyInstance().getCrewMemberData();
	private ArrayList<RegisteredCustomer> registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();
	private ArrayList<Ticket> tickets = Database.getOnlyInstance().getTicketData();
	private HashMap<String, Location> locationMap = new HashMap<>();
	private HashMap<String, Flight> flightMap = new HashMap<>();


    public SystemController(){
		for (Location loc : locations) {
            locationMap.put(loc.toString(), loc);
        }
		for (Flight flight : flights) {
            flightMap.put(flight.getFlightNum(), flight);
        }
    }
	public ArrayList<RegisteredCustomer> getRegisteredCustomers() {
		return registeredCustomers;
	}

	public Location getLocationByName(String name) {
        return locationMap.get(name);
    }
	public ArrayList<Flight> getFlightsCrewmembers(String crewmemberEmail){
		CrewMember crewmember = null;
		for(CrewMember c : crewMembers){
			if(c.getEmail().equals(crewmemberEmail)){
				crewmember = c;
			}
		}
		ArrayList<Flight> f = new ArrayList<>();
		for(int i = 0 ; i < flights.size(); i++){
			if(flights.get(i).getCrewMembers() == null){
				System.out.println(flights.get(i).getCrewMembers().get(0));
			}
			else if(flights.get(i).getCrewMembers().contains(crewmember)){
				f.add(flights.get(i));
			}
			
		}
		return f;
	}
	public ArrayList<RegisteredCustomer> getFlightsPassengers(String flightnumer){

		for(Flight f : flights){
			if(f.getFlightNum().equals(flightnumer)){
				return f.getPassengers();
			}
		}
		return null;
	}
	public ArrayList<String> getPassengerStrings(ArrayList<RegisteredCustomer> customers){
		ArrayList<String> customerStrings = new ArrayList<>();
		for(RegisteredCustomer customer: customers){
			customerStrings.add(customer.toString());
		}
		return customerStrings;
	}
	public ArrayList<Location> getLocations(){
		return locations;
	}

	public ArrayList<String> getFlightStrings(ArrayList<Flight> flights){
		ArrayList<String> flightStrings = new ArrayList<>();
		for (Flight flight : flights) {
			flightStrings.add(flight.toString());
		}
		return flightStrings;
	}
	public ArrayList<String> getLocationStrings(ArrayList<Location> locations){
		ArrayList<String> locationStrings = new ArrayList<>();
		for (Location location : locations) {
			locationStrings.add(location.toString());
		}
		return locationStrings;
	}

	public ArrayList<String> getLocationStrings(){
		ArrayList<Location> locations = getLocations();
		ArrayList<String> locationStrings = new ArrayList<>();
		for (Location location : locations) {
			locationStrings.add(location.toString());
		}
		return locationStrings;
	}

	public Flight getFlightByNum(String num) {
        return flightMap.get(num);
    }

	public ArrayList<Flight> getFlights(){
		return flights;
	}

	public RegisteredCustomer getUserByEmail(String email){
		registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();
		for(RegisteredCustomer customer : registeredCustomers){
			if(customer.getEmail().equals(email)){
				return customer;
			}
		}
		return null;
	}

	public String getNameByEmail(String email){
		registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();
		crewMembers = Database.getOnlyInstance().getCrewMemberData();
		for(RegisteredCustomer customer : registeredCustomers){
			if(customer.getEmail().equals(email)){
				return customer.getName().toString();
			}
		}
		for(CrewMember crew : crewMembers){
			if(crew.getEmail().equals(email)){
				return crew.getName().toString();
			}
		}
		return null;
	}

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

	public boolean cancelFlight(Flight f) {

		tickets.clear();
		tickets = Database.getOnlyInstance().getTicketData();

		for (RegisteredCustomer customer : registeredCustomers) {
			ArrayList<Ticket> ticketsToRemove = new ArrayList<>();
			for (Ticket ticket : customer.getTickets()) {
				if (ticket.getFlightNumber().equals(f.getFlightNum())) {
					ticketsToRemove.add(ticket);
				}
			}
		
			for (Ticket ticket : ticketsToRemove) {
				customer.removeTicket(ticket);
			}
		}

		return Database.getOnlyInstance().cancelFlight(f);
	}

	public boolean addFlight(Flight f) {
		return Database.getOnlyInstance().addFlightToDB(f);
	}

	public ArrayList<Aircraft> getAircrafts() {
		return Database.getOnlyInstance().getAircraftData();
	}

	public boolean addAircraft(Aircraft a) {
		return Database.getOnlyInstance().addAircraftToDB(a);
	}

	public boolean deleteAircraft(Aircraft a) {
		return Database.getOnlyInstance().deleteAircraft(a);
	}

	public boolean modifyFlight(Flight f) {
		return Database.getOnlyInstance().updateFlight(f);
	}

	public static void main(String[] args) {
		SystemController system = new SystemController();
	}
	
}






