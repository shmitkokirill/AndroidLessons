package ru.mirea.shmitko.mireaproject.ui.dataRoom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentCalculatorBinding;
import ru.mirea.shmitko.mireaproject.databinding.FragmentDataRoomBinding;

public class DataRoom extends Fragment {

    private AppDatabase db;
    private FragmentDataRoomBinding binding;
    private ListView lView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(
                        getContext(),
                        AppDatabase.class,
                        "db"
                )
                .allowMainThreadQueries()
                .build();
        data = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data
        );

        List<Notes> backup = db.notesDao().getAll();
        if (!backup.isEmpty()){
            for (Notes note : backup){
                data.add(note.toDo + " " + note.when);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataRoomBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        lView = (ListView) root.findViewById(R.id.listView);
        lView.setAdapter(adapter);
        return root;
    }

    public void on_btnSubmitClick(View v, String toDoTxt, String whenDoTxt) {
        Notes note = new Notes();
        note.toDo = toDoTxt;
        note.when = whenDoTxt;

        NotesDao nd = db.notesDao();
        nd.insert(note);

        data.add(toDoTxt + " " + whenDoTxt);
        adapter.notifyDataSetChanged();
    }

    public void on_btnRemoveClick(View v) {
        db.notesDao().deleteAll();
        data.clear();
        adapter.notifyDataSetChanged();
    }
}