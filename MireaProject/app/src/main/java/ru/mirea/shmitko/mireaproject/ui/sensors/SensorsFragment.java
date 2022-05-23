package ru.mirea.shmitko.mireaproject.ui.sensors;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.mirea.shmitko.mireaproject.MainActivity;
import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentSensorsBinding;

public class SensorsFragment extends Fragment {

    private FragmentSensorsBinding binding;

    public boolean isWork = false;
    public Uri imageUri;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    public static final int CAMERA_REQUEST = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSensorsBinding.inflate(
                inflater,
                container,
                false
        );
        int cameraPermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.CAMERA
                );
        int storagePermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                );

        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED &&
                storagePermissionStatus == PackageManager.PERMISSION_GRANTED
        ) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    REQUEST_CODE_PERMISSION_CAMERA
            );
        }
        return inflater.inflate(
                R.layout.fragment_sensors,
                container,
                false
        );
    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat(
                "yyyyMMdd_HHmmss"
        ).format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                );
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
            isWork = grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void on_btnMakePhotoClick(View v) {
        View root = binding.getRoot();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isWork == true) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // генерирование пути к файлу на основе authorities
            String authorities =
                    root.getContext().getPackageName() + ".provider";
            imageUri = FileProvider.getUriForFile(
                    root.getContext(),
                    authorities,
                    photoFile
            );
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    public void on_btnShowPhotoClick(View v) {
        View root = binding.getRoot();
        ImageView imgView = (ImageView) root.findViewById(R.id.imgView);
        imgView.setImageURI(imageUri);
    }
}