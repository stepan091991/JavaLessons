public class Employee {
    private String fullName;
    private int age;
    private String position;
    private double salary;

    public Employee(String fullName, int age, String position, double salary) {
        this.fullName = fullName;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    // Геттеры и сеттеры
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Возвращает строку для отображения в консоли
    @Override
    public String toString() {
        return fullName + ", " + age + " лет, " + position + ", " + salary + " руб.";
    }

    // Метод для записи в файл
    public String toFileString() {
        return String.join(";;", fullName, String.valueOf(age), position, String.valueOf(salary));
    }

    // Метод для создания объекта из строки
    public static Employee fromFileString(String line) {
        String[] parts = line.split(";;");
        return new Employee(parts[0], Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
    }
}
