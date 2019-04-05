package com.bb.ui;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle; 
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import com.bb.R;
import com.bb.model.Jiaoliu;
import com.bb.api.JiaoliuHttpAdapter;
import com.bb.util.AsyncImageLoader;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.util.Constants;

import edu.self.utils.AppContext;



public class JiaoliuListActivity2  extends  ListActivity {


    private JiaoliuAdapter adapter = null;
    
    private ArrayList<Jiaoliu> jiaoliuList;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = AppContext.userinfo.getUserName();      
        setContentView(R.layout.jiaoliu_list);  
        
		Button left_button = (Button) findViewById(R.id.left_button);
		left_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        new LoadTask().execute();    
			}
		});

		Button right_button = (Button) findViewById(R.id.right_button);
		right_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent( JiaoliuListActivity2.this, JiaoliuInfoAddActivity.class);
				startActivity(intent);
			}
		});

        new LoadTask().execute();     
    }
    
	
	/**
	 * 异步加载所有资源
	 *
	 */
	public class LoadTask extends AsyncTask<Void, Void, Void>{
	 
		protected Void doInBackground(Void... arg0) {
			try {
//				jiaoliuList =  JiaoliuHttpAdapter.getAllJiaoliuList(-1, -1,type) ;
				
				jiaoliuList =  JiaoliuHttpAdapter.getAllJiaoliuList2(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		protected void onPostExecute(Void result) {
			adapter = new JiaoliuAdapter() ;
			setListAdapter(adapter);
			removeDialog(0);
			super.onPostExecute(result);
		}
		
		protected void onPreExecute() {
			showDialog(0);
			super.onPreExecute();
		}
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent( JiaoliuListActivity2.this, JiaoliuInfoActivity.class);
		intent.putExtra("object", jiaoliuList.get(position));
		startActivity(intent);
	}
	

	public class JiaoliuAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return jiaoliuList.size();
		}

		public Object getItem(int arg0) {
			return jiaoliuList.get(arg0);
		}
		
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.jiaoliu_list_row, null);
 
		    TextView name =  (TextView) convertView.findViewById(R.id.name); ;
		    
	        Jiaoliu u = jiaoliuList.get(position); 
//	        name.setText(  u.getNeirong() + " " + u.getYonghu() + " " + u.getShijian()    );
	        name.setText(  u.getNeirong()   );
	        
			return convertView;
		}
	}
	
	
 
    
}
