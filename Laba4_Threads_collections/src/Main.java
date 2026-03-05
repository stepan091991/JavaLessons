import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String FILE_PATH = "employees.txt";
    private static EmployeeManager manager = new EmployeeManager();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";

        while (!choice.equals("0")) {
            System.out.println("1 — Добавить сотрудника");
            System.out.println("2 — Показать всех сотрудников");
            System.out.println("3 — Найти сотрудника");
            System.out.println("4 — Сохранить в файл");
            System.out.println("5 — Загрузить из файла");
            System.out.println("0 — Выход");
            try {
                choice = reader.readLine();

                switch (choice) {
                    case "1":
                        addEmployee(reader);
                        break;
                    case "2":
                        showAllEmployees();
                        break;
                    case "3":
                        findEmployee(reader);
                        break;
                    case "4":
                        saveEmployeesToFile();
                        break;
                    case "5":
                        loadEmployeesFromFile();
                        break;
                    case "0":
                        System.out.println("Выход из программы.");
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода: " + e.getMessage());
            }
        }
    }

    private static void addEmployee(BufferedReader reader) throws IOException {
        System.out.print("Введите ФИО: ");
        String fullName = reader.readLine();
        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(reader.readLine());
        System.out.print("Введите должность: ");
        String position = reader.readLine();
        System.out.print("Введите размер заработной платы: ");
        double salary = Double.parseDouble(reader.readLine());

        Employee employee = new Employee(fullName, age, position, salary);
        manager.addEmployee(employee);
        System.out.println("Сотрудник добавлен.");
    }

    private static void showAllEmployees() {
        if (manager.getEmployees().isEmpty()) {
            System.out.println("Нет сотрудников в списке.");
        } else {
            for (Employee employee : manager.getEmployees()) {
                System.out.println(employee);
            }
        }
    }

    private static void findEmployee(BufferedReader reader) throws IOException {
        System.out.print("Введите ФИО для поиска: ");
        String fullName = reader.readLine();
        Employee employee = manager.findEmployeeByName(fullName);
        if (employee != null) {
            System.out.println("Найденный сотрудник: " + employee);
        } else {
            System.out.println("Сотрудник не найден.");
        }
    }

    private static void saveEmployeesToFile() {
        FileSaveTask saveTask = new FileSaveTask(manager.getEmployees(), FILE_PATH);
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
        System.out.println("Сохранение данных запущено в отдельном потоке.");
    }

    private static void loadEmployeesFromFile() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                Employee employee = Employee.fromFileString(line);
                manager.addEmployee(employee);
            }
            System.out.println("Данные загружены из файла.");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата данных: " + e.getMessage());
        }
    }
}
