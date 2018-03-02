package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileToString extends logging{
	
public static String textToString()
	{
	File file = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator
			+ ReadProerties.propsObjectsSplit("TemplateName") + ReadProerties.propsObjectsSplit("TemplateType"));
	logger.info("calling template name : "+ReadProerties.propsObjectsSplit("TemplateName") + ReadProerties.propsObjectsSplit("TemplateType"));
	String line = "";
	String oldtext = "";
	BufferedReader reader = null;

	try {
		reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			oldtext += line + "\r\n";
		}
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	logger.info("==============================================calling webservice request==============================================");
	logger.info(oldtext);
	logger.info("======================================================================================================================");
	return oldtext;
	}
}