package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.text.format.DateFormat;
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
					/*
					File cache = new File("");
					for(File file : cache.listFiles()) {
						file.getName().equals("id");
					}
					*/
					JSONObject obj = array.getJSONObject(i);
					map = new HashMap<String, String>();
					
					map.put("user_name", obj.getString("creatorName"));
					
					
					map.put("activity_date", ", le "+obj.getString("date"));
					
					String ending = obj.getString("activityEnding");
					String sector = obj.getString("sectorName");					
					Log.d("sector", sector);
					if(ending == "sight") {
						map.put("activity_ending", "a apperçu ");
					}
					else {
						if(sector.contains("hunting")) {
							map.put("activity_ending", "a chassé ");
						}
						else if(sector.contains("fishing")) {
							map.put("activity_ending", "a peché ");
						}
						else if(sector.contains("picking")){
							//sector == picking
							map.put("activity_ending", "a cueilli ");
						}
						else {
							map.put("activity_ending", "a pris ");
						}
					}
					
					JSONObject organism = obj.getJSONObject("organism");
					int amount = Integer.parseInt(obj.getString("amountOfOrganism"));
					if(amount > 1) {
						map.put("activity_organism", 
								obj.getString("amountOfOrganism")+" "+organism.getString("specie")+"s");
					}
					else {
						map.put("activity_organism", 
								obj.getString("amountOfOrganism")+" "+organism.getString("specie"));
					}
					
					
					JSONObject location =  obj.getJSONObject("location");
					Geocoder gcd = new Geocoder(context, Locale.getDefault());
					List<Address> addresses = 
							gcd.getFromLocation(Double.parseDouble((String) location.get("latPos")), 
									Double.parseDouble((String) location.get("longPos")), 1);
					if (addresses.size() > 0) {
					    map.put("activity_location", "aux alentours de "+addresses.get(0).getLocality());
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
					map.put("fullName", obj.getString("fullName")+ "("+obj.getString("nickName")+")");
					map.put("id", obj.getString("id"));
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
	
	public static ArrayList<HashMap<String, String>> generateMarkersArray(String input) {
		
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

		try {
			JSONArray array = new JSONArray(input);
			HashMap<String, String> map;

			for(int i = 0; i < array.length(); i++) {
				try {
					
					JSONObject obj = array.getJSONObject(i);
					map = new HashMap<String, String>();
					
					map.put("activity_date", ", le "+obj.getString("date"));
					
					map.put("sector", obj.getString("sectorName"));
					
					JSONObject organism = obj.getJSONObject("organism");					
					map.put("organism", organism.getString("specie"));
					JSONObject location =  obj.getJSONObject("location");
					map.put("latitude", location.getString("latPos"));
					map.put("longitude", location.getString("longPos"));
						
					
					listItem.add(map);       
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
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
