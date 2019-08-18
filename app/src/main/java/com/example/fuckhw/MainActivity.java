package com.example.fuckhw;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FastChargeSurfaceView fastChargeSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fastChargeSurfaceView = findViewById(R.id.renderview);

        //fastChargeSurfaceView.setAlpha(0.0f);
        fastChargeSurfaceView.setChargeType(0);
        fastChargeSurfaceView.setBatteryLevel(96.0f);
        fastChargeSurfaceView.setEntranceLocation(0);
    }
}
