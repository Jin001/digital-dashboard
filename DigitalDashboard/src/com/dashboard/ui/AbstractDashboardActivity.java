package com.dashboard.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Abstract class definition for a DashboardActivity; 
 * defines common elements of all application screens.
 * @author Joe Rains
 *
 */
public class AbstractDashboardActivity extends Activity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		super.onCreateOptionsMenu(menu);
		
		// create and set the menu inflater
		getMenuInflater().inflate(R.menu.menu, menu);
		
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
			case R.id.landing:
				startActivity(new Intent(this, DashboardLandingActivity.class));
				break;
			case R.id.main:
				startActivity(new Intent(this, DashboardDisplayActivity.class));
				break;
			case R.id.settings:
				startActivity(new Intent(this, DashboardSettingsActivity.class));
				break;
			default:
				Toast.makeText(this, "Invalid Menu Option", Toast.LENGTH_LONG).show();
		}
		return true;
	}
	
	


}