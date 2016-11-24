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
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends Activity {
	// Declare Variables
	TextView txtname;
	String name;
	int cat_id;
	ArrayList<String> names= new ArrayList<String>();
	
	ListView sub_list;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.sub_cat_listview);
		
		// Retrieve data from MainActivity on item click event
		Intent i = getIntent();
		
		// Get the name
		name = i.getStringExtra("cat_name");
		cat_id=i.getIntExtra("cat_id",-1);
		
		Log.d("CATID", "--"+cat_id);
		names.add(name);
		
		new RemoteDataTask().execute();
		
		/*// Locate the TextView in singleitemview.xml
		txtname = (TextView) findViewById(R.id.cat_name);
		
		// Load the text into the TextView
		txtname.setText(name);*/
		
	}
	
	// RemoteDataTask AsyncTask
		private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(SubCategory.this);
				// Set progressdialog title
				mProgressDialog.setTitle(name);
				// Set progressdialog message
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				// Locate the class table named "Country" in Parse.com
				
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Phone_Numbers");
				//query.orderByDescending("_created_at");
				//query.selectKeys(Arrays.asList("cat_id", cat_id));
				
				query.whereEqualTo("cat_id", cat_id);
				/*query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				query.findInBackground(new FindCallback<ParseObject>() {
				public void done(List<ParseObject> scoreList, ParseException e) {
				  if (e == null) {
				    // Results were successfully found, looking first on the
				    // network and then on disk.
				  } else {
				    // The network was inaccessible and we have no cached data
				    // for this query.
				  }
				}
				});*/
				try {
					ob = query.find();
				} catch (ParseException e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// Locate the listview in listview_main.xml
				sub_list=(ListView) findViewById(R.id.subCatListView);
				// Pass the results into an ArrayAdapter
				adapter = new ArrayAdapter<String>(SubCategory.this,
						R.layout.listview_item);
				// Retrieve object "name" from Parse.com database
				for (ParseObject country : ob) {
					adapter.add((String) country.get("phone_name"));
				}
				
				// Binds the Adapter to the ListView
				sub_list.setAdapter(adapter);
				// Close the progressdialog
				mProgressDialog.dismiss();
				// Capture button clicks on ListView items
				sub_list.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// Send single item click data to SingleItemView Class
						Intent i = new Intent(SubCategory.this,	PhoneNumberPlate.class);
						// Pass data "name" followed by the position
						i.putExtra("phone_name", ob.get(position).getString("phone_name").toString());
						i.putExtra("phone_address", ob.get(position).getString("phone_address").toString());
						i.putExtra("phone_number", ob.get(position).getString("phone_number").toString());
						i.putExtra("phone_mobile", ob.get(position).getString("mobile_number").toString());
						// Open SingleItemView.java Activity
						startActivity(i);
					}
				});
			}
		}
}