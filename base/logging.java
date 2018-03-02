package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class logging {
	
	public static Logger logger = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());
	static
	{
		WriteIntoFile writeIntoFile=new WriteIntoFile();
		System.setProperty("log.filename", writeIntoFile.FileName);
	//	System.setProperty("log.timestamp", new  SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date()));
		PropertyConfigurator.configure("log4j.properties");
	}
}
