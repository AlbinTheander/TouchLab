package com.example.com.jayway.lab.pinchlist;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class PinchList extends ExpandableListActivity implements
		OnTouchListener {

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
}
