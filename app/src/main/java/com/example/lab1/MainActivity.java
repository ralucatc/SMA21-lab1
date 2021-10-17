package com.example.lab1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.lab1.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText eName;
    Button button;
    Button bShare;
    Button bSearch;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText) findViewById(R.id.eName);
        button = (Button) findViewById(R.id.button);
        bShare = (Button) findViewById(R.id.bShare);
        bSearch = (Button) findViewById(R.id.bSearch);

        // spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.colors,
                //android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                dialog(name);
            }
        });

        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share(v);
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search(v);
            }
        });
    }

    private void dialog(String name) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("This is the dialog")
                .setMessage(String.format("Buna %s", name))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "The message has been generated", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Dialog has been closed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();

        alertDialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String color = parent.getItemAtPosition(position).toString();

        switch (color) {
            case "Red":
                button.setTextColor(Color.RED);
            case "Yellow":
                button.setTextColor(Color.YELLOW);
            case "Blue":
                button.setTextColor(Color.BLUE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Share(View view) {
        String name = ((EditText) findViewById(R.id.eName)).getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, name);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void Search(View view) {
        String name = ((EditText) findViewById(R.id.eName)).getText().toString();
        String url = "https://www.google.com/search?q=" + name;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}