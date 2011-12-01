package com.dashboard.ui;

import com.dashboard.dto.HistoryDTO;
import com.dashboard.service.DashboardService;
import com.dashboard.service.IDashboardService;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Concrete implementation of an AbstractDashboardActivity, used for the main
 * dashboard display.
 * 
 * @author MazanSM
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

		// load history from the database
		loadHistory();

		// set the Trip and Max speed History reset button
		Button submitReset = (Button) findViewById(R.id.resetHistoryButton);
		submitReset.setOnClickListener(new HistoryResetListener(this));

	}

	private void loadHistory() {
		try {

			// try to get the history from the service
			IDashboardService service = new DashboardService(this);
			HistoryDTO history = service.getHistory();

			if (history != null) {

				// tripometer
				TextView tripometer = (TextView) findViewById(R.id.tripometerDisplay);
				tripometer.setText(String.valueOf(history.getDistance()));

				// max speed
				TextView maxSpeed = (TextView) findViewById(R.id.maxVelocityDisplay);
				maxSpeed.setText(String.valueOf(history.getMaxSpeed()));
			}

		} catch (Exception e) {
			Toast.makeText(this,
					"An error occurred loading history: " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Resets the history by adding a record with zeros.<br/>
	 * Inner class implementation of an OnClickListener used only for testing
	 * persistence.
	 * 
	 * @author MazanSM
	 * 
	 */
	class HistoryResetListener implements OnClickListener {

		private Context context;

		/**
		 * This class requires a Context to access the database
		 * 
		 * @param context
		 */

		public HistoryResetListener(Context context) {
			this.context = context;
		}

		@Override
		public void onClick(View v) {
			IDashboardService service = new DashboardService(this.context);
			try {

				// add a record with zeroes
				service.resetHistory();

				// reset the tripometer display
				TextView tripometer = (TextView) findViewById(R.id.tripometerDisplay);
				tripometer.setText("0");

				// reset the max speed display
				TextView maxSpeed = (TextView) findViewById(R.id.maxVelocityDisplay);
				maxSpeed.setText("0");

			} catch (Exception e) {

				// display the exception
				Toast.makeText(this.context,
						"An error occurred: " + e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

		}

	}

}
