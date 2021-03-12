package com.lyl.binderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import com.lyl.aidlserviceapp.ISendAidlInterface;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    ISendAidlInterface iSendAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setPackage("com.lyl.aidlserviceapp");
        intent.setAction("com.lyl.aidlserviceapp.AIDLService");
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                int value = Integer.parseInt(editText.getText().toString());
                if (iSendAidlInterface != null) {
                    try {
                        int result = iSendAidlInterface.read(value);
                        editText.setText("" + result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iSendAidlInterface = ISendAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iSendAidlInterface = null;
    }
}
