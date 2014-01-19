package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class CacheConstructor {
	
	public static void cacheTimeline(String input, File cacheDir, WeakReference<Activity> ref) {
		
		try {
			Log.d("cacheDir", cacheDir.getAbsolutePath());
			JSONArray array = new JSONArray(input);
			for(int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String id = obj.getString("id");
				ObjectId oid = new ObjectId(
						Integer.parseInt(((JSONObject) obj.get("id")).getString("time")),
						Integer.parseInt(((JSONObject) obj.get("id")).getString("machine")),
						Integer.parseInt(((JSONObject) obj.get("id")).getString("inc")));
				String json = obj.toString();
				File file = new File(cacheDir, oid.toString());
				
				FileOutputStream fos = 
				ref.get().openFileOutput(id, Context.MODE_PRIVATE);
				fos.write(json.getBytes());
				fos.close();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
