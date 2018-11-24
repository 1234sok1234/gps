package pl.kasperdubiel.gpswersja2;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.RoomMasterTable.TABLE_NAME;

public class RecycleViewActivity extends Activity
{
	private List<Person> persons;
	private RecyclerView rv;

	protected void onCreate(Bundle savedInstancesState)
	{
		super.onCreate(savedInstancesState);
		setContentView(R.layout.recyclerview_activity);
		rv = findViewById(R.id.rv);
		LinearLayoutManager llm = new LinearLayoutManager(this);
		rv.setLayoutManager(llm);
		rv.setHasFixedSize(true);
		initializeData();
		initializeAdapter();
		ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
		{
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1)
			{
				return false;
			}

			@Override
			public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
				final int position = viewHolder.getAdapterPosition(); //get position which is swipe

				if (direction == ItemTouchHelper.LEFT) {    //if swipe left

					AlertDialog.Builder builder = new AlertDialog.Builder(RecycleViewActivity.this); //alert for confirm to delete
					builder.setMessage("Are you sure to delete?");    //set messag
				}
			}
		};
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
		itemTouchHelper.attachToRecyclerView(rv);

	}

	private void initializeData()
	{
		persons = new ArrayList<>();
		persons.add(new Person("zbyszek", "22", R.mipmap.chan_face));
		persons.add(new Person("zbyszcascek", "28", R.mipmap.pepe_face));
		persons.add(new Person("zbyszasek", "12", R.mipmap.circle));
		persons.add(new Person("zbyszasek", "12", R.mipmap.chan_face));
		persons.add(new Person("zbyszasek", "12", R.mipmap.pepe_face));
		persons.add(new Person("zbyszasek", "12", R.mipmap.chan_face));
	}

	private void initializeAdapter()
	{
		RVAdapter adapter = new RVAdapter(persons,this);
		rv.setAdapter(adapter);
	}
}
