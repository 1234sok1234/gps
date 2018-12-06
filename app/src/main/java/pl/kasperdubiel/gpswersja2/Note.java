package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note
{
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String title;
	private String description;
	private double posi;
	private double posix;
	private int priority;

	public Note(String title, String description, int priority, double posi,double posix)
	{
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.posi = posi;
		this.posix = posix;

	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getDescription()
	{
		return description;
	}

	public int getPriority()
	{
		return priority;
	}

	public double getPosi()
	{
		return posi;
	}
	public double getPosix()
	{
		return posix;
	}
}
