package member_manager;

import id_generator.GeneratorMain;
import java.util.ArrayList;

public class Member {

    private String firstName;
    private String lastName;
    private int id;
    private int dateOfBirth;
    private double salary;
    private ArrayList<Timesheet> hoursWorked = new ArrayList<>();

    GeneratorMain idGen = new GeneratorMain();

    //constructor for a new employee where we need birthday to generate a new ID
    public Member (String firstName, String lastName, int dateOfBirth, double salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.id = loadID();
    }
    //constructor for an already existing employee where we have the ID already stored
    public Member (String firstName, String lastName, float ID, double salary ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id =(int) ID;  //have to have it as a float then cast it to an int so we have 2 different signatures for the constructors
        this.salary = salary;
    }
    //TODO To be used with the JSON save states
    public void addHours(int week, int hours) {
        //change this code to suit saving to JSON
        int index = week - 1;
        hoursWorked.get(index).setWeek(week);
        hoursWorked.get(index).setHours(hours);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    //generates a new ID based on names and birthday
    public int loadID() {
        id = idGen.generateID(this.firstName, this.lastName, this.dateOfBirth);
        return id;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int date_of_birth) {
        this.dateOfBirth = dateOfBirth;
    }

    //Added salary getter to find salary of a specific ID.
    public double getSalary(int id) {
        return salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  "Name: " + firstName +  " " + lastName + '\n' +
                "ID: " + id + '\n' +
                "Birthday: " + dateOfBirth + '\n' +
                "Salary: " + salary + "/hour" + '\n';

    }
}
