package pl.kasperdubiel.gpswersja2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Database;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private static final String TAG = "BOOMBOOMTESTGPS";
	private NoteViewModel noteViewModel;
	LiveData<List<Note>> y;
	List<Note> x;
	LiveData<List<Gps>> yy;
	List<Gps> xx;
	double szer = 0;
	double wysok = 0;
	Calendar time1, time2;
	long time1x, time2x;
	public static final int ADD_NOTE_REQUEST = 1;
	private GpsRepository repository1;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
		buttonAddNote.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
				startActivityForResult(intent, ADD_NOTE_REQUEST);
			}
		});
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setHasFixedSize(true);
		final NoteAdapter adapter = new NoteAdapter();
		recyclerView.setAdapter(adapter);
		sendEmail();

		//repository1.insert(new Gps(23d, 123d, 123));
		//repository1.getAllGps();
		//Gps curren=getItem(0);
		noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
		noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>()
		{
			@Override
			public void onChanged(@Nullable List<Note> notes)
			{    //update Recyccleview
				adapter.submitList(notes);
				//noteViewModel.getAllGps().observe(this,adapter.submitList(gps));
				//Toast.makeText(MainActivity.this, "onchangerd", Toast.LENGTH_SHORT).show();
			}
		});
		new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
		{
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1)
			{
				return false;
			}

			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i)
			{
				noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
				Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
			}
		}).attachToRecyclerView(recyclerView);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		//x.get(0).getWyso();
		if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK)
		{
			String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
			String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
			double proti = data.getDoubleExtra(AddNoteActivity.EXTRA_PROTI, 1);
			double protix = data.getDoubleExtra(AddNoteActivity.EXTRA_PROTIX, 1);
			int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);
			String czesto = data.getStringExtra(AddNoteActivity.EXTRA_INPUT);
			int ilo = data.getIntExtra(AddNoteActivity.EXTRA_ILO, 1);
			time1x = data.getLongExtra(AddNoteActivity.EXTRA_TIME1X, 1);
			Log.e(TAG, Long.toString(time1x));
			List<Gps> jnasd=NoteDatabase.getInstance(this).gpsDao().getAllGps();
			for(int xc=0;xc<jnasd.size();xc++)
			{
				//jnasd.get(xc).getWyso();
				Log.e(TAG, "llllllllllllll");
				Log.e(TAG, Double.toString(jnasd.get(xc).getWyso())+" "+Double.toString(jnasd.get(xc).getSzer())+" "+Double.toString(jnasd.get(xc).getCzas())+" "+Double.toString(jnasd.get(xc).getPren()));

			}
			Log.e(TAG, Integer.toString(jnasd.size()));

			NoteDatabase.getInstance(this).gpsDao().deleteAllGps();
			Log.e(TAG, "zzzzzzzzzzzzzzzzzzzzzzzzz");
			Log.e(TAG, "");
			Log.e(TAG, "zzzzzzzzzzzzzzzzzzzzzzzzz");
			time2x = data.getLongExtra(AddNoteActivity.EXTRA_TIME2X, 1);
			Log.e(TAG, Long.toString(time2x));
			time1 = Calendar.getInstance();
			time1.setTimeInMillis(time1x);
			int second = time1.get(Calendar.SECOND);
			int millisecond = time1.get(Calendar.MILLISECOND);
			Log.e(TAG, Long.toString(second) + " " + Long.toString(millisecond));
			//time2.setTimeInMillis(time2x);
			//	Gps gps = new Gps(55d, 43d);
			//noteViewModel.insert(gps);
			Note note = new Note(title, description, priority, proti, protix, czesto, time1x, time2x, ilo);
			noteViewModel.insert(note);
			Toast.makeText(this, "Workout just started", Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(this, "Workout did not start", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.delete_all_notes:
				noteViewModel.deleteAllNotes();
				//noteViewModel.deleteAllGps();
				Toast.makeText(this, "All workouts deleted", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	private void sendEmail() {
		//Getting content for email
		String email = "kasper_1996@tlen.pl";
		String subject = "xd";
		String message = "xdd";

		//Creating SendMail object
		SendMail sm = new SendMail(this, email, subject, message);

		//Executing sendmail to send email
		sm.execute();
	}
}
