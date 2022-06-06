package ru.mirea.shmitko.mireaproject.ui.dataRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "what_to_do")
    public String toDo;

    @ColumnInfo(name = "when_do")
    public String when;
}
