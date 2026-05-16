//Created by: Stepan4ek
//Date: 16.05.2026

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private JTable taskTable;
    private TaskTableModel tableModel;

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<String> statusCombo;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;

    public MainFrame() {
        setTitle("Менеджер задач");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        taskManager = new TaskManager("tasks.tasklist");
        tableModel = new TaskTableModel(taskManager.getAllTasks());

        initUI();
        setVisible(true);
    }

    private void initUI() {
        getContentPane().setBackground(new java.awt.Color(55, 58, 60));
        add(createInputPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Данные задачи"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Название:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        titleField = new JTextField(20);
        panel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Статус:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        statusCombo = new JComboBox<>(new String[]{"Новая", "В работе", "Завершена"});
        panel.add(statusCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Описание:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        panel.add(descScroll, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Список задач"));

        taskTable = new JTable(tableModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = taskTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(100);

        taskTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Task task = taskManager.getTask(selectedRow);
                    if (task != null) {
                        titleField.setText(task.getTitle());
                        descriptionArea.setText(task.getDescription());
                        statusCombo.setSelectedItem(task.getStatus());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(taskTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton = new JButton("Добавить");
        editButton = new JButton("Изменить");
        deleteButton = new JButton("Удалить");
        clearButton = new JButton("Очистить поля");

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        addButton.addActionListener(e -> addTask());
        editButton.addActionListener(e -> editTask());
        deleteButton.addActionListener(e -> deleteTask());
        clearButton.addActionListener(e -> clearFields());

        return panel;
    }

    private void addTask() {
        try {
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            String status = (String) statusCombo.getSelectedItem();

            if (title.isEmpty()) {
                throw new EmptyFieldException("Название задачи не может быть пустым!");
            }

            Task task = new Task(title, description, status);
            taskManager.addTask(task);
            tableModel.refresh();
            clearFields();

            JOptionPane.showMessageDialog(this, "Задача добавлена!", "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (EmptyFieldException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Выберите задачу для изменения!",
                    "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            String status = (String) statusCombo.getSelectedItem();

            if (title.isEmpty()) {
                throw new EmptyFieldException("Название задачи не может быть пустым!");
            }

            Task updatedTask = new Task(title, description, status);
            taskManager.updateTask(selectedRow, updatedTask);
            tableModel.refresh();

            JOptionPane.showMessageDialog(this, "Задача изменена!", "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (EmptyFieldException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Выберите задачу для удаления!",
                    "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Удалить выбранную задачу?", "Подтверждение",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            taskManager.deleteTask(selectedRow);
            tableModel.refresh();
            clearFields();
            JOptionPane.showMessageDialog(this, "Задача удалена!", "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionArea.setText("");
        statusCombo.setSelectedIndex(0);
        taskTable.clearSelection();
    }
}