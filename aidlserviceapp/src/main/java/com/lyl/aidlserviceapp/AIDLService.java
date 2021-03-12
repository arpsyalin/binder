package com.lyl.aidlserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;


public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    private Binder binder = new ISendAidlInterface.Stub() {
        @Override
        public int read(int count) throws RemoteException {
            return count * 2;
        }

    };

}
