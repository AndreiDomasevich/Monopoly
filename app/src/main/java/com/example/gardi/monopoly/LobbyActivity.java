package com.example.gardi.monopoly;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class LobbyActivity extends AppCompatActivity {

    private Integer[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.LTGRAY};
    private Spinner serverSpinner, client1Spinner, client2Spinner, client3Spinner;
    private String role;
    private ImageButton client1ImageButton, client2ImageButton, client3ImageButton;
    private Button startButton, exitButton;
    private EditText serverEditText, client1EditText, client2EditText, client3EditText;
    private CheckBox client1CheckBox, client2CheckBox, client3CheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Intent intent = getIntent();
        role = intent.getStringExtra("Role");

        findView();
        setSpinnerAdapter();
        setVisible();
    }

    public void findView() {
        findSpinner();
        findImageButton();
        findButton();
        findEdiText();
        findCheckBox();
    }

    public void findSpinner() {
        serverSpinner = (Spinner) findViewById(R.id.serverSpinner);
        client1Spinner = (Spinner) findViewById(R.id.client1Spinner);
        client2Spinner = (Spinner) findViewById(R.id.client2Spinner);
        client3Spinner = (Spinner) findViewById(R.id.client3Spinner);
    }

    public void findImageButton() {
        client1ImageButton = (ImageButton) findViewById(R.id.client1ImageButton);
        client2ImageButton = (ImageButton) findViewById(R.id.client2ImageButton);
        client3ImageButton = (ImageButton) findViewById(R.id.client3ImageButton);
    }

    public void findButton() {
        startButton = (Button) findViewById(R.id.startButton);
        exitButton = (Button) findViewById(R.id.exitButton);
    }

    public void findEdiText() {
        serverEditText = (EditText) findViewById(R.id.serverEditText);
        client1EditText = (EditText) findViewById(R.id.client1EditText);
        client2EditText = (EditText) findViewById(R.id.client2EditText);
        client3EditText = (EditText) findViewById(R.id.client3EditText);
    }

    public void findCheckBox() {
        client1CheckBox = (CheckBox) findViewById(R.id.client1CheckBox);
        client2CheckBox = (CheckBox) findViewById(R.id.client2CheckBox);
        client3CheckBox = (CheckBox) findViewById(R.id.client3CheckBox);
    }

    public void setVisible() {
        if(role == "Client") {
            client1ImageButton.setVisibility(View.INVISIBLE);
            client2ImageButton.setVisibility(View.INVISIBLE);
            client3ImageButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setSpinnerAdapter() {
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item,
                colors);

        serverSpinner.setAdapter(customSpinnerAdapter);
        client1Spinner.setAdapter(customSpinnerAdapter);
        client2Spinner.setAdapter(customSpinnerAdapter);
        client3Spinner.setAdapter(customSpinnerAdapter);
    }
}
