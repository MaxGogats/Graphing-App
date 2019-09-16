package com.example.graphapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Path;
import java.util.Random;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;

    private Paint paint = null;

    public MySurface(Context context) {
        super(context);

        setFocusable(true);

        if(surfaceHolder == null) {
            // Get surfaceHolder object.
            surfaceHolder = getHolder();
            // Add this as surfaceHolder callback object.
            surfaceHolder.addCallback(this);
        }

        // Set the parent view background color. This can not set surfaceview background color.
        this.setBackgroundColor(Color.WHITE);

        // Set current surfaceview at top of the view tree.
        this.setZOrderOnTop(true);

        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawGraph();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void drawGraph() {
        float startX = 0;
        float startY = 100;
        float stopX = 10000;
        float stopY = 10000;


        if(surfaceHolder.getSurface().isValid()){
            Canvas canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            canvas.drawLine(startX, startY, stopX, stopY, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);

        }

    }

    public void drawCircle(){
        if(surfaceHolder.getSurface().isValid()){
            Canvas canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            canvas.drawCircle(10, 10, 100, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}