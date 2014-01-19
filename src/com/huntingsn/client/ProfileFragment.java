package com.huntingsn.client;

import com.example.huntingsnclient.R;

import tasks.GetProfileTask;
import tasks.GetTimelineTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import dialogs.FriendsDialog;


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
		profileInit();
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//profileInit();
	}
	
	public void profileInit() {
		final String userName = getActivity().getIntent().getStringExtra(MainActivity.USER_NAME);
		final String userId = getActivity().getIntent().getStringExtra(MainActivity.USER_ID);
		
		profile_pic = (ImageView)getView().findViewById(R.id.user_pic_imageview);
		friends_pic = (ImageView)getView().findViewById(R.id.friends_button_imageview);
		
		user_name = (TextView)getView().findViewById(R.id.user_name_textview);
		user_name.setText(userName);
		
		activities_list = (ListView)getView().findViewById(R.id.activities_listview);
		
		
		new GetProfileTask(getActivity(), activities_list).execute(userId);
		
		friends_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle args = new Bundle();
				args.putString("userId", userId);
				FriendsDialog newFragment = new FriendsDialog();
				android.support.v4.app.FragmentManager ft = getFragmentManager();
				newFragment.setArguments(args);
				newFragment.show(ft, "Friends");
			}
		});
	}

}
