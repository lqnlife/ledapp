package com.bb.ui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.R;
import com.bb.api.ShoucangHttpAdapter;
import com.bb.model.Shoucang;
import com.bb.util.AsyncImageLoader;

import edu.self.utils.AppContext;



public class ShoucangListActivity  extends  ListActivity {


    private ShoucangAdapter adapter = null;
    
    private ArrayList<Shoucang> shoucangList;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      type = AppContext.userinfo.getUserName();   
        setContentView(R.layout.shoucang_list);  
        
//		Button left_button = (Button) findViewById(R.id.left_button);
//		left_button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//		        new LoadTask().execute();    
//			}
//		});
//
//		Button right_button = (Button) findViewById(R.id.right_button);
//		right_button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//
//				Intent intent = new Intent( ShoucangListActivity.this, ShoucangInfoAddActivity.class);
//				startActivity(intent);
//			}
//		});

        new LoadTask().execute();     
    }
    
	//    @Override  
  // public boolean onCreateOptionsMenu(Menu menu) {  
  //     // Inflate the menu; this adds items to the action bar if it is present.  
  //     super.onCreateOptionsMenu(menu);
  //   
  //     MenuItem refresh = 	menu.add(0, 1, 0, "刷新");  
  //     MenuItem add = 		menu.add(0, 2, 1, "添加");   
  //     
  //     //绑定到ActionBar    
  //     refresh.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);   
  //     add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);   
  //     return true;  
  // }  
  //
  //  @Override  
  //  protected void onStart() {  
  //      super.onStart();  
  //      ActionBar actionBar = this.getActionBar();  
  //      actionBar.setDisplayHomeAsUpEnabled(true);  
  //  }  
  //  
  //  public boolean onOptionsItemSelected(MenuItem item) {
	//	// TODO Auto-generated method stub
	//	switch (item.getItemId()) {
	//		case 1:
	//	        new LoadTask().execute();   
	//	        break;
	//		case 2:
	//			Intent intent = new Intent(this, ShoucangInfoAddActivity.class);
	//			startActivity(intent);
	//			break;
	//		default:
	//			break;
	//	}
	//	return super.onOptionsItemSelected(item);
	//}
	
	/**
	 * 异步加载所有资源
	 *
	 */
	public class LoadTask extends AsyncTask<Void, Void, Void>{
	 
		protected Void doInBackground(Void... arg0) {
			try {
				shoucangList =  ShoucangHttpAdapter.getAllShoucangList(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		protected void onPostExecute(Void result) {
			adapter = new ShoucangAdapter() ;
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
		Intent intent = new Intent( ShoucangListActivity.this, ShoucangInfoActivity.class);
		intent.putExtra("object", shoucangList.get(position));
		startActivity(intent);
	}
	

	public class ShoucangAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return shoucangList.size();
		}

		public Object getItem(int arg0) {
			return shoucangList.get(arg0);
		}
		
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.shoucang_list_row, null);
 
		    TextView name =  (TextView) convertView.findViewById(R.id.name); ;
		    
	        Shoucang u = shoucangList.get(position); 
	        name.setText(  u.getBiaoti()    );
	        
			return convertView;
		}
	}
	
	
 
    
}
