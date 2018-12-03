package pl.kasperdubiel.gpswersja2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import pl.kasperdubiel.gpswersja2.Gps;
@Dao
public interface GpsDao
{
	@Insert
	void insert(Gps gps);

	@Update
	void update(Gps gps);

	@Delete
	void delete(Gps gps);

	@Query("DELETE From gps_table")
	void deleteAllGps();

	@Query("SELECT * FROM gps_table ORDER BY id DESC")
	LiveData<List<Gps>> getAllGps();
	@Query("SELECT wyso From gps_table")
	void wyso();
	@Query("SELECT szer From gps_table")
	void szer();
}
