package com.example.com.jayway.lab.pinchlist;

import android.app.ExpandableListActivity;
import android.os.Bundle;

public class PinchList extends ExpandableListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_list);
 
        
        setListAdapter(new NameAdapter(this));
    }
}
