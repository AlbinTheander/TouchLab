package com.jayway.lab.multitouch;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchActivity extends Activity {

	private ImageView imageView;

	private float savedX;
	private float savedY;
	private Matrix savedMatrix = new Matrix();
	private Matrix imageMatrix = new Matrix();

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
			savedX = event.getX();
			savedY = event.getY();
			savedMatrix.set(imageView.getImageMatrix());
			break;
		case MotionEvent.ACTION_MOVE:
			// Update values by comparing the current values to
			// the saved ones
			float dx = event.getX() - savedX;
			float dy = event.getY() - savedY;
			imageMatrix.set(savedMatrix);
			imageMatrix.postTranslate(dx, dy);
			break;
		default:
			break;
		}
		imageView.setImageMatrix(imageMatrix);
		return true;
	}
}