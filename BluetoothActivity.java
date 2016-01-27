package com.st.devicefeaturesdemo;

import java.io.File;
import java.net.URI;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BluetoothActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;
    private boolean bluetooth = false;
    private Spinner spinnerDevices;
    ArrayAdapter<String> devices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Sorry no Bluetooth is supported", Toast.LENGTH_LONG).show();
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else
            bluetooth = true;

        spinnerDevices = (Spinner) this.findViewById(R.id.spinnerDevices);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            bluetooth = true;
    }

    public void getDevices(View v) {
        if (!bluetooth) {
            Toast.makeText(this, "Sorry no Bluetooth is supported", Toast.LENGTH_LONG).show();
            return;
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        devices = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                devices.add(device.getName() + ":" + device.getAddress());
            }
            spinnerDevices.setAdapter(devices);
        }
    }

    public void transferFileWithIntent(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setPackage("com.android.bluetooth");  // use only bluetooth application
        intent.setType("*/*");
        // create uri for file
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.d("Bluetooth", "Music Directory : " + path.toString());
        File filename = new File(path, "wonderfullife.mp3");

        Uri uri = Uri.fromFile(filename);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        String mac  = getSelectedDevice();
//        Log.d("Bluetooth","Selected Deviced : " + mac);
//        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mac);
        startActivity(intent);

    }

    private String getSelectedDevice() {
        String device = devices.getItem(spinnerDevices.getSelectedItemPosition());
        String mac = device.substring(device.indexOf(":") + 1);
        return mac;
    }


}
