package util;

public class Address {

    private int houseNum;
    private String streetName;
    private String city;
    private String country;
    private String postalCode;

    // Constructor
    public Address(int houseNum, String streetName, String city, String country, String postalCode) {
        this.houseNum = houseNum;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }
    // default ctor 
    public Address() {
        this.houseNum = 0;
        this.streetName = "";
        this.city = "";
        this.country = "";
        this.postalCode = "";
    }

    // Getters
    public int getHouseNum() {
        return houseNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Setters
    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
