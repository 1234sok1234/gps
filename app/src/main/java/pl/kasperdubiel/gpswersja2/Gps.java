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
	private Long czas;
	private Double pren;

	public Gps(Double wyso, Double szer,Long czas,double pren)
	{
		this.wyso = wyso;
		this.szer = szer;
		this.czas=czas;
		this.pren=pren;
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

	public Long getCzas()
	{
		return czas;
	}

	public Double getPren()
	{
		return pren;
	}
//komentarz

}
