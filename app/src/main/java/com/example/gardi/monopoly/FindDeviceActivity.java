package com.example.gardi.monopoly;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FindDeviceActivity extends AppCompatActivity {

    private Button enableBtButton, disableBtButton, findDevicesButton;
    private BluetoothController bluetoothController;
    private ListView devicesListView;
    private View.OnClickListener enableBTListener,disableBTlistener,searchDeviceslistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_device);

        bluetoothController = new BluetoothController(this);
        bluetoothController.getAdapter();
        bluetoothController.setBroadcastReceiver();

        enableBtButton = (Button) findViewById(R.id.enableBtButton);
        disableBtButton = (Button) findViewById(R.id.disableBtButton);
        findDevicesButton = (Button) findViewById(R.id.findDevicesButton);
        devicesListView = (ListView) findViewById(R.id.devicesListView);

        devicesListView.setAdapter(bluetoothController.btArrayAdapter);

        createButtonListeners();
        createListViewListener();

        registerReceiver(bluetoothController.actionFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    public void createButtonListeners() {
        enableBTListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothController.turnOnBluetooth();
            }
        };

        enableBtButton.setOnClickListener(enableBTListener);

        disableBTlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothController.turnOffBluetooth();
            }
        };

        disableBtButton.setOnClickListener(disableBTlistener);

        searchDeviceslistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothController.startDiscovery();
            }
        };

        findDevicesButton.setOnClickListener(searchDeviceslistener);
    }

    public void createListViewListener() {
        devicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String string = devicesListView.getItemAtPosition(position).toString();
                String[] adress = string.split("\n");
            }
        });
    }
}