package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.mongodb.util.JSON;

public class ListHashMapConstructor {

	JSON json;

	public static ArrayList<HashMap<String, String>> generateTimelineListArray(String input, Context context) {

		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		/*
		 * new String[] 
		 * {"user_name", "activity_date",
		 *  "activity_location", "activity_ending", "activity_organism"}, 
		 */
		try {
			JSONArray array = new JSONArray(input);
			HashMap<String, String> map;

			for(int i = 0; i < array.length(); i++) {
				try {

					JSONObject obj = array.getJSONObject(i);
					map = new HashMap<String, String>();
					
					map.put("user_name", "User");
					
					Log.d("date", obj.getString("date"));
					//Date date = new Date(obj.getString("date"));
					new Date(obj.getInt("date"));
					map.put("activity_date", obj.getString("date"));
					Log.d("activityEnding", obj.getString("activityEnding"));
					map.put("activity_ending", obj.getString("activityEnding"));
					
					JSONObject organism = obj.getJSONObject("organism");
					Log.d("organism", organism.getString("specie"));
					map.put("activity_organism", organism.getString("specie"));
					
					
					JSONObject location =  obj.getJSONObject("location");
					Log.d("latPos", location.getString("latPos"));
					Log.d("longPos", location.getString("longPos"));
					Geocoder gcd = new Geocoder(context, Locale.getDefault());
					List<Address> addresses = 
							gcd.getFromLocation(Double.parseDouble((String) location.get("latPos")), 
									Double.parseDouble((String) location.get("longPos")), 1);
					Log.d("ad", addresses.size()+"");
					if (addresses.size() > 0) {
						Log.d("location", ""+addresses.get(0).toString());
					    map.put("activity_location", addresses.get(0).getLocality());
					}
						
					
					listItem.add(map);       
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return listItem;
	}

	public static ArrayList<HashMap<String, String>> generateFriendsListArray(String input) {

		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		/*
		 * new String[] 
		 * {"user_name"}, 
		 */
		try {
			JSONArray array = new JSONArray(input);
			HashMap<String, String> map;

			for(int i = 0; i < array.length(); i++) {
				try {

					JSONObject obj = array.getJSONObject(i);
					map = new HashMap<String, String>();
					map.put("username", obj.getString("user_name"));

					listItem.add(map);               
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return listItem;
	}

	public static ArrayList<HashMap<String, String>> test(String input) {

		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;

		for(int i = 0; i < 5; i++) {
			map = new HashMap<String, String>();
			map.put("username", "Code : "+"user : "+i);
			map.put("activity_date", "Date : "+"date : "+i);
			map.put("activity_location", "Code : "+"location : "+i);
			map.put("activity_ending", "Code : "+"ending : "+i);
			map.put("activity_organism", "Code : "+"organism : "+i);
			result.add(map);
		}


		return result;
	}

}
