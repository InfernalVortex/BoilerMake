package com.wizardpoker.schedulrboilermake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pranav on 10/17/2015.
 */
public class SettingActivity extends Activity{

    public boolean feelGood;
    public boolean workUnderPressure;
    public int breakFrequency = 0;
    CheckBox feelingGood;
    CheckBox underPressure;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceStates){

        super.onCreate(savedInstanceStates);
        setContentView(R.layout.settings);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        feelingGood = (CheckBox) findViewById(R.id.checkBox);
        underPressure = (CheckBox) findViewById(R.id.checkBox2);
        s = (Spinner) findViewById(R.id.spinner);

    }

    public void buttonClick(View v) {
        feelGood = feelingGood.isChecked();
        workUnderPressure = underPressure.isChecked();
        String text = s.getSelectedItem().toString();
        if (text.equals("Rarely"))
            breakFrequency = 0;
        else if (text.equals("Sometimes"))
            breakFrequency = 1;
        else
            breakFrequency = 2;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(
                getApplicationContext(),
                "feelingGood=" + feelGood + ", workunderpressure=" + workUnderPressure +
                        "`  breakFrequency=" + breakFrequency,
                Toast.LENGTH_SHORT
        ).show();
    }
}
