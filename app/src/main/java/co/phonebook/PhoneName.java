package co.phonebook;


import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Phone_Numbers")
public class PhoneName extends ParseObject {
	public PhoneName(){
		
	}
	
	public int getCatId(){
		return getInt("cat_id");
	}
	
	public void setCatId(int cid){
		put("cat_id", cid);
	}
	
	public void setPhoneName(String phone_name){
		put("phone_name", phone_name);
	}
	
	public String getPhoneName(){
		return getString("phone_name");
	}

	public void setPhoneNumber(String phone_number){
		put("phone_number", phone_number);
	}
	
	public String getPhoneNumber(){
		return getString("phone_number");
	}
	public void setMobileNumber(String mobile_number){
		put("mobile_number", mobile_number);
	}
	
	public String getMobileNumber(){
		return getString("mobile_number");
	}
	
	public void setPhoneAddress(String phone_address){
		put("phone_address",phone_address);
	}
	
	public String getPhoneAddress(){
		return getString("phone_address");
	}
}
