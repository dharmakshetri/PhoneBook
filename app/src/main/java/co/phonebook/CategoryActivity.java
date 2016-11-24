package co.phonebook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends Activity implements OnItemClickListener {
	private ConnectionDetector cd;
	private boolean isInternetPresent;
	List<ParseObject> ob;
	List<ParseObject> pnObject;
	private ListView mListView;
	private CategoryAdapter mAdapter;
	private String YOUR_APPLICATION_ID = "B8rvkW7fFLF1XMO3JPBTf0WyhcBVQvh5fE9joLcc";
	private String  YOUR_CLIENT_KEY="WDx9DVZDgnVHBLdq2eYXYWCesB06vjwOEOCiTVnI";
	public ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list);
		//Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
		Log.d(STORAGE_SERVICE, "Saved Successfully-44");
		ParseObject.registerSubclass(Category.class);
		ParseObject.registerSubclass(PhoneName.class);
		//setData();
		mAdapter = new CategoryAdapter(this, new ArrayList<Category>());

		mListView = (ListView) findViewById(R.id.category_list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		Log.d(STORAGE_SERVICE, "Saved Successfully-555");
		cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isOnline();
        Log.d("isInternetPresent", "isInternetPresent="+isInternetPresent);
		if (isInternetPresent) {
			//updateData();
				}else {
					
				}
		new RemoteDataTask().execute();
		
		
	}
	// RemoteDataTask AsyncTask
		private class RemoteDataTask extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(CategoryActivity.this);
				// Set progressdialog title
				mProgressDialog.setTitle("Phone Book");
				// Set progressdialog message
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				
				updateData();
				setData();
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				mProgressDialog.dismiss();
			}
		}
	public void setData() {
		Log.d(STORAGE_SERVICE, "Saved Successfullywww-");
		final Category c= new Category();
		//c.setCatId(cat_id);
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("category");
		try {
			ob = query.find();
			for (ParseObject name : ob) {
				Log.d("TAG", "Cat Name add--"+(String) name.get("category_name") +" ID="+name.getInt("cat_id"));
				if(!((String) name.get("category_name")==null || name.get("category_name").equals("null"))) {
					Log.d("TAG", "IF Cat NAME--"+(String) name.get("category_name"));
					c.setCatName((String) name.get("category_name"));
					c.setCatId(name.getInt("cat_id"));
					c.saveEventually();
				}
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//c.setCatName("category_name");
		c.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				
			Log.d(STORAGE_SERVICE, "Saved Successfully-"+c.getCatName());
			}
		});
		
		// Counting number of phone number for respevice category
		/*final PhoneName pn= new PhoneName();
		//c.setCatId(cat_id);
		ParseQuery<ParseObject> query_phone_number_count = new ParseQuery<ParseObject>("Phone_Numbers");
		try {
			pnObject = query_phone_number_count.find();
			for (ParseObject name : pnObject) {
				Log.d("TAG", "Phone Name add--"+(String) name.get("phone_name"));
				pn.setPhoneName((String) name.get("phone_name"));
				c.saveEventually();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//c.setCatName("category_name");
		c.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				
			Log.d(STORAGE_SERVICE, "Saved Successfully-");
			}
		});*/
		//end of counting
	}

	public void updateData(){
		
		ParseQuery<Category> query = ParseQuery.getQuery(Category.class);
		//query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		//query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.findInBackground(new FindCallback<Category>() {
			@Override
			public void done(List<Category> tasks, ParseException error) {
				Log.d(ACCOUNT_SERVICE, "--tt-"+tasks.size());
				if(tasks != null){
					//mAdapter.clear();
					for (int i = 0; i < tasks.size(); i++) {
						PhoneBook.category.add(tasks.get(i).getCatName());
						//Log.e(ACCOUNT_SERVICE, "Cat ID=="+tasks.get(i).getCatId());
						
						// Counting number of phone number for respevice category
						final PhoneName pn= new PhoneName();
						//c.setCatId(cat_id);
						ParseQuery<ParseObject> query_phone_number_count = new ParseQuery<ParseObject>("Phone_Numbers");
						query_phone_number_count.whereEqualTo("cat_id", tasks.get(i).getCatId());
						try {
							ob = query_phone_number_count.find();
							int size=ob.size();
							//Log.e(ACCOUNT_SERVICE, "Cat ID=="+tasks.get(i).getCatId()+"  size=="+size);
							PhoneBook.category_count.add(String.valueOf(size));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//c.setCatName("category_name");
						/*c.saveInBackground(new SaveCallback() {
							
							@Override
							public void done(ParseException e) {
								
							Log.d(STORAGE_SERVICE, "Saved Successfully-");
							}
						});*/
						//mAdapter.add(tasks.get(i),pnObject.size());
						mAdapter.add(tasks.get(i));
					}
				}
			}
		});
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Category category = mAdapter.getItem(position);
		Log.d("TAG", "task Name--"+category.getCatName().toString());
		Log.d("TAG", "task ID--"+category.getCatId());
		TextView tvCategory = (TextView) view.findViewById(R.id.category_name);
		tvCategory.setText(category.getCatName());
		category.saveEventually();
		// Send single item click data to SingleItemView Class
		Intent i = new Intent(CategoryActivity.this,PhoneNameActivity.class);
		// Pass data "name" followed by the position
		i.putExtra("cat_name", category.getCatName().toString());
		i.putExtra("cat_id", category.getCatId());
		// Open SingleItemView.java Activity
		startActivity(i);
	}

}
