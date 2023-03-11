package src.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileHandle<T> {
    private String fileName;
    public FileHandle(String fileName) {
        this.fileName = fileName;
    }

    // Writes data to a file
    public  void writeFile( String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName,true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void writeFileEmpty( String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName,false))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads data from a file
    public  String readFile() {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Reads data from a file into a 2D array of objects, where each row in the file is a row in the array
    public  LinkedList<String[]>  readRowsFile( ) {
        LinkedList<String[]> result = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            int numRows = 0;
            int numCols = 0;
            while ((line = reader.readLine()) != null) {
                numRows++;
                String[] rowValues = line.split(",");
                result.add(rowValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
