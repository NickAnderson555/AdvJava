package java112.employee;

public class Employee {

private String employeeId;
private String firstName;
private String lastName;
private String ssn;
private String department;
private String room;
private String phone;
private String output;

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setOutput() {
        this.output = this.record();
    }

    public String getOutput() {
        return output;
    }

    public String toString() {
        return "employee id: " + employeeId + " first name: " + firstName
                + " last name: " + lastName + " SSN: " + ssn + " department: "
                + department + " room: " + room + " phone: " + phone;
    }

    public String record() {
        return employeeId + "\t" + firstName + "\t" + lastName + "\t"
                + ssn + "\t" + department + "\t" + room + "\t" + phone + "\n";
    }

}
