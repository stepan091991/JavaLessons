//Created by: Stepan4ek
//Date: 16.05.2026

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private List<Task> tasks;
    private String[] columnNames = {"Название", "Описание", "Статус", "Дата добавления"};

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return task.getTitle();
            case 1:
                return task.getDescription();
            case 2:
                return task.getStatus();
            case 3:
                return task.getDateAdded().toString();
            default:
                return null;
        }
    }

    public void refresh() {
        fireTableDataChanged();
    }
}