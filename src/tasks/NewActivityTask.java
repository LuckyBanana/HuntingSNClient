package tasks;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

public class NewActivityTask extends AsyncTask<String, String, String> {
	
	private Map<String, String> parameters = new HashMap<String, String>();
	
	public NewActivityTask(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String uri = params[0];
		//HashMap<String, String> parameters = new HashMap<String, String>();
		int i = 1;
		while(i+1 < params.length) {
			parameters.put(params[i], params[i+1]);
			i = i+2;
		}
		return "";//RESTHTTPClient.post(params[0]);
		
	}
	
	@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // TODO
        
    }


}
