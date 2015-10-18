package com.wizardpoker.schedulrboilermake;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Pranav on 10/17/2015.
 */
public class listView extends Activity {

    protected void onCreate(Bundle savedInstanceStates){
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_listview);

        Schedule s = new Schedule(true, true, 1, true);

        TextView txtview = (TextView) findViewById(R.id.textView3);

        for(String item : s.refreshSchedule()) {
            Log.e("testing", item);
            txtview.setText(item);
        }

        ListView myListView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, android.R.id.text1, s.refreshSchedule());

        myListView.setAdapter(adapter);

    }
}
