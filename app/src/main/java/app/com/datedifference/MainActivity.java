package app.com.datedifference;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.util.TimeZone.*;

public class MainActivity extends AppCompatActivity {
public EditText ed1,ed2;
public TextView ap1,ap2;
public ToggleButton dateday1,dateday2;
public String edtext1enabled,edtext2enabled;
public String edtext1disabled,edtext2disabled;
protected  View.OnFocusChangeListener ed1focuschange,ed2focuschange;

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.datepick1);
        ed2=(EditText) findViewById(R.id.datepick2);
        ap1=(TextView) findViewById(R.id.ap1);
        ap2=(TextView) findViewById(R.id.ap2);
        dateday1=(ToggleButton) findViewById(R.id.dateday1);
        dateday2=(ToggleButton) findViewById(R.id.dateday2);
        dateday1.setChecked(true);
        dateday2.setChecked(false);
        dateday1.setEnabled(false);
        ed2.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed1.setText("");
        ed2.setText("");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_round);
        final DialogFragment datepickerdialog=new DatePickerFragment();
        ed1focuschange=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {

                    if (dateday1.isChecked()) {
                        datepickerdialog.show(getFragmentManager(), "Διαλέξτε ημερομηνία");
                        ed1.setInputType(InputType.TYPE_CLASS_TEXT);
                    } else {
                        ed1.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }

                }
            }
        };
ed2focuschange=new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            if (dateday2.isChecked()) {
                datepickerdialog.show(getFragmentManager(), "Διαλέξτε ημερομηνία");
                ed2.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                ed2.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        }
    }
};
        dateday1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ed1.setOnFocusChangeListener(null);
                ed1.requestFocus();
                ed1.setOnFocusChangeListener(ed1focuschange);
                if (!b) {
                    edtext1enabled=ed1.getText().toString();
                    dateday2.setEnabled(false);
                    ed1.setText(edtext1disabled);
                } else  {
                    dateday2.setEnabled(true);
                    edtext1disabled=ed1.getText().toString();
                    ed1.setText(edtext1enabled);
                }
            ed1.setSelection(ed1.getText().toString().length());

            }
        });
        dateday2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ed2.setOnFocusChangeListener(null);
                ed2.requestFocus();
                ed2.setOnFocusChangeListener(ed2focuschange);
                if (!b) {
                    dateday1.setEnabled(false);
                    edtext2enabled=ed2.getText().toString();
                        ed2.setText(edtext2disabled);

                } else  {
                    dateday1.setEnabled(true);
                    edtext2disabled=ed2.getText().toString();
                     ed2.setText(edtext2enabled);

                }
                ed2.setSelection(ed2.getText().toString().length());
            }
        });


       // ed1.setOnFocusChangeListener(ed1focuschange);
       // ed2.setOnFocusChangeListener(ed2focuschange);

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dateday1.isChecked()) {
                    datepickerdialog.show(getFragmentManager(), "Διαλέξτε ημερομηνία");
                    ed1.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    ed1.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                ed2.setOnFocusChangeListener(null);
                ed2.setOnFocusChangeListener(ed2focuschange);
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dateday2.isChecked()) {
                    datepickerdialog.show(getFragmentManager(), "Διαλέξτε ημερομηνία");
                    ed2.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    ed2.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                ed1.setOnFocusChangeListener(null);
                ed1.setOnFocusChangeListener(ed1focuschange);
                // ed2.setOnFocusChangeListener(ed2focuschange);

            }
        });
TextWatcher twatcher=new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Calendar calendar=Calendar.getInstance();
        Date d1=null,d2=null;
        String hmerarxh="",hmertelos="";
        long[] result=null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfoutput = new SimpleDateFormat("EEE dd/MM/yyyy");
        if (ed2.getText().toString().length()>0) {
            if (ed1.getText().toString().length()>0) {
                //Ημερομηνίες και τα 2

                if ((ed1.getText().toString().contains("/")) && (ed2.getText().toString().contains("/")))
                {

                    try {
                        d1=df.parse(ed1.getText().toString());
                        d2=df.parse(ed2.getText().toString());
                        result=getTimeDifference(d1,d2 );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ap1.setVisibility(View.GONE);
                    ap2.setVisibility(View.VISIBLE);
                    if (result[0]==1) {
                        ap2.setText("Διαφορά ημερών ".concat(String.valueOf(result[0])).concat(" ημέρα"));
                    } else if (result[0]==0) {
                        ap2.setText("Δεν υπάρχει καμία διαφορά");
                    } else {
                        ap2.setText("Διαφορά ημερών ".concat(String.valueOf(result[0])).concat(" ημέρες"));
                    }
                } else if  (!(ed1.getText().toString().contains("/")) && (ed2.getText().toString().contains("/"))) {
                    try {
                        d1 = df.parse(ed2.getText().toString());
                        calendar.setTime(d1);
                        calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(String.valueOf(ed1.getText().toString())));
                       // d2.setTime(calendar.getTimeInMillis());
                        hmerarxh = dfoutput.format(calendar.getTimeInMillis());
                    } catch (ParseException e) {
                        ap1.setVisibility(View.GONE);
                        ap2.setVisibility(View.GONE);

                    }
                    ap1.setVisibility(View.VISIBLE);
                    ap2.setVisibility(View.GONE);
                    ap1.setText("Ημερομηνία ".concat(hmerarxh));

                } else if  ((ed1.getText().toString().contains("/")) && !(ed2.getText().toString().contains("/"))) {
                    try {
                        d1 = df.parse(ed1.getText().toString());
                        calendar.setTime(d1);
                        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(ed2.getText().toString())));
                        // d2.setTime(calendar.getTimeInMillis());
                        hmertelos = dfoutput.format(calendar.getTimeInMillis());
                    } catch (ParseException e) {
                        ap1.setVisibility(View.GONE);
                        ap2.setVisibility(View.GONE);

                    }
                    ap1.setVisibility(View.GONE);
                    ap2.setVisibility(View.VISIBLE);
                    ap2.setText("Ημερομηνία ".concat(hmertelos));

                } else {
                    ap1.setVisibility(View.GONE);
                    ap2.setVisibility(View.GONE);
                }
            } else {
                ap1.setVisibility(View.GONE);
                ap2.setVisibility(View.GONE);
            }
        } else {
            ap1.setVisibility(View.GONE);
            ap2.setVisibility(View.GONE);

        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
};

ed1.addTextChangedListener(twatcher);
ed2.addTextChangedListener(twatcher);
    }
    public void cleardate(View view) {
        if (view.getTag().equals("d1")) {
            ed1.setText("");
            if (dateday1.isChecked()) {
                edtext1enabled="";
            } else {
                edtext1disabled="";
            }
            ed1.setOnFocusChangeListener(null);
            ed1.requestFocus();
            ed1.setOnFocusChangeListener(ed1focuschange);
        }
        if (view.getTag().equals("d2")) {
            ed2.setText("");
            if (dateday2.isChecked()) {
                edtext2enabled="";
            } else {
                edtext2disabled="";
            }
            ed2.setOnFocusChangeListener(null);
            ed2.requestFocus();
            ed2.setOnFocusChangeListener(ed2focuschange);
        }

        ap1.setVisibility(View.GONE);
        ap2.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            final View view = factory.inflate(R.layout.about, null);
            String version;
                    Integer versionCode;
            TextView ver=view.findViewById(R.id.ver);
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                 version = pInfo.versionName;
                // versionCode = pInfo.versionCode;
                //Log.d("MyApp", "Version Name : "+version + "\n Version Code : "+versionCode);
                ver.setText("Version ".concat(version) );
            }catch(PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.d("MyApp", "PackageManager Catch : "+e.toString());
            }

            ImageView image= (ImageView) view.findViewById(R.id.dialog_imageview);
            image.setImageResource(R.drawable.lefter);


            TextView text1= (TextView) view.findViewById(R.id.epon);
            text1.setText(Html.fromHtml("\u00a9 2019 Λεοντόπουλος"));
            TextView text2= (TextView) view.findViewById(R.id.onom);
            text2.setText("Ελευθέριος");

            AlertDialog.Builder alertadd = new AlertDialog.Builder(
                    MainActivity.this);
            alertadd.setView(view);
            alertadd.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dlg, int sumthin) {
                    dlg.dismiss();
                }
            });

            alertadd.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static long[] getTimeDifference(Date d1, Date d2) {
        long[] result = new long[5];
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(getTimeZone("UTC"));
        cal.setTime(d1);

        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);

        long diff = Math.abs(cal.getTimeInMillis() - t1);
        final int ONE_DAY = 1000 * 60 * 60 * 24;
        final int ONE_HOUR = ONE_DAY / 24;
        final int ONE_MINUTE = ONE_HOUR / 60;
        final int ONE_SECOND = ONE_MINUTE / 60;

        long d = diff / ONE_DAY;
        diff %= ONE_DAY;

        long h = diff / ONE_HOUR;
        diff %= ONE_HOUR;

        long m = diff / ONE_MINUTE;
        diff %= ONE_MINUTE;

        long s = diff / ONE_SECOND;
        long ms = diff % ONE_SECOND;
        result[0] = d;
        result[1] = h;
        result[2] = m;
        result[3] = s;
        result[4] = ms;

        return result;
    }

}
