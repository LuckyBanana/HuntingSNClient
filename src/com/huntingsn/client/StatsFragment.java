package com.huntingsn.client;

import com.example.huntingsnclient.R;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class StatsFragment extends Fragment {
	
	private ListView activities_list;
	private static final String TAG = StatsFragment.class.getName();
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	public final static String[] listArray = new String[] {"map", "other thing"};

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_stats_fragment, container, false);
    }

	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);	
		
		activities_list = (ListView) getView().findViewById(R.id.stat_item_list);
		
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listArray);
		activities_list.setAdapter(myarrayAdapter);
		activities_list.setTextFilterEnabled(true);
		
		Log.e(TAG, "test log");

		activities_list.setOnItemClickListener(new OnItemClickListener()
		{
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    {
		    	if (position == 0 ) {
		    		Intent intent = new Intent(getActivity(), MapActivity.class);
					//intent.putExtra(MainActivity.USER_ID, userId);
					startActivity(intent);
		    	}
		        //Toast.makeText(getActivity(), "Stop Clicking me" + position, Toast.LENGTH_SHORT).show();
		    }
		});
		

		
		
		/*activities_list.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				// TODO Auto-generated method stub
				
			}
		});/**/
	}
}
