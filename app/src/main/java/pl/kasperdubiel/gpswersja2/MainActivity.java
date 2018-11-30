package pl.kasperdubiel.gpswersja2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.kasperdubiel.gpswersja2.jclass.Note;
import pl.kasperdubiel.gpswersja2.jclass.NoteViewModel;

public class MainActivity extends AppCompatActivity
{
	private NoteViewModel noteViewModel;

	TextView pn;
	TextView pa;
	ImageView pp;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		noteViewModel=ViewModelProviders.of(this).get(NoteViewModel.class);
		noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>()
		{
			@Override
			public void onChanged(@Nullable List<Note> notes)
			{
				//update Recyccleview
				Toast.makeText(MainActivity.this, "onchangerd", Toast.LENGTH_SHORT).show();
			}
		});

		pn = findViewById(R.id.textView);
		pa = findViewById(R.id.textView3);
		pp = findViewById(R.id.imageView);
		pn.setText("kaspi Dibi");
		pa.setText("fajnie");
		pp.setImageResource(R.mipmap.photo);
	}
}
