package com.garciaericn.t2d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 1/17/15.
 */
public class PowerMonitorReceiver extends BroadcastReceiver {

    private float currentBatteryLevel;

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

        if (isCharging) {
            Toast.makeText(context, "Device is charging", Toast.LENGTH_SHORT).show();
        }

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        currentBatteryLevel = level / (float) scale;

        Toast.makeText(context, "Current battery level: " + Float.toString(currentBatteryLevel) + "%", Toast.LENGTH_SHORT).show();
    }
}