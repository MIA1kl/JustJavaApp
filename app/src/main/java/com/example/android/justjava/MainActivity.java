package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View v) {



        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String message = createOrderSummary(name,price,hasWhippedCream,hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }


    }


    private int calculatePrice(boolean cream, boolean chocolate){
        int price = numberOfCoffees*5;
        if(cream){
            price+=numberOfCoffees;
        }
        if(chocolate){
            price+=numberOfCoffees*2;
        }
        return price;
    }

    private String createOrderSummary( String name, int price,boolean addWhippedCream, boolean addChocolate){
        String message="Name: "+name;
        message+="\nWith Whipped Cream? "+addWhippedCream;
        message+="\nWith Chocolate? "+addChocolate;
        message+="\nQuantity: "+numberOfCoffees;
        message+="\nTotal: $"+price;
        message+="\nThank You!";
        return message;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+numberOfCoffees);
    }




    public void decrement(View view) {
        if (numberOfCoffees == 1)   return;
        displayQuantity(--numberOfCoffees);
    }

    public void increment(View view) {
        if (numberOfCoffees == 100)   return;
        displayQuantity(++numberOfCoffees);
    }

}