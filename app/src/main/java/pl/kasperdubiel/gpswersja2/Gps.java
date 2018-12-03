package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "gps_table")

public class Gps
{
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String wyso;
	private String szer;

	public Gps(String wyso, String szer)
	{
		this.wyso = wyso;
		this.szer = szer;
	}

	public String getWyso()
	{
		return wyso;
	}

	public String getSzer()
	{
		return szer;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}
}
