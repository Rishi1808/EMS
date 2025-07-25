# Employee Management System (EMS)

A comprehensive Java-based Employee Management System that demonstrates core Object-Oriented Programming principles and provides complete CRUD operations for employee data management.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [OOP Principles Used](#oop-principles-used)
- [Classes and Their Responsibilities](#classes-and-their-responsibilities)
- [Menu Options](#menu-options)
- [Validation Features](#validation-features)
- [Installation and Setup](#installation-and-setup)
- [Usage Examples](#usage-examples)
- [Technical Specifications](#technical-specifications)

## 🔍 Overview

The Employee Management System is a console-based Java application designed to manage employee records efficiently. It provides a user-friendly menu-driven interface for performing various operations on employee data including adding, updating, searching, and deleting employee records.

## 🚀 Features

### Core Functionality
- **Employee CRUD Operations**: Create, Read, Update, and Delete employee records
- **Advanced Search**: Search by ID, name, or department
- **Salary Management**: Update salaries individually, by department, or for all employees
- **Data Analytics**: View highest/lowest salary employees and system statistics
- **Comprehensive Validation**: Input validation for all data fields
- **Age Compliance**: Ensures employees are at least 18 years old when joining

### Enhanced Features
- **Salary Transparency**: Display current salary before updates
- **Confirmation Prompts**: Safety confirmation for mass operations
- **Detailed Reporting**: Complete employee listings with formatted output
- **Error Handling**: Robust error handling and user feedback

## 🏗️ System Architecture

```
┌─────────────────┐
│     Main.java   │  ← User Interface & Menu System
├─────────────────┤
│ EmployeManager  │  ← Business Logic & Data Management
├─────────────────┤
│   Employee.java │  ← Data Model
├─────────────────┤
│ Validation.java │  ← Input Validation & Business Rules
└─────────────────┘
```

## 🎯 OOP Principles Used

### 1. **Encapsulation**
- **Employee Class**: All fields are private with public getter/setter methods
- **Data Protection**: Employee data is encapsulated and accessed through controlled methods
- **Information Hiding**: Internal implementation details are hidden from external classes

```java
public class Employee {
    private int employeeId;        // Private fields
    private String name;
    private double salary;
    
    public int getEmployeeId() {   // Controlled access
        return employeeId;
    }
    
    public void setSalary(double salary) {  // Controlled modification
        this.salary = salary;
    }
}
```

### 2. **Abstraction**
- **MenuOption Enum**: Abstract menu system with polymorphic execution
- **Validation Class**: Abstract validation logic separated from business logic
- **Manager Interface**: Abstract data operations through EmployeManager

```java
private enum MenuOption {
    ADD_EMPLOYEE(1, "Add Employee") {
        @Override
        public void execute() {    // Abstract method implementation
            addEmployee();
        }
    };
    
    public abstract void execute();  // Abstract method
}
```

### 3. **Inheritance**
- **Enum Extension**: MenuOption enum extends implicit Enum class
- **Object Class**: All classes inherit from Object (toString(), equals(), hashCode())

### 4. **Polymorphism**
- **Method Overriding**: toString(), equals(), hashCode() methods overridden
- **Runtime Polymorphism**: MenuOption execution through abstract method calls
- **Method Overloading**: Multiple validation methods with different parameters

```java
@Override
public String toString() {      // Method overriding
    return "Employee{" + /* employee details */ + "}";
}

@Override
public boolean equals(Object o) {  // Polymorphic method
    // Custom equality logic
}
```

### 5. **Composition**
- **EmployeManager**: Composed of List<Employee> and Map<Integer, Employee>
- **Main Class**: Composed of EmployeManager and Scanner objects

```java
public class EmployeManager {
    private List<Employee> employeeList = new ArrayList<>();      // Composition
    private Map<Integer, Employee> employeeById = new HashMap<>(); // Composition
}
```

## 📁 Classes and Their Responsibilities

### 1. **Employee.java** - Data Model
**Responsibility**: Represents an employee entity with all personal and professional details

**Key Features**:
- 13 private attributes (ID, name, salary, age, gender, etc.)
- Parameterized constructor for object creation
- Getter/Setter methods for controlled access
- Overridden toString() for readable output
- Encapsulation of employee data

**Fields**:
```java
private int employeeId;
private String name;
private double salary;
private int age;
private String gender;
private String address;
private String city;
private String dob;  // Date of Birth (YYYY-MM-DD)
private String doj;  // Date of Joining (YYYY-MM-DD)
private String departmentName;
private String designation;
private String panCardNumber;
private String aadharNumber;
```

### 2. **EmployeManager.java** - Business Logic Layer
**Responsibility**: Manages all employee operations and business logic

**Key Features**:
- Dual data structure (List + HashMap) for optimized operations
- CRUD operations implementation
- Search and filter functionalities
- Salary management operations
- Statistical analysis methods

**Core Methods**:
- `addEmployee(Employee emp)`: Adds new employee with duplicate ID check
- `deleteEmployee(int empId)`: Removes employee from both data structures
- `searchById(int empId)`: O(1) employee lookup using HashMap
- `searchByName/Department(String)`: Linear search with case-insensitive matching
- `updateSalary*()`: Various salary update operations
- `getHighest/LowestSalaryEmployee()`: Statistical analysis
- `getAllEmployees()`: Returns defensive copy of employee list

### 3. **Validation.java** - Validation Layer
**Responsibility**: Handles all input validation and business rule enforcement

**Validation Types**:
- **Format Validation**: PAN, Aadhaar, Date, Name patterns using Regex
- **Range Validation**: Age (18-65), Salary (0-10M), String lengths
- **Business Rules**: Age compliance at joining (≥18 years old)
- **Data Integrity**: Date format and logical date validation

**Key Methods**:
```java
isValidAadhaar(String)     // 12-digit pattern starting with 2-9
isValidPAN(String)         // ABCDE1234F format
isValidDate(String)        // YYYY-MM-DD with logical validation
isAgeAtLeast18AtJoining()  // Business rule compliance
```

### 4. **Main.java** - Presentation Layer
**Responsibility**: User interface, menu system, and application flow control

**Architecture Pattern**: Command Pattern using Enum
**Key Features**:
- Menu-driven interface with 16 options
- Input validation and error handling
- Formatted output and user feedback
- Exception handling and resource management

## 📋 Menu Options

| Option | Functionality | Description |
|--------|---------------|-------------|
| 1 | Add Employee | Create new employee record with full validation |
| 2 | Delete Employee | Remove employee by ID |
| 3 | Search by ID | Find specific employee using unique ID |
| 4 | Search by Name | Find employees with matching names |
| 5 | Search by Department | List all employees in a department |
| 6 | Update Name | Modify employee name with validation |
| 7 | Update Address | Change employee address |
| 8 | Update DOB | Modify date of birth with age compliance check |
| 9 | Update Salary by ID | Individual salary update with current salary display |
| 10 | Update Salary by Department | Bulk salary update for department |
| 11 | Update Salary for All | Mass salary update with confirmation |
| 12 | Highest Salary Employee | Display employee with maximum salary |
| 13 | Lowest Salary Employee | Display employee with minimum salary |
| 14 | System Statistics | Show total employees, departments, salary ranges |
| 15 | List All Employees | Display all employees with formatted output |
| 16 | Exit | Close application with cleanup |

## 🔐 Validation Features

### Input Validation
- **Employee ID**: Unique integer validation
- **Name**: 2-50 characters, alphabets and spaces only
- **Salary**: Non-negative, maximum 10 million
- **Age**: Between 18-65 years
- **Gender**: Male/Female/Other (case-insensitive)
- **Address**: 5-100 characters
- **City**: 2-50 characters
- **Dates**: YYYY-MM-DD format with logical validation

### Business Rule Validation
- **Age Compliance**: Employee must be ≥18 years old at joining date
- **Duplicate Prevention**: No duplicate employee IDs allowed
- **Data Integrity**: Date fields validated for format and logical consistency

### Format Validation (Regex Patterns)
```java
AADHAAR_PATTERN: "^[2-9]{1}[0-9]{11}$"     // 12 digits, starts with 2-9
PAN_PATTERN: "^[A-Z]{5}[0-9]{4}[A-Z]{1}$"  // ABCDE1234F format
DATE_PATTERN: "^\\d{4}-\\d{2}-\\d{2}$"     // YYYY-MM-DD
NAME_PATTERN: "^[A-Za-z ]+$"               // Letters and spaces only
```

## 🛠️ Installation and Setup

### Prerequisites
- Java JDK 8 or higher
- Command line interface (Terminal/Command Prompt)

### Installation Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Rishi1808/EMS.git
   cd EMS
   ```

2. **Compile the Source Code**:
   ```bash
   javac src/*.java
   ```

3. **Run the Application**:
   ```bash
   cd src
   java Main
   ```

### Project Structure
```
EMS/
├── src/
│   ├── Main.java           # Main application class
│   ├── Employee.java       # Employee data model
│   ├── EmployeManager.java # Business logic manager
│   └── Validation.java     # Input validation utility
├── README.md              # This file
└── .gitignore            # Git ignore rules
```

## 💡 Usage Examples

### Adding a New Employee
```
--- Add New Employee ---
Enter Employee ID: 101
Enter Name: John Doe
Enter Salary: 50000
Enter Age: 28
Enter Gender (Male/Female/Other): Male
Enter Address: 123 Main Street
Enter City: New York
Enter DOB (YYYY-MM-DD): 1995-06-15
Enter DOJ (YYYY-MM-DD): 2023-01-15
Enter Department Name: IT
Enter Designation: Software Engineer
Enter PAN Card Number: ABCDE1234F
Enter Aadhar Number: 234567890123
Employee added successfully.
```

### Updating Salary with Current Display
```
--- Update Salary by ID ---
Enter Employee ID to update salary: 101
Employee found: John Doe (ID: 101)
Current Salary: ₹50000.0
Enter new salary: 60000
Salary updated successfully.
Previous Salary: ₹50000.0
New Salary: ₹60000.0
```

## 🔧 Technical Specifications

### Data Structures Used
- **ArrayList**: Dynamic employee storage with indexed access
- **HashMap**: O(1) employee lookup by ID
- **HashSet**: Unique department tracking
- **Scanner**: User input handling

### Design Patterns
- **Command Pattern**: Menu options with execute() method
- **Singleton-like**: Static manager instance in Main class
- **Factory Pattern**: MenuOption.fromNumber() method
- **Template Method**: Abstract execute() in MenuOption enum

### Memory Management
- **Defensive Copying**: getAllEmployees() returns new ArrayList
- **Resource Cleanup**: Scanner closed in finally block
- **Efficient Lookups**: HashMap for O(1) ID-based operations

### Error Handling
- **Input Validation**: Comprehensive validation before processing
- **Exception Handling**: Try-catch blocks for robust error management
- **User Feedback**: Clear error messages and operation confirmations
- **Graceful Degradation**: Continues operation despite individual errors

## 🎖️ Key Learning Outcomes

This project demonstrates mastery of:
1. **Object-Oriented Design**: Proper class design with clear responsibilities
2. **Data Structure Selection**: Appropriate choice of collections for different operations
3. **Input Validation**: Comprehensive validation strategies
4. **Error Handling**: Robust exception management
5. **User Experience**: Intuitive menu design and clear feedback
6. **Code Organization**: Clean, maintainable code structure
7. **Business Logic Implementation**: Real-world business rule enforcement
