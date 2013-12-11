package httpclient;

import java.util.concurrent.CountDownLatch;

import android.util.Log;

public class RESTHTTPClientAsync {
	
	private String result;
	
	
	public String getTimeline(final String userId) {
		Log.d("Async", "1");
		String result = "";
		final CountDownLatch signal = new CountDownLatch(1);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.d("Async", "run");
				// TODO Auto-generated method stub
				//setResult(RESTHTTPClient.get(userId+"timeline"));
				setResult(RESTHTTPClient.get(""));
				signal.countDown();
			}
			
		}).run();
		Log.d("Async", "2");
		return getResult();
	}
	
	
	private void setResult(String result) {
		this.result = result;
	}
	
	private String getResult() {
		return this.result;
	}

}
