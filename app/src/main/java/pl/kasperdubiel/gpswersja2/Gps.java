package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "gps_table")

public class Gps
{
	@PrimaryKey(autoGenerate = true)
	private int id;
	private Double wyso;
	private Double szer;

	public Gps(Double wyso, Double szer)
	{
		this.wyso = wyso;
		this.szer = szer;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public Double getWyso()
	{
		return wyso;
	}

	public Double getSzer()
	{
		return szer;
	}


}
