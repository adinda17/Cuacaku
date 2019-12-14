package com.androdocs.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class CerahActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerah);


        //image button intent
        ImageButton laguCerah = (ImageButton) findViewById(R.id.lagu_cerah);

        laguCerah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CerahActivity.this, "clicked", Toast.LENGTH_LONG).show();

                Intent Lcerah = new  Intent(CerahActivity.this, LcerahActivity.class);
                startActivity(Lcerah);
            }
        });
    }
    private Button btnBack;
    int quantity = 0;


    public void increment(View view){
        if (quantity == 100){
            //show an error message as a toast
            Toast.makeText(this,"ga kelebihan nih?", Toast.LENGTH_SHORT ).show();
            //exit this method early because nothing to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if (quantity == 1){
            //show an error message as a toast
            Toast.makeText(this,"masa gaada sih", Toast.LENGTH_SHORT ).show();
            //exit this method early because nothing to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    private void displayQuantity(int numberOfFoods) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfFoods);
    }

    public void submitKirim(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
//        Editable nameEditable = nameField.getText();
        String name = nameField.getText().toString();

// Get user's cerita
        EditText ceritaField = (EditText) findViewById(R.id.cerita_field);
//        Editable ceritaEditable = ceritaField.getText();
        String cerita = ceritaField.getText().toString();

        // yang bikin happy
        CheckBox familyCheckBox = (CheckBox) findViewById(R.id.family_cb);
        boolean hasFamily = familyCheckBox.isChecked();


        CheckBox pacarCheckBox = (CheckBox) findViewById(R.id.pcr_cb);
        boolean hasPacar = pacarCheckBox.isChecked();


        CheckBox strangerCheckBox = (CheckBox) findViewById(R.id.st_cb);
        boolean hasStranger = strangerCheckBox.isChecked();


        CheckBox temanCheckBox = (CheckBox) findViewById(R.id.tmn_cb);
        boolean hasTeman = temanCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasFamily, hasPacar , hasStranger , hasTeman);

        // Display the order summary on the screen
        String priceMessage = createOrderSummary(name, cerita, price, hasFamily, hasPacar , hasStranger , hasTeman);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Catatan kebahagiaan mu " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            finish();
        }
    }
    /**
     * Calculates the price of the order.
     *
     * @param addFamily is whether or not we should include pempek in the price
     * @param addPacar   is whether or not we should include gandus in the price
     * @param addStranger   is whether or not we should include bongkol in the price
     * @param addTeman  is whether or not we should include risol in the price
     * @return  total poin bahagia mu price
     */
    private int calculatePrice(boolean addFamily, boolean addPacar , boolean addStranger , boolean addTeman) {
        // First calculate the price of one appetizer
        int basePrice = 5;

        // If the family made user happy, add 10000
        if (addFamily) {
            basePrice = basePrice + 10;
        }

        // If the pacar made user happy, add 10000
        if (addPacar) {
            basePrice = basePrice + 10;
        }

        // If the stranger made user happy, add 10000
        if (addStranger) {
            basePrice = basePrice + 10;
        }

        // If the teman made user happy, add 10000
        if (addTeman) {
            basePrice = basePrice + 10;
        }



        // Calculate the total poin bahagia mu price by multiplying by the quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param name            on the order
     * @param price           of the order
     * @param addFamily is whether or not to add pempek
     * @param addPacar    is whether or not to add kue gandus
     * @param addStranger    is whether or not to add bongkol
     * @param addTeman   is whether or not to add risol
     * @return text summary
     */
    private String createOrderSummary(String name, String cerita,int price,boolean addFamily, boolean addPacar , boolean 	addStranger , boolean addTeman) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_family, addFamily);
        priceMessage += "\n" + getString(R.string.order_summary_pacar, addPacar);
        priceMessage += "\n" + getString(R.string.order_summary_stranger, addStranger);
        priceMessage += "\n" + getString(R.string.order_summary_teman, addTeman);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" +  getString(R.string.order_summary_cerita, cerita);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }






}

