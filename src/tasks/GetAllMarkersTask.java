package tasks;

import httpclient.RESTHTTPClient;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import utils.ListHashMapConstructor;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.huntingsnclient.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GetAllMarkersTask extends AsyncTask<String, String, String> {
	
	private final WeakReference<GoogleMap> mapReference;
	private final WeakReference<Activity> activityReference;
	
	public GetAllMarkersTask(WeakReference<GoogleMap> gMapRef, WeakReference<Activity> activityRef) {
		mapReference = gMapRef;
		activityReference = activityRef;
	}
	
	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return RESTHTTPClient.get("users/"+uri[0]+"/timeLine");
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		/*
		 * TODO result = json
		 * json -> arraylist<hashmap>
		 */

		// TODO
		if(isCancelled()) {

		}
		else if(true) {
			
			ArrayList<HashMap<String, String>> data = 
					ListHashMapConstructor.generateMarkersArray(result);
			GoogleMap gMap = mapReference.get();
			for(HashMap<String, String> map : data) {
				Log.d("orga", map.get("organism"));
				MarkerOptions marker = new MarkerOptions()
				.position(new LatLng(
								Double.parseDouble(map.get("latitude")),
								Double.parseDouble(map.get("longitude"))))
				.title(map.get("organism"));
				
				String sector = map.get("sector");
				if(sector.contains("hunting")) {
					marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
					marker.snippet(activityReference.get().getBaseContext().getString(R.string.hunting));
				}
				else if(sector.contains("fishing")) {
					marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
					marker.snippet(activityReference.get().getBaseContext().getString(R.string.fishing));
				}
				else if(sector.contains("picking")){
					marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
					marker.snippet(activityReference.get().getBaseContext().getString(R.string.picking));
				}
				gMap.addMarker(marker);
				
			}

		}

	}

}
