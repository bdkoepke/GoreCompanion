package com.shanewmiller.gorecompanion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.shanewmiller.gorecompanion.Models.DetailedConditionsResult;
import com.shanewmiller.gorecompanion.TrailsListFragment.TrailsTask;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class LiftsListFragment extends ListFragment {

    Boolean isStarted = false;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    
    	setEmptyText("No data found");
    	
    	if (isStarted == false){
            UpdateList();  		
    	}
    }
    
    public void UpdateList(){
    	LiftsTask task = new LiftsTask();
		task.execute("http://shanewmiller.com/GoreCompanion/trailCondsJSON.php");
	}
    
    
    
    // Background task class
    public class LiftsTask extends AsyncTask <String, Void ,DetailedConditionsResult>{

    	private static final String TAG = "Task_ERROR!!"; 	
    	private ProgressDialog dialog;
    	private Context mContext;

    	@Override
    	protected void onPreExecute(){
    		super.onPreExecute();  		
    		mContext = getActivity();
    		
    		dialog = new ProgressDialog(mContext);
    		
    		if (isStarted == true){
	    		dialog.setMessage("Lift info? Coming right up!");
	            dialog.setIndeterminate(true);
	            dialog.setCancelable(false);
	            dialog.show();
    		}
    	}

    	@Override
    	protected DetailedConditionsResult doInBackground(String... url) {
    		 DefaultHttpClient client = new DefaultHttpClient(); 
    	     HttpGet getRequest = null;
    	     DetailedConditionsResult result = new DetailedConditionsResult();
    	     String name = "nope!";     
    	        try {
    	        	getRequest = new HttpGet(url[0]);
    	        	
    	           HttpResponse getResponse = client.execute(getRequest);
    	           final int statusCode = getResponse.getStatusLine().getStatusCode();
    	           
    	           if (statusCode != HttpStatus.SC_OK) { 
    	              Log.w(getClass().getSimpleName(), 
    	                  "Error " + statusCode + " for URL " + url);
    	           }
    	           try{
    	        	   HttpEntity getResponseEntity = getResponse.getEntity();
    	        	   Reader reader = new InputStreamReader(getResponseEntity.getContent());
                 	   Gson gson = new Gson();
    		           String responseString = reader.toString();
    		           result = gson.fromJson(reader, DetailedConditionsResult.class);
    	           }
    	           catch(Exception e){
    	           	Log.e(TAG,"gson part: " + e.toString());
    	           }
    	        } 
    	        catch (IOException e) {
    	        	Log.e(TAG,e.toString()+"       ** Main try in doInBackground **  ");
    	           getRequest.abort();
    	           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
    	        }
    	        catch (Exception e){
    	        	Log.e(TAG,e.toString()+"       ** Main try in doInBackground **  ");
    	        }
    	    return result;
    	}
    	protected void onPostExecute(DetailedConditionsResult result) { 
    		
    		if (dialog.isShowing()){
        		dialog.dismiss();			
    		}

    		LiftListAdaptor adapter = new LiftListAdaptor(mContext,result.lifts);
    		setListAdapter(adapter); 
    		
    		// Marks the fragment as started
        	isStarted = true;
    	}
    }
    
    // method to create a  toast, takes in a string to display
    public void toaster(String textstring){
    	Context context = getActivity().getApplicationContext();
    	CharSequence text = textstring;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
}
