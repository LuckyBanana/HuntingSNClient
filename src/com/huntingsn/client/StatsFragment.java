package com.huntingsn.client;

import java.util.ArrayList;
import java.util.HashMap;

import maps.StatsMapActivity;
import maps.StatsMapAllActivity;

import com.example.huntingsnclient.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StatsFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private String userId;
	private ListView statsList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.activity_stats_fragment, container, false);


	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		userId = getActivity().getIntent().getStringExtra(MainActivity.USER_ID);

		statsList = (ListView)getActivity().findViewById(R.id.stats_item_list);
		statsListInit();

	}


	public void statsListInit() {
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("stat_name", getString(R.string.map_activity_personal));
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("stat_name", getString(R.string.map_activity_all));
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("stat_name", "");
		data.add(map1);
		data.add(map2);
		data.add(map3);
		SimpleAdapter mSchedule = new SimpleAdapter (
				getActivity().getBaseContext(),
				data, 
				R.layout.stats_list_item,
				new String[] {"stat_name"}, 
				new int[] {
					R.id.stat_name,
				}
				);
		statsList.setAdapter(mSchedule);
		

		statsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2) {
				case 0:
					Intent intent = new Intent(getActivity(), StatsMapActivity.class);
					intent.putExtra("statId", userId);
					getActivity().startActivity(intent);
					break;
				case 1:
					Intent intent2 = new Intent(getActivity(), StatsMapAllActivity.class);
					intent2.putExtra("statId", userId);
					getActivity().startActivity(intent2);
					break;
				}
			}
		});
	}

}
