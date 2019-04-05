package com.bb.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.widget.TextView;
import com.bb.R; 
import com.bb.util.Constants;
import com.bb.model.Dingyue;


public class  DingyueInfoActivity extends Activity {


	private Dingyue dingyue ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.dingyue_info);
		 
		dingyue = (Dingyue) getIntent().getSerializableExtra("object");

		TextView tv_lanmu = (TextView) this.findViewById(R.id.lanmu);
		tv_lanmu.setText(  "栏目名 : " + dingyue.lanmu   ) ;
		TextView tv_yonghu = (TextView) this.findViewById(R.id.yonghu);
		tv_yonghu.setText(  "用户 : " + dingyue.yonghu   ) ;
     
		Button btn_button1 = (Button) findViewById(R.id.button1) ;
		btn_button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

//		    	Intent intent = new Intent( DingyueInfoActivity.this , DingyueListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
//				startActivity( intent );
				finish();
			}
		}) ; 
		
	}
	
	
}
