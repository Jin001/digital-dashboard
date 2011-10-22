package com.dashboard.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * Abstract class definition for a DashboardActivity; 
 * defines common elements of all application screens.
 * @author Joe Rains
 *
 */
public class AbstractDashboardActivity extends Activity {
	
	
    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the layout to main.xml
        setContentView(R.layout.main);
    }
}