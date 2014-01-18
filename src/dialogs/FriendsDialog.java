package dialogs;

import tasks.GetFriendsTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.huntingsnclient.R;

public class FriendsDialog extends DialogFragment {
	
	private ListView friends_listview;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.friends_dialog, container, false);
		getDialog().setTitle("Friends");
		
		friends_listview = (ListView)layout.findViewById(R.id.friends_listview);
		new GetFriendsTask(getActivity(), friends_listview);
		

		return layout;
	}
}
