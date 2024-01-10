package app.com.datedifference;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    protected DateFormat df;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year;
        int month;
        int day;
        df = new SimpleDateFormat("dd/MM/yyyy");
        if (((((MainActivity) getActivity()).ed1.isFocused() && (((MainActivity) getActivity()).ed1.toString().contains("/")))))
        {
            try {
                Date caled1= df.parse((((MainActivity) getActivity()).ed1.getText().toString()));
                //Calendar cal = Calendar. getInstance();
                calendar.setTime(caled1);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (((((MainActivity) getActivity()).ed1.isFocused() && (!((MainActivity) getActivity()).ed2.toString().contains("/")))))
                  {
                try {
                    Date caled1= df.parse((((MainActivity) getActivity()).ed1.getText().toString()));

                    calendar.setTime(caled1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if ((((MainActivity) getActivity()).ed1.isFocused()) && (((MainActivity) getActivity()).ed2.getText().toString().contains("/"))) {
                try {
                    Date caled1= df.parse((((MainActivity) getActivity()).ed2.getText().toString()));
                    //Calendar cal = Calendar. getInstance();
                    calendar.setTime(caled1);
                    calendar.add(Calendar.DAY_OF_MONTH,-1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if ((((MainActivity) getActivity()).ed2.isFocused()) && (((MainActivity) getActivity()).ed2.getText().toString().length()==0))
            {
                try {
                    Date caled1= df.parse((((MainActivity) getActivity()).ed1.getText().toString()));
                    //Calendar cal = Calendar. getInstance();
                    calendar.setTime(caled1);
                    calendar.add(Calendar.DAY_OF_MONTH,1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if ((((MainActivity) getActivity()).ed2.isFocused()) && (((MainActivity) getActivity()).ed2.getText().toString().contains("/")))
            {
                try {
                    Date caled1= df.parse((((MainActivity) getActivity()).ed2.getText().toString()));
                    //Calendar cal = Calendar. getInstance();
                    calendar.setTime(caled1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        return dpd;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day, 0, 0, 0);
        Date chosenDate = cal.getTime();


        String formattedDate = df.format(chosenDate);
        if (((MainActivity) getActivity()).ed1.isFocused()) {

                (((MainActivity) getActivity()).ed1).setText(formattedDate);
                (((MainActivity) getActivity()).ed1).setSelection(formattedDate.length());
            }
            if (((MainActivity) getActivity()).ed2.isFocused()) {
                {
                    (((MainActivity) getActivity()).ed2).setText(formattedDate);
                    (((MainActivity) getActivity()).ed2).setSelection(formattedDate.length());
                }
            }

        }
}
