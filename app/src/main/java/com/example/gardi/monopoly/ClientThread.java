package com.example.gardi.monopoly;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by gardi on 15.11.2016.
 */
public class ClientThread extends Thread {
    private BluetoothSocket bluetoothSocket;
    private BluetoothDevice bluetoothDevice;
    private ConnectedThread connectedThread;

    ClientThread(BluetoothAdapter bluetoothAdapter, UUID uuid) {
        BluetoothSocket tmpBluetoothSocket = null;
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(bluetoothAdapter.getAddress());

        try {
            tmpBluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        } catch(IOException e) {}

        bluetoothSocket = tmpBluetoothSocket;
    }

    @Override
    public void run() {
        try {
            bluetoothSocket.connect();
        } catch(IOException e) {
            try {
                bluetoothSocket.close();
            }
            catch (IOException closeException) {
                return;
            }
        }

        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
    }
}
