package tasks;

import httpclient.RESTHTTPClient;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;

public class NewActivityTask extends AsyncTask<String, String, String> {
	
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private WeakReference<Activity> activityRef;
	
	/*
	 *  
	 */
	
	public NewActivityTask(HashMap<String, String> parameters, WeakReference<Activity> ref) {
		this.parameters = parameters;
		this.activityRef = ref;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String uri = params[0];

		return RESTHTTPClient.post(params[0], parameters);
		
	}
	
	@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // TODO
        activityRef.get().finish();
    }


}
