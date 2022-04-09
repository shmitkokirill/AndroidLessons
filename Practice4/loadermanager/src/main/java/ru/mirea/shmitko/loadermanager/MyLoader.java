package ru.mirea.shmitko.loadermanager;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    public static final String ARG_WORD = "KEY";
    private String firstName;
    public MyLoader(@NonNull Context context, Bundle bundle) {
        super(context);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        return firstName;
    }
}
