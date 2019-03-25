package pl.s230473.kulkasensory;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

class SensorActivity implements Runnable, SensorListener, SensorEventListener {
    Kulka kulka;
    Handler handler;
    TextView textPunkty;
    TextView textRekord;
    double punkty = 0;
    double rekord = 0;
    Button obszar;
    MediaPlayer ring;
    boolean soundStart = false;
    int level_timer = 0;
    double scale = 1.0;

    public SensorActivity(MediaPlayer r, Button b, TextView p, TextView re, Kulka k, Handler h) {
        kulka = k;
        handler = h;
        textPunkty = p;
        textRekord = re;
        obszar = b;
        ring = r;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int randomX = 0;
        int randomY = 0;
        //int randomX = (int)(Math.random() * 10 - 5);
        //int randomY = (int)(Math.random() * 10 - 5);
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
            level_timer++;
            try {
                Thread.sleep(24);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        kulka.move();
                        kulka.invalidate();
                        if(kulka.getCenterDistance() <= obszar.getWidth()*obszar.getScaleX()/2) {
                            punkty += 0.01;
                            scale -= 0.001;
                            obszar.setScaleX((float) scale);
                            obszar.setScaleY((float) scale);
                            if(punkty >= rekord) {
                                rekord = punkty;
                                textRekord.setText("Rekord: " + (int)rekord);
                            }
                            ring.stop();
                            soundStart = false;
                        } else {
                            punkty = 0.0;
                            soundStart = true;
                            if(!soundStart) ring.start();
                            scale = 1;
                            obszar.setScaleX((float) scale);
                            obszar.setScaleY((float) scale);
                        }
                        textPunkty.setText("Punkty: " + (int)punkty);
                        /*if(level_timer >= 100)
                        {
                            if(scale-0.1 > 0.1) {
                                scale -= 0.05;
                                obszar.setScaleX((float) scale);
                                obszar.setScaleY((float) scale);
                            }
                            level_timer = 0;
                        }*/
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
