package utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;

public class ListHashMapConstructor {

	JSON json;

	public static ArrayList<HashMap<String, String>> generateTimelineListArray(String input) {
		
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
					map.put("user_name", obj.getString("user_name"));
					map.put("activity_date", obj.getString("activity_date"));
					map.put("activity_location", obj.getString("activity_location"));
					map.put("activity_ending", obj.getString("activity_ending"));
					map.put("activity_organism", obj.getString("activity_organism"));

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
			map.put("user_name", "Code : "+"user : "+i);
			map.put("activity_date", "Date : "+"date : "+i);
			map.put("activity_location", "Code : "+"location : "+i);
			map.put("activity_ending", "Code : "+"ending : "+i);
			map.put("activity_organism", "Code : "+"organism : "+i);
			result.add(map);
		}


		return result;
	}

}
