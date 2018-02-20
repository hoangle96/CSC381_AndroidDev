package com.example.leh2.travel_java;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leh2.travel_java.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //declare a global JSONarray -- the array of objects we will get from reading the JSON file
    JSONArray myJSON_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tvSire = (TextView) findViewById(R.id.tvSire);
        final TextView tvTrainer = (TextView) findViewById(R.id.tvTrainer);
        final ImageView ivSilks = (ImageView) findViewById(R.id.imgV);
        Button btnClip = (Button) findViewById(R.id.btnShow);
        //associate the full name with the listView
        final Spinner spHorse = (Spinner) findViewById(R.id.spHorse);

        try{
            //http://stackoverflow.com/questions/6420293/reading-android-raw-text-file
            InputStream is = getResources().openRawResource(R.raw.sites);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);

            String myText = new String(buffer);
            //Toast.makeText(getBaseContext(), "Got here", Toast.LENGTH_LONG).show();
            //http://stackoverflow.com/questions/9605913/how-to-parse-json-in-android
            //extract the main (top-level) JSON object from the file read above
            JSONObject myJSON_object = new JSONObject(myText);

            //the main JSON object is/includes an array called horses
            //extract that array
            myJSON_array = myJSON_object.getJSONArray("sites");

            //create a String array of horse rank and name -- used to populate spinner
            String[] site_name_and_city = new String[myJSON_array.length()];
            for (int i=0; i < myJSON_array.length(); i++){
                try{
                    //get an individual element of the JSON array
                    JSONObject aJSON_element = myJSON_array.getJSONObject(i);
                    //get the individual properties of the JSON element
                    String site_name = aJSON_element.getString("name");
                    String site_city= aJSON_element.getString("city");
                    //make the employee full name
                    site_name_and_city[i] = site_name + " - " + site_city;

                }
                catch (JSONException e)
                {
                    Toast.makeText(getBaseContext(), "Dude, you have to know what the JSON looks like to parse it", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }




            ArrayAdapter<String> aaHorse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, site_name_and_city);
            aaHorse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spHorse.setAdapter(aaHorse);

            spHorse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tvUserItem = (TextView) view;
                    Toast.makeText(getApplicationContext(), "You chose "+tvUserItem.getText().toString() , Toast.LENGTH_SHORT).show();
                    //tvSire.setText("-"+i);
                    try {
                        tvSire.setText("  " + myJSON_array.getJSONObject(i).getString("horseSire"));
                        tvTrainer.setText("  " + myJSON_array.getJSONObject(i).getString("horseTrainer"));
                        int iv_id = getResources().getIdentifier(myJSON_array.getJSONObject(i).getString("horseSilks"), "drawable",
                                getPackageName());
                        ivSilks.setImageResource(iv_id);
                    }catch(Exception e){

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
        catch(Exception e){
            Toast.makeText(getBaseContext(), "Problem with file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //*****************************************************************************************
        btnClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = spHorse.getSelectedItemPosition();
                //Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();

                try {
                    String ytc = myJSON_array.getJSONObject(id).getString("horseYouTubeCode");
                    //Toast.makeText(getApplicationContext(), ytc, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+ytc)));
                }catch(JSONException e){

                }

            } //end onClick
        });//end setOnClickListener


    }//end onCreate
}//end MainActivity11


