package ru.mirea.shmitko.mireaproject.ui.settings;

import static ru.mirea.shmitko.mireaproject.MainActivity.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.sql.CallableStatement;

import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private String KEY_HOMEPAGE;
    private String KEY_BACKGROUNG; 
    private EditText txtEditHomeP, txtEditBackColor;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        KEY_HOMEPAGE = getString(R.string.KEY_HOMEPAGE);
        KEY_BACKGROUNG = getString(R.string.KEY_BACKGROUND);
        binding = FragmentSettingsBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        txtEditHomeP = root.findViewById(R.id.txtEditHomePage);
        txtEditHomeP.setText(
                preferences
                        .getString(
                                KEY_HOMEPAGE,
                                "https://google.com"
                        )
        );

        txtEditBackColor = root.findViewById(R.id.txtEditBackColor);
        txtEditBackColor.setText(
                preferences
                        .getString(
                                KEY_BACKGROUNG,
                                "White"
                        )
        );

        root
                .findViewById(R.id.btnSave)
                .setOnClickListener(this::on_btnSaveClick);
        return root;
    }

    public void on_btnSaveClick(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_BACKGROUNG, txtEditBackColor.getText().toString());
        editor.putString(KEY_HOMEPAGE, txtEditHomeP.getText().toString());
        editor.apply();
    }
}