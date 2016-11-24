package co.phonebook;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("category")
public class Category extends ParseObject{
	public Category(){
		
	}
	
	public int getCatId(){
		return getInt("cat_id");
	}
	
	public void setCatId(int cid){
		put("cat_id", cid);
	}
	
	public String getCatName(){
		return getString("category_name");
	}
	
	public void setCatName(String cname){
		put("category_name", cname);
	}
	
	/*public int getCatTotalCount(){
		return getInt();
	}
*/
}
