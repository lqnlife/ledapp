package com.bb.ui;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.bb.R;


public class  InfoActivity extends Activity {



		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState); 
			setContentView(R.layout.show_info);
			 
			String text = getFromAssets("1.html");

			TextView tv_biaoti = (TextView) this.findViewById(R.id.biaoti);
			tv_biaoti.setText(  text   ) ;
		}
	 
	
	  public String getFromAssets(String fileName){ 

          String Result="";
          try { 
              InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) , "gbk" ); 
              BufferedReader bufReader = new BufferedReader(inputReader);
              String line="";
              
              while((line = bufReader.readLine()) != null)
                  Result += line;
              
          } catch (Exception e) { 
              e.printStackTrace(); 
          }

          return Result;
	  } 
	  
	
	
}
