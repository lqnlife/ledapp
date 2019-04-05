package com.bb.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;




public class BaseAuthenicationHttpClient {
	
	
	 static public String doRequest(String urlString, String name, String password, HashMap<String,String> params) 
	 {
		try{
	    	URL url = new URL (urlString);
	        String userPassword = name+":"+password;
	
	        String encoding = Base64.encode(userPassword).trim();
	
	
	        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	        uc.setRequestProperty("Authorization", "Basic " + encoding);
	        uc.setRequestProperty("User-Agent", "Mozilla/5.0");
	        
	        uc.setDoInput(true);
	        uc.setDoOutput(true);
	        uc.setRequestMethod("POST");
	        
	        
	        if (params != null && !params.isEmpty()) {
	        	StringBuffer buf = new StringBuffer();
	        	for(String key : params.keySet()){
	        		 buf.append("&").append(key).append("=").append(params.get(key));
	        	}
	        	buf.deleteCharAt(0);
	            uc.getOutputStream().write(buf.toString().getBytes("gb2312"));  
	            uc.getOutputStream().close();  
	        }  
	  
	        InputStream content = (InputStream)uc.getInputStream();
	        BufferedReader in = new BufferedReader (new InputStreamReader (content,"gb2312"));
	        String line = in.readLine();//will refactory
	//	          while ((line = in.readLine()) != null) {
	//	            System.out.println (line);
	//	            }
	        in.close();
	        return line.trim();
		}catch(IOException e){
			e.printStackTrace() ;
		}
		return null;

	 }
	 
    
	 static public String doRequest(String urlString, String name, String password)  
	 {
		 System.out.println( " com.bb doRequest url===================" + urlString );
		 try{
	        URL url = new URL (urlString);

	        URLConnection uc = url.openConnection();
	        
	        
	        InputStream content = (InputStream)uc.getInputStream();
	        BufferedReader in = new BufferedReader (new InputStreamReader(content,"gb2312"));
	        String line = in.readLine();//will refactory

	        in.close();
	        return line.trim();
		 }catch(IOException e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
}
