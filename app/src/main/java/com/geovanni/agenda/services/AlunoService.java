package com.geovanni.agenda.services;

import com.geovanni.agenda.dto.AlunoSync;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by geovanni on 08/08/17.
 */

public interface AlunoService {

    @POST("aluno")
    Call <Void> insere (@Body Aluno aluno);


    @GET("aluno")
    Call <AlunoSync> lista();

    @DELETE("aluno/{id}")
    Call <Void> deleta(@Path("id") String id);

    @GET("aluno/diff")
    Call <AlunoSync> novos(@Header("datahora") String versao);
}
