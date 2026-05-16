//Created by: Stepan4ek
//Date: 16.05.2026

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            setDarkTheme();

        } catch (Exception e) {
            System.err.println("Не удалось установить тему: " + e.getMessage());
        }

        SwingUtilities.invokeLater(MainFrame::new);
    }
    public static void setDarkTheme(){

        UIManager.put("control", new java.awt.Color(60, 63, 65));
        UIManager.put("info", new java.awt.Color(60, 63, 65));
        UIManager.put("nimbusBase", new java.awt.Color(50, 52, 55));
        UIManager.put("nimbusAlertYellow", new java.awt.Color(80, 80, 60));
        UIManager.put("nimbusDisabledText", new java.awt.Color(140, 140, 140));
        UIManager.put("nimbusFocus", new java.awt.Color(90, 130, 180));
        UIManager.put("nimbusGreen", new java.awt.Color(70, 100, 70));
        UIManager.put("nimbusInfoBlue", new java.awt.Color(60, 80, 110));
        UIManager.put("nimbusLightBackground", new java.awt.Color(65, 68, 70));
        UIManager.put("nimbusOrange", new java.awt.Color(120, 100, 60));
        UIManager.put("nimbusRed", new java.awt.Color(130, 70, 70));
        UIManager.put("nimbusSelectedText", new java.awt.Color(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new java.awt.Color(70, 100, 150));
        UIManager.put("text", new java.awt.Color(220, 220, 220));

        UIManager.put("Button.background", new java.awt.Color(75, 80, 85));
        UIManager.put("Button.foreground", new java.awt.Color(220, 220, 220));
        UIManager.put("Button.disabledText", new java.awt.Color(130, 130, 130));
        UIManager.put("Button.select", new java.awt.Color(90, 95, 100));

        UIManager.put("ComboBox.background", new java.awt.Color(65, 68, 70));
        UIManager.put("ComboBox.foreground", new java.awt.Color(220, 220, 220));
        UIManager.put("ComboBox.selectionBackground", new java.awt.Color(70, 100, 150));
        UIManager.put("ComboBox.selectionForeground", new java.awt.Color(255, 255, 255));

        UIManager.put("Label.foreground", new java.awt.Color(200, 200, 200));

        UIManager.put("TextField.background", new java.awt.Color(65, 68, 70));
        UIManager.put("TextField.foreground", new java.awt.Color(220, 220, 220));
        UIManager.put("TextField.caretForeground", new java.awt.Color(220, 220, 220));

        UIManager.put("TextArea.background", new java.awt.Color(65, 68, 70));
        UIManager.put("TextArea.foreground", new java.awt.Color(220, 220, 220));
        UIManager.put("TextArea.caretForeground", new java.awt.Color(220, 220, 220));

        UIManager.put("Table.background", new java.awt.Color(60, 63, 65));
        UIManager.put("Table.foreground", new java.awt.Color(220, 220, 220));
        UIManager.put("Table.gridColor", new java.awt.Color(90, 90, 90));
        UIManager.put("Table.selectionBackground", new java.awt.Color(70, 100, 150));
        UIManager.put("Table.selectionForeground", new java.awt.Color(255, 255, 255));
        UIManager.put("TableHeader.background", new java.awt.Color(80, 83, 85));
        UIManager.put("TableHeader.foreground", new java.awt.Color(220, 220, 220));

        UIManager.put("OptionPane.background", new java.awt.Color(60, 63, 65));
        UIManager.put("OptionPane.messageForeground", new java.awt.Color(220, 220, 220));
        UIManager.put("Panel.background", new java.awt.Color(55, 58, 60));

        UIManager.put("ScrollPane.background", new java.awt.Color(60, 63, 65));

        UIManager.put("TitledBorder.titleColor", new java.awt.Color(180, 180, 180));
    }
}