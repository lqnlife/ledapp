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
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.R; 
import com.bb.util.AsyncImageLoader;
import com.bb.util.Constants;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.model.Gonggao;

import edu.self.utils.AppContext;


public class  GonggaoInfoActivity extends Activity {


	private Gonggao gonggao ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.gonggao_info);
		 
		gonggao = (Gonggao) getIntent().getSerializableExtra("object");

		TextView tv_biaoti = (TextView) this.findViewById(R.id.biaoti);
		tv_biaoti.setText(  gonggao.biaoti   ) ;
		
//		从服务器上获取图片，并且显示
		ImageView iv = (ImageView) this.findViewById(R.id.tupian) ; 
        String picPath = Constants.WEB_APP_URL + gonggao.tupian  ; 
		AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
		
		Drawable cachedImage = asyncImageLoader.loadDrawable(
    			picPath , iv , new ImageCallback() {

					public void imageLoaded(Drawable imageDrawable,
							ImageView imageView, String imageUrl) {
						imageView.setImageDrawable(imageDrawable);
					}
				});

		if (cachedImage == null) {
			iv.setImageResource(R.drawable.pork);
		} else {
			iv.setImageDrawable(cachedImage);
		}
		
		
		TextView tv_neirong = (TextView) this.findViewById(R.id.neirong);
		tv_neirong.setText(  gonggao.neirong   ) ;
		TextView tv_shijian = (TextView) this.findViewById(R.id.shijian);
		tv_shijian.setText(  "时间 : " + gonggao.shijian   ) ;
		
		
		Button button1 = (Button) findViewById(R.id.button1) ;
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doIt();
			}
		}) ; 
		
		Button button2 = (Button) findViewById(R.id.button2) ;
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

		    	Intent intent = new Intent( GonggaoInfoActivity.this , JiaoliuListActivity.class ) ;
				intent.putExtra("type", gonggao.biaoti       );
				startActivity( intent );
			}
		}) ; 

		
		Button button3 = (Button) findViewById(R.id.button3) ;
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent share_intent = new Intent();
		        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
		        share_intent.setType("text/plain");//设置分享内容的类型
		        share_intent.putExtra(Intent.EXTRA_SUBJECT, gonggao.biaoti );	//添加分享内容标题
		        share_intent.putExtra(Intent.EXTRA_TEXT, gonggao.getNeirong() );	//添加分享内容
		        //创建分享的Dialog
		        share_intent = Intent.createChooser(share_intent, "我要分享");
		        startActivity(share_intent);
			}
		}) ; 
		
	}
	
	private void doIt(){
		
		new Thread(){
			public void run() {
				try {
					JSONObject jsonObject = new JSONObject();
				    jsonObject.put("lanmu", gonggao.getLeibie() );
				    jsonObject.put("yonghu", AppContext.userinfo.getUserName());
				     
				    jsonObject.put("uid", Constants.userId );
					
					
					HttpPost post = new HttpPost( Constants.SERVER + "/dingyue.do?method=saveJson");
					
					List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
					params.add(new BasicNameValuePair("dingyue", jsonObject.toString()));
					
					post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					post.getParams().setBooleanParameter( CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
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
							final String content = builder.toString();
							response.getEntity().consumeContent();
							runOnUiThread(new Runnable() {
								public void run() {
									removeDialog(0);
									if ( !content.trim().equals("ERROR") ) {
//										GonggaoInfoActivity.this.finish();
										Toast.makeText( GonggaoInfoActivity.this, "添加成功", Toast.LENGTH_LONG).show();
									} else {
										Toast.makeText( GonggaoInfoActivity.this, "添加失败", Toast.LENGTH_LONG).show();
									}
								}
							});
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}
	
	
}
