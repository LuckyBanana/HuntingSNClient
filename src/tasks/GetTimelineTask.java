package tasks;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import utils.ListHashMapConstructor;

import com.example.huntingsnclient.R;

import httpclient.RESTHTTPClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GetTimelineTask extends AsyncTask<String, String, String> {

	private final WeakReference<ListView> listViewreference;
	private final WeakReference<Activity> activityReference;

	public GetTimelineTask(Activity activity, ListView listView) {
		listViewreference = new WeakReference<ListView>(listView);
		activityReference = new WeakReference<Activity>(activity);
	}

	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return RESTHTTPClient.get(uri[0]);
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
		else if(listViewreference != null && activityReference != null) {

			//ArrayList<HashMap<String, String>> data = ListHashMapConstructor.generateTimelineListArray(result);
			ArrayList<HashMap<String, String>> data = ListHashMapConstructor.test(result);

			ListView listView = listViewreference.get();
			Activity activity = activityReference.get();
			SimpleAdapter mSchedule = new SimpleAdapter (
					activity.getBaseContext(),
					data, 
					R.layout.activity_item,
					new String[] {"user_name", "activity_date", "activity_location", "activity_ending", "activity_organism"}, 
					new int[] {
						R.id.user_name,
						R.id.activity_date, 
						R.id.activity_location, 
						R.id.activity_ending, 
						R.id.activity_organism,}
					);
			listView.setAdapter(mSchedule);
		}

	}


}
