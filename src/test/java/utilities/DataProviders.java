package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        String filePath = ".\\testData\\testData.xlsx";
        String sheetName = "Sheet1";

        ExcelUtil excelUtil = new ExcelUtil(filePath, sheetName);
        Object[][] data = excelUtil.getSheetData();
        
        
        excelUtil.closeWorkbook();

        return data;
              
        
        
    }
    
    public static void main(String[] args) {
        Object[][] data = DataProviders.getLoginData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
}


