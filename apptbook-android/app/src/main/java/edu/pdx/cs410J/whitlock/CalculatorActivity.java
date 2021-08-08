package edu.pdx.cs410J.whitlock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    public static final String EXTRA_SUM = "Sum";
    public static final String EXTRA_APPOINTMENT = "Appointment";
    private double sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(view -> addOperandsAndDisplaySum());

        Button returnToMain = findViewById(R.id.return_to_main);
        returnToMain.setOnClickListener(view -> sendSumAndAppointmentBackToMain());
    }

    private void sendSumAndAppointmentBackToMain() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_SUM, this.sum);

        Appointment appointment = new Appointment("Appointment for sum: " + this.sum, "Begin", "End");
        intent.putExtra(EXTRA_APPOINTMENT, appointment);

        setResult(RESULT_OK, intent);
        finish();
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
        this.sum = leftNumber + rightNumber;
        sum.setText(String.valueOf(this.sum));
    }

    private void displayErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}