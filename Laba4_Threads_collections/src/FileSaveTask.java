import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaveTask implements Runnable {
    private List<Employee> employees;
    private String filePath;

    public FileSaveTask(List<Employee> employees, String filePath) {
        this.employees = employees;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                writer.write(employee.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }
}
