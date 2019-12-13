package member_manager;

import id_generator.GeneratorMain;

public class Member {

    private String firstName;
    private String lastName;
    private int id;
    private int dateOfBirth;
    private double salary;
    GeneratorMain idGen = new GeneratorMain();

    public Member (String firstName, String lastName, int dateOfBirth, double salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.id = setId();
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

    public int setId() {
        id = idGen.generateID(this.firstName, this.lastName, this.dateOfBirth);
        return id;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int date_of_birth) {
        this.dateOfBirth = dateOfBirth;
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
