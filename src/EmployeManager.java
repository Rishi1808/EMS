import java.util.*;

public class EmployeManager {
    private List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
    }

    public void deleteEmployee(int empId) {
        employeeList.removeIf(emp -> emp.getEmployeeId() == empId);
    }

    public Employee searchById(int empId) {
        for (Employee emp : employeeList) {
            if (emp.getEmployeeId() == empId) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeManager that = (EmployeManager) o;
        return Objects.equals(employeeList, that.employeeList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employeeList);
    }

    public List<Employee> searchByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (emp.getName().equalsIgnoreCase(name)) {
                result.add(emp);
            }
        }
        return result;
    }

    public List<Employee> searchByDepartment(String dept) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (emp.getDepartmentName().equalsIgnoreCase(dept)) {
                result.add(emp);
            }
        }
        return result;
    }

    public void updateSalaryById(int empId, double newSalary) {
        Employee emp = searchById(empId);
        if (emp != null) {
            emp.setSalary(newSalary);
        }
    }

    public void updateSalaryByDepartment(String dept, double newSalary) {
        for (Employee emp : employeeList) {
            if (emp.getDepartmentName().equalsIgnoreCase(dept)) {
                emp.setSalary(newSalary);
            }
        }
    }

    public void updateSalaryForAll(double newSalary) {
        for (Employee emp : employeeList) {
            emp.setSalary(newSalary);
        }
    }

    public Employee getHighestSalaryEmployee() {
        return Collections.max(employeeList, Comparator.comparingDouble(Employee::getSalary));
    }

    public Employee getLowestSalaryEmployee() {
        return Collections.min(employeeList, Comparator.comparingDouble(Employee::getSalary));
    }


}
