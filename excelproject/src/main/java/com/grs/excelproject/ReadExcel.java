package com.grs.excelproject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	  
	private static String filePath ="./file/grs.xlsx";
	
	public static void main(String[] args) {
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(filePath);
			
			//workbook.getSheetAt(0);
			XSSFSheet sheet =workbook.getSheet("Sheet1");
			
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(1).getLastCellNum();
			
			for (int r = 0; r <= rows; r++) {
				XSSFRow row =sheet.getRow(r);
				
				for (int c = 0; c < cols; c++) {
					XSSFCell cell=row.getCell(c);
					switch (cell.getCellType()) {
					case STRING :System.out.print(cell.getStringCellValue());break;
					case NUMERIC :System.out.print((long)cell.getNumericCellValue());break;
					case BOOLEAN :System.out.print(cell.getBooleanCellValue());break;
					}
					System.out.print("\t\t");
				}
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				workbook.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
//		try {
//			FileInputStream inputStream = new FileInputStream(filePath);
//			Workbook workbook = new XSSFWorkbook(inputStream);
//			DataFormatter dataFormatter =new DataFormatter();
//			Iterator<Sheet> sheets = workbook.sheetIterator();
//			while(sheets.hasNext()) {
//				Sheet sh =sheets.next();
//				System.out.println("Sheet Name is : "+sh.getSheetName());
//				System.out.println("-------------------------------------------------------------------");
//				Iterator<Row> it =sh.iterator();
//				while(it.hasNext()) {
//					Row row = it.next();
//					Iterator<Cell> cell =  row.iterator();
//					while(cell.hasNext()) {
//						String cellValue =dataFormatter.formatCellValue(cell.next());
//						System.out.print(cellValue+"\t\t");
//					}
//					System.out.println();
//					System.out.println("-------------------------------------------------------------------");
//				}
//			}
//			workbook.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
