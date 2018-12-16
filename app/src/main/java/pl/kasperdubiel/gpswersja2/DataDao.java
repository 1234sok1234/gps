package pl.kasperdubiel.gpswersja2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface DataDao
{
	@Insert
	void insert(Data data);

	@Update
	void update(Data data);

	@Delete
	void delete(Data data);

	@Query("DELETE From Data_table")
	void deleteAllData();

	@Query("SELECT * FROM Data_table")
	List<Data> getAllData();
}
