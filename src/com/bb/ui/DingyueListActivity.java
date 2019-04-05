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
import com.bb.model.Dingyue;
import com.bb.api.DingyueHttpAdapter;
import com.bb.util.AsyncImageLoader;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.util.Constants;

import edu.self.utils.AppContext;



public class DingyueListActivity  extends  ListActivity {


    private DingyueAdapter adapter = null;
    
    private ArrayList<Dingyue> dingyueList;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        type = (String) getIntent().getExtras().get("type");     
        type = AppContext.userinfo.getUserName();         
        setContentView(R.layout.dingyue_list);  
        
		Button left_button = (Button) findViewById(R.id.left_button);
		left_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        new LoadTask().execute();    
			}
		});

//		Button right_button = (Button) findViewById(R.id.right_button);
//		right_button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//
//				Intent intent = new Intent( DingyueListActivity.this, DingyueInfoAddActivity.class);
//				startActivity(intent);
//			}
//		});

        new LoadTask().execute();     
    }
     
	 
	/**
	 * 异步加载所有资源
	 *
	 */
	public class LoadTask extends AsyncTask<Void, Void, Void>{
	 
		protected Void doInBackground(Void... arg0) {
			try {
				dingyueList =  DingyueHttpAdapter.getAllDingyueList(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		protected void onPostExecute(Void result) {
			adapter = new DingyueAdapter() ;
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
//		Intent intent = new Intent( DingyueListActivity.this, DingyueInfoActivity.class);
		
		Intent intent = new Intent( DingyueListActivity.this, GonggaoListActivity.class);
//		intent.putExtra("object", dingyueList.get(position));

		intent.putExtra("type", dingyueList.get(position).getLanmu() );
		startActivity(intent);
	}
	

	public class DingyueAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return dingyueList.size();
		}

		public Object getItem(int arg0) {
			return dingyueList.get(arg0);
		}
		
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dingyue_list_row, null);
 
		    TextView name =  (TextView) convertView.findViewById(R.id.name); ;
		    
	        Dingyue u = dingyueList.get(position); 
	      // name.setText(  u.name    );
	        
				name.setText( u.getLanmu() ); 
		 
		 
			return convertView;
		}
	}
	
	
 
    
}
