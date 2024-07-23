package ApachePOI;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;

public class _09_Practice2 {

    /*
     * By calling a method from the main, path and sheet name information is given from Excel.
     * Read the data in the desired column and put it in a list.
     * In this question, the source for Excel: Sheet 2 of ApacheExcel.xlsx will be used.
     *
     */
    public static void main(String[] args) {

        String path = "src/test/java/ApachePOI/resources/ApacheExcel.xlsx";
        String sheetName = "testCitizen";
        int numberOfColumns = 4;

        ArrayList<ArrayList<String>> data = getData(path, sheetName, numberOfColumns);

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                System.out.print(data.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<String>> getData(String path, String sheetName, int numberOfColumns) {
        ArrayList<ArrayList<String>> table = new ArrayList<>();

        Sheet sheet = null;
        try{
            FileInputStream inputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet(sheetName);
        }catch (IOException exception){
            System.out.println("exception: " +exception.getMessage());
        }
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; j++) {
                row.add(sheet.getRow(i).getCell(j).toString());
            }
            table.add(row);
        }

        return table;
    }
}