package co.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhoneNameAdapter extends ArrayAdapter<PhoneName> {
	private Context mContext;
	private List<PhoneName> phone_names;
	
	public PhoneNameAdapter(Context context, ArrayList<PhoneName> arrayList) {
		super(context, R.layout.phone_name_row_item, arrayList);
		this.mContext = context;
		this.phone_names = arrayList;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
			convertView = mLayoutInflater.inflate(R.layout.phone_name_row_item, null);
		}
		
		PhoneName phoneNameValue = phone_names.get(position);
		
		TextView phoneNameView = (TextView) convertView.findViewById(R.id.phone_name);
		
		phoneNameView.setText(phoneNameValue.getPhoneName());
		
		return convertView;
	}

}
