package co.phonebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddYourNumber extends Activity {
	EditText etNameOfRepresentation, etPhoneNumbers, etMobileNumbers,
			etAddress;
	Spinner spinCategory;
	List<Category> spinCategoryArray = new ArrayList<Category>();
	Category c;
	Button btnSave;
	
	String strNameOfRepresentation,strPhoneNumber,strMobileNumber,strAddress,strCategory;
	int intCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_your_number);

		setUpViews();

		ArrayAdapter<String> categoryadapter = new ArrayAdapter<String>(
				AddYourNumber.this, android.R.layout.simple_spinner_item,
				PhoneBook.category);
		categoryadapter.setDropDownViewResource(R.layout.spinner_style);
		spinCategory.setPrompt("Choose Category");
		spinCategory.setAdapter(categoryadapter);
		categoryadapter.notifyDataSetChanged();
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				strNameOfRepresentation=etNameOfRepresentation.getText().toString();
				strPhoneNumber=etPhoneNumbers.getText().toString();
				strMobileNumber=etMobileNumbers.getText().toString();
				strAddress=etAddress.getText().toString();
				
			}
		});
		

	}

	private void setUpViews() {
		etNameOfRepresentation = (EditText) findViewById(R.id.et_name_of_represenation);
		etPhoneNumbers = (EditText) findViewById(R.id.et_landline_number);
		etMobileNumbers = (EditText) findViewById(R.id.et_mobile_number);
		etAddress = (EditText) findViewById(R.id.et_address);
		spinCategory = (Spinner) findViewById(R.id.spin_category);
		btnSave=(Button) findViewById(R.id.btn_save);
	}

}
