package pl.kasperdubiel.gpswersja2;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
		RVAdapter adapter = new RVAdapter(persons);
		rv.setAdapter(adapter);
	}
}
