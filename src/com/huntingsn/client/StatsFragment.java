package com.huntingsn.client;

import com.example.huntingsnclient.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class StatsFragment extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	private String userId;

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
		TextView view = (TextView)getActivity().findViewById(R.id.stats_label);
		view.setText("Carte des activités");
		view.setTextSize(15.0f);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), StatsMapActivity.class);
				i.putExtra("statId", userId);
				getActivity().startActivity(i);
			}
		});
	}

}
