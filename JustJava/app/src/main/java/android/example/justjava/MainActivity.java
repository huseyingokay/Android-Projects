package android.example.justjava;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantityofCoffee = 0;
    int price = 0;
    String writing = new String();


    public void increaseOrder(View view) {
        quantityofCoffee++;
        display(quantityofCoffee);
    }

    public void decreaseOrder(View view) {
        if(quantityofCoffee!=0) {
            quantityofCoffee--;
            display(quantityofCoffee);
        }
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void submitOrder(View view) {
        EditText customerNameId = (EditText) findViewById(R.id.customerName);
        String customerName = customerNameId.getText().toString();

        CheckBox isWhippedCream = (CheckBox) findViewById(R.id.whipped_checkbox);
        CheckBox isChocolated = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = isChocolated.isChecked();
        boolean hasWhippedCream = isWhippedCream.isChecked();

        price = 0;
        price = 5*quantityofCoffee;

        if(hasChocolate==true)
            price+=2*quantityofCoffee;
        if(hasWhippedCream==true)
            price+=1*quantityofCoffee;

        displayString(customerName, hasWhippedCream, hasChocolate, price);
        composeEmail("huseyin.gokay@ozu.edu.tr","Deneme Maili");
    }

    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: " + addresses));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, writing);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void displayString(String customerName, boolean isWhippedCream, boolean hasChocolate, int price) {
        TextView priceTextView = (TextView) findViewById(R.id.message_text_view);
        writing +=  customerName;

        if(isWhippedCream)
            writing+="\n "+ getString(R.string.withWC);
        else {
            writing+="\n "+ getString(R.string.noWC);
        }

        if(hasChocolate)
            writing+="\n "+ getString(R.string.withCho);
        else
            writing+="\n "+ getString(R.string.noCho);

        writing+="\n "+ getString(R.string.finalQuantity)+quantityofCoffee + "\n " +getString(R.string.total)+ price + "$ \n " + getString(R.string.thank);
        priceTextView.setText(writing);
    }
}