package pl.kasperdubiel.gpswersja2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>
{
	public static class PersonViewHolder extends RecyclerView.ViewHolder
	{
		CardView cv;
		TextView pn;
		TextView pa;
		ImageView pp;

		PersonViewHolder(View itemView)
		{
			super(itemView);
			cv = itemView.findViewById(R.id.cv);
			pn = itemView.findViewById(R.id.pn);
			pa = itemView.findViewById(R.id.pa);
			pp = itemView.findViewById(R.id.pp);
		}
	}

	List<Person> persons;

	RVAdapter(List<Person> persons)
	{
		this.persons = persons;

	}
@Override
public void onAttachedToRecyclerView(RecyclerView recyclerView)
{
	super.onAttachedToRecyclerView(recyclerView);
}
	@Override
	public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
		PersonViewHolder pvh= new PersonViewHolder(v);
		return pvh;
	}
	@Override
	public void onBindViewHolder(PersonViewHolder personViewHolder,int i)
	{
		personViewHolder.pn.setText(persons.get(i).name);
		personViewHolder.pa.setText(persons.get(i).age);
		personViewHolder.pp.setImageResource(persons.get(i).photoid);
	}
	@Override
	public int getItemCount()
	{
		return persons.size();
	}
}
