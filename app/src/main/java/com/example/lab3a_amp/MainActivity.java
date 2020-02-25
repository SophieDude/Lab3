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
    public BigDecimal lhs = null;
    public String operator = null;
    public BigDecimal rhs = null;
    public BigDecimal result = null;
    public Operator currentOperator;
    public String NewNumber;
    public boolean addDecimal;

    private enum Operator {

        ADD,SUBTRACT,MULTIPLY,DIVIDE,MODULUS;
    }

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
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(String.valueOf(NumberScreen));
        NewNumber = "True";
    }

    public void CalButtononClick(View v){

        String buttonText = ((Button) v) .getText().toString();

        TextView t = (TextView) findViewById(R.id.textView);

        switch(buttonText) {
            case "+":
                if (lhs == null) {
                    currentOperator = Operator.ADD;
                    lhs = new BigDecimal(NumberScreen);
                    NewNumber = "True";
                    break;
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                    t.setText(String.valueOf(NumberScreen));
                    evaluateExpression();
                    NumberScreen = result.toString();
                    lhs = new BigDecimal(NumberScreen);
                    currentOperator = Operator.ADD;
                    NewNumber = "True";
                    break;
                }
            case "-":
                if (lhs == null) {
                    currentOperator = Operator.SUBTRACT;
                    lhs = new BigDecimal(NumberScreen);
                    NewNumber = "True";
                    break;
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                    t.setText(String.valueOf(NumberScreen));
                    evaluateExpression();
                    NumberScreen = result.toString();
                    lhs = new BigDecimal(NumberScreen);
                    currentOperator = Operator.SUBTRACT;
                    NewNumber = "True";
                    break;
                }
            case "\u00D7":
                if (lhs == null) {
                    currentOperator = Operator.MULTIPLY;
                    lhs = new BigDecimal(NumberScreen);
                    NewNumber = "True";
                    break;
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                    t.setText(String.valueOf(NumberScreen));
                    evaluateExpression();
                    NumberScreen = result.toString();
                    lhs = new BigDecimal(NumberScreen);
                    currentOperator = Operator.MULTIPLY;
                    NewNumber = "True";
                    break;
                }
            case "\u00F7":
                if (lhs == null) {
                    currentOperator = Operator.DIVIDE;
                    lhs = new BigDecimal(NumberScreen);
                    NewNumber = "True";
                    break;
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                    t.setText(String.valueOf(NumberScreen));
                    evaluateExpression();
                    NumberScreen = result.toString();
                    lhs = new BigDecimal(NumberScreen);
                    currentOperator = Operator.DIVIDE;
                    NewNumber = "True";
                    break;
                }
            case "%":
                lhs = new BigDecimal(NumberScreen);
                currentOperator = Operator.MODULUS;
                evaluateExpression();
                NumberScreen = result.toString();
                t.setText(String.valueOf(NumberScreen));
                NewNumber = "True";
                break;
            case "\u221A":
                double current = Double.parseDouble(NumberScreen);
                double squareroot = Math.pow(current, 0.5);
                NumberScreen = String.valueOf(squareroot);
                t.setText(String.valueOf(NumberScreen));
                if (rhs == null) {
                    lhs = new BigDecimal(NumberScreen);
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                }
                break;
            case "\u00B1":
                NumberScreen = ((new BigDecimal(NumberScreen)).negate().toString());
                if (rhs == null) {
                    lhs = new BigDecimal(NumberScreen);
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                }
                t.setText(String.valueOf(NumberScreen));
                break;
            case ".":
                addDecimal();
                if (rhs == null) {
                    lhs = new BigDecimal(NumberScreen);
                }
                else {
                    rhs = new BigDecimal(NumberScreen);
                }
                t.setText(String.valueOf(NumberScreen));
                break;
            case "C":
                NumberScreen = "0";
                t.setText(String.valueOf(NumberScreen));
                lhs.equals(null);
                rhs.equals(null);
                NewNumber = "True";
                break;
            case "=":
                rhs = new BigDecimal(NumberScreen);
                evaluateExpression();
                NumberScreen = result.toString();
                t.setText(String.valueOf(NumberScreen));
                NewNumber = "True";
                break;
            default:
                if (NewNumber.equals("True")){
                    NumberScreen = buttonText;
                    NewNumber = "False";
                }
                else {
                    if (addDecimal) {
                        NumberScreen = NumberScreen.concat(".");
                        addDecimal = false;
                    }

                    NumberScreen = NumberScreen + buttonText;
                }
                t.setText(String.valueOf(NumberScreen));
                break;

        }
    }

    private void addDecimal() {

        if (!NumberScreen.contains(".")){

            addDecimal = true;
            if (NewNumber.equals("True")){
                NumberScreen = "0";
            }
        }

    }

    private void evaluateExpression() {
        if (lhs == null) {
            lhs = new BigDecimal(NumberScreen);
        }
        if (rhs == null) {
            rhs = new BigDecimal(NumberScreen);
        }
        switch(currentOperator) {
            case ADD:
                result = lhs.add(rhs);
                lhs = result;
                break;
            case SUBTRACT:
                result = lhs.subtract(rhs);
                lhs = result;
                break;
            case MULTIPLY:
                result = lhs.multiply(rhs);
                lhs = result;
                break;
            case DIVIDE:
                result = lhs.divide(rhs);
                lhs = result;
                break;
            case MODULUS:
                result = lhs.remainder(rhs);
                lhs = result;
                break;
            default:
                break;
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
