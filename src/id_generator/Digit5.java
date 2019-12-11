package id_generator;

//TODO Integrate the id_generator with the rest of the program.
//TODO Move all the output and input options to the UI class.
public class Digit5 {

    private String firstName;
    private String lastName;
    private int birthday;
    private int ID;
    private String maskID;

    public Digit5 (String firstName, String lastName, String maskID, int birthday, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maskID = maskID;
        this.birthday = birthday;
        this.ID = ID;
    }

    //getters and setters (not all are used atm, may not need all for this project but if someone is bored can find some
    // sort of implementation for them? <3)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getID() {
        return ID;
    }

    public void setID(int newID) {
        this.ID = newID;
    }

    public String getMaskID() {
        return maskID;
    }

    public void setMaskID(String newMaskID) {
        this.maskID = newMaskID;
    }

    public String toString() {
        return "\n>> First name: " + this.firstName + "\n" +
                ">> Last name: " + this.lastName + "\n" +
                ">> ID: " + this.ID + " (Mask ID: " + this.maskID + ")\n>>";
    }

}
