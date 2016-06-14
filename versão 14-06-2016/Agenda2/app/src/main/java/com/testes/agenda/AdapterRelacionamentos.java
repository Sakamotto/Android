package com.testes.agenda;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.testes.agenda.entidade.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 14/06/16.
 */
public class AdapterRelacionamentos extends RecyclerView.Adapter<AdapterRelacionamentos.ViewHolder> {

    Object listaContatos = new Object();
    ArrayList<Contato> c;
    ArrayList<Integer> listaIds = new ArrayList<>();
    private static ControladorCardRelacionamentos ccr;

    public AdapterRelacionamentos(Object listaContatos, ArrayList<Integer> listaIds){
        this.listaContatos = listaContatos;
        this.listaIds = listaIds;
        c = ((ArrayList<Contato>) listaContatos);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tituloCard;
        TextView nome;
        TextView email;
        CheckBox cbRelacionamento;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloCard = (TextView) itemView.findViewById(R.id.tituloRelacionamento);
            nome = (TextView) itemView.findViewById(R.id.nomeRelacionamento);
            email = (TextView) itemView.findViewById(R.id.emailRelacionamento);
            cbRelacionamento = (CheckBox) itemView.findViewById(R.id.cbRelacionamento);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ccr.onItemClick(getAdapterPosition(), v);
        }
    }

    public ArrayList<Integer> getListaIds(){
        return listaIds;
    }


    @Override
    public AdapterRelacionamentos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_relacionamentos, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterRelacionamentos.ViewHolder holder, int position) {
        final int pos = position;

        holder.tituloCard.setText(c.get(position).getNome());
        holder.nome.setText(c.get(position).getNome());
        holder.email.setText(c.get(position).getEmail());
        holder.cbRelacionamento.setChecked(c.get(position).isSelected());

        holder.cbRelacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                c.get(pos).setSelected(cb.isSelected());

                if(cb.isChecked()){
                    listaIds.add(c.get(pos).getId());
                }

                Log.v("CHECKBOX --","Clicado em " + c.get(pos).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return c.size();
    }

    public void setOnItemClickListener(ControladorCardRelacionamentos controlador){
        ccr = controlador;
    }

    public interface ControladorCardRelacionamentos{
        void onItemClick(int position, View v);
    }

}
