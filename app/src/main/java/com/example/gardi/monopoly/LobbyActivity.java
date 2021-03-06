package com.example.gardi.monopoly;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class LobbyActivity extends AppCompatActivity {

    private Integer[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.LTGRAY};
    private Spinner serverSpinner, client1Spinner, client2Spinner, client3Spinner;
    private String role;
    private ImageButton client1ImageButton, client2ImageButton, client3ImageButton;
    private Button startButton, exitButton;
    private EditText serverEditText, client1EditText, client2EditText, client3EditText;
    private CheckBox client1CheckBox, client2CheckBox, client3CheckBox;
    private User user;
    private BluetoothController bluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Intent intent = getIntent();
        role = intent.getStringExtra("Role");

        findView();
        setSpinnerAdapter();
        setVisible();

        if(role.equals("Server")) {
            startServerThread();
        }
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
        if(role.equals("Client") ) {
            client1ImageButton.setEnabled(false);
            client1ImageButton.setFocusable(false);
            client2ImageButton.setEnabled(false);
            client2ImageButton.setFocusable(false);
            client3ImageButton.setEnabled(false);
            client3ImageButton.setFocusable(false);
            startButton.setEnabled(false);
            startButton.setFocusable(false);
        }

        if(role.equals("Server") ) {
            client1Spinner.setEnabled(false);
            client1Spinner.setFocusable(false);
            client1EditText.setEnabled(false);
            client1EditText.setFocusable(false);
            client1CheckBox.setEnabled(false);
            client1CheckBox.setFocusable(false);

            client2Spinner.setEnabled(false);
            client2Spinner.setFocusable(false);
            client2EditText.setEnabled(false);
            client2EditText.setFocusable(false);
            client2CheckBox.setEnabled(false);
            client2CheckBox.setFocusable(false);

            client3Spinner.setEnabled(false);
            client3Spinner.setFocusable(false);
            client3EditText.setEnabled(false);
            client3EditText.setFocusable(false);
            client3CheckBox.setEnabled(false);
            client3CheckBox.setFocusable(false);
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

    public boolean setBluetoothController() {
        bluetoothController = new BluetoothController(this);
        if(bluetoothController.getAdapter()) {
            bluetoothController.turnOnBluetooth();
            return true;
        }

        return false;
    }

    public void startServerThread() {
        setBluetoothController();

        ServerThread serverThread = new ServerThread(bluetoothController.bluetoothAdapter, BluetoothController.uuid);

        serverThread.start();
    }
}
