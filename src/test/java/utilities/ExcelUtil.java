package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ExcelUtil {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public int getColumnCount() {
        Row headerRow = sheet.getRow(0);
        return (headerRow == null) ? 0 : headerRow.getLastCellNum();
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }

        return getCellValueAsString(cell);
    }

    public Object[][] getSheetData() {
        int rowCount = getRowCount(); // excludes header row index logic handled below
        int colCount = getColumnCount();

        if (rowCount == 0 || colCount == 0) {
            return new Object[0][0];
        }

        Object[][] data = new Object[rowCount][colCount];

        // Start from row 1 assuming row 0 is header
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }

    public Object[][] getSheetDataBySheetName(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);
        if (this.sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found.");
        }
        return getSheetData();
    }

    public int getColumnIndex(String columnName) {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new RuntimeException("Header row is missing in sheet: " + sheet.getSheetName());
        }

        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
                return cell.getColumnIndex();
            }
        }

        throw new RuntimeException("Column '" + columnName + "' not found in sheet: " + sheet.getSheetName());
    }

    public String getCellData(int rowNum, String columnName) {
        int colNum = getColumnIndex(columnName);
        return getCellData(rowNum, colNum);
    }

    private String getCellValueAsString(Cell cell) {
        DataFormatter formatter = new DataFormatter();

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }
                return formatter.formatCellValue(cell);

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                try {
                    return formatter.formatCellValue(cell, workbook.getCreationHelper().createFormulaEvaluator());
                } catch (Exception e) {
                    return cell.getCellFormula();
                }

            case BLANK:
                return "";

            default:
                return formatter.formatCellValue(cell);
        }
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to close workbook", e);
        }
    }
}