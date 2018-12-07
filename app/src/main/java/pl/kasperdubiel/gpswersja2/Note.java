package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "note_table")
public class Note
{
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String title;
	private String description;
	private String czestotli;
	private int ilo;
	private double posi;
	private double posix;
	private long time1;
	private long time2;

	//private Date time1;

	//private Date time2;
	private int priority;
	/*
	public Note(String title, String description, int priority, double posi, double posix,String czestotli)
	{
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.posi = posi;
		this.posix = posix;
		this.czestotli = czestotli;
	}
*/
	public Note(String title, String description, int priority, double posi, double posix,String czestotli,long time1,long time2,int ilo)
	{
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.posi = posi;
		this.posix = posix;
		this.czestotli = czestotli;
		this.time1 = time1;
		this.time2 = time2;
		this.ilo=ilo;

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
/*
	public Date getTime1()
	{
		return time1;
	}

	public Date getTime2()
	{
		return time2;
	}
*/
	public double getPosi()
	{
		return posi;
	}

	public String getCzestotli()
	{
		return czestotli;
	}

	public double getPosix()
	{
		return posix;
	}
	public int getIlo()
	{
		return ilo;
	}
	public long getTime1()
	{
		return time1;
	}
	public long getTime2()
	{
		return time2;
	}
}
