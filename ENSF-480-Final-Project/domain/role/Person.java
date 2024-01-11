package role;
import util.*;

public interface Person{
    // name and email default
    public Name name(); 
    public String email = "";

    public Name getName();
    public String getEmail();
    public void setName(Name name);
    public void setEmail(String email);
}