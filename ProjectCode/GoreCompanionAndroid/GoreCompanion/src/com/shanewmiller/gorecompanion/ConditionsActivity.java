package com.shanewmiller.gorecompanion;


import android.annotation.TargetApi;
import android.app.*;
import android.app.ActionBar.Tab;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ConditionsActivity extends Activity {

	private ProgressDialog pd;
	LiftsListFragment liftFragment;
	TrailsListFragment trailFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Conditions");
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Tab TrailTab = actionBar.newTab()
						.setText("Trails")
						.setTabListener(new TabListener<TrailsListFragment>(this, "trails", TrailsListFragment.class));
		actionBar.addTab(TrailTab);
		
		TabListener<LiftsListFragment> liftTab = new TabListener<LiftsListFragment>(this, "lifts", LiftsListFragment.class);
		
		Tab LiftsTab = actionBar.newTab()
				.setText("Lifts")
				.setTabListener(liftTab);
		actionBar.addTab(LiftsTab);
		
		
	}
	
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.conditionsmenu, menu);
	    return true;
	  }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.refreshConditions:
	    	RefreshConditions();
	      break;
	    case R.id.action_settings:
    		Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT).show();
			break;
	    case android.R.id.home:
	    	NavUtils.navigateUpFromSameTask(this);
	        break;
	    default:
	      break;
	    }

	    return true;
	  }
	

	private void RefreshConditions() {

		if(trailFragment != null && trailFragment.isVisible()){
			trailFragment.UpdateList();
		}
		if(liftFragment != null && liftFragment.isVisible()){
			liftFragment.UpdateList();
		}
		
		
	}
	
	@Override
	public void onAttachFragment(Fragment fragment){
		super.onAttachFragment(fragment);
		
		if(fragment instanceof LiftsListFragment){
			liftFragment = (LiftsListFragment) fragment;
		}
		else if (fragment instanceof TrailsListFragment){
			trailFragment = (TrailsListFragment) fragment;
		}
		
	}


	public static class TabListener<T extends ListFragment> implements ActionBar.TabListener {
		public Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }
        
        @Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment =  Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }
            
       @Override
       public void onTabUnselected(Tab tab, FragmentTransaction ft) {
           if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }
 
        @Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
	}
}
