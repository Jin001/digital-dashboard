package com.dashboard.ui;

import android.os.Bundle;

/**
 * Concrete instantiation of an AbstractDashboardActivity,
 * used for displaying and editing application settings.
 * @author Joe Rains
 *10/25/2011 -Levi: Connected activity to setting.xml for layout. 
 *
 *
 *
 */
public class DashboardSettingsActivity extends AbstractDashboardActivity {

    /** 
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the layout to settings.xml
        setContentView(R.layout.settings);
    }
}
