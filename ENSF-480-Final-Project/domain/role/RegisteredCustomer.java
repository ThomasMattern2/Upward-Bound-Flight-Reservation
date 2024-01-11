package role;

import util.*;
import flightInfo.*;

import java.util.ArrayList;

public class RegisteredCustomer implements Person{
    private Name name; 
    private String email;
    private String password;
    private Address address; 
    private Payment payment = new Payment("", 0);
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>(); 
    
    public RegisteredCustomer(Name name, String email, String password, Address a, Payment p){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = a;
        this.payment = p;
    }
   
    public RegisteredCustomer(Name name, String email, String password, Address a){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = a;
    }
    
    @Override
    public Name getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setName(Name name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Name name() {
        return null;
    }

     public Payment getPayment(){
        return this.payment; 
    }

    public void setPayment(Payment p){
        this.payment = p; 
    }

    public Address getAddress(){
        return this.address; 
    }

    public void setAddress(Address a){
        this.address = a; 
    }

    public ArrayList<Ticket> getTickets(){
        return this.tickets; 
    }

    public void addTicket(Ticket t){
        this.tickets.add(t); 
    }

    public void removeTicket(Ticket t){
        this.tickets.remove(t); 
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        String firstName = this.name.getFirstName();
        String lastName = this.name.getLastName();

        stringBuilder.append(firstName)
                     .append(" ")
                     .append(lastName);
    
        return stringBuilder.toString();
    }
}
