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

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button startGame = null;

    private boolean drawBall = true;

    private LinearLayout canvasLayout = null;

    MySurface customSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Falling Meteor Game");

        initControls();

        // Hide the app title bar.
        getSupportActionBar().hide();

        // Make app full screen to hide top status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create custom surfaceview object.
        customSurfaceView = new MySurface(getApplicationContext());

        // Set this as the onTouchListener to process custom surfaceview ontouch event.
        customSurfaceView.setOnTouchListener(this);

        // Add the custom surfaceview object to the layout.
        canvasLayout.addView(customSurfaceView);

        // Click this button to draw a green rectangle move after finger touch.
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fallingMeteor();
            }
        });

    }

    /*Create falling meteors*/
    private void fallingMeteor(){

    }

    /* Initialise ui controls. */
    private void initControls()
    {
        if(startGame == null)
        {
            startGame = (Button)findViewById(R.id.startGame);
        }

        // This layout is used to contain custom surfaceview object.
        if(canvasLayout == null)
        {
            canvasLayout = (LinearLayout)findViewById(R.id.customViewLayout);
        }
    }

    /* If user finger touch the surfaceview object. */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // If user touch the custom SurfaceView object.
        if(view instanceof SurfaceView) {

            float x = motionEvent.getX();

            float y = motionEvent.getY();

            customSurfaceView.setCircleX(x);

            customSurfaceView.setCircleY(y);

            if (drawBall) {
                // Create and set a red paint to custom surfaceview.
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                customSurfaceView.setPaint(paint);

                customSurfaceView.drawBall();
            }
            // Tell android os the onTouch event has been processed.
            return true;
        }else
        {
            // Tell android os the onTouch event has not been processed.
            return false;
        }
    }
}