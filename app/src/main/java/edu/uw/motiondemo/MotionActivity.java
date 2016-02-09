package edu.uw.motiondemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.util.Arrays;
import java.util.List;

public class MotionActivity extends AppCompatActivity {

    private static final String TAG = "**MOTION**";

    private DrawingSurfaceView view;

    private GestureDetector mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);

        mDetector = new GestureDetector(this, new MyGestureListener());
                //new MyGestureListener();

        view.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.v(TAG, "" + event);

       boolean gesture = mDetector.onTouchEvent(event);
        if(gesture)return true;

       int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG, "Finger down!");
                view.ball.cx = event.getX();
                view.ball.cy = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                Log.v(TAG, "Finger up!");

                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                //second finger
                //multi touch pseudo-example
                //int mSecondPointerId = event.getPointerId(1);


            case MotionEvent.ACTION_MOVE:
//                view.ball.cx = event.getX();
//                view.ball.cy = event.getY();
//                return true;
//
                //event.findPointerIndex(mSecondPointerId)
                    //respond to second finger
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
           return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float scaleFactor = .01f;
            Log.v(TAG, "Fling@" + velocityX + "," + velocityY);
            view.ball.dx = velocityX*scaleFactor;
            view.ball.dy = -1*velocityY*scaleFactor;
            return true;
        }
    }

    class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            detector.getScaleFactor();

            return super.onScale(detector);
        }
    }

}
