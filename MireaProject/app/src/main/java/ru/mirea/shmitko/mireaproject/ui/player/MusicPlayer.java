package ru.mirea.shmitko.mireaproject.ui.player;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentBrowserBinding;
import ru.mirea.shmitko.mireaproject.databinding.FragmentMusicPlayerBinding;
import ru.mirea.shmitko.mireaproject.ui.browser.MyWebViewClient;

public class MusicPlayer extends Fragment {

    private FragmentMusicPlayerBinding binding;
    private Button playBtn;
    private Button stopBtn;
    MyPlayerService service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMusicPlayerBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}