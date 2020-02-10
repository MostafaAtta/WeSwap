package com.atta.weswap;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        if (getIntent().getExtras() != null){
            int catId = getIntent().getIntExtra("cat_id", 0);

            Toast.makeText(this, Integer.toString(catId), Toast.LENGTH_LONG).show();
        }
    }
}
