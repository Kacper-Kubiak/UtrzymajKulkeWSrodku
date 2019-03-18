package pl.s230473.kulkasensory;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Kulka kulka;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorActivity sensorActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        kulka = findViewById(R.id.kulka);
        Button obszar = findViewById(R.id.btn);
        TextView punkty = findViewById(R.id.punkty);
        sensorActivity = new SensorActivity(obszar, punkty, kulka, new Handler());
        Thread t = new Thread(sensorActivity);
        t.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorActivity, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorListener) sensorActivity);
    }
}
