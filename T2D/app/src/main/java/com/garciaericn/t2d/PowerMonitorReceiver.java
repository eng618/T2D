package com.garciaericn.t2d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 1/17/15.
 */
public class PowerMonitorReceiver extends BroadcastReceiver {

    private static final String TAG = "PMReceiver.TAG";

    @Override
    public void onReceive(Context context, Intent intent) {



        // Obtain action from intent to check which broadcast is being received
        String action = intent.getAction();

        // Perform action according to type
        switch (action) {
//            case (Intent.ACTION_BATTERY_CHANGED): {
//                Log.i(TAG, "Battery has changed");
//
//                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
//                currentBatteryLevel = -1;
//
//                if (currentLevel >= 0 && scale > 0) {
//                    currentBatteryLevel = (currentLevel * 100) / scale;
//                    Toast.makeText(context, "Current battery level: " + Float.toString(currentBatteryLevel) + "%" + " level: " + currentLevel + " scale: " + scale, Toast.LENGTH_LONG).show();
//                }
//
//
//                break;
//            }
            case (Intent.ACTION_POWER_CONNECTED): {
                Log.i(TAG, "Power connected");

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

                String chargingType = null;

                if (usbCharge) {
                    chargingType = "USB";
                } else if (acCharge) {
                    chargingType = "AC Power";
                }

                if (isCharging && chargingType != null) {
                    Toast.makeText(context, "Device is charging via: " + chargingType, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Device is charging.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case (Intent.ACTION_POWER_DISCONNECTED): {
                Log.i(TAG, "Power disconnected");
                Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show();
                break;
            }
            case (Intent.ACTION_BATTERY_LOW): {
                Log.i(TAG, "Battery low");
                Toast.makeText(context, "Battery low", Toast.LENGTH_SHORT).show();
                break;
            }
            case (Intent.ACTION_BATTERY_OKAY): {
                Log.i(TAG, "Battery Okay");
                Toast.makeText(context, "Battery Okay", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
