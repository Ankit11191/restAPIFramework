package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CallExcelFile {
	private static Workbook workbook(String FileName) {
		
		String file_name = ReadProerties.propsObjectsSplit(FileName);
		FileInputStream productname = null;
		
		try {
			productname = new FileInputStream(
					System.getProperty("user.dir") + File.separator + "SourceFiles" + File.separator + file_name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(productname);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return wb;
	}

	public static ArrayList<ArrayList<String>> readCellValue(String fileName) {

		Workbook wb = CallExcelFile.workbook(fileName);
		Sheet sh = wb.getSheetAt(0);
		Iterator<Row> itrrow = sh.rowIterator();

		Row row;
		Cell cell;
		
		ArrayList<ArrayList<String>> rowvalue = new ArrayList<ArrayList<String>>();

		DataFormatter dataFormatter = new DataFormatter();

		while (itrrow.hasNext()) {
			row = itrrow.next();
			if (row.getRowNum() >= 0) {
				ArrayList<String> cellvalue = new ArrayList<String>();
				Iterator<Cell> itrCell = row.cellIterator();

				while (itrCell.hasNext()) {
					cell = itrCell.next();
					cellvalue.add(dataFormatter.formatCellValue(cell));
				}
				rowvalue.add(cellvalue);
			}
		}
		return rowvalue;
	}

	public static void updateCell(String FileName,String rowID) {
		
		String file_name = ReadProerties.propsObjectsSplit(FileName);

		String filepath = System.getProperty("user.dir") + File.separator + "SourceFiles" + File.separator + file_name;

		Workbook wb = CallExcelFile.workbook(FileName);

		Sheet sh = wb.getSheetAt(0);

		Iterator<Row> itrrow = sh.rowIterator();
		DataFormatter dataFormatter = new DataFormatter();

		while (itrrow.hasNext()) {
			Row row = itrrow.next();
			if (row.getRowNum() > 0) {
				Iterator<Cell> itrCell = row.cellIterator();
				while (itrCell.hasNext()) {
					Cell cell = itrCell.next();
					dataFormatter.formatCellValue(cell);

					if (dataFormatter.formatCellValue(cell).equals(rowID)) {
						cell = row.getCell(2);
						cell.setCellValue("Pass 121");

						FileOutputStream outputStream = null;
						try {
							outputStream = new FileOutputStream(filepath);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						try {
							wb.write(outputStream);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
	}

}
