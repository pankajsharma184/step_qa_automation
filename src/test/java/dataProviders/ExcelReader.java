package dataProviders;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelReader {

    public ArrayList<HashMap<String, String>> valuesFromExcel = new ArrayList<>();
    public HashMap<String, String> headersMap = new HashMap<String, String>();

    public ArrayList<HashMap<String, String>> read(String path) throws IOException {
        FileInputStream fis = new FileInputStream(new File(path));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();

        while (rowIterator.hasNext()) {

            HashMap<String, String> valueFromRow = new HashMap<>();

            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            String cellValue = "";
            int headerMapkeyCount = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {

                    case Cell.CELL_TYPE_STRING:
                        cellValue = cell.getStringCellValue();
                        break;

                    case Cell.CELL_TYPE_NUMERIC:
                        cellValue = String.valueOf((long) cell.getNumericCellValue());
                        break;
                }

                if (row.getRowNum() == 0) {
                    headersMap.put("" + headerMapkeyCount, cellValue);
                    headerMapkeyCount++;
                } else {
                    valueFromRow.put(headersMap.get("" + headerMapkeyCount), cellValue);
                    headerMapkeyCount++;
                }

            }

            if (row.getRowNum() != 0) {
                valuesFromExcel.add(valueFromRow);
            }
        }
        fis.close();

        for (int i = 0; i < valuesFromExcel.size(); i++){

            valuesFromExcel.get(i).forEach((key, value) -> {
                System.out.println( key + " --> " + value);
            });
     }

        return valuesFromExcel;
    }
}


