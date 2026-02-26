package utilities;


import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

		
		@DataProvider(name="LoginData")
		public String [][] getData() throws IOException {
			
			String path =".\\testData\\Opencart_LoginData.xlsx"; //gives current project location 
			ExcelUtility xl = new ExcelUtility(path);  //creating an object for ExcelUtility
			
			int rownum = xl.getRowCount("Sheet1");
			int colcount = xl.getCellCount("Sheet1",1);
			
			String logindata[][] = new String [rownum][colcount]; //created for two dimension array which can store
			
			for (int i=1; i<=rownum; i++) {           //1 //read the data from x1 storing in two dimensional array
				for(int j=0; j<colcount; j++) {       //0  i is row j is col
					logindata[i-1][j] = xl.getCellData("Sheet1", i, j);     //1,0
				}
			}
			return logindata;  //returning two dimension array
		}
	
}
   


