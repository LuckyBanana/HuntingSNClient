package tasks;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import utils.ListHashMapConstructor;

import com.example.huntingsnclient.R;

import httpclient.RESTHTTPClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GetFriendsTask extends AsyncTask<String, String, String> {

	private final WeakReference<ListView> listViewreference;
	private final WeakReference<Activity> activityReference;
	
	public GetFriendsTask(Activity activity, ListView listView) {
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
			ArrayList<HashMap<String, String>> data = ListHashMapConstructor.generateFriendsListArray(result);

			ListView listView = listViewreference.get();
			Activity activity = activityReference.get();
			SimpleAdapter mSchedule = new SimpleAdapter (
					activity.getBaseContext(),
					data, 
					R.layout.friend_item,
					new String[] {"fullName"}, 
					new int[] {
						R.id.friend_name,}
					);
			listView.setAdapter(mSchedule);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
				}
			});
		}

	}


}
