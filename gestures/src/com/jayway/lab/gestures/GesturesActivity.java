package com.jayway.lab.gestures;

import java.util.Set;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GesturesActivity extends Activity {

	private EditText text;
	private LinearLayout iconLayout;
	private GestureLibrary gestureLib;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (EditText) findViewById(R.id.text);
		iconLayout = (LinearLayout) findViewById(R.id.icons);
		loadGestures();
	}

	private void loadGestures() {
		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		gestureLib.load();
		Set<String> entries = gestureLib.getGestureEntries();
		for(String entry: entries) {
			Gesture gesture = gestureLib.getGestures(entry).get(0);
			Bitmap icon = gesture.toBitmap(50, 50, 3, Color.YELLOW);
			addGestureIcon(icon);
		}
		
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