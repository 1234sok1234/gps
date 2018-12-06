package pl.kasperdubiel.gpswersja2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import pl.kasperdubiel.gpswersja2.Note;
import pl.kasperdubiel.gpswersja2.NoteRepository;

public class NoteViewModel extends AndroidViewModel
{
	private NoteRepository repository;
	private GpsRepository repository1;
	private LiveData<List<Note>> allNotes;
	private LiveData<List<Gps>> allGps;

	public NoteViewModel(@NonNull Application application)
	{
		super(application);
		repository = new NoteRepository(application);
		repository1 = new GpsRepository(application);
		allNotes = repository.getAllNotes();
		allGps = repository1.getAllGps();
	}

	public void insert(Note note)
	{
		repository.insert(note);
	}

	public void update(Note note)
	{
		repository.update(note);
	}

	public void delete(Note note)
	{
		repository.delete(note);
	}
	public void insert(Gps gps)
	{
		repository1.insert(gps);
	}

	public void update(Gps gps)
	{
		repository1.update(gps);
	}

	public void delete(Gps gps)
	{
		repository1.delete(gps);
	}

	public void deleteAllNotes()
	{
		repository.deleteAllNotes();
	}

	public void deleteAllGps()
	{
		repository1.deleteAllGps();
	}
	public LiveData<List<Gps>> getAllGps()
	{
		return allGps;
	}

	public LiveData<List<Note>> getAllNotes()
	{
		return allNotes;
	}


}
