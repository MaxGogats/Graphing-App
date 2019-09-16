package com.example.graphapp;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout customViewLayout = null;
    private Button startGame = null;
    MySurface mySurface = null;
    public boolean drawGraph = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create custom surfaceview object.
        MySurface mySurface = new MySurface(getApplicationContext());

        mySurface.setOnTouchListener(this);

        customViewLayout.addView(mySurface);

        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                drawGraph = false;
            }
        });


    }

    public void initControls() {
        if (customViewLayout == null) {
            customViewLayout = (LinearLayout) findViewById(R.id.customViewLayout);
        }

        if (startGame == null) {
            startGame = findViewById(R.id.startGame);
        }
    }

    /* If user finger touch the surfaceview object. */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

    }
}