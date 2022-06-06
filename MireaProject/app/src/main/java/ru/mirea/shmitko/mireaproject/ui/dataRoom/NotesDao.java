package ru.mirea.shmitko.mireaproject.ui.dataRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes")
    List<Notes> getAll();

    @Query("SELECT * FROM notes WHERE nid IN (:ids)")
    List<Notes> loadAllByIds(int[] ids);

    @Query("SELECT * FROM notes WHERE what_to_do LIKE :first LIMIT 1")
    Notes findByTask(String first);

    @Insert
    void insert(Notes note);

    @Insert
    void insertAll(Notes... notes);

    @Delete
    void delete(Notes notes);

    @Query("DELETE FROM notes")
    void deleteAll();
}
