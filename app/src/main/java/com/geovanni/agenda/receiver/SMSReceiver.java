package com.geovanni.agenda.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.geovanni.agenda.dao.AlunoDAO;

/**
 * Created by geovanni on 19/07/17.
 * BroadcastReceiver = Tratar eventos do sistema
 */

public class SMSReceiver extends BroadcastReceiver {
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);

        if(dao.ehAluno(telefone)){
            Toast.makeText(context, "Chegou um SMS", Toast.LENGTH_SHORT).show();
        }

        dao.close();

    }
}
