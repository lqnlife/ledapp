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
import com.bb.model.Leibie;


public class  LeibieInfoActivity extends Activity {


	private Leibie leibie ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.leibie_info);
		 
		leibie = (Leibie) getIntent().getSerializableExtra("object");

		TextView tv_mingcheng = (TextView) this.findViewById(R.id.mingcheng);
		tv_mingcheng.setText(  "名称 : " + leibie.mingcheng   ) ;
     
		Button btn_button1 = (Button) findViewById(R.id.button1) ;
		btn_button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

//		    	Intent intent = new Intent( LeibieInfoActivity.this , LeibieListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
//				startActivity( intent );
				finish();
			}
		}) ; 
		
	}
	
	
}
