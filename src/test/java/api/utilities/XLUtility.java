package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	/**
	 * Initializes the Excel utility with the given file path.
	 *
	 * @param path The path of the Excel file.
	 */
	public XLUtility(String path)
	{
		this.path=path;
	}

	/**
	 * Retrieves the total row count in a given sheet.
	 *
	 * @param sheetName The name of the sheet.
	 * @return The number of rows in the sheet.
	 * @throws Exception If an error occurs while reading the file.
	 */
	public int getRowCount(String sheetName) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	/**
	 * Retrieves the total cell count in a specific row of a given sheet.
	 *
	 * @param sheetName The name of the sheet.
	 * @param rownum The row index (zero-based).
	 * @return The number of cells in the specified row.
	 * @throws Exception If an error occurs while reading the file.
	 */
	public int getCellCount(String sheetName, int rownum) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}

	/**
	 * Retrieves cell data as a string from a given sheet, row, and column.
	 *
	 * @param sheetName The name of the sheet.
	 * @param rownum The row index (zero-based).
	 * @param colnum The column index (zero-based).
	 * @return The formatted cell data as a string.
	 * @throws Exception If an error occurs while reading the file.
	 */
	public String getCellData(String sheetName, int rownum, int colnum) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // returns the formated value of cell as a string regardless
		}
		catch(Exception e)
		{
			data="";
		}

		workbook.close();
		fi.close();
		return data;

	}

	/**
	 * Sets cell data in a given sheet at the specified row and column.
	 *
	 * @param sheetName The name of the sheet.
	 * @param rownum The row index (zero-based).
	 * @param colnum The column index (zero-based).
	 * @param data The data to write into the cell.
	 * @throws Exception If an error occurs while writing to the file.
	 */
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws Exception
	{
		File xlfile = new File(path);
		if(!xlfile.exists()) // If file not exists then create new file
		{
			workbook=new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}

		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetName)==-1) //if sheet not exists then create new sheet
		{
			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
		}

		if(sheet.getRow(rownum)==null) //if row not exists then create new row
		{
			sheet.createRow(rownum);
			row=sheet.getRow(rownum);
		}

		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.close();
		fi.close();
		fo.close();
	}

	/**
	 * Fills a cell with a green background color.
	 *
	 * @param sheetName The name of the sheet.
	 * @param rownum The row index (zero-based).
	 * @param colnum The column index (zero-based).
	 * @throws Exception If an error occurs while formatting the cell.
	 */
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();

	}

	/**
	 * Fills a cell with a red background color.
	 *
	 * @param sheetName The name of the sheet.
	 * @param rownum The row index (zero-based).
	 * @param colnum The column index (zero-based).
	 * @throws Exception If an error occurs while formatting the cell.
	 */
	public void fillRedColor(String sheetName, int rownum, int colnum) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);		
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();

	}

}

