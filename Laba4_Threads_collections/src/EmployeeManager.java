import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    // Добавление нового сотрудника
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Получение всех сотрудников
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Поиск сотрудника по ФИО
    public Employee findEmployeeByName(String fullName) {
        for (Employee employee : employees) {
            if (employee.getFullName().equalsIgnoreCase(fullName)) {
                return employee;
            }
        }
        return null;
    }
}
