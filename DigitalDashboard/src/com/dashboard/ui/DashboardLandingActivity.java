package com.dashboard.ui;


import com.dashboard.ui.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
/**
 * Concrete instantiation of an AbstractDashboardActivity, used for the main dashboard display.
 * @author Levi Lynch
 * 10/25/2011 -Levi: Connected activity to landing.xml for layout.
 *
 *
 */


public class DashboardLandingActivity extends Activity{
    /** 
     *  Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the layout to landing.xml
        setContentView(R.layout.landing);
        final Button button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	setContentView(R.layout.testing);
            }
        });
        
        
    }
       

};







