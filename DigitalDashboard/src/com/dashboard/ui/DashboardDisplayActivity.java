package com.dashboard.ui;

import android.os.Bundle;

/**
 * Concrete instantiation of an AbstractDashboardActivity, used for the main dashboard display.
 * @author Joe Rains
 *
 */
public class DashboardDisplayActivity extends AbstractDashboardActivity {

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
