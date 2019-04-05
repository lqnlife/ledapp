package edu.self.utils;

import java.io.Serializable;

import com.bb.util.Constants;

import edu.self.model.UserInfo;

/**
 * 全局类
 * @author mzba
 *
 */
public class AppContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static UserInfo userinfo;
	
	public static final String Preferences_user = "user";
	public static final String Preferences_userid = "userid";
	public static final String Preferences_password = "password";
	public static final String Preferences_autologin = "autologin";
	public static final String Preferences_savepassword = "savepassword";
 
	public static final String IP = Constants.IP ;
	public static final String PORT = Constants.PORT ;
	public static final String SERVER = Constants.SERVER ; 
	
	public static final String SERVER_LOGIN = "/Login";

	public static final String SERVER_REGISTER = "/RegisterAccount";
	public static final String SERVER_USERS = SERVER + "/Users"; //JsonArray.tostring
	public static final String SERVER_UPDATEUSER = SERVER + "/UpdateUser"; //boolean
	public static final String SERVER_USERBYUSERID = SERVER + "/UserByuserId"; //Jsonobject

	public static final String HTTP_POST = "POST";
	public static final String HTTP_GET = "GET";
}
