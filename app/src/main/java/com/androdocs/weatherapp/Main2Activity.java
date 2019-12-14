package com.androdocs.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find the View that shows the numbers category
        TextView cerah = (TextView) findViewById(R.id.cerah);

        // Set a click listener on that View
        cerah.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent cerahIntent = new Intent(Main2Activity.this,CerahActivity.class);

                // Start the new activity
                startActivity(cerahIntent);
            }
        });

        // Find the View that shows the family category
        TextView hujan = (TextView) findViewById(R.id.hujan);

        // Set a click listener on that View
        hujan.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent hujanIntent = new Intent(Main2Activity.this, HujanActivity.class);

                // Start the new activity
                startActivity(hujanIntent);
            }
        });

        // Find the View that shows the colors category
        TextView panas = (TextView) findViewById(R.id.panas);

        // Set a click listener on that View
        panas.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ColorsActivity}
                Intent panasIntent = new Intent(Main2Activity.this,PanasActivity.class);

                // Start the new activity
                startActivity(panasIntent);
            }
        });
    }
}
