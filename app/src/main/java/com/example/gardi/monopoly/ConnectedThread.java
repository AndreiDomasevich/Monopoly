package com.example.gardi.monopoly;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gardi on 15.11.2016.
 */
public class ConnectedThread extends Thread{
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    ConnectedThread(BluetoothSocket bluetoothSocket) {
        this.bluetoothSocket = bluetoothSocket;
        InputStream tmpInputStream = null;
        OutputStream tmpOutputStream = null;

        try {
            tmpOutputStream = this.bluetoothSocket.getOutputStream();
            tmpInputStream = this.bluetoothSocket.getInputStream();
        } catch(IOException e) {}

        inputStream = tmpInputStream;
        outputStream = tmpOutputStream;
    }

    public void write(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {}
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while(true) {
            try {
                bytes = inputStream.read(buffer);
            } catch(IOException e) {
                break;
            }
        }
    }
}
