package com.geovanni.agenda.firebase;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.dto.AlunoSync;
import com.geovanni.agenda.event.AtualizaListaAlunoEvent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

/**
 * Created by geovanni on 14/08/17.
 */

public class AgendaMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> mensagem = remoteMessage.getData();

        Log.i("mensagem recebida", String.valueOf(mensagem));

        converteParaAluno(mensagem);
    }

    private void converteParaAluno(Map<String, String> mensagem) {

        String chaveDeAcesso = "alunoSync";

        if (mensagem.containsKey(chaveDeAcesso)){
            String json = mensagem.get(chaveDeAcesso);

            ObjectMapper mapper = new ObjectMapper();

            try {
                AlunoSync alunoSync = mapper.readValue(json, AlunoSync.class);

                AlunoDAO alunoDAO = new AlunoDAO(this);

                alunoDAO.sincroniza(alunoSync.getAlunos());

                alunoDAO.close();

                EventBus eventBus = EventBus.getDefault();


                eventBus.post(new AtualizaListaAlunoEvent());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
