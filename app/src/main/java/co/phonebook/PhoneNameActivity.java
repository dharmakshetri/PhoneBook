package co.phonebook;

import android.app.Activity;
import android.content.Intent;
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


public class PhoneNameActivity extends Activity implements OnItemClickListener {
	private ConnectionDetector cd;
	private boolean isInternetPresent;
	List<ParseObject> ob;
	private ListView mListView;
	private PhoneNameAdapter mAdapter;
	private String YOUR_APPLICATION_ID = "B8rvkW7fFLF1XMO3JPBTf0WyhcBVQvh5fE9joLcc";
	private String  YOUR_CLIENT_KEY="WDx9DVZDgnVHBLdq2eYXYWCesB06vjwOEOCiTVnI";
	
	String name;
	int cat_id;
	ArrayList<String> names= new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list);
		//Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
		Log.d(STORAGE_SERVICE, "Saved Successfully-44");
		ParseObject.registerSubclass(PhoneName.class);
		//setData();
		
		Intent i = getIntent();
		
		// Get the name
		name = i.getStringExtra("cat_name");
		cat_id=i.getIntExtra("cat_id",-1);
		
		Log.d("CATID", "--"+cat_id);
		mAdapter = new PhoneNameAdapter(this, new ArrayList<PhoneName>());

		mListView = (ListView) findViewById(R.id.category_list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		Log.d(STORAGE_SERVICE, "Saved Successfully-555");
		cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isOnline();
        Log.d("isInternetPresent", "isInternetPresent="+isInternetPresent);
		if (isInternetPresent) {
				}else {
					
				}
	 
		updateData();
	}

	public void setData() {
		Log.d(STORAGE_SERVICE, "Saved Successfullywww-");
		final PhoneName c= new PhoneName();
		//c.setCatId(cat_id);
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Phone_Numbers");
		query.whereEqualTo("cat_id", cat_id);
		try {
			ob = query.find();
			for (ParseObject name : ob) {
				Log.d("TAG", "Name add--"+(String) name.get("phone_name"));
				c.setPhoneName((String) name.get("phone_name"));
				c.setCatId(name.getInt("cat_id"));
				c.setPhoneNumber((String) name.get("phone_number"));
				c.setMobileNumber((String) name.get("mobile_number"));
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
		});
	/*	ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("category");
		try {
			ob = query.find();
			for (ParseObject number : ob) {
				Log.d("TAG", "Name add--"+(String) number.get("category_name"));
				c.setCatName((String) number.get("category_name"));
				//c.setCatId(number.getInt("cat_id"));
				c.saveEventually();
			}
		} catch (ParseException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}*/
		
	}

	public void updateData(){
		
		ParseQuery<PhoneName> query = ParseQuery.getQuery(PhoneName.class);
		//query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		//query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.findInBackground(new FindCallback<PhoneName>() {
			@Override
			public void done(List<PhoneName> ph, ParseException error) {
				Log.d(ACCOUNT_SERVICE, "--tt-"+ph.size());
				if(ph != null){
					//mAdapter.clear();
					
					
					for (int i = 0; i < ph.size(); i++) {
						if(ph.get(i).getCatId()==cat_id)
						{
						mAdapter.add(ph.get(i));
						}
					}
				}
			}
		});
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PhoneName phone = mAdapter.getItem(position);
		Log.d("TAG", " Name--"+phone.getPhoneName().toString());
		Log.d("TAG", " ID------"+phone.getCatId());
		TextView tvphoneName = (TextView) view.findViewById(R.id.phone_name);
		tvphoneName.setText(phone.getPhoneName().toString());
		phone.saveEventually();
		
		// Send single item click data to SingleItemView Class
		Intent i = new Intent(PhoneNameActivity.this,PhoneNumberPlate.class);
		// Pass data "name" followed by the position
		i.putExtra("phone_name", phone.getPhoneName().toString());
		i.putExtra("cat_id", phone.getCatId());
		i.putExtra("phone_address", phone.getPhoneAddress().toString());
		i.putExtra("phone_number", phone.getPhoneNumber().toString());
		i.putExtra("moible_number",  phone.getMobileNumber().toString());
		startActivity(i);
	}

}
