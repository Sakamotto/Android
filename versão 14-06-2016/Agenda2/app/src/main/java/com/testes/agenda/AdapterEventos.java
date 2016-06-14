package com.testes.agenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testes.agenda.entidade.Contato;
import com.testes.agenda.entidade.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 13/06/16.
 */
public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.ViewHolder>{

    Object listaEventos = new Object();
    private Context context;
    private static ControladorCardEvento cCard;

    public AdapterEventos(Context context, Object listaEventos){
        this.context = context;
        this.listaEventos = listaEventos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView tituloCard;
        protected TextView titulo;
        protected TextView dataEvento;
        protected TextView local;
        private Context context;

        public ViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context = context;

            tituloCard = (TextView) itemView.findViewById(R.id.tituloCard);
            titulo = (TextView) itemView.findViewById(R.id.tituloEvento);
            local = (TextView) itemView.findViewById(R.id.localEvento);
            dataEvento = (TextView) itemView.findViewById(R.id.dataEvento);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cCard.onItemClick(getAdapterPosition(), v);
        }
    }


    public void setOnItemClickListener(ControladorCardEvento controlador){
        cCard = controlador;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_layout_eventos, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Evento e = ((ArrayList<Evento>)listaEventos).get(position);

        holder.tituloCard.setText(e.getTitulo());
        holder.titulo.setText(e.getTitulo());
        holder.dataEvento.setText(e.getData());
        holder.local.setText(e.getLocal());

    }

    @Override
    public int getItemCount() {
        return ((ArrayList<Evento>)listaEventos).size();
    }

    public interface ControladorCardEvento{
        void onItemClick(int position, View v);
    }

}
