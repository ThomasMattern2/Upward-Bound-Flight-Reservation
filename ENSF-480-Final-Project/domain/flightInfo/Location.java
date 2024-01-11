package flightInfo;

public class Location {
    private String airportName;
    private String city;
    private String country;

    public Location(){} //need for db class

    public Location(String airport,String city, String country){
        this.airportName = airport;
        this.city = city;
        this.country = country;
    }
    public Location getLocation(){
        return this;
    }
    // Getter for airportName
    public String getAirportName() {
        return airportName;
    }

    // Setter for airportName
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Setter for city
    public void setCity(String city) {
        this.city = city;
    }

    // Getter for country
    public String getCountry() {
        return country;
    }

    // Setter for country
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return "Airport: " + airportName + " | City: " + city + " | Country: " + country;
    }
}
