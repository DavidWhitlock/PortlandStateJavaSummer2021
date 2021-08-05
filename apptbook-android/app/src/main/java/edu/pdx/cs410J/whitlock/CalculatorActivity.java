package edu.pdx.cs410J.whitlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(view -> addOperandsAndDisplaySum());
    }

    private void addOperandsAndDisplaySum() {
        EditText leftOperand = findViewById(R.id.left_operand);
        EditText rightOperand = findViewById(R.id.right_operand);

        double leftNumber;
        String leftString = leftOperand.getText().toString();
        try {
            leftNumber = Double.parseDouble(leftString);

        } catch (NumberFormatException ex) {
            displayErrorMessage("Cannot parse left operand: " + leftString);
            return;
        }
        double rightNumber;
        String rightString = rightOperand.getText().toString();
        try {
            rightNumber = Double.parseDouble(rightString);
        } catch (NumberFormatException ex) {
            displayErrorMessage("Cannot parse right operand: " + rightString);
            return;
        }


        TextView sum = findViewById(R.id.sum);
        sum.setText(String.valueOf(leftNumber + rightNumber));
    }

    private void displayErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}