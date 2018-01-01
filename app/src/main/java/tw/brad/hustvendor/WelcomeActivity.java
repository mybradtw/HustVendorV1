package tw.brad.hustvendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        View view = findViewById(R.id.welcome);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        timer = new Timer();
        timer.schedule(new MyTask(), 3*1000);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void finish() {
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }
}
