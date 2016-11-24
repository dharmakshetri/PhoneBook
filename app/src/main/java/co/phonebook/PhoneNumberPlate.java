package co.phonebook;

import java.util.ArrayList;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneNumberPlate extends Activity implements OnClickListener  {
	// Declare Variables
	TextView txtname, txtaddress, txtnumber1, txtnumber2, txtnumber3,
			txtnumber4, txtnumber5, txtmobile;
	ImageView imgPhone1,imgPhone2,imgPhone3,imgPhone4,imgPhone5,imgMobile1,imgMoible2,imgMobile3;
	String name, number, address, mobile;
	int cat_id;
	ArrayList<String> names = new ArrayList<String>();
	private Context context;
	RelativeLayout rl;
	String[] phone_split,mobile_split;
	private int j,k;
	ArrayList<TextView> arrtextview;
	TextView[] tv_phone, tv_mobile;
	ImageView [] img_phone,img_mobile;
	LinearLayout lp1,lp2,lp3,lp4,lp5,lm1,lm2,lm3;
	RelativeLayout radd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.phone_number_plate);

		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,
				PhoneStateListener.LISTEN_CALL_STATE);
		// Retrieve data from MainActivity on item click event
		Intent i = getIntent();

		// Get the name
		name = i.getStringExtra("phone_name");
		address = i.getStringExtra("phone_address");
		number = i.getStringExtra("phone_number");
		mobile = i.getStringExtra("moible_number");

		Log.d("PHONE", "number=="+name);
		// Locate the TextView in singleitemview.xml
		txtname = (TextView) findViewById(R.id.tv_name_of_representation);
		txtname.setText(name);

		txtaddress = (TextView) findViewById(R.id.tv_address_of_representation);
		txtaddress.setText(address);
		linearui();
		phonenumberui();
		mobilenumberui();
		
		
		
		

		/*
		 * //arrtextview= new ArrayList<TextView>; arrtextview.add(txtnumber1);
		 * arrtextview.add(txtnumber2); arrtextview.add(txtnumber3);
		 * arrtextview.add(txtnumber4); arrtextview.add(txtnumber5);
		 */

		

	

		// Log.d("LOGLOG", "--"+phone_split.length);

		/*rl = (RelativeLayout) findViewById(R.id.bottom_relative);
		rl.setOnClickListener(this);*/



	}

	private void linearui() {

		lp1=(LinearLayout) findViewById(R.id.LPhone1);
		lp2=(LinearLayout) findViewById(R.id.LPhone2);
		lp3=(LinearLayout) findViewById(R.id.LPhone3);
		lp4=(LinearLayout) findViewById(R.id.LPhone4);
		lp5=(LinearLayout) findViewById(R.id.LPhone5);
		
		lm1=(LinearLayout) findViewById(R.id.LMobile1);
		lm2=(LinearLayout) findViewById(R.id.LMobile2);
		lm3=(LinearLayout) findViewById(R.id.LMobile3);
		
		radd=(RelativeLayout)findViewById(R.id.reladd);
		radd.setClickable(true);
		radd.setOnClickListener(this);
		
		lp1.setClickable(true);
		lp2.setClickable(true);
		lp3.setClickable(true);
		lp4.setClickable(true);
		lp5.setClickable(true);
		
		lm1.setClickable(true);
		lm2.setClickable(true);
		lm3.setClickable(true);
		
		lp1.setOnClickListener(this);
		lp2.setOnClickListener(this);
		lp3.setOnClickListener(this);
		lp4.setOnClickListener(this);
		lp5.setOnClickListener(this);
		
		lm1.setOnClickListener(this);
		lm2.setOnClickListener(this);
		lm3.setOnClickListener(this);
		
		
		
		
	}

	private void mobilenumberui() {
		mobile_split=mobile.split(",");

		
		tv_mobile= new TextView[3];
		img_mobile= new ImageView[3];
				
		tv_mobile[0]=(TextView)findViewById(R.id.mobile_of_phone1);
		tv_mobile[1]=(TextView)findViewById(R.id.mobile_of_phone2);
		tv_mobile[2]=(TextView)findViewById(R.id.mobile_of_phone3);
		
		img_mobile[0]=(ImageView)findViewById(R.id.image_of_mobile1);
		img_mobile[1]=(ImageView)findViewById(R.id.image_of_mobile2);
		img_mobile[2]=(ImageView)findViewById(R.id.image_of_mobile3);
		/*
		tv_mobile[0].setOnClickListener(this);
		tv_mobile[1].setOnClickListener(this);
		tv_mobile[2].setOnClickListener(this);*/
		
		switch (phone_split.length) {
		case 1:
			setvisiabilityMobile(1);
			break;
		case 2:
			setvisiabilityMobile(2);
			break;
		case 3:
			setvisiabilityMobile(3);
			break;
		default:
			break;
		}
		for (k = 0; k < mobile_split.length; k++) {
			tv_mobile[k].setText(mobile_split[k].toString());
			}
	}

	private void setvisiabilityMobile(int sizeofmobile) {
		for (int sv=0;sv<sizeofmobile;sv++){
			tv_mobile[sv].setVisibility(View.VISIBLE);
			img_mobile[sv].setVisibility(View.VISIBLE);
		}
		
	}

	private void phonenumberui() {
		tv_phone= new TextView[5];
		img_phone= new ImageView[5];
		phone_split = number.split(",");
		tv_phone[0] = (TextView) findViewById(R.id.numbers_of_phone1);
		tv_phone[1] = (TextView) findViewById(R.id.numbers_of_phone2);
		tv_phone[2] = (TextView) findViewById(R.id.numbers_of_phone3);
		tv_phone[3] = (TextView) findViewById(R.id.numbers_of_phone4);
		tv_phone[4] = (TextView) findViewById(R.id.numbers_of_phone5);
		
		img_phone[0] = (ImageView) findViewById(R.id.image_of_phone1);
		img_phone[1] = (ImageView) findViewById(R.id.image_of_phone2);
		img_phone[2] = (ImageView) findViewById(R.id.image_of_phone3);
		img_phone[3] = (ImageView) findViewById(R.id.image_of_phone4);
		img_phone[4] = (ImageView) findViewById(R.id.image_of_phone5);


		/*tv_phone[0].setOnClickListener(this);
		tv_phone[1].setOnClickListener(this);
		tv_phone[2].setOnClickListener(this);
		tv_phone[3].setOnClickListener(this);
		tv_phone[4].setOnClickListener(this);*/
		
		switch (phone_split.length) {
		case 1:
			setvisiabilityphone(1);
			
			break;
		case 2:
			setvisiabilityphone(2);
			break;
		case 3:
			setvisiabilityphone(3);
			break;
		case 4:
			setvisiabilityphone(4);
			break;
		case 5:
			setvisiabilityphone(5);
			break;
		default:
			break;
		}
		for (j = 0; j < phone_split.length; j++) {
			tv_phone[j].setText(phone_split[j].toString());
			}
	}

	private void setvisiabilityphone(int sizeOfVisiablity) {
		for (int sv=0;sv<sizeOfVisiablity;sv++){
			tv_phone[sv].setVisibility(View.VISIBLE);
			img_phone[sv].setVisibility(View.VISIBLE);
		}
		
		
	}

	// monitor phone call activities
	private class PhoneCallListener extends PhoneStateListener {

		private boolean isPhoneCalling = false;

		String LOG_TAG = "LOGGING 123";

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}

			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");

				isPhoneCalling = true;
			}

			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended,
				// need detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");

				if (isPhoneCalling) {

					Log.i(LOG_TAG, "restart app");

					// restart app
					Intent i = getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(
									getBaseContext().getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);

					isPhoneCalling = false;
				}

			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.LPhone1:
			ContentValues contactValues = new ContentValues();
	        contactValues.put(Data.RAW_CONTACT_ID, 001);
	        contactValues.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
	        contactValues.put(Phone.NUMBER, "555-555-5555");
	        contactValues.put(Phone.TYPE, Phone.TYPE_CUSTOM);
	        contactValues.put(Phone.LABEL, "John");
	        Uri dataUri = getContentResolver().insert(
	             Data.CONTENT_URI, contactValues);
			  Intent callIntentp1 = new Intent(Intent.ACTION_CALL);
			  //callIntentp1.setDataAndType(Uri.parse("tel:"+phone_split[1].toString()),"Ram");
			//  callIntentp1.setData(Uri.parse("tel:"+phone_split[0].toString()));
			  callIntentp1.setData(dataUri);
			  startActivity(callIntentp1);
			break;
		case R.id.LPhone2:
			Intent callIntentp2 = new Intent(Intent.ACTION_CALL);
			  callIntentp2.setData(Uri.parse("tel:"+phone_split[1].toString()));
			  startActivity(callIntentp2);
			break;
		case R.id.LPhone3:
			Intent callIntentp3 = new Intent(Intent.ACTION_CALL);
			  callIntentp3.setData(Uri.parse("tel:"+phone_split[2].toString()));
			  startActivity(callIntentp3);
			break;
		case R.id.LPhone4:
			Intent callIntentp4 = new Intent(Intent.ACTION_CALL);
			  callIntentp4.setData(Uri.parse("tel:"+phone_split[3].toString()));
			  startActivity(callIntentp4);
			break;
		case R.id.LPhone5:
			Intent callIntentp5 = new Intent(Intent.ACTION_CALL);
			callIntentp5.setData(Uri.parse("tel:"+phone_split[4].toString()));
			  startActivity(callIntentp5);
			break;
		case R.id.LMobile1:
			Intent callIntentm1 = new Intent(Intent.ACTION_CALL);
			callIntentm1.setData(Uri.parse("tel:"+mobile_split[0].toString()));
			  startActivity(callIntentm1);
			break;
		case R.id.LMobile2:
			Intent callIntentm2 = new Intent(Intent.ACTION_CALL);
			callIntentm2.setData(Uri.parse("tel:"+mobile_split[1].toString()));
			  startActivity(callIntentm2);
			break;
		case R.id.LMobile3:
			Intent callIntentm3 = new Intent(Intent.ACTION_CALL);
			callIntentm3.setData(Uri.parse("tel:"+mobile_split[2].toString()));
			  startActivity(callIntentm3);
			break;
		case R.id.reladd:
			Intent iaddnumber= new Intent(getApplicationContext(),AddYourNumber.class);
			startActivity(iaddnumber);
			break;
		default:
			break;
		}

	}
}