package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CallWebService{

	static String protocallType = ReadProerties.propsObjectsSplit("protocallType") + "://";
	static String envName = ReadProerties.propsObjectsSplit("envName");
	static String wsPAth = ReadProerties.propsObjectsSplit("wsPAth");

	public static Response postAPI(String RequestString) {
		return RestAssured.given().contentType("application/xml").body(RequestString).when()
				.post(protocallType + envName + wsPAth);
	}

	public static Response getAPI() {
		return RestAssured.given().get(protocallType + envName + wsPAth);
	}

	public static Response putAPI(String RequestString) {
		return RestAssured.given().given().contentType("application/xml").body(RequestString).when()
				.get(protocallType + envName + wsPAth);
	}

	public static Response deleteAPI(String RequestString) {
		return RestAssured.given().delete(protocallType + envName + wsPAth);
	}

	public static void response(String RequestString) {
		Response response = null;
		response = postAPI(RequestString);
		WriteIntoFile writeIntoFile=new WriteIntoFile();
		writeIntoFile.write(response.getBody().asString());
		
	}
}
