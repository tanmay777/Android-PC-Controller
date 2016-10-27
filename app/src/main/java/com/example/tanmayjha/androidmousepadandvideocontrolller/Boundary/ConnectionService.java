package com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by tanmay jha on 27-10-2016.
 */

public class ConnectionService extends Service {

    public ConnectionService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
