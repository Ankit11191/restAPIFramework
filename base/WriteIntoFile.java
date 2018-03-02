package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteIntoFile extends logging {
	public String FileName = ReadProerties.propsObjectsSplit("TemplateName")
			+ new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss").format(new Date());
	
	public void write(String ResponseString) {
		final String OutPutFileName = System.getProperty("user.dir") + File.separator + "Response" + File.separator
				+ FileName + ReadProerties.propsObjectsSplit("TemplateType");
		logger.info("Responce File ia available at : " + OutPutFileName);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(OutPutFileName))) {
			bw.write(ResponseString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
