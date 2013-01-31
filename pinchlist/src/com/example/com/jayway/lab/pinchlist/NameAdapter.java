package com.example.com.jayway.lab.pinchlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class NameAdapter extends BaseExpandableListAdapter {

	private static final String[] LAST_NAMES = { "Andersson", "Bengtsson",
			"Carlquist", "Davidsson", "Eklšv" };
	private static final String[] FIRST_NAMES = { "Fredrika", "Gusten",
			"Harald", "Ingeborg", "Jon" };

	private Context context;

	public NameAdapter(Context context) {
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return FIRST_NAMES[childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					android.R.layout.simple_expandable_list_item_2, null);
		}
		TextView textView = (TextView) view.findViewById(android.R.id.text2);
		textView.setText(FIRST_NAMES[childPosition]);
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return FIRST_NAMES.length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return LAST_NAMES[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return LAST_NAMES.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					android.R.layout.simple_expandable_list_item_2, null);
		}
		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(LAST_NAMES[groupPosition]);
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
