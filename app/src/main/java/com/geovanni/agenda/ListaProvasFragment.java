package com.geovanni.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.geovanni.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by geovanni on 25/07/17.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);


        List<String> topicosPort = Arrays.asList("Sujeito","Objeto","Objeto Indireto");

        Prova provaPortugues = new Prova("Portugues", "25/09/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equacoes","Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "28/09/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues,provaMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) view.findViewById(R.id.provas_lista);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Prova prova = (Prova) parent.getItemAtPosition(position);

                Toast.makeText(getContext(), "Clicou na prova de: " + prova, Toast.LENGTH_SHORT).show();

                Intent vaiParaDetalhes = new Intent(getContext(), DetalhesProvaActivity.class);

                vaiParaDetalhes.putExtra("prova", prova);

                startActivity(vaiParaDetalhes);
            }
        });


        return view;

    }
}
