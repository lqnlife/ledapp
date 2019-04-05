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
import com.bb.model.Jiaoliu;


public class  JiaoliuInfoActivity extends Activity {


	private Jiaoliu jiaoliu ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.jiaoliu_info);
		 
		jiaoliu = (Jiaoliu) getIntent().getSerializableExtra("object");

		TextView tv_biaoti = (TextView) this.findViewById(R.id.biaoti);
		tv_biaoti.setText(  "标题 : " + jiaoliu.biaoti   ) ;
		TextView tv_neirong = (TextView) this.findViewById(R.id.neirong);
		tv_neirong.setText(  "内容 : " + jiaoliu.neirong   ) ;
		TextView tv_shijian = (TextView) this.findViewById(R.id.shijian);
		tv_shijian.setText(  "时间 : " + jiaoliu.shijian   ) ;
		TextView tv_yonghu = (TextView) this.findViewById(R.id.yonghu);
		tv_yonghu.setText(  "用户 : " + jiaoliu.yonghu   ) ;
     
		Button btn_button1 = (Button) findViewById(R.id.button1) ;
		btn_button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

//		    	Intent intent = new Intent( JiaoliuInfoActivity.this , JiaoliuListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
//				startActivity( intent );
				finish();
			}
		}) ; 
		
	}
	
	
}
