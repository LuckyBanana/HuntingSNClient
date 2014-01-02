package com.example.huntingsnclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private ImageView profile_pic;
	private ImageView friends_pic;
	
	private TextView user_name;
	
	private ListView activities_list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.activity_profile_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		profile_pic = (ImageView)getView().findViewById(R.id.user_pic_imageview);
		friends_pic = (ImageView)getView().findViewById(R.id.friends_button_imageview);
		
		user_name = (TextView)getView().findViewById(R.id.user_name_textview);
		
		activities_list = (ListView)getView().findViewById(R.id.activities_listview);
		
	}

}
