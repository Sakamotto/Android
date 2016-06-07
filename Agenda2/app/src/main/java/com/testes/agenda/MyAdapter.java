package com.testes.agenda;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testes.agenda.entidade.Contato;

import java.util.List;

/**
 * Created by cristian on 07/06/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Contato> listaContatos;

    public MyAdapter(List<Contato> listaContatos){
        this.listaContatos = listaContatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView titulo;
        protected TextView nome;
        protected TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            nome = (TextView) itemView.findViewById(R.id.nome);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }

    // Cria novas views (chamado pelo gerenciador de layouts)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Contato c = listaContatos.get(position);
        holder.titulo.setText(c.getNome());
        holder.nome.setText(c.getNome());
        holder.email.setText(c.getEmail());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
