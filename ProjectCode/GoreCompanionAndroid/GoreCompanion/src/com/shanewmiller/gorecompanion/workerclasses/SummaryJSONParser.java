package com.shanewmiller.gorecompanion.workerclasses;

import android.content.Context;

import com.shanewmiller.gorecompanion.Models.ConditionSummaryResult;
import com.shanewmiller.gorecompanion.Models.DetailedConditionsResult;
import java.util.concurrent.ExecutionException;

public class SummaryJSONParser {
	// url to make request
    private static String url = "http://shanewmiller.com/GoreCompanion/conditionsSummaryJSON.php";
    private static final String TAG = "JSONParser_ERROR!!";  
    
    public ConditionSummaryResult GetSummary(Context context){
		
    	ConditionSummaryTask task = new ConditionSummaryTask(context);
    	
    	ConditionSummaryResult result = new ConditionSummaryResult();
    	
    	try {
    		result = task.execute(url).get();
			
			if (result != null){
				return result;
        	}
			
		}  catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
    	}   	
    	return null;    	
    }
}
  

