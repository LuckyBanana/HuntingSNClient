package com.example.huntingsnclient;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import pickers.DateTimePickerFragment;
import pickers.DateTimePickerFragment.OnDateTimeDialogResult;
import tasks.NewActivityTask;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewActivity extends Activity {
	
	
	private String userId;


	private Spinner sector_spinner;
	private Spinner ending_spinner;
	private Spinner sex_spinner;
	private Spinner amount_spinner;
	private TextView date_textview;
	private EditText organism_edittext;
	private Button validate_button;

	private Map<String, Integer> dateFragementValues = new HashMap<String, Integer>();
	private Map<String, Object> newActivityValues = new HashMap<String, Object>();
	private Map<String, Double> locationValues = new HashMap<String, Double>();
	
	private boolean doubleBackToExitPressedOnce = false;
	
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		
		//savedInstanceState.getCharSequence("userId");
		
		organism_edittext = (EditText)findViewById(R.id.organism_edittext);
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		spinnersInit();
		dateTextviewInit();
		gpsButtonInit();
				
	}
	
	@Override
	protected void onResume(){
		gpsButtonInit();
	}
	
	private void gpsButtonInit() {
		
		validate_button = (Button)findViewById(R.id.validate_activity_button);
		
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            validate_button.setText("Save Activity");
            //validate_button.setBackgroundColor(Color.GRAY);
            validate_button.setEnabled(false);
            validate_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(validateForm()) {
						new NewActivityTask(FullStringMapBuiler.build(newActivityValues)).execute("userId"+"/add");
					}
				}
			});
            
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
				
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onLocationChanged(Location location) {
					// TODO Auto-generated method stub
					validate_button.setEnabled(true);
					locationValues.clear();
					locationValues.put("latitude", location.getLatitude());
					locationValues.put("longitude", location.getLongitude());
				}
			});
            
        }else{
            //validate_button.setBackgroundColor(Color.GREEN);
            validate_button.setText("Enable GPS");
            validate_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Press back once the gps is enabled", Toast.LENGTH_SHORT).show();
					Intent callGPSSettingIntent = new Intent(
	                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                startActivity(callGPSSettingIntent);
				}
			});
        }
	}

	private void spinnersInit() {

		sector_spinner = (Spinner)findViewById(R.id.sector_spinner);
		ending_spinner = (Spinner)findViewById(R.id.ending_spinner);
		sex_spinner = (Spinner)findViewById(R.id.sex_spinner);
		amount_spinner = (Spinner)findViewById(R.id.amount_spinner);

		ArrayAdapter<CharSequence> sector_adapter = ArrayAdapter.createFromResource(this,
				R.array.sector_array, android.R.layout.simple_spinner_item);
		sector_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sector_spinner.setAdapter(sector_adapter);

		ArrayAdapter<CharSequence> ending_adapter = ArrayAdapter.createFromResource(this,
				R.array.ending_array, android.R.layout.simple_spinner_item);
		ending_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ending_spinner.setAdapter(ending_adapter);

		ArrayAdapter<CharSequence> sex_adapter = ArrayAdapter.createFromResource(this,
				R.array.sex_array, android.R.layout.simple_spinner_item);
		sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sex_spinner.setAdapter(sex_adapter);

		ArrayAdapter<CharSequence> amount_adapter = ArrayAdapter.createFromResource(this,
				R.array.amount_array, android.R.layout.simple_spinner_item);
		amount_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		amount_spinner.setAdapter(amount_adapter);
	}

	private void dateTextviewInit() {
		date_textview = (TextView) findViewById(R.id.activity_date_textview);

		
		final Calendar c = Calendar.getInstance();
        
        dateFragementValues.put("day", c.get(Calendar.DAY_OF_MONTH));
        dateFragementValues.put("month", c.get(Calendar.MONTH));
        dateFragementValues.put("year", c.get(Calendar.YEAR));
        dateFragementValues.put("hour", c.get(Calendar.HOUR_OF_DAY));
        dateFragementValues.put("minute", c.get(Calendar.MINUTE));
		
        updateTimeLabel(dateFragementValues);

		date_textview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DateTimePickerFragment newFragment = new DateTimePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
				newFragment.setDialogResult(new OnDateTimeDialogResult() {
					@Override
					public void finish(Map<String, Integer> result) {
						dateFragementValues = result;
						updateTimeLabel(dateFragementValues);
					}
				});
			}
		});
	}
	
	private void updateTimeLabel(Map<String, Integer> values) {
		date_textview.setText(dateFragementValues.get("day")+
				"/"+dateFragementValues.get("month")+
				"/"+dateFragementValues.get("year")+
				" - "+dateFragementValues.get("hour")+
				":"+dateFragementValues.get("minute"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*case R.id.save_activity:
			if (validateForm()) {
				
			}
			else {
				
			}
			//Intent intent = new Intent(TimelineActivity.this, AddNewActivity.class);
			//startActivityForResult(intent, 1);
			return true;*/
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
             doubleBackToExitPressedOnce=false;   

            }
        }, 2000);
    } 
	
	public boolean validateForm() {
		
		
		if(organism_edittext.getText().toString().length() == 0) {
			organism_edittext.setError("You must indicate a valid organism.");
			return false;
		}
		else  {
			newActivityValues.clear();
			newActivityValues.put("sector", sector_spinner.getSelectedItem().toString());
			newActivityValues.put("ending", ending_spinner.getSelectedItem().toString());
			newActivityValues.put("organism", organism_edittext.getText().toString());
			newActivityValues.put("sex", sex_spinner.getSelectedItem().toString());
			newActivityValues.putAll(dateFragementValues);
			newActivityValues.putAll(locationValues);
			return true;
		}
	}
	

	public static class FullStringMapBuiler {

		private static Map<String, String> result = new HashMap<String, String>();

		public static Map<String, String> build(Map<String, Object> input) {
			for(String s : input.keySet()) {
				result.put(s, input.get(s).toString());
			}
			return result;
		}

	}

}
