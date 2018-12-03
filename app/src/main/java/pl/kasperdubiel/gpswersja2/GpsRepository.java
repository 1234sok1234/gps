package pl.kasperdubiel.gpswersja2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

import pl.kasperdubiel.gpswersja2.Gps;
import pl.kasperdubiel.gpswersja2.GpsDao;
import pl.kasperdubiel.gpswersja2.NoteDatabase;

public class GpsRepository
{
	private GpsDao gpsDao;
	private LiveData<List<Gps>> allGps;


	public GpsRepository(Application application)
	{
		NoteDatabase database = NoteDatabase.getInstance(application);
		gpsDao = database.gpsDao();


		allGps = gpsDao.getAllGps();
	}

	public void insert(Gps gps)
	{
		new InsertGpsAsyncTask(gpsDao).execute(gps);
	}
	public void update(Gps gps)
	{
		new UpdateGpsAsyncTask(gpsDao).execute(gps);
	}

	public void delete(Gps gps)
	{
		new DeleteGpsAsyncTask(gpsDao).execute(gps);
	}

	public void deleteAllGps()
	{
		new DeleteAllGpsAsyncTask(gpsDao).execute();
	}

	private static class InsertGpsAsyncTask extends AsyncTask<Gps, Void, Void>
	{
		private GpsDao gpsDao;

		private InsertGpsAsyncTask(GpsDao gpsDao)
		{
			this.gpsDao = gpsDao;
		}

		@Override
		protected Void doInBackground(Gps... gpss)
		{
			gpsDao.insert(gpss[0]);
			return null;
		}
	}
	private static class UpdateGpsAsyncTask extends AsyncTask<Gps, Void, Void>
	{
		private GpsDao gpsDao;

		private UpdateGpsAsyncTask(GpsDao gpsDao)
		{
			this.gpsDao = gpsDao;
		}

		@Override
		protected Void doInBackground(Gps... gpss)
		{
			gpsDao.update(gpss[0]);
			return null;
		}
	}
	private static class DeleteGpsAsyncTask extends AsyncTask<Gps, Void, Void>
	{
		private GpsDao gpsDao;

		private DeleteGpsAsyncTask(GpsDao gpsDao)
		{
			this.gpsDao = gpsDao;
		}

		@Override
		protected Void doInBackground(Gps... gpss)
		{
			gpsDao.delete(gpss[0]);
			return null;
		}
	}

	private static class DeleteAllGpsAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private GpsDao gpsDao;

		private DeleteAllGpsAsyncTask(GpsDao gpsDao)
		{
			this.gpsDao = gpsDao;
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			gpsDao.deleteAllGps();
			return null;
		}
	}

}
