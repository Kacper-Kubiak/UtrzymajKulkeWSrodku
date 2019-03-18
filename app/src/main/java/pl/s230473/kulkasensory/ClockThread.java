package pl.s230473.kulkasensory;

import android.os.Handler;

public class ClockThread implements Runnable {

    private Handler handler;
    private Kulka kulka;

    public ClockThread(Kulka k, Handler h) {
        handler = h;
        kulka = k;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        kulka.move();
                        kulka.invalidate();
                    }
                });
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}