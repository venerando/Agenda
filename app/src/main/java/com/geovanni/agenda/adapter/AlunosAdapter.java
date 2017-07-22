package com.geovanni.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.geovanni.agenda.ListaAlunos;
import com.geovanni.agenda.R;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by geovanni on 17/07/17.
 */

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getIdAluno();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.list_item, parent,false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone =(TextView) view.findViewById(R.id.item_telefone);
            campoTelefone.setText(aluno.getTelefone());

       /* TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        if (campoEndereco != null){
            campoEndereco.setText(aluno.getEndereco());
        }*/

       /*
        RatingBar campoNota = (RatingBar) view.findViewById(R.id.item_nota);

        if (campoNota !=null){
            campoNota.setProgress(aluno.getNota().intValue());
        }
        */

        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        if (campoSite !=null){
            campoSite.setText(aluno.getSite());
        }

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);

        String caminhoFoto = aluno.getCaminhoFoto();

        if (caminhoFoto !=null) {

            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //Limitando tamanho imagem
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            //Imagem ocupando espaço total do ImageView
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

            return view;

    }
}
