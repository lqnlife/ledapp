package com.bb.ui;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader; 
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

import android.app.Activity; 
import android.os.Bundle; 
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bb.util.Constants;
import com.bb.R;



/**
 * 添加
 *
 */
public class ShoucangInfoAddActivity extends Activity {

	private EditText ed_yonghu; 
	private EditText ed_biaoti; 
	private EditText ed_neirong; 
	private EditText ed_shijian; 
  
	private Button btnOk; 
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoucang_info_add);
		
		ed_yonghu = (EditText) this.findViewById(R.id.yonghu);
		ed_biaoti = (EditText) this.findViewById(R.id.biaoti);
		ed_neirong = (EditText) this.findViewById(R.id.neirong);
		ed_shijian = (EditText) this.findViewById(R.id.shijian);
	     
		btnOk = (Button) findViewById(R.id.button1);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showDialog(0);
				
			}
		});
	}
	
	
}
