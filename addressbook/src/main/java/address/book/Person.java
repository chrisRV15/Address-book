package address.book;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;


public class Person implements Serializable {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty street;
    private SimpleStringProperty city;
    private SimpleStringProperty state;

    Person(String fName, String lName, String mailE, String Street, String City, String State){
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(mailE);
        this.street = new SimpleStringProperty(Street);
        this.city = new SimpleStringProperty(City);
        this.state = new SimpleStringProperty(State);
    }

    public void setFirstName(String fName){
        firstName.set(fName);
    }

    public void setLastName(String lName){
        lastName.set(lName);
    }

    public void setEmail(String mailE){
        email.set(mailE);
    }

    public void setStreet(String Street){
        street.set(Street);
    }

    public void setCity(String City){
        city.set(City);
    }

    public void setState(String State){
        state.set(State);
    }


    public String getFirstName(){
        return firstName.get();
    }
    
    public String getLastName(){
        return lastName.get();
    }

    public String getEmail(){
        return email.get();
    }

    public String getStreet(){
        return street.get();
    }

    public String getCity(){
        return city.get();
    }

    public String getState(){
        return state.get();
    }

    @Override
    public String toString(){
        return "| First Name: " + getFirstName() + " | LastName: " + getLastName() + " | Email: " + getEmail() + " | Street: " + getStreet()
        + " | City: " + getCity() + " | State: " + getState() + " |"; 
    }

}
