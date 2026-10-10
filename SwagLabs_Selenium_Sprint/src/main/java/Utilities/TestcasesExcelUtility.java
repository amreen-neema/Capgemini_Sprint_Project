package Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestcasesExcelUtility {
	static String path = "./src/test/resources/Propertites/SwagLab_test_cases.xlsx";

    public static String getData(String sheetName, int row, int column) throws Exception {

        FileInputStream fis = new FileInputStream(path);

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);

        String data = sheet.getRow(row).getCell(column).toString();

        workbook.close();
        fis.close();

        return data;
    }

}
