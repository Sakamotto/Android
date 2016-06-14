package com.testes.agenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    Object listaContatos = new Object();
    private Context context;
    private static ControladorCard cCard;

    public MyAdapter(Context context, Object listaContatos){
        this.context = context;
        this.listaContatos = listaContatos;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView titulo;
        protected TextView nome;
        protected TextView email;
        protected TextView telefone;
        private Context context;

        public ViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context = context;

            titulo = (TextView) itemView.findViewById(R.id.titulo);
            nome = (TextView) itemView.findViewById(R.id.nome);
            email = (TextView) itemView.findViewById(R.id.email);
            telefone = (TextView) itemView.findViewById(R.id.telefone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cCard.onItemClick(getAdapterPosition(), v);
        }
    }

    // Cria novas views (chamado pelo gerenciador de layouts)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(context, v);
    }

    public void setOnItemClickListener(ControladorCard controlador){
        cCard = controlador;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Contato c = ((ArrayList<Contato>)listaContatos).get(position);
        holder.titulo.setText(c.getNome());
        holder.nome.setText(c.getNome());
        holder.email.setText(c.getEmail());
        holder.telefone.setText(c.getCelular());
    }

    @Override
    public int getItemCount() {
        if(!((ArrayList<Contato>)listaContatos).isEmpty()){
            return ((ArrayList<Contato>)listaContatos).size();
        }
        return 0;
    }

    public interface ControladorCard{
        void onItemClick(int position, View v);
    }

}
