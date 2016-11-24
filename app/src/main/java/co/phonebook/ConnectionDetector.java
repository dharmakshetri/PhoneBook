package co.phonebook;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
	
	 private Context _context;
     
	    public ConnectionDetector(Context context){
	        this._context = context;
	    }

	    
	    public boolean isOnline(){
	    	ConnectivityManager cm = (ConnectivityManager)  _context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni != null && ni.isConnected())
			{
				return true;
			}

			return false;
	    }
	

}
