import java.util.*;

public class EmployeManager {
    private List<Employee> employeeList = new ArrayList<>();

    private Map<Integer, Employee> employeeById = new HashMap<>();

    public boolean addEmployee(Employee emp) {
        if (emp == null || employeeById.containsKey(emp.getEmployeeId())) {
            return false;
        }

        employeeList.add(emp);
        employeeById.put(emp.getEmployeeId(), emp);

        return true;
    }

    public boolean deleteEmployee(int empId) {
        Employee emp = employeeById.get(empId);
        if (emp == null) {
            return false;
        }

        employeeList.remove(emp);
        employeeById.remove(empId);

        return true;
    }

    public Employee searchById(int empId) {
        return employeeById.get(empId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
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

    public boolean updateSalaryById(int empId, double newSalary) {
        Employee emp = searchById(empId);
        if (emp != null && newSalary >= 0) {
            emp.setSalary(newSalary);
            return true;
        }
        return false;
    }

    public int updateSalaryByDepartment(String dept, double newSalary) {
        if (newSalary < 0) {
            return 0;
        }

        int count = 0;
        for (Employee emp : employeeList) {
            if (emp.getDepartmentName().equalsIgnoreCase(dept)) {
                emp.setSalary(newSalary);
                count++;
            }
        }
        return count;
    }

    public int updateSalaryForAll(double newSalary) {
        if (newSalary < 0) {
            return 0;
        }

        employeeList.forEach(emp -> emp.setSalary(newSalary));
        return employeeList.size();
    }

    public Employee getHighestSalaryEmployee() {
        return employeeList.isEmpty() ? null
                : Collections.max(employeeList, Comparator.comparingDouble(Employee::getSalary));
    }

    public Employee getLowestSalaryEmployee() {
        return employeeList.isEmpty() ? null
                : Collections.min(employeeList, Comparator.comparingDouble(Employee::getSalary));
    }

    public int getEmployeeCount() {
        return employeeList.size();
    }

    public List<String> getAllDepartments() {
        Set<String> departments = new HashSet<>();
        for (Employee emp : employeeList) {
            departments.add(emp.getDepartmentName());
        }
        return new ArrayList<>(departments);
    }

    public boolean hasEmployee(int empId) {
        return employeeById.containsKey(empId);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeList);
    }

    // Percentage-based salary update methods
    public boolean updateSalaryByPercentageById(int empId, double percentage) {
        Employee emp = searchById(empId);
        if (emp != null && percentage >= -100) { // Allow negative percentage for salary cuts, but not below -100%
            double currentSalary = emp.getSalary();
            double newSalary = currentSalary + (currentSalary * percentage / 100);
            if (newSalary >= 0) { // Ensure salary doesn't go negative
                emp.setSalary(newSalary);
                return true;
            }
        }
        return false;
    }

    public int updateSalaryByPercentageByDepartment(String dept, double percentage) {
        if (percentage < -100) {
            return 0;
        }

        int count = 0;
        for (Employee emp : employeeList) {
            if (emp.getDepartmentName().equalsIgnoreCase(dept)) {
                double currentSalary = emp.getSalary();
                double newSalary = currentSalary + (currentSalary * percentage / 100);
                if (newSalary >= 0) { // Ensure salary doesn't go negative
                    emp.setSalary(newSalary);
                    count++;
                }
            }
        }
        return count;
    }

    public int updateSalaryByPercentageForAll(double percentage) {
        if (percentage < -100) {
            return 0;
        }

        int count = 0;
        for (Employee emp : employeeList) {
            double currentSalary = emp.getSalary();
            double newSalary = currentSalary + (currentSalary * percentage / 100);
            if (newSalary >= 0) { // Ensure salary doesn't go negative
                emp.setSalary(newSalary);
                count++;
            }
        }
        return count;
    }

}
