//Created by: Stepan4ek
//Date: 16.05.2026

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    public void addTask(Task task) throws EmptyFieldException {
        if (task.getTitle().trim().isEmpty()) {
            throw new EmptyFieldException("Название задачи не может быть пустым!");
        }
        tasks.add(task);
        saveToFile();
    }

    public void updateTask(int index, Task updatedTask) throws EmptyFieldException {
        if (updatedTask.getTitle().trim().isEmpty()) {
            throw new EmptyFieldException("Название задачи не может быть пустым!");
        }
        if (index >= 0 && index < tasks.size()) {
            updatedTask.setDateAdded(tasks.get(index).getDateAdded());
            tasks.set(index, updatedTask);
            saveToFile();
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveToFile();
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка сохранения файла: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        Task task = Task.fromString(line);
                        tasks.add(task);
                    } catch (Exception e) {
                        System.err.println("Ошибка чтения строки: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла: " + e.getMessage());
        }
    }
}