package com.example.untitled;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.google.android.gms.location.LocationResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

import static android.util.Log.e;

public class LocationReceiver extends BroadcastReceiver {
    static final String ACTION_PROCESS_UPDATES = "com.google.android.gms.location.sample.locationupdatespendingintent.action.PROCESS_UPDATES";
    private FlutterEngine flutterEngine = MainActivity.engine;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(ACTION_PROCESS_UPDATES)) {
            LocationResult result = LocationResult.extractResult(intent);
            e("22", "lr -> onReceive  ->  : Receiving" + result);
            if (result != null) {
                List<Location> locations = result.getLocations();
                try {
//
                    for (Location l : locations) {
                        new sqliteHelper(context).addLocation(l.getTime(),l.getLatitude(),l.getLongitude(),l.getAccuracy());
//                        String CHANNEL = "sample.flutter.io/nativeCall";
//                        MethodChannel methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
//                        Map<String, String> map = new LinkedHashMap<>();
//                        map.put("time", String.valueOf(l.getTime()));
//                        map.put("lat", String.valueOf(l.getLatitude()));
//                        map.put("long", String.valueOf(l.getLongitude()));
//                        map.put("acc", String.valueOf(l.getAccuracy()));
//                        methodChannel.invokeMethod("getNativeCall", map);
                    }
                } catch (Exception e) {
                    e("28", "lr -> onReceive  ->  : " + e.getMessage());
                }
            }
        }
    }
}