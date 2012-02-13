package com.jayway.lab.multitouch;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchActivity extends Activity {

	private ImageView imageView;

	private float savedX;
	private float savedY;
	private Matrix savedMatrix = new Matrix();
	private Matrix imageMatrix = new Matrix();

	private int fingersUsed;

	private float savedDistance;

	private float savedAngle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.image);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			// Save values
			fingersUsed = 1;
			savedX = event.getX();
			savedY = event.getY();
			savedMatrix.set(imageView.getImageMatrix());
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			savedX = (event.getX(0) + event.getX(1)) / 2;
			savedY = (event.getY(0) + event.getY(1)) / 2;
			savedDistance = distance(event);
			savedAngle = angle(event);
			savedMatrix.set(imageView.getImageMatrix());
			fingersUsed = 2;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			fingersUsed = 0;
			break;
		case MotionEvent.ACTION_MOVE:
			// Update values by comparing the current values to
			// the saved ones
			if (fingersUsed == 1) {
				float dx = event.getX() - savedX;
				float dy = event.getY() - savedY;
				imageMatrix.set(savedMatrix);
				imageMatrix.postTranslate(dx, dy);
			} else if (fingersUsed == 2) {
				float midX = (event.getX(0) + event.getX(1)) / 2;
				float midY = (event.getY(0) + event.getY(1)) / 2;
				float dx = midX - savedX;
				float dy = midY - savedY;
				float newDistance = distance(event);
				float newAngle = angle(event);
				float scale = newDistance / savedDistance;
				imageMatrix.set(savedMatrix);
				imageMatrix.postTranslate(dx, dy);
				imageMatrix.postScale(scale, scale, midX, midY);
				imageMatrix.postRotate(savedAngle - newAngle, midX, midY);
			}
			break;
		default:
			break;
		}
		imageView.setImageMatrix(imageMatrix);
		return true;
	}

	/**
	 * returns the distance between the two first pointers in the event
	 * 
	 * @return the distance
	 */
	private float distance(MotionEvent event) {
		float xDiff = event.getX(1) - event.getX(0);
		float yDiff = event.getY(1) - event.getY(0);
		return FloatMath.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	/**
	 * returns the angle in degrees of the line going from the first pointer to
	 * the second pointer in the MotionEvent.
	 * 
	 * @return the angle in degrees
	 */
	private float angle(MotionEvent event) {
		float xDiff = event.getX(1) - event.getX(0);
		float yDiff = event.getY(1) - event.getY(0);
		double radianAngle = Math.atan2(xDiff, yDiff);
		return (float) (radianAngle * 180 / Math.PI);
	}
}