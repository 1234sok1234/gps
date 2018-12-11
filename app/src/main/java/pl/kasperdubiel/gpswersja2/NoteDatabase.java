package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class, Gps.class}, version = 9)
public abstract class NoteDatabase extends RoomDatabase
{
	private static NoteDatabase instance;

	public abstract NoteDao noteDao();

	public abstract GpsDao gpsDao();

	public static synchronized NoteDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database").fallbackToDestructiveMigration().addCallback(roomCallback).allowMainThreadQueries().build();
		}
		return instance;
	}

	private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
	{
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db)
		{
			super.onCreate(db);
			new PopulateDbAsyncTask(instance).execute();
		}
	};

	private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private NoteDao noteDao;
		private GpsDao gpsDao;

		private PopulateDbAsyncTask(NoteDatabase db)
		{
			noteDao = db.noteDao();
			gpsDao = db.gpsDao();
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			noteDao.insert(new Note("Title 1", "Description 1", 1, 5d, 5d, "asdas", 20l, 123l, 123));
			gpsDao.insert(new Gps(12d, 123d, 13d,123d));


			return null;
		}
	}
}
