package com.shanewmiller.gorecompanion;

//import com.actionbarsherlock.app.SherlockActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.R.string;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.shanewmiller.gorecompanion.Models.Trail;
import com.shanewmiller.gorecompanion.Models.DetailedConditionsResult;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] TitleArray;
    private HomeFragment homeFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // do all setup of the left navigation drawer
        setupnavdrawer(savedInstanceState);
    }
    
    // get fragment instances to interact with them anytime
    @Override
	public void onAttachFragment(Fragment fragment){
		super.onAttachFragment(fragment);
		
		if(fragment instanceof HomeFragment){
			homeFragment = (HomeFragment) fragment;
		}
//		else if (fragment instanceof TrailsListFragment){
//			trailFragment = (TrailsListFragment) fragment;
//		}
	}
    
    public void refreshCurrentFragment(){
    	if(homeFragment != null && homeFragment.isVisible()){
    		homeFragment.refresh();
		}
    }
    
    // ----------------------------------------------------------------------
    // Main Menu Config
    // ----------------------------------------------------------------------
    @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.conditionsmenu, menu);
	    return true;
	  }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		  if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
		  }
		  switch (item.getItemId()) {
		  case R.id.refreshConditions:
			  refreshCurrentFragment();
			  break;
		  case R.id.action_settings:
	    	  Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
	    	  	.show();
	    	  break;
		  default:
			  break;
		  }
		  return true;
	  }
    
    // ----------------------------------------------------------------------
    // Navigation Drawer Config
    // ----------------------------------------------------------------------
    
    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
 
    
    // setup of the left navigation drawer
    public void setupnavdrawer(Bundle savedInstanceState){
   	
    	// Set the title list for nav drawer
        TitleArray = getResources().getStringArray(R.array.navdrawerlist);
        ListAdapter adapter = new ArrayAdapter<String>(this,R.layout.nav_drawer_item,R.id.navDrawerTitle,TitleArray);
        mDrawerList = (ListView)findViewById(R.id.list_left_drawer);
        mDrawerList.setAdapter(adapter);
        
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerToggle = new ActionBarDrawerToggle(
        		this, 
        		mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        )
        {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(getTitle());
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(getTitle());
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }          
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
      super.onPostCreate(savedInstanceState);
      // Sync the toggle state after onRestoreInstanceState has occurred.
      mDrawerToggle.syncState();
    }
 
    
     /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
    	Fragment fragment = null;
        switch (position) {
        case 0:
    		fragment = new HomeFragment(); 
    		break;
        case 1:
        	this.startActivity(new Intent(this, ConditionsActivity.class));
            break;
        default:
            break;
        }
 
        if (fragment != null) {
        	mDrawerLayout.closeDrawer(mDrawerList);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void gotoconditions(View view){
        Intent intent = new Intent(this, ConditionsActivity.class);
        startActivity(intent);
    }

    // method to create a  toast, takes in a string to display
    public void toaster(String textstring){
    	Context context = getApplicationContext();
    	CharSequence text = textstring;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }

    
}
