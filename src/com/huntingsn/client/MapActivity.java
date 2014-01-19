package com.huntingsn.client;

//import android.app.Activity;
import java.util.ArrayList;

import com.example.huntingsnclient.R;
import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	private CameraPosition cameraPos; //current camera position
	private final static String TAG = MapActivity.class.getName();
	private final static LatLng defaultCurrentLocation = new LatLng(46.4584676, 2.4623584);
	private final static String SharedPrefCameraName = "huntingMapCameraPosition";
	private ArrayList<MarkerOptions> positions = null;
	//private LatLng myPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        saveCameraPref();
    }
    
    
    
    
    private void saveCameraPref() {
		//save camera position 
    	cameraPos = mMap.getCameraPosition();
    	
		SharedPreferences.Editor editor = getSharedPreferences(SharedPrefCameraName, 0).edit();    	 
		editor.putFloat("longitude", (float) cameraPos.target.longitude);
		editor.putFloat("latitude", (float) cameraPos.target.latitude);
		editor.putFloat("zoom", cameraPos.zoom);
		editor.putFloat("bearing", cameraPos.bearing);
		editor.putFloat("tilt", cameraPos.tilt);
		editor.commit();
    }
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
        	mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
            	mMap.setMyLocationEnabled(true);
            	if (cameraPos != null) {
                	Log.e(TAG, "resume camera pos "+cameraPos.toString());
                	mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
                } else {
	            	SharedPreferences settings = getSharedPreferences(SharedPrefCameraName, 0);
	            	LatLng currentPos = this.getCurrentLocation();
	            	Log.e(TAG, "current position " + currentPos.toString());
	            	double longitude = (double) settings.getFloat("longitude", (float) currentPos.longitude);
	            	double latitude = (double) settings.getFloat("latitude", (float) currentPos.latitude);
	            	float zoom = settings.getFloat("zoom", 5);
	            	float bearing = settings.getFloat("bearing", 0);
	            	float tilt = settings.getFloat("tilt", 0);
	            	
	            	CameraPosition cameraPosition = new CameraPosition.Builder()
	            	.target(new LatLng(latitude, longitude))      // Sets the center of the map to Mountain View
	            	.zoom(zoom)                   // Sets the zoom
	            	.bearing(bearing)                // Sets the orientation of the camera to east
	            	.tilt(tilt)                   // Sets the tilt of the camera 
	            	.build();                  // Creates a CameraPosition from the builder
	            	
	            	mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                setUpMap();
            } else {
            	Toast.makeText(getApplicationContext(), R.string.unableToShowMap, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        ArrayList<MarkerOptions> positions = this.getPositions();
        for (MarkerOptions position : positions) {
        	position.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        	position.draggable(false);
        	mMap.addMarker(position);
        }
    }
    
    private ArrayList<MarkerOptions> getPositions() {
    	if (this.positions == null) {
    		//fake positions, retrive from user account
    		this.positions = new ArrayList<MarkerOptions>();
	    	
    		this.positions.add(new MarkerOptions().position(new LatLng(43.6219425, 3.8960743)).title("Marker 1"));
	    	
    		this.positions.add(new MarkerOptions().position(new LatLng(43.8442165, 4.3629932)).title("Marker 2"));
	    	
    		this.positions.add(new MarkerOptions().position(new LatLng(44.3746998, 2.5722217)).title("Rodez"));
	    	
    		this.positions.add(new MarkerOptions().position(new LatLng(43.5204551, 4.1652393)).title("Aigues-Mortes"));
    	}
    	return this.positions;
    	
    }
    
    private LatLng getCurrentLocation() {
    	Criteria criteria = new Criteria();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) 
        	location = mMap.getMyLocation();
        if (location != null) 
        	return new LatLng(location.getLatitude(), location.getLongitude());
        return defaultCurrentLocation;
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	saveCameraPref();
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
    
    
}