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

import edu.self.utils.AppContext;

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
public class DingyueInfoAddActivity extends Activity {

	private EditText ed_lanmu; 
	private EditText ed_yonghu; 
  
	private Button btnOk; 
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dingyue_info_add);
		
		ed_lanmu = (EditText) this.findViewById(R.id.lanmu);
		ed_yonghu = (EditText) this.findViewById(R.id.yonghu);
	     
		btnOk = (Button) findViewById(R.id.button1);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showDialog(0);
				
			}
		});
	}
	
	
}
