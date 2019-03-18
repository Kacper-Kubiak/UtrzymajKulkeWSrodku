package pl.s230473.kulkasensory;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

class SensorActivity implements Runnable, SensorListener, SensorEventListener {
    Kulka kulka;
    Handler handler;
    TextView textPunkty;
    double punkty = 0;
    Button obszar;

    public SensorActivity(Button b, TextView p, Kulka k, Handler h) {
        kulka = k;
        handler = h;
        textPunkty = p;
        obszar = b;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int randomX = (int)(Math.random() * 10 - 5);
        int randomY = (int)(Math.random() * 10 - 5);
        kulka.setMovePosX((int) Math.ceil(-event.values[0])-randomX);
        kulka.setMovePosY((int) Math.ceil(event.values[1])-randomY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {

    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            kulka.setCenterPos();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true)
        {
            try {
                Thread.sleep(24);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        kulka.move();
                        kulka.invalidate();
                        if(kulka.getCenterDistance() <= obszar.getWidth()/2) {
                            punkty += 0.01;
                        } else {
                            punkty = 0.0;
                        }
                        textPunkty.setText("Punkty: " + (int)punkty);
                    }
                });
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
