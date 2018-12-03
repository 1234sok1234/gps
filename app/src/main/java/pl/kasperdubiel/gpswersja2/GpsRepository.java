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

/*
	public GpsRepository(Application application)
	{
		NoteDatabase database = NoteDatabase.getInstance(application);
		gpsDao = database.gpsDao();


		allGps = gpsDao.getAllGps();
	}

	public void insert(Gps gps)
	{
		new InsertNoteAsyncTask(gpsDao).execute(gps);
	}

	public void deleteAllGps()
	{
		new DeleteAllNotesAsyncTask(gpsDao).execute();
	}

	private static class InsertNoteAsyncTask extends AsyncTask<Gps, Void, Void>
	{
		private GpsDao gpsDao;

		private InsertNoteAsyncTask(NoteDao noteDao)
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
	*/

	private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private NoteDao noteDao;

		private DeleteAllNotesAsyncTask(NoteDao noteDao)
		{
			this.noteDao = noteDao;
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			noteDao.deleteAllNotes();
			return null;
		}
	}

}
