package com.example.androidapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    double amount =0.0;
    Toolbar toolbar;
    TextView subTotal , discount , shipping,total;
    Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        amount = getIntent().getDoubleExtra("amount", 0.0);

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amt);
        paymentBtn = findViewById(R.id.pay_btn);

        subTotal.setText(amount + "$");
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethod();
            }
        });
    }
    private void paymentMethod(){

    Checkout checkout = new Checkout();

    checkout.setImage(R.drawable.rp);
    checkout.setKeyID("rzp_test_7kd514yJVUnXdU");


    final Activity activity = this;

        try {
        JSONObject options = new JSONObject();
        //Set Company Name
        options.put("name", "ARUN SINGH");
        //Ref no
        options.put("description", "Reference No. #123456");
        //Image to be display
        options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
        //options.put("order_id", "order_9A33XWu170gUtm");
            options.put("theme.color","#3399cc");
        // Currency type
        options.put("currency", "USD");
        //double total = Double.parseDouble(mAmountText.getText().toString());
        //multiply with 100 to get exact amount in rupee
        amount = amount * 100;
        //amount
        options.put("amount", amount);
        JSONObject preFill = new JSONObject();
        //email
        preFill.put("email", "arunsingh17683@gmail.com");
        //contact
        preFill.put("contact", "8383949486");

        options.put("prefill", preFill);

        checkout.open(activity, options);
    } catch (Exception e) {
        Log.e("TAG", "Error in starting Razorpay Checkout", e);
    }
}

@Override
public void onPaymentSuccess(String s) {
    Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
}

@Override
public void onPaymentError(int i, String s) {

    Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
}
}


