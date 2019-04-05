package edu.self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.bb.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.self.model.UserInfo;
import edu.self.utils.AppContext;
import edu.self.utils.HttpUtil;

/**
 * 修改用户信息 
 * 
 */
public class UpdateUserInfoActivity extends Activity {

	private UserInfo userinfo;
	private EditText etUserName;
	private EditText etUserPassword;
	private EditText etUserPassword2;
	private EditText etPhone;
	private EditText etAddress   ; 
	private Button btnOk;
	
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateuserinfo);
		
		userinfo =  AppContext.userinfo  ;
		
		etUserName = (EditText) findViewById(R.id.userinfo_edit_userName);
		etUserName.setText(userinfo.getUserName());
		etUserPassword = (EditText) findViewById(R.id.userinfo_edit_pwd);
		etUserPassword.setText(userinfo.getPassword());
		etUserPassword2 = (EditText) findViewById(R.id.userinfo_edit_pwd2);
		etPhone = (EditText) findViewById(R.id.userinfo_edit_phone);
		etPhone.setText(userinfo.getPhone());
		etAddress = (EditText) findViewById(R.id.userinfo_edit_address);
		etAddress.setText(userinfo.getAddress());
 
		 
		btnOk = (Button) findViewById(R.id.userinfo_btn);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String password2 = etUserPassword2.getText().toString().trim();
				final String userName = etUserName.getText().toString().trim();
				final String phone = etPhone.getText().toString().trim();
				final String address = etAddress.getText().toString().trim();
				dialog = new ProgressDialog(UpdateUserInfoActivity.this);
				dialog.setMessage("正在修改...请稍候");
				dialog.show();
				new Thread(){
					public void run() {
						JSONObject userObject = new JSONObject();
						try {
							userObject.put("userName", userName);
							userObject.put("password", password2);
							userObject.put("phone", phone);
							userObject.put("address", address); 
							userObject.put("uid", userinfo.getUid());
							if (HttpUtil.isConnectInternet(UpdateUserInfoActivity.this)) {
								HttpPost post = new HttpPost(AppContext.SERVER_UPDATEUSER);
								List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
								params.add(new BasicNameValuePair("user", userObject.toString()));
								post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
								post.getParams().setBooleanParameter(
										CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
								HttpResponse response = (HttpResponse) new DefaultHttpClient().execute(post);
								if (response != null) {
									if (200 == response.getStatusLine().getStatusCode()) {
										InputStream is = response.getEntity().getContent();
										Reader reader = new BufferedReader(new InputStreamReader(is));
										StringBuilder builder = new StringBuilder((int) response.getEntity().getContentLength());
										char[] temp = new char[4000];
										int len = 0;
										while ((len = reader.read(temp)) != -1) {
											builder.append(temp, 0, len);
										}
										reader.close();
										is.close();
										String content = builder.toString();
										response.getEntity().consumeContent();
										if (content.trim().equals("true")) {
											dialog.dismiss();
											runOnUiThread(new Runnable() {
												public void run() {
													dialog.dismiss();
													UpdateUserInfoActivity.this.finish();
													Toast.makeText(UpdateUserInfoActivity.this, "修改用户信息成功", Toast.LENGTH_LONG).show();
												}
											});
										} else {
											runOnUiThread(new Runnable() {
												public void run() {
													dialog.dismiss();
													Toast.makeText(UpdateUserInfoActivity.this, "修改用户信息失败", Toast.LENGTH_LONG).show();
												}
											});
										}
									}
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							runOnUiThread(new Runnable() {
								public void run() {
									if (dialog.isShowing()) {
										dialog.dismiss();
									}
								}
							});
						}
					};
				}.start();
			}
		});
		
	}
	 
	
	public void makeToast(String msg) {
		Toast.makeText(UpdateUserInfoActivity.this, msg, Toast.LENGTH_LONG).show();
	}
}
