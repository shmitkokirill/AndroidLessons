package ru.mirea.shmitko.mireaproject.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.mirea.shmitko.mireaproject.MainActivity;
import ru.mirea.shmitko.mireaproject.R;
import ru.mirea.shmitko.mireaproject.databinding.FragmentMapsBinding;

public class MapsFragment extends Fragment {

    private FragmentMapsBinding binding;
    private boolean isWork = false;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 100;

    private GoogleMap mMap;
    private OnMapReadyCallback callback = new OnMapReadyCallback()  {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            mMap.setOnMapClickListener((MainActivity) getActivity());

            if (
                    ActivityCompat.checkSelfPermission(
                            getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(
                                    getContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
            ) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setTrafficEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            setUpMap();
        }
    };

    private void setUpMap() {
        // ???????????????? ???????? ??????????????
        LatLng mirea = new LatLng(55.670005, 37.479894);
        LatLng mireaStr = new LatLng(55.794229, 37.700772);
        LatLng mireaStavropol = new LatLng(
                45.04978516747865,
                41.912036808158526
        );
        LatLng mireaFriazino = new LatLng(
                55.96052637057602,
                38.05177215619503
        );
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                mireaStr).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions().title("??????????")
                .snippet("?????? ????????????????: 28 ?????? 1947 ??." +
                        "\n????????????????????: 55.670005, 37.479894" +
                        "\n??????????: ????????????, ????. ??????????????????????, 78")
                .position(mirea));
        mMap.addMarker(new MarkerOptions().title("??????????")
                .snippet("?????? ????????????????: 18 ?????????????? 1996 ????????" +
                        "\n????????????????????: 45.04978516747865, 41.912036808158526" +
                        "\n??????????: ???????????????????? ????. ????????????????, ??. 8")
                .position(mireaStavropol));
        mMap.addMarker(new MarkerOptions().title("??????????")
                .snippet("?????? ????????????????: 1962 ??." +
                        "\n????????????????????: 55.670005, 37.479894" +
                        "\n??????????: ??????????????, ????. ????????????????????, ??. 2??")
                .position(mireaFriazino));
        mMap.addMarker(new MarkerOptions().title("??????????")
                .snippet("?????? ????????????????: 29 ?????????????? 1936 ??." +
                        "\n????????????????????: 55.670005, 37.479894" +
                        "\n??????????: ????????????, ????. ??????????????????, ??. 20"
                ).position(mireaStr));
    }

    public GoogleMap getMap() {
        return mMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();

        int locationPermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                );
        int secondLocationPermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                );

        if (locationPermissionStatus == PackageManager.PERMISSION_GRANTED &&
        secondLocationPermissionStatus == PackageManager.PERMISSION_GRANTED
        ) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    REQUEST_CODE_PERMISSION_LOCATION);
        }
        return root;
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}