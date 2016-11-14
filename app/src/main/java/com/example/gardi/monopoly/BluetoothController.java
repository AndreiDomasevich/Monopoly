package com.example.gardi.monopoly;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by gardi on 14.11.2016.
 */
public class BluetoothController {

    private BluetoothAdapter bluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    private final static UUID uuid = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB");

    private  Activity activity;

    BluetoothController(Activity activity) {
        this.activity = activity;
    }

    public boolean getAdapter() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter  == null)
            return false;
        else
            return true;
    }

    public void turnOnBluetooth() {
        if(!bluetoothAdapter.isEnabled()) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
        }
    }

    public String getAddress() {
        return bluetoothAdapter.getAddress();
    }

}
