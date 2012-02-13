package com.jayway.lab.gestures;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GesturesActivity extends Activity {

	private EditText text;
	private LinearLayout iconLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (EditText) findViewById(R.id.text);
		iconLayout = (LinearLayout) findViewById(R.id.icons);
	}

	/**
	 * resets the text view showing the input from the gestures. Used from the XML.
	 * 
	 * @param view
	 */
	public void reset(View view) {
		text.setText("");
	}

	/**
	 * adds the provided gesture icon to the ones shown on-screen.
	 * 
	 * @param icon
	 *            the icon to display
	 */
	private void addGestureIcon(Bitmap icon) {
		ImageView imageView = new ImageView(this);
		imageView.setImageBitmap(icon);
		iconLayout.addView(imageView);
	}

}