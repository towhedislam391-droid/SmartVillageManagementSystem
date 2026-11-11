
package com.smartvillage;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileService {

    // Save table data into a text file
    public static void saveData(DefaultTableModel model, String fileName)
            throws IOException {

        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(fileName)))) {

            for (int i = 0; i < model.getRowCount(); i++) {

                for (int j = 0; j < model.getColumnCount(); j++) {

                    String value = String.valueOf(
                            model.getValueAt(i, j));

                    value = value.replace("|", "/")
                                 .replace("\n", " ");

                    if (j > 0) {
                        writer.print("|");
                    }

                    writer.print(value);
                }

                writer.println();
            }
        }
    }

    // Load data from a text file
    public static void loadData(DefaultTableModel model, String fileName)
            throws IOException {

        model.setRowCount(0);

        File file = new File(fileName);

        File folder = file.getParentFile();

        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split("\\|", -1);

                if (values.length == model.getColumnCount()) {
                    model.addRow(values);
                }
            }
        }
    }
}
