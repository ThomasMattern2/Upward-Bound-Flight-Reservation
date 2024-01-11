package controller;

import role.*;

import java.util.ArrayList;

import com.owen_guldberg.gmailsender.GMailer;

import boundary.Database;

import util.*;

public class AuthenticationController {

    public AuthenticationController() { }
    
    public boolean loginCrewMember(String email, String password){
        ArrayList<CrewMember> registeredCrewMembers = Database.getOnlyInstance().getCrewMemberData();
        for(CrewMember crew : registeredCrewMembers){
            if(crew.getEmail().equals(email) && crew.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean loginUser(String email, String password) {

        ArrayList<RegisteredCustomer> registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();

        for (RegisteredCustomer customer : registeredCustomers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean registerNewUser(String firstName, String lastName, String email, String password, 
                               int houseNum, String streetName, String city, 
                               String country, String postalCode) {

        ArrayList<RegisteredCustomer> registeredCustomers = Database.getOnlyInstance().getRegisteredCustomerData();
        for (RegisteredCustomer customer : registeredCustomers) {
            if (customer.getEmail().equals(email)) {
                return false;
            }
        }

        Address address = new Address(houseNum, streetName, city, country, postalCode);

        Name customerName = new Name(firstName, lastName);
        RegisteredCustomer newCustomer = new RegisteredCustomer(customerName, email, password, address);

        return registerUser(newCustomer, email);
    }

    public boolean registerUser(RegisteredCustomer user, String email) {
        ArrayList<RegisteredCustomer> passengerData = Database.getOnlyInstance().getRegisteredCustomerData();
        for(RegisteredCustomer passenger : passengerData){
            if (user == passenger) {
                return false;
            }
        }

        // Send user a promotion code via gmail for signing up
        // First time running app will be prompted to login to the gmail acc in browser
        // user: skywardboundpromotions@gmail.com
        // pass: skyward_bound1
        try {
        GMailer gMailer = new GMailer();
        gMailer.sendMail(email,"Thank you for registering with Skyward Bound!",
                "As a token of our appreciation, we're delighted to offer you a special promotion. Use the promo code below for 50% of your first flight!\r\n" +
                "\r\n" +
                "Promo Code: MEMBER50\r\n" +
                "\r\n" +
                "We look forward to serving you on board and providing you with a great travel experience.\r\n" +
                "Best regards, \r\n" +
                "The Skyward Bound Team");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user doesnt exist in RegisteredCustomer
        Database.getOnlyInstance().saveUser(user);
        return true;
    }
}
