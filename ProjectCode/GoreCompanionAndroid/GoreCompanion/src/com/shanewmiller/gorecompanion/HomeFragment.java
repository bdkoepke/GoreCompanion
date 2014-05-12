package com.shanewmiller.gorecompanion;
/*
 * This is the home fragment, which is launched when the user opens the app
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.shanewmiller.gorecompanion.Models.ConditionSummaryResult;
import com.shanewmiller.gorecompanion.Models.DetailedConditionsResult;
import com.shanewmiller.gorecompanion.workerclasses.SummaryJSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
   
   TextView homeTrailsView; 
   TextView homeLiftsView;
   TextView homeBaseView;
   TextView homePrimaryView;
   TextView homeSecondaryView;
	
   public HomeFragment(){}
   
   ConditionSummaryResult result;
   SummaryJSONParser parser;
   
   @Override
   public void onActivityCreated(Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       
       refresh();
   }
   
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    
       return rootView;
   }
   
   public void refresh(){
	   View view = this.getView();
	   if (result == null){
		   homeTrailsView = (TextView) view.findViewById(R.id.trailsText); 
		   homeLiftsView = (TextView) view.findViewById(R.id.liftsText);
		   homeBaseView = (TextView) view.findViewById(R.id.baseDepthText);
		   homePrimaryView = (TextView) view.findViewById(R.id.primarySurfaceText);
		   homeSecondaryView = (TextView) view.findViewById(R.id.secondarySurfaceText);
		}
       
	   SummaryTask task = new SummaryTask();
	   task.execute("http://shanewmiller.com/GoreCompanion/conditionsSummaryJSON.php");
   }
   
   
   // Background task class
   public class SummaryTask extends AsyncTask <String, Void ,ConditionSummaryResult>{

   	private static final String TAG = "Task_ERROR!!"; 	
   	private ProgressDialog dialog;
   	private Context mContext;

   	@Override
   	protected void onPreExecute(){
   		super.onPreExecute();
   		
   		mContext = getActivity();
   		dialog = new ProgressDialog(mContext);
   		dialog.setMessage("Incoming snow info!");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
   	}

   	@Override
	protected ConditionSummaryResult doInBackground(String... url) {
		 DefaultHttpClient client = new DefaultHttpClient(); 
	     HttpGet getRequest = null;
	     ConditionSummaryResult result = new ConditionSummaryResult();
	     String name = "nope!";     
	        try {
				getRequest = new HttpGet(url[0]);
					
				HttpResponse getResponse = client.execute(getRequest);
				final int statusCode = getResponse.getStatusLine().getStatusCode();
				   
				if (statusCode != HttpStatus.SC_OK) { 
					Log.w(getClass().getSimpleName(), 
					"Error " + statusCode + " for URL " + url);
				}
			
				HttpEntity getResponseEntity = getResponse.getEntity();
				Reader reader = new InputStreamReader(getResponseEntity.getContent());
				Gson gson = new Gson();
				String responseString = reader.toString();
				result = gson.fromJson(reader, ConditionSummaryResult.class);
			}
			catch(Exception e){
				Log.e(TAG,"gson part: " + e.toString());
			}
	        
		return result;
	}
   	protected void onPostExecute(ConditionSummaryResult result) { 
   		
   		if (dialog.isShowing()){
       		dialog.dismiss();			
   		}
		homeTrailsView.setText(result.trailCount.toString());
		homeLiftsView.setText(result.liftCount.toString());
		homeBaseView.setText(result.base);
		homePrimaryView.setText(result.primarySurface);
		homeSecondaryView.setText(result.secondarySurface);
   		
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
