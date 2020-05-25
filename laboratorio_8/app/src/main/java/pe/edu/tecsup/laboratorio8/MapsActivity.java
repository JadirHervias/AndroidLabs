package pe.edu.tecsup.laboratorio8;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pe.edu.tecsup.laboratorio8.services.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import pe.edu.tecsup.laboratorio8.models.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    private GoogleMap mMap;

    ArrayList<LatLng> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Map> call = service.getDataMarkers("-12.046373,-77.042755", "50000",
                "restaurant", "pollo%20a%20la%20brasa" , "AIzaSyAn8DpxSG8yU35XhtDeS5R_eMvBI8XXm2g");

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                for (Result result : response.body().getResults()){
                    LatLng markerLocation = new LatLng(
                            result.getGeometry().getLocation().getLat(),
                            result.getGeometry().getLocation().getLng());
                    String markerTitle = result.getName();
                    mMap.addMarker(new MarkerOptions().position(markerLocation).title(markerTitle));
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "No se pudo encontrar la ubicación :C",
                        Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(),t.getMessage());
            }
        });

        //        CameraPosition cameraTecsup = new CameraPosition
//                .Builder().target(locationTecsup).zoom(17).build();
//        mMap.addMarker(
//                new MarkerOptions()
//                        .position(locationTecsup)
//                        .title("Tecsup")
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraTecsup));

        // LatLng locationTecsup = new LatLng(-12.04528043132399, -76.95236206054688);


        for(int i = 0; i < arrayList.size(); i++) {
            mMap.addMarker(
                    new MarkerOptions()
                            .position(arrayList.get(i))
                            .title("Marker")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }


        // Muestra los botones de control de zoom de la cámara
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Permite al usuario cambiar el nivel de zoom mediante arrastre o gestura
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        // Muestra la brújula en el mapa
        mMap.getUiSettings().setCompassEnabled(true);

        // Permite al usuario rotar el mapa mediante arrastre o gestura
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Tipos de mapas:

//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // (Tipo por defecto)
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        mMap.setMapType(GoogleMap.MAP_TYPE_NONE); // (No muestra mapa)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

            if (hasLocationPermission !=
                    PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);

            } else {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
        } else {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(REQUEST_CODE_ASK_PERMISSIONS == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {

                Toast.makeText(this, "No concedió los permisos :(", Toast.LENGTH_SHORT).show();

            }
        }else{

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }
}
