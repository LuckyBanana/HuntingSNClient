package loopj;

import java.lang.ref.WeakReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.example.huntingsnclient.R;
import com.huntingsn.client.MainActivity;
import com.huntingsn.client.TimelineActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RESTClientUsage {

	private JSONArray jsonArray = new JSONArray();
	private String result ="";
	private WeakReference<Activity> activityReference;
	private WeakReference<EditText> passwordReference;



	public RESTClientUsage(WeakReference<Activity> ref) {
		activityReference = ref;
	}
	
	public RESTClientUsage(WeakReference<Activity> activityRef, WeakReference<EditText> passwordRef) {
		activityReference = activityRef;
		passwordReference = passwordRef;
	}
	

	public String login(String email, String password) {
		
		final RequestParams rp = new RequestParams();
		final Activity activity = activityReference.get();
		final EditText mPasswordView = passwordReference.get();
		
		rp.add("email", email);
		rp.add("password", password);

				RESTClient.post("login", rp, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray response) {
						JSONObject firstObject;
						String result = "";
						try {
							setJsonArray(response);
							for(int i = 0; i < response.length(); i++){
								firstObject = (JSONObject) response.get(0);
								result += firstObject.toString();
							}
							Log.d("rslt", result);
							setResult(result);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Intent intent = new Intent(activity, TimelineActivity.class);
						intent.putExtra(MainActivity.USER_ID, result);
						activity.startActivity(intent);
					}
					
					@Override
					public void onFailure(Throwable e){
						mPasswordView
						.setError(activity.getBaseContext().getString(R.string.error_incorrect_password));
						mPasswordView.requestFocus();
					}
					
					@Override
					public void onFinish() {
						
					}
				});

		return getResult();
	}

	/*
	 * GET & SET
	 */

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
