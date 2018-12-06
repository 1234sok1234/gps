package pl.kasperdubiel.gpswersja2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
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

import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private static final String TAG = "BOOMBOOMTESTGPS";
	private NoteViewModel noteViewModel,noteViewModel1;
	LiveData<List<Note>> y;
	List<Note> x;
	LiveData<List<Gps>> yy;
	List<Gps> xx;
	double szer=0;
	double wysok=0;
	public static final int ADD_NOTE_REQUEST = 1;


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
			double proti = data.getDoubleExtra(AddNoteActivity.EXTRA_PROTI,1);
			double protix = data.getDoubleExtra(AddNoteActivity.EXTRA_PROTIX,1);
			int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);
		//	Gps gps = new Gps(55d, 43d);
			//noteViewModel.insert(gps);
			Note note = new Note(title, description, priority, proti,protix);
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
				noteViewModel.deleteAllGps();
				Toast.makeText(this, "All workouts deleted", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
