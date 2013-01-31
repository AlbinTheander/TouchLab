package com.example.com.jayway.lab.pinchlist;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class PinchList extends ExpandableListActivity implements
		OnTouchListener {

	private boolean twoFingersDown;
	private float savedDistance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinch_list);

		setListAdapter(new NameAdapter(this));
		getExpandableListView().setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		boolean handled = false;
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			twoFingersDown = true;
			savedDistance = distance(event);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			twoFingersDown = false;
			break;
		case MotionEvent.ACTION_MOVE:
			// Update values by comparing the current values to
			// the saved ones
			if (twoFingersDown) {
				float newDistance = distance(event);
				float distanceDiff = newDistance - savedDistance;
				if (distanceDiff > 50) {
					expandAll();
				} else if (distanceDiff < -50)
					collapseAll();
				handled = true;
			}
			break;
		default:
			break;
		}

		if (handled) {
			return true;
		} else {
			return getExpandableListView().onTouchEvent(event);
		}
	}
	
	/**
	 * collapses all the groups in the list
	 */
	private void collapseAll() {
		ExpandableListAdapter adapter = getExpandableListAdapter();
		ExpandableListView listView = getExpandableListView();
		int groupCount = adapter.getGroupCount();
		for (int i = 0; i < groupCount; i++) {
			listView.collapseGroup(i);
		}
	}

	/**
	 * expands all groups in the list
	 */
	private void expandAll() {
		ExpandableListAdapter adapter = getExpandableListAdapter();
		ExpandableListView listView = getExpandableListView();
		int groupCount = adapter.getGroupCount();
		for (int i = 0; i < groupCount; i++) {
			listView.expandGroup(i);
		}
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

}
