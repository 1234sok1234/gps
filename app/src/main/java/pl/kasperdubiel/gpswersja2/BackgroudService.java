package pl.kasperdubiel.gpswersja2;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
import static pl.kasperdubiel.gpswersja2.App.CHANNEL_ID;

public class BackgroudService extends Service
{
	private static final String TAG = "BOOMBOOMTESTGPS";
	private LocationManager mLocationManager = null;
	private int LOCATION_INTERVAL = 1000;
	private static final float LOCATION_DISTANCE = 10f;
	Calendar cal = Calendar.getInstance();
	int millisecond;
	int second;
	double roznica, x1, x2;
	Gps gps;


	//private NoteViewModel noteViewModel;


	private class LocationListener implements android.location.LocationListener
	{


		Location mLastLocation;

		public LocationListener(String provider)
		{
			Log.e(TAG, "LocationListener " + provider);
			mLastLocation = new Location(provider);
		}

		@Override
		public void onLocationChanged(Location location)
		{
			Intent intent  = new Intent("1");
			intent.putExtra("x",location.getLatitude());
			intent.putExtra("y",location.getLongitude());
			sendBroadcast(intent);
			cal = Calendar.getInstance();
			second = cal.get(Calendar.SECOND);
			millisecond = cal.get(Calendar.MILLISECOND);
			Log.e(TAG, Long.toString(second) + " " + Long.toString(millisecond));
			//Log.e(TAG, Integer.toString(LOCATION_INTERVAL));
			//Log.e(TAG, "onLocationChanged: " + location.getLatitude() + " " + location.getLongitude());
			//Gps gps=new Gps(location.getLatitude(),location.getLongitude());
			//noteViewModel.insert(gps);

			mLastLocation.set(location);
		}

		@Override
		public void onProviderDisabled(String provider)
		{
			Log.e(TAG, "onProviderDisabled: " + provider);
		}

		@Override
		public void onProviderEnabled(String provider)
		{
			Log.e(TAG, "onProviderEnabled: " + provider);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
			Log.e(TAG, "onStatusChanged: " + provider);
		}
	}

	LocationListener[] mLocationListeners = new LocationListener[]{new LocationListener(LocationManager.GPS_PROVIDER), new LocationListener(LocationManager.NETWORK_PROVIDER)};

	@Override
	public void onCreate()
	{
		poczatek();
		Log.e(TAG, "onCreate");
		initializeLocationManager();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		LOCATION_INTERVAL = Integer.parseInt(intent.getStringExtra("inputExtra"));
		try
		{
			Log.i(TAG, Integer.toString(LOCATION_INTERVAL));
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListeners[1]);
		} catch (java.lang.SecurityException ex)
		{
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex)
		{
			Log.d(TAG, "network provider does not exist, " + ex.getMessage());
		}
		try
		{
			Log.i(TAG, Integer.toString(LOCATION_INTERVAL));
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, 0, mLocationListeners[0]);
		} catch (java.lang.SecurityException ex)
		{
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex)
		{
			Log.d(TAG, "gps provider does not exist " + ex.getMessage());
		}
		String input = intent.getStringExtra("inputExtra");
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Example Service").setContentText(input).setSmallIcon(R.drawable.ic_android).setContentIntent(pendingIntent).build();

		startForeground(1, notification);
		Log.e(TAG, "onStartCommand");
		super.onStartCommand(intent, flags, startId);

		//do heavy work on a background thread
		//stopSelf();

		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();
		if (mLocationManager != null)
		{
			for (int i = 0; i < mLocationListeners.length; i++)
			{
				try
				{
					mLocationManager.removeUpdates(mLocationListeners[i]);
				} catch (Exception ex)
				{
					Log.i(TAG, "fail to remove location listners, ignore", ex);
				}
			}
		}
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	private void initializeLocationManager()
	{
		Log.e(TAG, "initializeLocationManager");
		if (mLocationManager == null)
		{
			mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		}
	}

	public void poczatek()
	{

	}
}