package maps;

import java.lang.ref.WeakReference;

import tasks.GetPersonalMarkersTask;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.huntingsnclient.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class StatsMapActivity extends Activity {

	// Google Map
    private GoogleMap googleMap;
    private String userId;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        userId = getIntent().getStringExtra("statId");
        
        try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.mapView)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        R.string.unable_show_map, Toast.LENGTH_SHORT)
                        .show();
            }
            else {
            	CameraPosition position =
            			new CameraPosition.Builder()
            				.target(new LatLng(46.604167, 2.567368))
            				.zoom(5.0f)
            				.build();
            	googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
            	placeMarkers();
            }
        }
    }
    
    public void placeMarkers() {
    	new GetPersonalMarkersTask(new WeakReference<GoogleMap>(googleMap), new WeakReference<Activity>(this)).execute(userId);
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
}