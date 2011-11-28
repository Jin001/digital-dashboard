package com.dashboard.ui;

import com.dashboard.dto.BackgroundMode;
import com.dashboard.dto.SettingsDTO;
import com.dashboard.dto.TravelMode;
import com.dashboard.dto.Units;
import com.dashboard.service.DashboardService;
import com.dashboard.service.IDashboardService;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Concrete instantiation of an AbstractDashboardActivity, used for displaying
 * and editing application settings.
 * 
 * @author MazanSM
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

		// load the settings from the database
		loadSavedSettings();

		// set the settings test button
		Button saveSettings = (Button) findViewById(R.id.SaveSettingsButton);
		saveSettings.setOnClickListener(new SaveSettingsSubmitListener(this));

	}

	/**
	 * Loads the settings already stored in the database.
	 */
	private void loadSavedSettings() {

		try {
			// display the settings from the database
			IDashboardService service = new DashboardService(this);
			SettingsDTO settings = service.getSettings();

			// set the background mode
			int id = settings.getBackgroundMode().equals(BackgroundMode.Day) ? R.id.bgModeDay
					: R.id.bgModeNight;
			RadioButton rb = (RadioButton) findViewById(id);
			rb.setChecked(true);

			// set the travel mode
			id = settings.getTravelMode().equals(TravelMode.Bike) ? R.id.travelBike
					: settings.getTravelMode().equals(TravelMode.Walk) ? R.id.travelWalk
							: R.id.travelMotor;
			rb = (RadioButton) findViewById(id);
			rb.setChecked(true);

			// set the units
			id = settings.getUnits().equals(Units.US) ? R.id.USUnits
					: R.id.MetricUnits;
			rb = (RadioButton) findViewById(id);
			rb.setChecked(true);

		} catch (Exception e) {
			Toast.makeText(this,
					"An error occurred loading settings: " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	class SaveSettingsSubmitListener implements OnClickListener {

		private Context context;

		/**
		 * This class requires a Context to access the database
		 * 
		 * @param context
		 */
		public SaveSettingsSubmitListener(Context context) {
			this.context = context;
		}

		@Override
		public void onClick(View v) {

			// get the background mode
			RadioGroup bgModeGroup = (RadioGroup) findViewById(R.id.setBackgroundModeGroup);
			RadioButton rb = (RadioButton) findViewById(bgModeGroup
					.getCheckedRadioButtonId());
			String bgMode = rb.getText().toString();

			// get the travel mode
			RadioGroup travelModeGroup = (RadioGroup) findViewById(R.id.setTravelModeGroup);
			rb = (RadioButton) findViewById(travelModeGroup
					.getCheckedRadioButtonId());
			String travelMode = rb.getText().toString().replaceAll("\\s", "");

			// get the units
			RadioGroup unitsGroup = (RadioGroup) findViewById(R.id.setUnitsGroup);
			rb = (RadioButton) findViewById(unitsGroup
					.getCheckedRadioButtonId());
			String units = rb.getText().toString();

			// set the settings object
			SettingsDTO settings = new SettingsDTO();
			settings.setBackgroundMode(BackgroundMode.valueOf(bgMode));
			settings.setTravelMode(TravelMode.valueOf(travelMode));
			settings.setUnits(Units.valueOf(units));

			try {

				// update the settings
				IDashboardService service = new DashboardService(this.context);
				service.updateSettings(settings);

				// display the settings from the database
				settings = service.getSettings();
				StringBuilder msg = new StringBuilder(
						"The current settings are:\r\n\r\n");
				msg.append(settings.getBackgroundMode().name() + "\r\n");
				msg.append(settings.getTravelMode().name() + "\r\n");
				msg.append(settings.getUnits().name());
				Toast.makeText(this.context, msg.toString(), Toast.LENGTH_LONG)
						.show();

			} catch (Exception e) {

				// display the exception
				Toast.makeText(this.context,
						"An error occurred: " + e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

		}
	}

}
