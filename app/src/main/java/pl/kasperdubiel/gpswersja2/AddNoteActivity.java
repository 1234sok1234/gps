package pl.kasperdubiel.gpswersja2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.location.LocationManager.GPS_PROVIDER;
import static pl.kasperdubiel.gpswersja2.App.CHANNEL_ID;

public class AddNoteActivity extends AppCompatActivity
{
	private Notification notification;
	private EditText editTextInput;
	double szer = 0;
	double wysok = 0;
	double x, y;
	int ilo = 0;
	int incr = 0;
	private TextView text1;
	long time1x, time2x,timexxx;
	String input;
	double xl = 0, yl = 0;
	double xn = 0, yn = 0;
	int zmienna = 0;
	double dystans = 0;


	private TextView text2;
	private TextView odleglosc;
	private TextView sredniapr;
	public static final String EXTRA_TITLE = "pl.kasperdubiel.gpswersja2.EXTRA_TITLE";
	public static final String EXTRA_DESCRIPTION = "pl.kasperdubiel.gpswersja2.EXTRA_DESCRIPTION";
	public static final String EXTRA_PRIORITY = "pl.kasperdubiel.gpswersja2.EXTRA_PRIORITY";
	public static final String EXTRA_PROTI = "pl.kasperdubiel.gpswersja2.EXTRA_PROTI";
	public static final String EXTRA_PROTIX = "pl.kasperdubiel.gpswersja2.EXTRA_PROTIX";
	public static final String EXTRA_TIME1X = "pl.kasperdubiel.gpswersja2.EXTRA_TIME1X";
	public static final String EXTRA_TIME2X = "pl.kasperdubiel.gpswersja2.EXTRA_TIME2X";
	public static final String EXTRA_INPUT = "pl.kasperdubiel.gpswersja2.EXTRA_INPUT";
	public static final String EXTRA_ILO = "pl.kasperdubiel.gpswersja2.EXTRA_ILO";


	private EditText editTextTitle;
	private EditText editTextDescription;

	private NumberPicker numberPickerPriority;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		editTextInput = findViewById(R.id.edit_text_input);
		text1 = findViewById(R.id.szerr);
		text2 = findViewById(R.id.wysoo);
		odleglosc = findViewById(R.id.odleglosc);
		sredniapr=findViewById(R.id.srednia);
		editTextTitle = findViewById(R.id.edit_text_title);
		editTextDescription = findViewById(R.id.edit_text_description);

		numberPickerPriority = findViewById(R.id.number_picker_priority);
		numberPickerPriority.setMinValue(1);
		numberPickerPriority.setMaxValue(10);
		if (getSupportActionBar() != null)
		{
			getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		}
		setTitle("New workout");
	}

	private BroadcastReceiver br = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			Bundle bundle = intent.getExtras();
			Log.i("xddddd", bundle.getDouble("x") + " " + bundle.getDouble("y"));
			szer = szer + bundle.getDouble("x");
			wysok = wysok + bundle.getDouble("y");
			Log.i("xddddd", "xxxxxxxxxxxxxxxdddddddddd");
			text1.setText(Double.toString(bundle.getDouble("x")));
			text2.setText(Double.toString(bundle.getDouble("y")));
			xn = bundle.getDouble("x");
			yn = bundle.getDouble("y");
			if (zmienna > 0)
			{
				if (bundle.getBoolean("stop") == false)
				{
					liczenieodleglosc();
				}
			}
			odleglosc.setText(Double.toString(dystans)+" m");
			timexxx=bundle.getLong("zzzz");
			xl = xn;
			yl = yn;
			zmienna++;
			ilo = bundle.getInt("ilo");
			time1x = bundle.getLong("z");
			time2x = bundle.getLong("a");
			//NoteDatabase.getInstance(getApplicationContext()).gpsDao().insert(new Gps(123d,123d,123d));
			double czas=timexxx-time1x;
			if (czas!=0)
			{
				double sredniaaa = dystans / czas * 1000;
				sredniapr.setText(Double.toString(sredniaaa)+"m/s");
			}


			incr++;
			/*
			Gps gps = new Gps(bundle.getDouble("x"), bundle.getDouble("y"));
			Log.i(TAG, "+++++++");
			noteViewModel1.insert(gps);
			y=noteViewModel.getAllNotes();
			x=y.getValue();

			Log.e(TAG, "initxxxxxxxxxxxxxxxxxxxxxxxxxxxxxr");
			Log.e(TAG,Double.toString(x.get(0).getPosi()));
			yy=noteViewModel1.getAllGps();
			xx=yy.getValue();

			Log.e(TAG, "initxxxxxxxxxxxxccccccccccccccccccccccccccxxxxxxxxxxxxxxxxxr");
			Log.e(TAG,Double.toString(xx.get(0).getSzer()));
			*/
		}
	};

	@Override
	protected void onResume()
	{
		super.onResume();
		registerReceiver(br, new IntentFilter("1"));

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		unregisterReceiver(br);

	}

	private void saveNote()
	{

		String title = editTextTitle.getText().toString();
		String description = editTextDescription.getText().toString();
		int priority = numberPickerPriority.getValue();

		if (title.trim().isEmpty() || description.trim().isEmpty())
		{
			Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent data = new Intent();
		data.putExtra(EXTRA_TITLE, title);
		data.putExtra(EXTRA_DESCRIPTION, description);
		data.putExtra(EXTRA_PRIORITY, priority);
		data.putExtra(EXTRA_PROTI, dystans);
		data.putExtra(EXTRA_PROTIX, y);
		data.putExtra(EXTRA_TIME1X, time1x);
		data.putExtra(EXTRA_TIME2X, time2x);
		data.putExtra(EXTRA_ILO, ilo);
		data.putExtra(EXTRA_INPUT, input);


		setResult(RESULT_OK, data);

		finish();

	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.add_note_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.save_note:
				saveNote();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void startService(View v)
	{

		input = editTextInput.getText().toString();
		if (input.matches(""))
		{
			input = "1000";
		}

		Intent serviceIntent = new Intent(this, BackgroudService.class);
		serviceIntent.putExtra("inputExtra", input);
		serviceIntent.putExtra("inputExtra2", "5000");

		ContextCompat.startForegroundService(this, serviceIntent);

	}

	public void set()
	{
		String input = editTextInput.getText().toString();

		Intent serviceIntent = new Intent(this, BackgroudService.class);
		serviceIntent.putExtra("inputExtra", "savovalem");
		serviceIntent.putExtra("inputExtra2", "2000");

		ContextCompat.startForegroundService(this, serviceIntent);
	}

	public void stopService(View v)
	{
		x = szer / incr;
		y = wysok / incr;
		Log.i("xddddd", "ooooooooooooooooooooooooooooooo");
		Log.i("xddddd", Double.toString(x));
		Log.i("xddddd", Double.toString(y));
		Intent serviceIntent = new Intent(this, BackgroudService.class);
		stopService(serviceIntent);
	}

	public double liczenieodleglosc()
	{
		Location mLastLocation = new Location(GPS_PROVIDER);
		double odleglos = 0;
		float[] res = new float[3];
		mLastLocation.distanceBetween(xl, yl, xn, yn, res);
		Log.e("ascsa", " sssssssssssssssssssssssssssss");
		Log.e("casdc", Double.toString(xl));
		Log.e("casdc", Double.toString(yl));
		Log.e("casdc", Double.toString(xn));
		Log.e("casdc", Double.toString(xn));
		Log.e("Asc", Float.toString(res[0]));
		Log.e("ascsa", " sssssssssssssssssssssssssssss");
		odleglos = res[0];
		dystans = dystans + res[0];
		return odleglos;
	}
}