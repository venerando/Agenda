package com.geovanni.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by geovanni on 19/07/17.
 * BroadcastReceiver = Tratar eventos do sistema
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Chegou um SMS", Toast.LENGTH_SHORT).show();
    }
}
