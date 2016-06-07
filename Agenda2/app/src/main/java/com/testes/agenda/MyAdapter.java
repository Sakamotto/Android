package com.testes.agenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.agenda.entidade.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 07/06/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Contato> listaContatos;
    private Context context;

    public MyAdapter(Context context, List<Contato> listaContatos){
        this.context = context;
        this.listaContatos = listaContatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView titulo;
        protected TextView nome;
        protected TextView email;
        protected TextView telefone;
        private Context context;

        public ViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context = context;
//            final ArrayList<String> listaExtra = new ArrayList<>();

            titulo = (TextView) itemView.findViewById(R.id.titulo);
            nome = (TextView) itemView.findViewById(R.id.nome);
            email = (TextView) itemView.findViewById(R.id.email);
            telefone = (TextView) itemView.findViewById(R.id.telefone);
            // TÁ ERRADO .. NÃO É AQUI ...
//            listaExtra.add(nome.getText().toString());
//            listaExtra.add(email.getText().toString());
//            listaExtra.add(telefone.getText().toString());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, titulo.getText().toString() + " clicado", Toast.LENGTH_SHORT).show();
//                    Intent editarContato = new Intent(v.getContext(), EditarContato.class);
//                    editarContato.putStringArrayListExtra("contato", listaExtra);
//                    v.getContext().startActivity(editarContato);
//                }
//            });
        }
    }

    // Cria novas views (chamado pelo gerenciador de layouts)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Contato c = listaContatos.get(position);
        holder.titulo.setText(c.getNome());
        holder.nome.setText(c.getNome());
        holder.email.setText(c.getEmail());
        holder.telefone.setText(c.getCelular());
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }
}
