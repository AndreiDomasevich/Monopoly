package com.example.gardi.monopoly;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by gardi on 15.11.2016.
 */
public class ServerThread extends Thread {

    private BluetoothServerSocket bluetoothServerSocket;
    private ConnectedThread connectedThread;

    ServerThread(BluetoothAdapter bluetoothAdapter, UUID uuid) {
        BluetoothServerSocket tmpBluetoothServerSocket = null;

        try {
            tmpBluetoothServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("Monopoly", uuid);
        } catch(IOException e) {}

        bluetoothServerSocket = tmpBluetoothServerSocket;
    }

    @Override
    public void run() {
        BluetoothSocket bluetoothSocket = null;
        while(true) {
            try {
                bluetoothSocket = bluetoothServerSocket.accept();
            } catch(IOException e) {
                break;
            }

            if(bluetoothSocket != null) {
                connectedThread = new ConnectedThread(bluetoothSocket);
                connectedThread.start();
            }
        }
    }
}
