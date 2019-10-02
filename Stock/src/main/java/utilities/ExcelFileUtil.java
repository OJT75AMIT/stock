package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.base.Throwables;

public class ExcelFileUtil
{
	Workbook wb;

	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("D:\\Amit\\StockAccounting\\TestInputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
   
	//row count 
	public int rowCount(String sheetname)
	{
	return wb.getSheet(sheetname).getLastRowNum();	
	}
	
	
	//col count
	public int colCount(String sheetname,int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
	
	
	
	//reading data from excel sheet
	public String getData(String sheetname,int row,int column)
	{
		String data="";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC )
          {
			int CellData=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(CellData);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		
		return data;
	}
	//writing data to excel sheet
	public void setData(String sheetname, int row,int column, String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass"))
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			//apply color to text
			font.setColor(IndexedColors.GREEN.index);
			//apply bold to text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
			
			
			
		} else 
			if(status.equalsIgnoreCase("fail"))
		      {
				//create cell style
				CellStyle style=wb.createCellStyle();
				//create font
				Font font=wb.createFont();
				//apply color to text
				font.setColor(IndexedColors.RED.index);
				//apply bold to text
				font.setBold(true);
				//set font
				style.setFont(font);
				//set cell style
				rownum.getCell(column).setCellStyle(style);
		      }else
		    	  if(status.equalsIgnoreCase("not executed"))
		    	  {
		    		//create cell style
						CellStyle style=wb.createCellStyle();
						//create font
						Font font=wb.createFont();
						//apply color to text
						font.setColor(IndexedColors.BLUE.index);
						//apply bold to text
						font.setBold(true);
						//set font
						style.setFont(font);
						//set cell style
						rownum.getCell(column).setCellStyle(style);
		    	  }
		
		
		FileOutputStream fos=new FileOutputStream("D:\\Amit\\StockAccounting\\TestOutputs\\OutputSheet.xlsx");
		wb.write(fos);
		fos.close();
		
		

	}
	/*
	public static void main(String[] args) throws Throwable 
{
		ExcelFileUtil excel=new ExcelFileUtil();
		
		System.out.println(excel.rowCount("Sheet1"));
		
		System.out.println(excel.colCount("Sheet1", 1));
		
		System.out.println(excel.getData("Sheet1", 1, 1));
		
		excel.setData("Sheet1", 1, 2, "Fail");
		excel.setData("Sheet1", 2, 2, "Not Executed");
	}*/
}
