package httpclient;

import tasks.PostTask;


public class RESTHTTPClientImpl {
	
	private String result;
	
	
	public String getTimeline(final String userId) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setResult(RESTHTTPClient.get(userId+"/timeline"));
			}
			
		}).run();
		
		/*
		String result = "";
		new GetTask().execute(userId);
		return result;
		
		*/
		
		return getResult();
		
	}
	
	public String login(String userName, String password) {
		String loginUrl = "http...";
		/*
		 * TODO Verifier les query strings.
		 */
		new PostTask().execute(loginUrl, "username", userName, "password", password);
		return "";
	}
	
	
	private void setResult(String result) {
		this.result = result;
	}
	
	private String getResult() {
		return this.result;
	}

}
