package com.shanewmiller.gorecompanion.workerclasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.shanewmiller.gorecompanion.Models.ConditionSummary;
import com.shanewmiller.gorecompanion.Models.ConditionSummaryResult;
import com.shanewmiller.gorecompanion.Models.Trail;
import com.shanewmiller.gorecompanion.Models.DetailedConditionsResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ConditionSummaryTask extends AsyncTask <String, Void ,ConditionSummaryResult>{

	private static final String TAG = "Task_ERROR!!"; 	
	private ProgressDialog dialog;
	private Context mContext;
	
	public ConditionSummaryTask(Context context){
		mContext = context;
	}
	@Override
	protected void onPreExecute(){
		super.onPreExecute();

		dialog = new ProgressDialog(mContext);
        dialog.setMessage("loading!");
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
		dialog.dismiss();
	}
}


