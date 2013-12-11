package com.example.huntingsnclient;

import httpclient.RESTHTTPClient;
import httpclient.RESTHTTPClientAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TimelineFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView activityList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.activity_timeline_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activityListInit();
	}

	public void activityListInit() {
		activityList = (ListView)getView().findViewById(R.id.timeline_item_list);

		SimpleAdapter mSchedule = new SimpleAdapter (
				getActivity().getBaseContext(),
				getUserTimeline(), 
				R.layout.activity_item,
				new String[] {"user_name", "activity_date", "activity_location", "activity_ending", "activity_organism"}, 
				new int[] {
					R.id.user_name,
					R.id.activity_date, 
					R.id.activity_location, 
					R.id.activity_ending, 
					R.id.activity_organism,}
				);
		activityList.setAdapter(mSchedule);


	}

	public ArrayList<HashMap<String, String>> getUserTimeline() {

		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;

		/*
		 * TODO GET USER TIMELINE
		 * RETURN ArrayList<HashMap<String, String>>
		 */
		
		/*
		 * TODO REPLACE URL
		 * "/:id/?activity?/"
		 */
		
		// String request = RESTHTTPClient.get("userId/activity");
		
		RESTHTTPClientAsync client = new RESTHTTPClientAsync();
		String request = client.getTimeline("userId");
		
		//Log.d("Request", "HTTP OK");
		//Log.d("Result", request);

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
