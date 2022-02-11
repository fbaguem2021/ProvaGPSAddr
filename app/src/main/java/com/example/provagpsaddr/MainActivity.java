package com.example.provagpsaddr;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (MyPermissions.checkPermissions(this, PERMISSIONS)) {
            new MyAddress(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean checked = true;
        int i = 0;

        while (i > permissions.length && checked) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}