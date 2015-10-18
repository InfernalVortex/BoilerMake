package com.wizardpoker.schedulrboilermake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";

    private boolean mUnderPressure;
    private boolean mBreakDuring;
    private int mBreakFrequency;
    private Button mSettingButtom;
    private Button mNextButton;
    private String title;
    private int time;
    private int importance;
    private EditText editName;
    private EditText editTime;
    private EditText editImportance;

    public void setUnderPressure(boolean mUnderPressure) {
        this.mUnderPressure = mUnderPressure;
    }

    public void setBreakFrequency(int mBreakFrequency) {
        this.mBreakFrequency = mBreakFrequency;
    }

    public void setBreakPeriod(boolean mBreakDuring) {
        this.mBreakDuring = mBreakDuring;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Schedule s = new Schedule(true,true,1,true);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettingButtom = (Button) findViewById(R.id.setting_Button);
        mSettingButtom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });



        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                EditText editName = (EditText) findViewById(R.id.task_Name);
                if(editName.getText().toString() == null ||
                        editName.getText().toString() == ""){
                    title = "Event";
                }
                else {
                    title = editName.getText().toString();
                }

                EditText editTime = (EditText) findViewById(R.id.task_Time);
                if(editTime.getText().toString().equals("")){
                    time = 0;
                }
                else {
                    String a = editTime.getText().toString();
                    time = Integer.parseInt(a);
                }

                EditText editImportance = (EditText) findViewById(R.id.task_Importance);
                if(editImportance.getText().toString().equals("")){
                    importance = 0;
                }
                else {
                    String b = editImportance.getText().toString();
                    importance = Integer.parseInt(b);
                }

                s.addEvent(title, time, importance);

                editName.setText("");
                editTime.setText("");
                editImportance.setText("");
            }
        });



        // Restore Data from previous opening if exists
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean underPressure = settings.getBoolean("underPressure", false);
        int breakFrequency = settings.getInt("breakFrequency", 1); // default 1 = med (30:10)
        boolean breakDuring = settings.getBoolean("breakDuring", true);
        setUnderPressure(underPressure);
        setBreakFrequency(breakFrequency);
        setBreakPeriod(breakDuring);
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
        editor.putBoolean("breakPeriod", mBreakDuring);
        editor.putInt("breakFrequency", mBreakFrequency);


        //Commit edits
        editor.commit();
    }
}
