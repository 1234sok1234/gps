package pl.kasperdubiel.gpswersja2;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		Calendar time1, time2;
		long time1x, time2x;
		time1=Calendar.getInstance();
		time2=Calendar.getInstance();
		Note currentNote = getItem(position);
		time1x=currentNote.getTime1();
		time2x=currentNote.getTime2();
		time1.setTimeInMillis(time1x);
		time2.setTimeInMillis(time2x);
		long diff=time2x-time1x;
		long diffSeconds = diff / 1000;

		// Calculate difference in minutes
		long diffMinutes = diff / (60 * 1000);

		// Calculate difference in hours
		long diffHours = diff / (60 * 60 * 1000);

		// Calculate difference in days
		long diffDays = diff / (24 * 60 * 60 * 1000);
		holder.textViewTitle.setText(currentNote.getTitle());
		holder.textViewDescription.setText(currentNote.getDescription());
		holder.textViewPriority.setText("Priority: "+String.valueOf(currentNote.getPriority()));
		holder.textViewPosi.setText("Distance: "+String.valueOf(currentNote.getPosi())+"m");
		holder.textViewPosix.setText("Time: "+diffDays+"days,"+diffHours+"hours,"+diffMinutes+"minutes,"+diffSeconds+"seconds");
		SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm:s a");
		holder.textViewyy.setText("Started at: "+format.format(time1.getTime()));
		holder.textViewPosi2.setText("Stopped at: "+format.format(time2.getTime()));
		holder.textViewPax.setText("Interval: "+String.valueOf(currentNote.getCzestotli())+" ms");
	}

/*

						time1.get(Calendar.DAY_OF_MONTH)+"/"+
						time1.get(Calendar.MONTH)+"/"+
						time1.get(Calendar.YEAR)+"  "+
						time1.get(Calendar.HOUR_OF_DAY)+":"+
						time1.get(Calendar.MINUTE)+","+
						time1.get(Calendar.SECOND)
 */
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
		private TextView textViewPosix;
		private TextView textViewPosi2;
		private TextView textViewyy;
		private TextView textViewPax;

		public NoteHolder(View itemView)
		{
			super(itemView);
			textViewTitle = itemView.findViewById(R.id.text_view_title);
			textViewDescription = itemView.findViewById(R.id.text_view_description);
			textViewPriority = itemView.findViewById(R.id.text_view_priority);
			textViewPosi = itemView.findViewById(R.id.text_view_posi);
			textViewPosix = itemView.findViewById(R.id.text_view_posix);
			textViewyy=itemView.findViewById(R.id.text_view_yy);
			textViewPosi2=itemView.findViewById(R.id.text_view_posi2);
			textViewPax=itemView.findViewById(R.id.text_view_pax);

		}
	}
}
