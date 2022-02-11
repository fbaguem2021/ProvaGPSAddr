package com.example.provagpsaddr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyAddress {
    private Activity activity;
    private Location location;

    public MyAddress(Activity activity) {
        this.activity = activity;

        Button btnGetLocation = activity.findViewById(R.id.BtnGetLocation);
        ImageButton btnShowMap = activity.findViewById(R.id.BtnShowMap);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                FusedLocationProviderClient client =
                        LocationServices.getFusedLocationProviderClient(activity);
                try {
                    client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            MyAddress.this.location = location;
                            showLocation();
                            String address = getAddressdFromLocation();
                            showAddress(address);
                        }
                    });
                } catch (SecurityException ex) {
                    Toast.makeText(activity, "Erron durante el acceso al GPS: \n"+
                            ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMaps();
            }
        });
    }

    private String getAddressdFromLocation() {
        String address = null;

        try {
            Geocoder geocoder = new Geocoder(activity, Locale.getDefault());

            List<Address> addresses = geocoder.
                    getFromLocation(location.getLatitude(), location.getLongitude(), 1);
             address = addresses.get(0).getAddressLine(0);

        } catch (IOException ex) {
            Toast.makeText(activity, "Error durante la obtención de la dirección: \n"+
                    ex.toString(), Toast.LENGTH_LONG).show();
        }

        return address;
    }

    private void showLocation() {
        TextView lat = activity.findViewById(R.id.LblLatitude);
        TextView lon = activity.findViewById(R.id.LblLongitude);

        lat.setText("Latitude:  " + location.getLatitude());
        lon.setText("Longitude: " + location.getLongitude());
    }

    private void showAddress(String address) {
        TextView addr = activity.findViewById(R.id.LblAddress);
        addr.setText("Direccion: "+address);
    }

    private void openGoogleMaps() {
        if (location != null) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+location.getLatitude()+","
                    + location.getLongitude() + " (My Location)");

            Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            intent.setPackage("com.google.android.apps,maos");
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "", Toast.LENGTH_LONG).show();
        }

    }
}
