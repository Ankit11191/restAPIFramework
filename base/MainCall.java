package callWebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import base.FileToString;
import base.logging;
import base.CallWebService;
import base.ColumnWiseRead;


public class MainCall extends logging{

	public static void main(String[] aass) {

		String xmlrequest = FileToString.textToString();
		
		Map<String, ArrayList<String>> tokens=ColumnWiseRead.columnWiseCalling("FileName");
		Map<String, String> token=new HashMap<String, String>();
		int ArrayListSize=0;

		for(Map.Entry<String, ArrayList<String>> arrayCount : tokens.entrySet())
		{
			ArrayListSize=arrayCount.getValue().size();
		}
		
		for(int itrNo=0;itrNo<ArrayListSize;itrNo++)
		{
			for(Map.Entry<String, ArrayList<String>> ankit : tokens.entrySet())
			{
				token.put(ankit.getKey(), ankit.getValue().get(itrNo));
			}
			String patternString = "\\$\\{(" + StringUtils.join(token.keySet(), "|") + ")\\}";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(xmlrequest);
			
			StringBuffer RequestString = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(RequestString, token.get(matcher.group(1)));
			}
			matcher.appendTail(RequestString);
			CallWebService.response(RequestString.toString());
		}
		System.out.println("Done");
	}
}