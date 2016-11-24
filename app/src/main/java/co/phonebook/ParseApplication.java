package co.phonebook;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {
	 private String YOUR_APPLICATION_ID = "B8rvkW7fFLF1XMO3JPBTf0WyhcBVQvh5fE9joLcc";
	private String  YOUR_CLIENT_KEY="WDx9DVZDgnVHBLdq2eYXYWCesB06vjwOEOCiTVnI";
	@Override
	public void onCreate() {
		super.onCreate();
		//YOUR_APPLICATION_ID ==B8rvkW7fFLF1XMO3JPBTf0WyhcBVQvh5fE9joLcc
		//YOUR_CLIENT_KEY==WDx9DVZDgnVHBLdq2eYXYWCesB06vjwOEOCiTVnI
		// Add your initialization code here
		Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		//defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
