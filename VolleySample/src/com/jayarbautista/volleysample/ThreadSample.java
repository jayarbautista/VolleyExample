package com.jayarbautista.volleysample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class ThreadSample extends Activity {
    private ProgressBar bar;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_thread_layout);
        bar = (ProgressBar) findViewById(R.id.progressBar1);
    }

    public void startProgress(View view) {
        bar.setProgress(0);
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                final int value = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bar.setProgress(value);
            }
        }
    }
}
