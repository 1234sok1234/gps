package pl.kasperdubiel.gpswersja2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import static pl.kasperdubiel.gpswersja2.App.CHANNEL_ID;

public class AddNoteActivity extends AppCompatActivity
{
	private Notification notification;
	private EditText editTextInput;

	public static final String EXTRA_TITLE = "pl.kasperdubiel.gpswersja2.EXTRA_TITLE";
	public static final String EXTRA_DESCRIPTION = "pl.kasperdubiel.gpswersja2.EXTRA_DESCRIPTION";
	public static final String EXTRA_PRIORITY = "pl.kasperdubiel.gpswersja2.EXTRA_PRIORITY";

	private EditText editTextTitle;
	private EditText editTextDescription;
	private NumberPicker numberPickerPriority;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		editTextInput = findViewById(R.id.edit_text_input);

		editTextTitle = findViewById(R.id.edit_text_title);
		editTextDescription = findViewById(R.id.edit_text_description);
		numberPickerPriority = findViewById(R.id.number_picker_priority);
		numberPickerPriority.setMinValue(1);
		numberPickerPriority.setMaxValue(10);
		if (getSupportActionBar() != null)
		{
			getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		}
		setTitle("New workout");
	}

	private void saveNote()
	{

		String title = editTextTitle.getText().toString();
		String description = editTextDescription.getText().toString();
		int priority = numberPickerPriority.getValue();

		if (title.trim().isEmpty() || description.trim().isEmpty())
		{
			Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent data = new Intent();
		data.putExtra(EXTRA_TITLE, title);
		data.putExtra(EXTRA_DESCRIPTION, description);
		data.putExtra(EXTRA_PRIORITY, priority);

		setResult(RESULT_OK, data);

		finish();

	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.add_note_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.save_note:
				saveNote();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void startService(View v)
	{

		String input = editTextInput.getText().toString();
		if(input.matches(""))
		{
			input="1000";
		}

		Intent serviceIntent = new Intent(this, BackgroudService.class);
		serviceIntent.putExtra("inputExtra", input);
		serviceIntent.putExtra("inputExtra2", "5000");

		ContextCompat.startForegroundService(this, serviceIntent);

	}

	public void set()
	{
		String input = editTextInput.getText().toString();

		Intent serviceIntent = new Intent(this, BackgroudService.class);
		serviceIntent.putExtra("inputExtra", "savovalem");
		serviceIntent.putExtra("inputExtra2", "2000");

		ContextCompat.startForegroundService(this, serviceIntent);
	}

	public void stopService(View v)
	{
		Intent serviceIntent = new Intent(this, BackgroudService.class);
		stopService(serviceIntent);
	}
}