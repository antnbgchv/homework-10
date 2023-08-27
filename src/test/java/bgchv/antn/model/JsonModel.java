package bgchv.antn.model;

import java.util.List;

public class JsonModel {
    private String firstName, lastName, phoneNumber, dateOfBirth, currentAddress;
    private List<String> favoriteFood;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public List<String> getFavoriteFood() {
        return favoriteFood;
    }

}
