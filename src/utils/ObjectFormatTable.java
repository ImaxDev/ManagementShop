package src.utils;

import src.dto.OrderDTO;

import java.util.LinkedList;

public class ObjectFormatTable {
    final String ANSI_RED = "\u001B[31m";

    // ANSI escape code to reset color
    final String ANSI_RESET = "\u001B[0m";
    private String[] columnNames;
    private LinkedList<String[]> data;

    public ObjectFormatTable(String[] columnNames, LinkedList<String[]> data) {
        this.columnNames = columnNames;
        this.data = data;
    }
    public void printTable() {
        System.out.println(ANSI_RED+"TOTAL "+data.size()+" "+ANSI_RESET);
        // Calculate the width of each column
        int[] columnWidths = new int[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            columnWidths[i] = columnNames[i].length();

            for (int j = 0; j < data.size(); j++) {
                Object value = data.get(j)[i];
                if (value != null) {
                    int width = value.toString().length();
                    if (width > columnWidths[i]) {
                        columnWidths[i] = width;
                    }
                }
            }
        }

        // Print the column headers
        for (int i = 0; i < columnNames.length; i++) {
            System.out.printf( "%-" + columnWidths[i] + "s ", columnNames[i]);
            System.out.printf("| \t");

        }
        System.out.println("");

        // Print the data rows
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                Object value = data.get(i)[j];
                if (value == null) {
                    System.out.printf("%-" + columnWidths[j] + "s ", "");
                } else {
                    System.out.printf("%-" + columnWidths[j] + "s ", value.toString());
                }
                System.out.printf("| \t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
