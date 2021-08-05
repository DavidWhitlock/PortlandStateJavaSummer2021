package edu.pdx.cs410J.whitlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import edu.pdx.cs410J.whitlock.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final int GET_SUM_FROM_CALCULATOR = 42;
    private ActivityMainBinding binding;
    private ArrayAdapter<Double> sums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appointment appointment = new Appointment("Teach Java", "07/28/2021 5:30 PM", "07/28/2021 9:00 PM");
                Snackbar.make(view, appointment.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button launchCalculator = findViewById(R.id.launch_calculator);
        launchCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivityForResult(intent, GET_SUM_FROM_CALCULATOR);
            }
        });

        this.sums = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ListView listOfSums = findViewById(R.id.sums);
        listOfSums.setAdapter(this.sums);
        listOfSums.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, "Selected item " + i + ": " + item, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GET_SUM_FROM_CALCULATOR && data != null) {
            double sum = data.getDoubleExtra(CalculatorActivity.EXTRA_SUM, 0.0);
            this.sums.add(sum);
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