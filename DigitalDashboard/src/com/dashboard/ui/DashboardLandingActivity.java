package com.dashboard.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Concrete implementation of an AbstractDashboardActivity, used for the main dashboard display.
 * @author Levi Lynch
 * 10/25/2011 -Levi: Connected activity to landing.xml for layout.
 *
 *
 */


public class DashboardLandingActivity extends AbstractDashboardActivity {
    /** 
     *  Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the layout to landing.xml
        setContentView(R.layout.landing);
        
        // create and set the OnClickListener for the Main Display button
        //This Controls the Dashboard button
        Button button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent startButton = new Intent(DashboardLandingActivity.this, DashboardDisplayActivity.class);
               startActivity(startButton);
            }
        });
        
        // create and set the OnClickListener for the Settings button
        //This Controls the Settings Button
        button = (Button) findViewById(R.id.btnSettings);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent settingButton = new Intent(DashboardLandingActivity.this, DashboardSettingsActivity.class);
               startActivity(settingButton);
            }
        });
        // create and set the OnClickListener for the Exit button
        //This Controls the Exit Button
//        button = (Button)this.findViewById(R.id.btnExit);
//        button.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//        	  System.exit(0);
//          }
//       });
        
    }
       

};







