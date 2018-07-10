package com.project.thirdpartylogin;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.project.reversepojos.TableUser;
import com.google.gson.JsonObject;
public class LoginByGoogle {
	public static String callURL(String myURL) {
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			System.out.println("leaving.............callUrl method");
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}

		return sb.toString();
	}

/*		public static String returnjsonobject(String token) {
		String url="https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="+token;
		String jsonstring=callURL(url);
		System.out.println("response string"+jsonstring);
		return jsonstring;

	}*/
		
		public static  TableUser returnuserobjectFromToken(String token) {
		//public static String returnjsonobject(String token) {
		String url="https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="+token;
		String jsonstring=callURL(url);
		System.out.println("response string"+jsonstring);
		 //JsonObject myjsonobj=new JsonParser().parse(jsonstring).getAsJsonObject();
		 //return myjsonobj;
		 	JsonElement jelement = new JsonParser().parse(jsonstring);
		    com.google.gson.JsonObject  jobject = jelement.getAsJsonObject();
		    //above jobject is json object from google
		    return updateJsonIntoUserTable(jobject);
//		    //return jobject;
	}
		
	
		public static TableUser updateJsonIntoUserTable(JsonObject jsonobject){
			System.out.println("json objectin json into user:-"+jsonobject);
			TableUser user=new TableUser();
			String google_user_id_element="";
			String email_verified_element="";
			String email_element="";
			String picture_element="";
			String given_name_element="";
			String family_name_element="";
			String google_name_element="";
			String google_client_id="";
//			for local environment
//			String google_client_id_by_vikas="19436701882-vf714918tl2dnhbe13b6m68hjrlitoad.apps.googleusercontent.com";
//			for production environment
			String google_client_id_by_vikas="1000357136963-79hv2bhd5apn8vbd7lmuaa365eqnsk7c.apps.googleusercontent.com";
			
			if(jsonobject.has("sub")==false){
				google_user_id_element="";	
			}else{
			google_user_id_element=jsonobject.get("sub").toString();
			google_user_id_element=google_user_id_element.replace("\"", "");;
			}
			
			if(jsonobject.has("email_verified")==false){
				email_verified_element="";	
			}else{
				email_verified_element=jsonobject.get("email_verified").toString();
				email_verified_element=email_verified_element.replace("\"", "");
			}
			
			if(jsonobject.has("email")==false){
				email_element="";	
			}else{
				email_element=jsonobject.get("email").toString();
				email_element=email_element.replace("\"", "");		
			}
			
			if(jsonobject.has("picture")==false){
				picture_element="";	
			}else{
				picture_element=jsonobject.get("picture").toString();
				picture_element=picture_element.replace("\"", "");	
			}
			if(jsonobject.has("aud")==false){
				google_client_id="";	
			}else{
				google_client_id=jsonobject.get("aud").toString();
				google_client_id=google_client_id.replace("\"", "");
			}
			if(jsonobject.has("name")==false){
				google_name_element="";	
			}else{
				google_name_element=jsonobject.get("name").toString();
				google_name_element=google_name_element.replace("\"", "");	
			}
			
			if(jsonobject.has("given_name")==false){
				given_name_element="";	
			}else{
				given_name_element=jsonobject.get("given_name").toString();
				given_name_element=given_name_element.replace("\"", "");	
			}
			if(jsonobject.has("family_name")==false){
				family_name_element="";	
			}else{
				family_name_element=jsonobject.get("family_name").toString();
				family_name_element=family_name_element.replace("\"", "");
				
			}
//			System.out.println("---------json object-------------from google "+jsonobject.toString());
//			System.out.println("---------json object-------------from google");
//			System.out.println("-----------------reached..........subject:-"+jsonobject.getAsJsonObject().get("sub").toString());
//			System.out.println("google user_id..............."+google_user_id_element);
//			System.out.println("google email_verified_element..............."+email_verified_element);
//			System.out.println("google email_element..............."+email_element);
//			System.out.println("google picture_element..............."+picture_element);
//			System.out.println("google given_name_element..............."+given_name_element);
			System.out.println("google full name"+google_name_element.toString());
			System.out.println(email_verified_element.equals("true"));
			System.out.println(google_client_id.equals(google_client_id_by_vikas));
//			System.out.println("google family_name_element..............."+family_name_element);
//			System.out.println("------------loginbygoogle jsonobject element-------------------------"+"email_element"+email_element+"picture_element"+picture_element+"given_name_element"+given_name_element+"family_name_element"+family_name_element);
//			System.out.println("------------reched before checkuser in google login get method----------------");
			if(email_verified_element.equals("true")&& google_client_id.equals(google_client_id_by_vikas)){
				System.out.println("-----User Verified after getting Json from google with aud & email_verified element--------");
				user.setUserEmail(email_element);
				user.setGoogleUserId(google_user_id_element);
				user.setGoogleprofilePictureUrl(picture_element);
				user.setFirstName(given_name_element);
				user.setLastName(family_name_element);
				user.setGoogleFullName(google_name_element);
				user.setRole("user");
				user.setUserStatus("active");
				return user;
			}else
				System.out.println("user not verified--------email_verfied or client id mismatch");
				return null;
		}
		

}
