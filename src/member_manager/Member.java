package member_manager;

import id_generator.GeneratorMain;

public class Member {

    private String firstName;
    private String lastName;
    private int id;
    private int date_of_birth;
    private double salary;
    GeneratorMain idGen = new GeneratorMain();

    public Member (String firstName, String lastName, int date_of_birth, double salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
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
        id = idGen.generateID(this.firstName, this.lastName, this.date_of_birth);
        return id;
    }

    public int getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(int date_of_birth) {
        this.date_of_birth = date_of_birth;
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
                "Birthday: " + date_of_birth + '\n' +
                "Salary=" + salary + "/hour" + '\n';

    }
}
