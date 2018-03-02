package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ColumnWiseRead {

	public static Map<String, ArrayList<String>> columnWiseCalling(String FileName) {

		String file_name =ReadProerties.propsObjectsSplit(FileName);

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
		Sheet sh = wb.getSheetAt(0);

		Row row = null;

		DataFormatter dataFormatter = new DataFormatter();

		Map<String, ArrayList<String>> excelMap=new HashMap<String, ArrayList<String>>();

		
		for (int i = 0; i < sh.getRow(0).getPhysicalNumberOfCells(); i++) {
			Iterator<Row> itrrow = sh.rowIterator();
			ArrayList<String> valueList = new ArrayList<>();
			String key = itrrow.next().getCell(i).toString();
			for (int j = 0; j < sh.getLastRowNum(); j++) {
				row = itrrow.next();
					valueList.add(dataFormatter.formatCellValue(row.getCell(i)));
			}
			excelMap.put(key, valueList);
		}
		return excelMap;
	}
}
