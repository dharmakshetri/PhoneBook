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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends Activity {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ArrayAdapter<String> adapter;
	private ConnectionDetector cd;
	private boolean isInternetPresent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listview_main);
		// Execute RemoteDataTask AsyncTask
		cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isOnline();
        Log.d("isInternetPresent", "isInternetPresent="+isInternetPresent);
		if (isInternetPresent) {
			Log.d("TAG", "Internet available");
		new RemoteDataTask().execute();
		}else
		{
			Log.d("TAG", "Plz Check Ur Internet Connection");
			updateCachedata();
		}
		
		//new RemoteDataTask().execute();
	}

	private void updateCachedata() {
	
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("category");
		query.orderByAscending("phone_name");
		try {
			ob = query.find();
		} catch (ParseException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<ParseObject>() {
		public void done(List<ParseObject> scoreList, ParseException e) {
		  if (e == null) {
			 // loaddata();
		    // Results were successfully found, looking first on the
		    // network and then on disk.
		  } else {
			  Toast.makeText(getApplicationContext(), "We have no cached data!", Toast.LENGTH_SHORT).show();
		    // The network was inaccessible and we have no cached data
		    // for this query.
		  }
		}
		});
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Nepali Phone Book");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			// Locate the class table named "Country" in Parse.com
			final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("category");
			query.orderByAscending("phone_name");
			query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
			query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
			  if (scoreList!= null) {
				  //ob = query.find();
				loaddata(scoreList);
				  
			    // Results were successfully found, looking first on the
			    // network and then on disk.
			  } else {
				  Toast.makeText(getApplicationContext(), "We have no cached data!", Toast.LENGTH_SHORT).show();
			    // The network was inaccessible and we have no cached data
			    // for this query.
			  }
			}
			});
			//boolean isCached = query.hasCachedResult();
			//if (isCached == true) Toast.makeText(getApplicationContext(), "cache is available", Toast.LENGTH_SHORT).show();
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			loaddata(ob);
		}
	}

	public void loaddata(List<ParseObject> scoreList) {

		// Locate the listview in listview_main.xml
		listview = (ListView) findViewById(R.id.listview);
		// Pass the results into an ArrayAdapter
		adapter = new ArrayAdapter<String>(MainActivity.this,
				R.layout.listview_item);
		// Retrieve object "name" from Parse.com database
		for (ParseObject number : ob) {
			adapter.add((String) number.get("category_name"));
		}
		// Binds the Adapter to the ListView
		listview.setAdapter(adapter);
		// Close the progressdialog
		mProgressDialog.dismiss();
		// Capture button clicks on ListView items
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Send single item click data to SingleItemView Class
				Intent i = new Intent(MainActivity.this,SubCategory.class);
				// Pass data "name" followed by the position
				i.putExtra("cat_name", ob.get(position).getString("category_name").toString());
				i.putExtra("cat_id", ob.get(position).getInt("cat_id"));
				// Open SingleItemView.java Activity
				startActivity(i);
			}
		});
	
		
	}
}