package pl.kasperdubiel.gpswersja2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
private Context mContext;
	RVAdapter(List<Person> persons,Context mContext)
	{
		this.persons = persons;
		this.mContext=mContext;

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
		return new PersonViewHolder(v);
	}
	@Override
	public void onBindViewHolder(final PersonViewHolder personViewHolder,int i)
	{
		Person person=persons.get(i);
		personViewHolder.pn.setText(persons.get(i).name);
		personViewHolder.pa.setText(persons.get(i).age);
		personViewHolder.pp.setImageResource(persons.get(i).photoid);
		Glide.with(mContext).load(person.getPhotoid()).into(personViewHolder.pp);
		personViewHolder.pp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showPopupMenu(personViewHolder.pp);
			}
		});
	}
	private void showPopupMenu(View view) {
		// inflate menu
		PopupMenu popup = new PopupMenu(mContext, view);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.menu_album, popup.getMenu());
		popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
		popup.show();
	}
	class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

		public MyMenuItemClickListener() {
		}

		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			switch (menuItem.getItemId()) {
				case R.id.action_add_favourite:
					Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.action_play_next:
					Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
					return true;
				default:
			}
			return false;
		}
	}
	@Override
	public int getItemCount()
	{
		return persons.size();
	}
}
