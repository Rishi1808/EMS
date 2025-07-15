public class Employee {
    private int employeeId;
    private String name;
    private double salary;
    private int age;
    private String gender;
    private String address;
    private String city;
    private String dob; // Format: YYYY-MM-DD
    private String doj; // Format: YYYY-MM-DD
    private String departmentName;
    private String designation;
    private String panCardNumber;
    private String aadharNumber;

    // Constructor
    public Employee(int employeeId, String name, double salary, int age, String gender,
                    String address, String city, String dob, String doj,
                    String departmentName, String designation,
                    String panCardNumber, String aadharNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.dob = dob;
        this.doj = doj;
        this.departmentName = departmentName;
        this.designation = designation;
        this.panCardNumber = panCardNumber;
        this.aadharNumber = aadharNumber;
    }

    // Getters and Setters for all fields
    // Example:
    public int getEmployeeId() {
        return employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getDoj() {
        return doj;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", dob='" + dob + '\'' +
                ", doj='" + doj + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", designation='" + designation + '\'' +
                ", panCardNumber='" + panCardNumber + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                '}';
    }
    // Add other getters and setters as needed
}
