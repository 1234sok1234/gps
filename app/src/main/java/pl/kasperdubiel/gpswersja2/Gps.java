package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "gps_table")

public class Gps
{
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	private int id;
	private Double wyso;
	private Double szer;
	private Double czas;

	public Gps(Double wyso, Double szer,double czas)
	{
		this.wyso = wyso;
		this.szer = szer;
		this.czas=czas;
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

	public Double getCzas()
	{
		return czas;
	}
//komentarz

}
