package com.example.gardi.monopoly;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by gardi on 14.11.2016.
 */
public class BluetoothController {

    public BluetoothAdapter bluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    public final static UUID uuid = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public BroadcastReceiver actionFoundReceiver;

    public ArrayAdapter<String> btArrayAdapter;
    private  Activity activity;

    BluetoothController(Activity activity) {
        this.activity = activity;

        btArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1);
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

    public void turnOffBluetooth() {
        if(bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

    public void startDiscovery() {
        if(bluetoothAdapter.isEnabled()) {
            btArrayAdapter.clear();
            bluetoothAdapter.startDiscovery();
        }
    }

    public void setBroadcastReceiver() {
        actionFoundReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device  = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    btArrayAdapter.add(device.getName()+"\n"+device.getAddress());
                    btArrayAdapter.notifyDataSetChanged();
                    bluetoothAdapter.cancelDiscovery();
                }
            }
        };
    }

    public String getAddress() {
        return bluetoothAdapter.getAddress();
    }
}
