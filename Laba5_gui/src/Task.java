//Created by: Stepan4ek
//Date: 16.05.2026

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private String status;
    private LocalDate dateAdded;

    public Task(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateAdded = LocalDate.now();
    }

    public Task(String title, String description, String status, LocalDate dateAdded) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return title + ";" + description + ";" + status + ";" + dateAdded.toString();
    }

    public static Task fromString(String line) {
        String[] parts = line.split(";", 4);
        String title = parts[0];
        String description = parts[1];
        String status = parts[2];
        LocalDate dateAdded = LocalDate.parse(parts[3]);
        return new Task(title, description, status, dateAdded);
    }
}