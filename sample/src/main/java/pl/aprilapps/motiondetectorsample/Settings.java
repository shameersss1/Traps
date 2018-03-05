package pl.aprilapps.motiondetectorsample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Settings extends Activity {

    EditText et1,et2,et3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Gwalior");
        categories.add("Bhopal");


        List<String> categories1 = new ArrayList<String>();
        categories1.add("Hazira Thana");
        categories1.add("Gole Ka Mandir");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);


       et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        et3 = (EditText) findViewById(R.id.editText5);



        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(Settings.this);

        // Get the value for the run counter
        String name= app_preferences.getString("name", "");
        String phone= app_preferences.getString("phone", "");
        String add= app_preferences.getString("add", "");


        et1.setText(name);
        et2.setText(phone);
        et3.setText(add);


        Button b = (Button) findViewById(R.id.button);

       final SharedPreferences.Editor editor = app_preferences.edit();



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       String name1 = et1.getText().toString();
       String phone1 = et2.getText().toString();
       String add1 = et3.getText().toString();

                editor.putString("name",name1);
                editor.putString("phone",phone1);
                editor.putString("add", add1);
                editor.commit(); // Very important

                Toast.makeText(getApplicationContext(),"Successfully Updated" ,Toast.LENGTH_SHORT).show();

//                Intent i = new Intent(Settings.this,MotionDetectionActivity.class);
//                startActivity(i);


            }
        });



    }
}
