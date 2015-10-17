package com.wizardpoker.schedulrboilermake;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";

    private boolean mUnderPressure;
    private int mBreakInterval;
    private int mBreakPeriod;

    public void setUnderPressure(boolean mUnderPressure) {
        this.mUnderPressure = mUnderPressure;
    }

    public void setBreakInterval(int mBreakInterval) {
        this.mBreakInterval = mBreakInterval;
    }

    public void setBreakPeriod(int mBreakPeriod) {
        this.mBreakPeriod = mBreakPeriod;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Restore Data from previous opening if exists
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean underPressure = settings.getBoolean("underPressure", false);
        int breakInterval = settings.getInt("breakInterval", 15);
        int breakPeriod = settings.getInt("breakPeriod", 5);
        setUnderPressure(underPressure);
        setBreakInterval(breakInterval);
        setBreakPeriod(breakPeriod);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Create Editor object to make preference changes.
        // Objects from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("underPressure", mUnderPressure);
        editor.putInt("breakInterval", mBreakInterval);
        editor.putInt("breakPeriod", mBreakPeriod);

        //Commit edits
        editor.commit();
    }
}
