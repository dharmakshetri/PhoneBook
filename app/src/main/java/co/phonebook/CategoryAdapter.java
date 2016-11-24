package co.phonebook;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
	private Context mContext;
	private List<Category> mCategory;
	
	public CategoryAdapter(Context context, ArrayList<Category> arrayList) {
		super(context, R.layout.category_row_item, arrayList);
		this.mContext = context;
		this.mCategory = arrayList;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
			convertView = mLayoutInflater.inflate(R.layout.category_row_item, null);
		}
		
		Category category = mCategory.get(position);
		TextView CategoryNameView = (TextView) convertView.findViewById(R.id.category_name);
		CategoryNameView.setText(category.getCatName());
		
		Log.i("TEST", "Category ID="+category.getCatId()+"==="+PhoneBook.category_count);
		
		TextView CategoryCountView=(TextView) convertView.findViewById(R.id.cat_count);
		
		CategoryCountView.setText(PhoneBook.category_count.get(position) +" - "+ category.getCatName());
		CategoryCountView.setTextColor(Color.RED);
		
		return convertView;
	}

}
