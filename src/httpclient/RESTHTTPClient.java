package httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class RESTHTTPClient {

	public final static String baseUrl = "http://vita-instinct.herokuapp.com/";

	public RESTHTTPClient() {


	}

	public static String get(String requestUrl) {
		URL url;
		String result = "";

		try {
			//url = new URL(baseUrl+requestUrl);
			url = new URL("http://www.google.fr/");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			result = readStream(in);
			urlConnection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(result != null) {
			Log.d("HTTP GET", result);
		}
		return result;

	}

	public static String post(String requestUrl, HashMap<String, String> params) {
		URL url;
		String result = "";

		if(params != null) {

			try {
				url = new URL(baseUrl+requestUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				OutputStream out = connection.getOutputStream();
				writeStream(out, ParamsStringBuilder.build(params));

				InputStream in = new BufferedInputStream(connection.getInputStream());
				result = readStream(in);

				connection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {

		}

		return result;
	}

	private static String readStream(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();  
		BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);  
		for (String line = r.readLine(); line != null; line =r.readLine()){  
			sb.append(line);  
		}  
		is.close();  
		return sb.toString();
	}

	private static void writeStream(OutputStream out, String params) {
		OutputStreamWriter writer = new OutputStreamWriter(out);
		try {
			writer.write("nom=dupont");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static class ParamsStringBuilder {

		Map<String, String> params = new HashMap<String, String>();

		public ParamsStringBuilder(HashMap<String, String> params) {
			this.params = params;
		}

		/*
		 * RETURNS key1=value1&key2=value2&...
		 */
		public static String build(HashMap<String, String> params) {
			String result = "";
			for(String s : params.keySet()) {
				result += s+"="+params.get(s)+"&";
			}
			return result.substring(0,result.length()-1);

		}
	}

}
