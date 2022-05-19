package ru.mirea.shmitko.mireaproject.ui.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private WebView webView;
    private String startPage = "https://www.google.com/";
    private FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBrowserBinding.inflate(
            inflater,
            container,
            false
        );
        View root = binding.getRoot();

        webView = root.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(startPage);
        webView.setWebViewClient(new MyWebViewClient());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}