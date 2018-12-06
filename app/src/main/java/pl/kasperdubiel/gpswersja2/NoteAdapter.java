package pl.kasperdubiel.gpswersja2;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder>
{
	public NoteAdapter()
	{
		super(DIFF_CALLBACK);
	}
	private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK=new DiffUtil.ItemCallback<Note>()
	{
		@Override
		public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem)
		{
			return oldItem.getId()==newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem)
		{
			return oldItem.getTitle().equals(newItem.getTitle()) &&
					oldItem.getDescription().equals(newItem.getDescription()) &&
					oldItem.getPriority() == newItem.getPriority();
		}
	};

	@NonNull
	@Override
	public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.note_item, parent, false);
		return new NoteHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
		Note currentNote = getItem(position);
		holder.textViewTitle.setText(currentNote.getTitle());
		holder.textViewDescription.setText(currentNote.getDescription());
		holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
		holder.textViewPosi.setText(String.valueOf(currentNote.getPosi()));
	}


	public Note getNoteAt(int positon)
	{
		return getItem(positon);
	}
	class NoteHolder extends RecyclerView.ViewHolder
	{
		private TextView textViewTitle;
		private TextView textViewDescription;
		private TextView textViewPriority;
		private TextView textViewPosi;

		public NoteHolder(View itemView)
		{
			super(itemView);
			textViewTitle = itemView.findViewById(R.id.text_view_title);
			textViewDescription = itemView.findViewById(R.id.text_view_description);
			textViewPriority = itemView.findViewById(R.id.text_view_priority);
			textViewPosi = itemView.findViewById(R.id.text_view_posi);

		}
	}
}
