package dialogs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.huntingsnclient.R;

public class DateTimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, OnTimeSetListener 
{

	private DatePicker datePicker;
	private TimePicker timePicker;

	private Button validateButton;
	private Button cancelButton;
	private Button nowButton;

	private Map<String, Integer> values = new HashMap<String, Integer>();
	OnDateTimeDialogResult tDialogResult;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.date_time_dialog, container, false);

		getDialog().setTitle(R.string.date_picker_title);
		buttonsInit(layout);

		datePicker = (DatePicker)layout.findViewById(R.id.datePicker1);
		timePicker = (TimePicker)layout.findViewById(R.id.timePicker1);

		datePicker.setCalendarViewShown(false);
		timePicker.setIs24HourView(true);

		return layout;
	}

	private void buttonsInit(View layout) {

		validateButton = (Button)layout.findViewById(R.id.validate_date);
		cancelButton = (Button)layout.findViewById(R.id.cancel_date);
		nowButton = (Button)layout.findViewById(R.id.now_date);

		validateButton.setOnClickListener(new ValidateListener());

		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDialog().dismiss();
			}
		});

		nowButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
		        int year = c.get(Calendar.YEAR);
		        int month = c.get(Calendar.MONTH);
		        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		        int hour = c.get(Calendar.HOUR_OF_DAY);
		        int minute = c.get(Calendar.MINUTE);
		        
		        datePicker.updateDate(year, month, dayOfMonth);
		        timePicker.setCurrentHour(hour);
		        timePicker.setCurrentMinute(minute);
			}
		});
	}

	@Override
	public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub

	}

	private class ValidateListener implements View.OnClickListener {

		public ValidateListener() {

		}

		@Override
		public void onClick(View v) {

			if( tDialogResult != null ) {
				values.put("day", datePicker.getDayOfMonth());
				values.put("month", datePicker.getMonth());
				values.put("year", datePicker.getYear());
				values.put("hour", timePicker.getCurrentHour());
				values.put("minute", timePicker.getCurrentMinute());
				tDialogResult.finish(values);
			}
			DateTimePickerFragment.this.dismiss();
		}

	}

	public void setDialogResult(OnDateTimeDialogResult dialogResult){
		tDialogResult = dialogResult;
	}

	public interface OnDateTimeDialogResult{
		void finish(Map<String, Integer> result);
	}

}
