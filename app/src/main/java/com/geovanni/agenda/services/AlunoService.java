package com.geovanni.agenda.services;

import com.geovanni.agenda.dto.AlunoSync;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by geovanni on 08/08/17.
 */

public interface AlunoService {

    @POST("aluno")
    Call <Void> insere (@Body Aluno aluno);


    @GET("aluno")
    Call <AlunoSync> lista();

}
