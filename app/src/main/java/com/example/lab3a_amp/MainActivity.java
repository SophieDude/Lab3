package com.example.lab3a_amp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public String NumberScreen;
    public BigDecimal lhs;
    public String operator;
    public BigDecimal rhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Genesis Does What Nintendon't", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        NumberScreen = "0";
    }

    public void CalButtononClick(View v){

        String buttonText = ((Button) v) .getText().toString();

        if (buttonText.equals("C")) {
            NumberScreen = "0";
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText(String.valueOf(NumberScreen));
        }
        else if (NumberScreen.equals("0")) {
            if (!buttonText.equals("=")){
                NumberScreen = buttonText;
                TextView t = (TextView) findViewById(R.id.textView);
                t.setText(String.valueOf(NumberScreen));
                lhs = new BigDecimal(NumberScreen);

            }
        }
        else if (!buttonText.equals("=")) {
            NumberScreen = NumberScreen + buttonText;
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText(String.valueOf(NumberScreen));
        }
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
}
