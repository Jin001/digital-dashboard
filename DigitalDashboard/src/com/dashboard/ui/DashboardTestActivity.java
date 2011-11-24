package com.dashboard.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dashboard.dto.HistoryDTO;
import com.dashboard.service.DashboardService;
import com.dashboard.service.IDashboardService;

/**
 * A testing activity created to test persistence development.
 * @author Joe Rains
 *
 */
public class DashboardTestActivity extends AbstractDashboardActivity {


    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the layout to main.xml
        setContentView(R.layout.testing);
        
        // set the history test button
        Button testSubmit = (Button)findViewById(R.id.testHistorySubmit);
        testSubmit.setOnClickListener(new TestHistorySubmitListener(this));
        
        // set the history reset button
        testSubmit = (Button)findViewById(R.id.testHistoryReset);
        testSubmit.setOnClickListener(new TestHistoryResetListener(this));
        
//        // set the settings test button
//        testSubmit = (Button)findViewById(R.id.testSettingsSubmit);
//        testSubmit.setOnClickListener(new TestSettingsSubmitListener(this));
    }
    
    
    /**
     * Resets the history by adding a record with zeroes.<br/>
     * Inner class implementation of an OnClickListener used only for testing persistence.
     * @author Joe Rains
     *
     */
    class TestHistoryResetListener implements OnClickListener {

    	private Context context;
    	
    	/**
    	 * This class requires a Context to access the database
    	 * @param context
    	 */
    	public TestHistoryResetListener(Context context) {
    		this.context = context;
    	}
    	
    	@Override
		public void onClick(View v) {
			IDashboardService service = new DashboardService(this.context);
			try {
				// add a record with zeroes
				((DashboardService)service).resetHistory();
				
				// reset the tripometer display
				TextView tripometer = (TextView)findViewById(R.id.tripometerDisplay);
	    		tripometer.setText("0");
				
				// reset the max speed dislay
				TextView maxSpeed = (TextView)findViewById(R.id.maxVelocityDisplay);
				maxSpeed.setText("0");
				
			} catch (Exception e) {
				
				// display the exception
				Toast.makeText(this.context, "An error occurred: "+e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
		}
    	
    }
        
//    /**
//     * Tests the settings persistence mechanisms.<br/>
//     * Inner class implementation of an OnClickListener used only for testing persistence.
//     * @author Joe Rains
//     *
//     */
//    class TestSettingsSubmitListener implements OnClickListener {
//
//    	private Context context;
//    	
//    	/**
//    	 * This class requires a Context to access the database
//    	 * @param context
//    	 */
//    	public TestSettingsSubmitListener(Context context) {
//    		this.context = context;
//    	}
//    	
//		@Override
//		public void onClick(View arg0) {
//			
//			// get the background mode
//			RadioGroup bgModeGroup = (RadioGroup)findViewById(R.id.backgroundModeGroup);
//			RadioButton rb = (RadioButton)findViewById(bgModeGroup.getCheckedRadioButtonId());
//			String bgMode = rb.getText().toString();
//			
//			// get the travel mode
//			RadioGroup travelModeGroup = (RadioGroup)findViewById(R.id.travelModeGroup);
//			rb = (RadioButton)findViewById(travelModeGroup.getCheckedRadioButtonId());
//			String travelMode = rb.getText().toString().replaceAll("\\s", "");
//			
//			// get the units
//			RadioGroup unitsGroup = (RadioGroup)findViewById(R.id.unitsGroup);
//			rb = (RadioButton)findViewById(unitsGroup.getCheckedRadioButtonId());
//			String units = rb.getText().toString();
//			
//			// set the settings object
//			SettingsDTO settings = new SettingsDTO();
//			settings.setBackgroundMode(BackgroundMode.valueOf(bgMode));
//			settings.setTravelMode(TravelMode.valueOf(travelMode));
//			settings.setUnits(Units.valueOf(units));
//			
//			try {
//				
//				// update the settings
//				IDashboardService service = new DashboardService(this.context);
//				service.updateSettings(settings);
//				
//				// display the settings from the database
//				settings = service.getSettings();
//				StringBuilder msg = new StringBuilder("The current settings are:\r\n\r\n");
//				msg.append(settings.getBackgroundMode().name()+"\r\n");
//				msg.append(settings.getTravelMode().name()+"\r\n");
//				msg.append(settings.getUnits().name());
//				Toast.makeText(this.context, msg.toString(), Toast.LENGTH_LONG).show();
//				
//			} catch (Exception e) {
//				
//				// display the exception
//				Toast.makeText(this.context, "An error occurred: "+e.getMessage(), Toast.LENGTH_LONG);
//			}
//		}
//    	
//    }
    
    
    /**
     * Tests the history persistence mechanisms.<br/>
     * Inner class implementation of an OnClickListener used only for testing persistence.
     * @author Joe Rains
     *
     */
    class TestHistorySubmitListener implements OnClickListener {
    	
    	private Context context;
    	
    	/**
    	 * Constructor for a TestHistorySubmitListener.
    	 * This class requires a Context to access the database.
    	 * @param context the calling Activity
    	 */
    	public TestHistorySubmitListener(Context context) {
    		this.context = context;
    	}
    	
    	@Override
    	public void onClick(View v) {
    		
    		// create a dashboard service
    		IDashboardService service = new DashboardService(this.context);    		
    		
    		// get the current speed
    		int speed = service.getCurrentSpeed();
    		char[] digits = String.valueOf(speed).toCharArray();
    		
    		// set the tens digit
    		int tensDigit = digits.length == 1 ? 0 : Integer.parseInt(digits[0]+"");
    		ImageView tensImage = (ImageView)findViewById(R.id.tensDigit);
    		setImageSource(tensDigit, tensImage);
    		
    		// set the ones digit
    		int onesDigit = digits.length == 1 ? Integer.parseInt(digits[0]+"") : Integer.parseInt(digits[1]+"");
    		ImageView onesImage = (ImageView)findViewById(R.id.onesDigit);
    		setImageSource(onesDigit, onesImage);
    		
    		// get the distance traveled
    		int distance = service.getCurrentDistance();
    		
    		// update the history
	    	HistoryDTO history = null;
    		try {
	    		history = new HistoryDTO();
	    		history.setDistance(distance);
	    		history.setMaxSpeed(speed);
	    		service.updateHistory(history);
	    		
			} catch (Exception e) {
				Toast.makeText(this.context, "An error occurred: "+e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			// set the new screen values
			try {
				// get the current history
				history = service.getHistory();
					    		
				// tripometer
				TextView tripometer = (TextView)findViewById(R.id.tripometerDisplay);
	    		tripometer.setText(String.valueOf(history.getDistance()));
				
				// max speed
				TextView maxSpeed = (TextView)findViewById(R.id.maxVelocityDisplay);
				maxSpeed.setText(String.valueOf(history.getMaxSpeed()));
				
				// show a message if a new high speed has been reached
				if (speed == history.getMaxSpeed()) {
					Toast.makeText(this.context, "Max Speed Achieved!", Toast.LENGTH_LONG).show();
				}
				
			} catch (Exception e) {
				
				// show the exception
				Toast.makeText(this.context, "An error occurred: "+e.getMessage(), Toast.LENGTH_LONG).show();
			}
    	}
    	
    	
    	/**
    	 * Sets the image resource based on the int passed.
    	 * @param i the int value to display
    	 * @param img the android image object to set
    	 */
    	private void setImageSource(int i, ImageView img) {
    		// set the appropriate id value
    		switch (i) {
	    		case 0:
	    			img.setImageResource(R.drawable.digit0);
	    			break;
	    		case 1:
	    			img.setImageResource(R.drawable.digit1);
	    			break;
	    		case 2:
	    			img.setImageResource(R.drawable.digit2);
	    			break;
	    		case 3:
	    			img.setImageResource(R.drawable.digit3);
	    			break;
	    		case 4:
	    			img.setImageResource(R.drawable.digit4);
	    			break;
	    		case 5:
	    			img.setImageResource(R.drawable.digit5);
	    			break;
	    		case 6:
	    			img.setImageResource(R.drawable.digit6);
	    			break;
	    		case 7:
	    			img.setImageResource(R.drawable.digit7);
	    			break;
	    		case 8:
	    			img.setImageResource(R.drawable.digit8);
	    			break;
	    		case 9:
	    			img.setImageResource(R.drawable.digit9);
	    			break;
	    		default:
	    			img.setImageResource(R.drawable.digit0);
    		}
    	}
    }
}
