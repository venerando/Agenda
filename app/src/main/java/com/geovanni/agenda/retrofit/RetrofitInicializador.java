package com.geovanni.agenda.retrofit;


import com.geovanni.agenda.services.AlunoService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by geovanni on 08/08/17.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.30:8080/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public AlunoService getAlunoService() {

        return retrofit.create(AlunoService.class);

    }
}
