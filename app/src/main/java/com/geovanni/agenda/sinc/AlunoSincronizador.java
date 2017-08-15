package com.geovanni.agenda.sinc;

import android.content.Context;
import android.util.Log;

import com.geovanni.agenda.ListaAlunos;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.dto.AlunoSync;
import com.geovanni.agenda.event.AtualizaListaAlunoEvent;
import com.geovanni.agenda.retrofit.RetrofitInicializador;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoSincronizador {
    private final Context context;
    private EventBus bus = EventBus.getDefault();

    public AlunoSincronizador(Context context) {
        this.context = context;
    }

    public void buscaAlunos() {
        Call<AlunoSync> call = new RetrofitInicializador().getAlunoService().lista();
        call.enqueue(new Callback<AlunoSync>() {
            @Override
            public void onResponse(Call<AlunoSync> call, Response<AlunoSync> response) {
                AlunoSync alunosSync = response.body();
                AlunoDAO dao = new AlunoDAO(context);
                dao.sincroniza(alunosSync.getAlunos());
                dao.close();
                bus.post(new AtualizaListaAlunoEvent());
            }

            @Override
            public void onFailure(Call<AlunoSync> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                bus.post(new AtualizaListaAlunoEvent());
            }
        });
    }
}