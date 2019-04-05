package com.bb.ui;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bb.R;
import com.bb.util.Constants;

import edu.self.LoginActivity;
import edu.self.UpdateUserInfoActivity;
import edu.self.component.AppException;
import edu.self.component.Connect;
import edu.self.model.UserInfo;
import edu.self.utils.AppContext;



/**
 * 系统启动类，显示操作
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

 
    private LinearLayout oneRow,    threeRow  ;
	private ProgressDialog dialog ;  
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_wp);

    	dialog = new ProgressDialog(this);
		dialog.setMessage("正在加载用户信息...请稍候");
		dialog.show();

		new Thread(){
			public void run() {
				loadAllUserInfo();
				runOnUiThread(new Runnable() {
					public void run() {  
						for (UserInfo userinfo: list) {
							if (userinfo.getUserId().equals(Constants.userId )) {
								AppContext.userinfo = userinfo; 
								load();
							}  
						} 
						dialog.dismiss();
					}
				});
			};
		}.start();
		
    }

    
    private void load(){
    	
        oneRow = (LinearLayout)findViewById(R.id.one_row);
        oneRow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { 
                Intent i = new Intent(MainActivity.this, LeibieListActivity.class);
        		startActivity(i);
            }
        });

        LinearLayout two_row = (LinearLayout)findViewById(R.id.two_row);
        two_row.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {

        	   Intent i = new Intent(MainActivity.this, DingyueListActivity.class);
       		   startActivity(i);
           }
        });
        
        threeRow = (LinearLayout)findViewById(R.id.three_row);
        threeRow.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {

               Intent i = new Intent(MainActivity.this, JiaoliuListActivity2.class);
               startActivity(i);
           }
        });
        
        LinearLayout zero_row = (LinearLayout)findViewById(R.id.zero_row);
        zero_row.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
             Intent i = new Intent(MainActivity.this, UpdateUserInfoActivity.class);
             startActivity(i);
           }
        });

        LinearLayout five_row = (LinearLayout)findViewById(R.id.five_row);
        five_row.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, LoginActivity.class);
               startActivity(i);
               finish();
           }
        });
          
    }
    
    
  
  	private List<UserInfo> list;

      /**
  	 * 加载所有用户的信息
  	 */
  	private void loadAllUserInfo() {
  		list = new ArrayList<UserInfo>();
  		Connect connect = new Connect(AppContext.SERVER_USERS, AppContext.HTTP_POST);
  		try {
  			byte[] data = connect.queryServer(null);
  			JSONObject object = new JSONObject(new String(data, "gb2312"));
  			JSONArray userArray = object.getJSONArray("users");
  			for (int i = 0;i < userArray.length();i++) {
  				JSONObject userObject = userArray.getJSONObject(i);
  				UserInfo userinfo = new UserInfo();
  				userinfo.setUid(userObject.getInt("uid"));
  				userinfo.setPassword(userObject.getString("password"));
  				userinfo.setUserId(userObject.getString("userId"));
  				userinfo.setUserName(userObject.getString("userName"));
  				userinfo.setAddress(userObject.getString("address"));
  				userinfo.setPhone(userObject.getString("phone"));
  				list.add(userinfo);
  			}
  		} catch (AppException e) {
  			e.printStackTrace();
  		} catch (JSONException e) {
  			e.printStackTrace();
  		} catch (UnsupportedEncodingException e) {
  			e.printStackTrace();
  		}
  	}
  	
 
	
	

	
}
