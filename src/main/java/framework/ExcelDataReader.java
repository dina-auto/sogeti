package framework;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataReader {
	public static List<Map<String, String>> readTestData(String filePath, String sheetName) {
	    List<Map<String, String>> testDataList = new ArrayList<>();
	    try (FileInputStream file = new FileInputStream(filePath)) {
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheet(sheetName);
	        XSSFRow headerRow = sheet.getRow(0);
	        int lastColumnNum = headerRow.getLastCellNum();

	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            XSSFRow dataRow = sheet.getRow(i);
	            Map<String, String> testData = new HashMap<>();

	            for (int j = 0; j < lastColumnNum; j++) {
	                XSSFCell headerCell = headerRow.getCell(j);
	                XSSFCell dataCell = dataRow.getCell(j);

	                String headerValue = headerCell.getStringCellValue();

	                // Handle different cell types
	                String cellValue = "";
	                if (dataCell != null) {
	                    switch (dataCell.getCellType()) {
	                        case STRING:
	                            cellValue = dataCell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            cellValue = String.valueOf(dataCell.getNumericCellValue());
	                            break;
	                        // Handle other cell types as needed
	                    }
	                }

	                testData.put(headerValue, cellValue);
	            }

	            testDataList.add(testData);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return testDataList;
	}

}
