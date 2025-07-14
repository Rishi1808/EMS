import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeManager manager = new EmployeManager();

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Search Employee by Name");
            System.out.println("5. Search Employee by Department");
            System.out.println("6. Update Employee Name");
            System.out.println("7. Update Employee Address");
            System.out.println("8. Update Employee DOB");
            System.out.println("9. Update Salary by ID");
            System.out.println("10. Update Salary by Department");
            System.out.println("11. Update Salary for All Employees");
            System.out.println("12. Display Employee with Highest Salary");
            System.out.println("13. Display Employee with Lowest Salary");
            System.out.println("14. Exit :0");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter DOB (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter DOJ (YYYY-MM-DD): ");
                    String doj = scanner.nextLine();
                    System.out.print("Enter Department Name: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter PAN Card Number: ");
                    String pan = scanner.nextLine();
                    System.out.print("Enter Aadhar Number: ");
                    String aadhar = scanner.nextLine();

                    Employee emp = new Employee(id, name, salary, age, gender, address, city, dob, doj, dept, designation, pan, aadhar);
                    manager.addEmployee(emp);
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteEmployee(deleteId);
                    System.out.println("Employee deleted if ID existed.");
                    break;

                case 3:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    Employee foundById = manager.searchById(searchId);
                    System.out.println(foundById != null ? foundById : "Employee not found.");
                    break;

                case 4:
                    System.out.print("Enter Employee Name to search: ");
                    String searchName = scanner.nextLine();
                    List<Employee> foundByName = manager.searchByName(searchName);
                    foundByName.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter Department Name to search: ");
                    String searchDept = scanner.nextLine();
                    List<Employee> foundByDept = manager.searchByDepartment(searchDept);
                    foundByDept.forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Enter Employee ID to update name: ");
                    int updateNameId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    Employee empToUpdateName = manager.searchById(updateNameId);
                    if (empToUpdateName != null) {
                        empToUpdateName.setName(newName);
                        System.out.println("Name updated.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Employee ID to update address: ");
                    int updateAddressId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    Employee empToUpdateAddress = manager.searchById(updateAddressId);
                    if (empToUpdateAddress != null) {
                        empToUpdateAddress.setAddress(newAddress);
                        System.out.println("Address updated.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 8:
                    System.out.print("Enter Employee ID to update DOB: ");
                    int updateDobId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new DOB (YYYY-MM-DD): ");
                    String newDob = scanner.nextLine();
                    Employee empToUpdateDob = manager.searchById(updateDobId);
                    if (empToUpdateDob != null) {
                        empToUpdateDob.setDob(newDob);
                        System.out.println("DOB updated.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 9:
                    System.out.print("Enter Employee ID to update salary: ");
                    int updateSalaryId = scanner.nextInt();
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    manager.updateSalaryById(updateSalaryId, newSalary);
                    System.out.println("Salary updated if ID existed.");
                    break;

                case 10:
                    System.out.print("Enter Department Name to update salary: ");
                    String deptToUpdate = scanner.nextLine();
                    System.out.print("Enter new salary: ");
                    double deptSalary = scanner.nextDouble();
                    manager.updateSalaryByDepartment(deptToUpdate, deptSalary);
                    System.out.println("Salaries updated for department.");
                    break;

                case 11:
                    System.out.print("Enter new salary for all employees: ");
                    double allSalary = scanner.nextDouble();
                    manager.updateSalaryForAll(allSalary);
                    System.out.println("Salaries updated for all employees.");
                    break;

                case 12:
                    Employee highest = manager.getHighestSalaryEmployee();
                    System.out.println(highest != null ? highest : "No employees found.");
                    break;

                case 13:
                    Employee lowest = manager.getLowestSalaryEmployee();
                    System.out.println(lowest != null ? lowest : "No employees found.");
                    break;

                case 14:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
