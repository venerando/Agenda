package com.geovanni.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.geovanni.agenda.modelo.Prova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito","Objeto","Objeto Indireto");

        Prova provaPortugues = new Prova("Portugues", "25/09/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equacoes","Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "28/09/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues,provaMatematica);

        ArrayAdapter <Prova> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) findViewById(R.id.provas_lista);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Prova prova = (Prova) parent.getItemAtPosition(position);

                Toast.makeText(ProvasActivity.this, "Clicou na prova de: " + prova, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
