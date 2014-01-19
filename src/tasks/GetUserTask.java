package tasks;

import httpclient.RESTHTTPClient;
import android.os.AsyncTask;
import android.util.Log;

public class GetUserTask extends AsyncTask<String, String, String> {
	
	//base_url/users/:user_id

	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return RESTHTTPClient.get(uri[0]);
	}
	
	@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // TODO
        Log.d("res", result);
    }


}
