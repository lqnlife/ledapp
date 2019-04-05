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
import com.bb.model.Gonggao;
import com.bb.api.GonggaoHttpAdapter;
import com.bb.util.AsyncImageLoader;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.util.Constants;



public class GonggaoListActivity  extends  ListActivity {


    private GonggaoAdapter adapter = null;
    
    private ArrayList<Gonggao> gonggaoList;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = (String) getIntent().getExtras().get("type");        
        setContentView(R.layout.gonggao_list);  
        
		Button left_button = (Button) findViewById(R.id.left_button);
		left_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        new LoadTask().execute();    
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
				gonggaoList =  GonggaoHttpAdapter.getAllGonggaoList(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		protected void onPostExecute(Void result) {
			adapter = new GonggaoAdapter() ;
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
		Intent intent = new Intent( GonggaoListActivity.this, GonggaoInfoActivity.class);
		intent.putExtra("object", gonggaoList.get(position));
		startActivity(intent);
	}
	

	public class GonggaoAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return gonggaoList.size();
		}

		public Object getItem(int arg0) {
			return gonggaoList.get(arg0);
		}
		
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.gonggao_list_row, null);
 
		    TextView name =  (TextView) convertView.findViewById(R.id.name); ;
		    
	        Gonggao u = gonggaoList.get(position); 
	        name.setText(  u.getBiaoti()    );
	        
			return convertView;
		}
	}
	
	
 
    
}
