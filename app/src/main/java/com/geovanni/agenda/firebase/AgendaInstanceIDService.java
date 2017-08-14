package com.geovanni.agenda.firebase;


import android.util.Log;

import com.geovanni.agenda.retrofit.RetrofitInicializador;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geovanni on 11/08/17.
 */

public class AgendaInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token Firebase", "Refreshed token: " + refreshedToken);


        enviaTokenParaServidor(refreshedToken);
    }


    private void enviaTokenParaServidor(final String token) {

        Call<Void> call = new RetrofitInicializador().getDispositivoService().enviaToken(token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Token Enviado", token);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Log.e("Token falhou", t.getMessage());

            }
        });


    }

}
