
public class Main {
    //Основной класс программы
    public static void main(String[] args) {
        Company company = new Company();

        company.addEmployee(new Employee("Иван", 30, 50000));
        company.addEmployee(new Manager("Мария", 40, 60000, 10000));
        company.addEmployee(new Developer("Алексей", 28, 45000, 5));
        company.addEmployee(new Developer("Настя", 25, 40000, 10));
        company.addEmployee(new Manager("Сергей", 35, 55000, 12000));

        company.displayEmployeeInfo();
        System.out.println("Общая зарплата: " + company.calculateTotalSalary());
        System.out.println("Сотрудник с максимальной зарплатой: " +
                company.findMaxSalaryEmployee().getInfo());
    }
}
class Employee{
    //Класс работника
    private String name;
    private int age;
    private double baseSalary;

    public Employee(String name, int age, double baseSalary) {
        this.name = name;
        this.age = age;
        this.baseSalary = baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }

    public String getInfo() {
        return "Сотрудник: " + name + ", возраст: " + age + ", зарплата: " + calculateSalary();
    }
}

class Manager extends Employee {
    //Класс менеджера, наследуется от класса работника
    private double bonus;

    public Manager(String name, int age, double baseSalary, double bonus) {
        super(name, age, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + bonus;
    }

    @Override
    public String getInfo() {
        return "Менеджер: " + super.getInfo() + ", премия: " + bonus;
    }
}

class Developer extends Employee {
    //Класс разработчика
    private int overtimeHours;

    public Developer(String name, int age, double baseSalary, int overtimeHours) {
        super(name, age, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + (overtimeHours * 50); // фиксированная ставка за переработку
    }

    @Override
    public String getInfo() {
        return "Разработчик: " + super.getInfo() + ", переработанные часы: " + overtimeHours;
    }
}

class Company {
    //Класс компании
    private Employee[] employees;
    private int employeeCount;
    private static final int MAX_EMPLOYEES = 10; // Максимальное количество сотрудников

    public Company() {
        employees = new Employee[MAX_EMPLOYEES];
        employeeCount = 0;
    }

    public void addEmployee(Employee employee) {
        //Добовляем нового работника
        if (employeeCount < MAX_EMPLOYEES) {
            employees[employeeCount] = employee;
            employeeCount++;
        } else {
            System.out.println("Не удалось добавить сотрудника. Достигнуто максимальное количество сотрудников.");
        }
    }

    public void displayEmployeeInfo() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].getInfo());
        }
    }

    public double calculateTotalSalary() {
        //Считаем общёю зарплату
        double total = 0;
        for (int i = 0; i < employeeCount; i++) {
            total += employees[i].calculateSalary();
        }
        return total;
    }

    public Employee findMaxSalaryEmployee() {
        //Находим работника с максимальной зп
        Employee maxEmployee = null;
        for (int i = 0; i < employeeCount; i++) {
            if (maxEmployee == null || employees[i].calculateSalary() > maxEmployee.calculateSalary()) {
                maxEmployee = employees[i];
            }
        }
        return maxEmployee;
    }
}
