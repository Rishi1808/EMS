import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static EmployeManager manager = new EmployeManager();

    private enum MenuOption {
        ADD_EMPLOYEE(1, "Add Employee") {
            @Override
            public void execute() {
                addEmployee();
            }
        },
        DELETE_EMPLOYEE(2, "Delete Employee") {
            @Override
            public void execute() {
                deleteEmployee();
            }
        },
        SEARCH_BY_ID(3, "Search Employee by ID") {
            @Override
            public void execute() {
                searchEmployeeById();
            }
        },
        SEARCH_BY_NAME(4, "Search Employee by Name") {
            @Override
            public void execute() {
                searchEmployeeByName();
            }
        },
        SEARCH_BY_DEPARTMENT(5, "Search Employee by Department") {
            @Override
            public void execute() {
                searchEmployeeByDepartment();
            }
        },
        UPDATE_NAME(6, "Update Employee Name") {
            @Override
            public void execute() {
                updateEmployeeName();
            }
        },
        UPDATE_ADDRESS(7, "Update Employee Address") {
            @Override
            public void execute() {
                updateEmployeeAddress();
            }
        },
        UPDATE_DOB(8, "Update Employee DOB") {
            @Override
            public void execute() {
                updateEmployeeDOB();
            }
        },
        UPDATE_SALARY_BY_ID(9, "Update Salary by ID") {
            @Override
            public void execute() {
                updateSalaryById();
            }
        },
        UPDATE_SALARY_BY_DEPT(10, "Update Salary by Department") {
            @Override
            public void execute() {
                updateSalaryByDepartment();
            }
        },
        UPDATE_SALARY_ALL(11, "Update Salary for All Employees") {
            @Override
            public void execute() {
                updateSalaryForAll();
            }
        },
        DISPLAY_HIGHEST_SALARY(12, "Display Employee with Highest Salary") {
            @Override
            public void execute() {
                displayHighestSalaryEmployee();
            }
        },
        DISPLAY_LOWEST_SALARY(13, "Display Employee with Lowest Salary") {
            @Override
            public void execute() {
                displayLowestSalaryEmployee();
            }
        },
        SHOW_STATISTICS(14, "Show System Statistics") {
            @Override
            public void execute() {
                showSystemStatistics();
            }
        },
        EXIT(15, "Exit") {
            @Override
            public void execute() {
                /* Exit handled in processChoice */ }
        };

        private final int number;
        private final String description;

        MenuOption(int number, String description) {
            this.number = number;
            this.description = description;
        }

        public abstract void execute();

        public int getNumber() {
            return number;
        }

        public String getDescription() {
            return description;
        }

        public static MenuOption fromNumber(int number) {
            for (MenuOption option : values()) {
                if (option.number == number) {
                    return option;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Management System");

        try {
            while (true) {
                displayMenu();
                int choice = getValidChoice();

                if (!processChoice(choice)) {
                    break; // Exit requested
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Thank you for using Employee Management System!");
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Employee Management System =====");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getNumber() + ". " + option.getDescription());
        }
        System.out.print("Enter your choice: ");
    }

    private static int getValidChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static boolean processChoice(int choice) {
        try {
            MenuOption selectedOption = MenuOption.fromNumber(choice);
            if (selectedOption != null) {
                if (selectedOption == MenuOption.EXIT) {
                    return false;
                }
                selectedOption.execute();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Please try again.");
        }
        return true;
    }

    private static int getValidIntegerInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getValidDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static String getValidStringInput(String prompt, java.util.function.Predicate<String> validator,
            String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!validator.test(input)) {
                System.out.println(errorMessage);
            }
        } while (!validator.test(input));
        return input;
    }

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");

        int id = getValidIntegerInput("Enter Employee ID: ");

        if (manager.hasEmployee(id)) {
            System.out.println("Error: Employee ID already exists. Please use a different ID.");
            return;
        }

        String name = getValidStringInput("Enter Name: ",
                Validation::isValidName,
                "Invalid name. Please use only letters and spaces (2-50 characters).");

        double salary = getValidDoubleInput("Enter Salary: ");
        while (!Validation.isValidSalary(salary)) {
            System.out.println("Invalid salary. Please enter a non-negative value.");
            salary = getValidDoubleInput("Enter Salary: ");
        }

        int age = getValidIntegerInput("Enter Age: ");
        while (!Validation.isValidAge(age)) {
            System.out.println("Invalid age. Age must be between 18 and 65.");
            age = getValidIntegerInput("Enter Age: ");
        }

        String gender = getValidStringInput("Enter Gender (Male/Female/Other): ",
                Validation::isValidGender,
                "Invalid gender. Please enter Male, Female, or Other.");

        String address = getValidStringInput("Enter Address: ",
                addr -> Validation.isValidString(addr, 5, 100),
                "Address must be between 5 and 100 characters.");

        String city = getValidStringInput("Enter City: ",
                c -> Validation.isValidString(c, 2, 50),
                "City name must be between 2 and 50 characters.");

        String dob = getValidStringInput("Enter DOB (YYYY-MM-DD): ",
                Validation::isValidDate,
                "Invalid date format. Please use YYYY-MM-DD format.");

        String doj = getValidStringInput("Enter DOJ (YYYY-MM-DD): ",
                Validation::isValidDate,
                "Invalid date format. Please use YYYY-MM-DD format.");

        System.out.print("Enter Department Name: ");
        String dept = scanner.nextLine().trim();

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine().trim();

        String pan = getValidStringInput("Enter PAN Card Number: ",
                p -> Validation.isValidPAN(p.toUpperCase()),
                "Invalid PAN format. Use format: ABCDE1234F").toUpperCase();

        String aadhar = getValidStringInput("Enter Aadhar Number: ",
                Validation::isValidAadhaar,
                "Invalid Aadhar format. Must be 12 digits starting with 2-9.");

        Employee emp = new Employee(id, name, salary, age, gender, address, city, dob, doj, dept, designation, pan,
                aadhar);
        if (manager.addEmployee(emp)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        int deleteId = getValidIntegerInput("Enter Employee ID to delete: ");

        if (manager.deleteEmployee(deleteId)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found with ID: " + deleteId);
        }
    }

    private static void searchEmployeeById() {
        System.out.println("\n--- Search Employee by ID ---");
        int searchId = getValidIntegerInput("Enter Employee ID to search: ");

        Employee found = manager.searchById(searchId);
        if (found != null) {
            System.out.println("Employee found:");
            System.out.println(found);
        } else {
            System.out.println("Employee not found with ID: " + searchId);
        }
    }

    private static void searchEmployeeByName() {
        System.out.println("\n--- Search Employee by Name ---");
        System.out.print("Enter Employee Name to search: ");
        String searchName = scanner.nextLine().trim();

        if (searchName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        List<Employee> found = manager.searchByName(searchName);
        if (found.isEmpty()) {
            System.out.println("No employees found with name: " + searchName);
        } else {
            System.out.println("Found " + found.size() + " employee(s):");
            found.forEach(System.out::println);
        }
    }

    private static void searchEmployeeByDepartment() {
        System.out.println("\n--- Search Employee by Department ---");
        System.out.print("Enter Department Name to search: ");
        String searchDept = scanner.nextLine().trim();

        if (searchDept.isEmpty()) {
            System.out.println("Department name cannot be empty.");
            return;
        }

        List<Employee> found = manager.searchByDepartment(searchDept);
        if (found.isEmpty()) {
            System.out.println("No employees found in department: " + searchDept);
        } else {
            System.out.println("Found " + found.size() + " employee(s) in " + searchDept + ":");
            found.forEach(System.out::println);
        }
    }

    private static void updateEmployeeName() {
        System.out.println("\n--- Update Employee Name ---");
        int updateId = getValidIntegerInput("Enter Employee ID to update name: ");

        Employee emp = manager.searchById(updateId);
        if (emp != null) {
            String newName = getValidStringInput("Enter new name: ",
                    Validation::isValidName,
                    "Invalid name. Please use only letters and spaces (2-50 characters).");
            emp.setName(newName);
            System.out.println("Name updated successfully.");
        } else {
            System.out.println("Employee not found with ID: " + updateId);
        }
    }

    private static void updateEmployeeAddress() {
        System.out.println("\n--- Update Employee Address ---");
        int updateId = getValidIntegerInput("Enter Employee ID to update address: ");

        Employee emp = manager.searchById(updateId);
        if (emp != null) {
            String newAddress = getValidStringInput("Enter new address: ",
                    addr -> Validation.isValidString(addr, 5, 100),
                    "Address must be between 5 and 100 characters.");
            emp.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        } else {
            System.out.println("Employee not found with ID: " + updateId);
        }
    }

    private static void updateEmployeeDOB() {
        System.out.println("\n--- Update Employee DOB ---");
        int updateId = getValidIntegerInput("Enter Employee ID to update DOB: ");

        Employee emp = manager.searchById(updateId);
        if (emp != null) {
            String newDob = getValidStringInput("Enter new DOB (YYYY-MM-DD): ",
                    Validation::isValidDate,
                    "Invalid date format. Please use YYYY-MM-DD format.");
            emp.setDob(newDob);
            System.out.println("DOB updated successfully.");
        } else {
            System.out.println("Employee not found with ID: " + updateId);
        }
    }

    private static void updateSalaryById() {
        System.out.println("\n--- Update Salary by ID ---");
        int updateId = getValidIntegerInput("Enter Employee ID to update salary: ");

        double newSalary = getValidDoubleInput("Enter new salary: ");

        if (manager.updateSalaryById(updateId, newSalary)) {
            System.out.println("Salary updated successfully.");
        } else {
            System.out.println("Failed to update salary. Employee not found or invalid salary.");
        }
    }

    private static void updateSalaryByDepartment() {
        System.out.println("\n--- Update Salary by Department ---");
        System.out.print("Enter Department Name to update salary: ");
        String dept = scanner.nextLine().trim();

        if (dept.isEmpty()) {
            System.out.println("Department name cannot be empty.");
            return;
        }

        double newSalary = getValidDoubleInput("Enter new salary: ");

        int updated = manager.updateSalaryByDepartment(dept, newSalary);
        if (updated > 0) {
            System.out.println("Updated salary for " + updated + " employees in " + dept + " department.");
        } else {
            System.out.println("No employees found in department or invalid salary.");
        }
    }

    private static void updateSalaryForAll() {
        System.out.println("\n--- Update Salary for All Employees ---");
        double newSalary = getValidDoubleInput("Enter new salary for all employees: ");

        int updated = manager.updateSalaryForAll(newSalary);
        if (updated > 0) {
            System.out.println("Updated salary for all " + updated + " employees.");
        } else {
            System.out.println("No employees to update or invalid salary.");
        }
    }

    private static void displayHighestSalaryEmployee() {
        System.out.println("\n--- Employee with Highest Salary ---");
        Employee highest = manager.getHighestSalaryEmployee();
        if (highest != null) {
            System.out.println(highest);
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void displayLowestSalaryEmployee() {
        System.out.println("\n--- Employee with Lowest Salary ---");
        Employee lowest = manager.getLowestSalaryEmployee();
        if (lowest != null) {
            System.out.println(lowest);
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void showSystemStatistics() {
        System.out.println("\n===== System Statistics =====");
        System.out.println("Total Employees: " + manager.getEmployeeCount());

        List<String> departments = manager.getAllDepartments();
        System.out.println("Total Departments: " + departments.size());

        if (!departments.isEmpty()) {
            System.out.println("Departments: " + String.join(", ", departments));
        }

        Employee highest = manager.getHighestSalaryEmployee();
        Employee lowest = manager.getLowestSalaryEmployee();

        if (highest != null) {
            System.out.println("Highest Salary: ₹" + highest.getSalary() + " (" + highest.getName() + ")");
        }
        if (lowest != null) {
            System.out.println("Lowest Salary: ₹" + lowest.getSalary() + " (" + lowest.getName() + ")");
        }
    }
}
