package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Data_table")

public class Data
{
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	private int id;
	private String data;
	public Data(String data)
	{
		this.data = data;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public String getData()
	{
		return data;
	}
}
