package com.example.roger.exercicio4;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Item>{

    ArrayList<Filme> filmes;

    public Adaptador(ArrayList<Filme> filmes){
        this.filmes = filmes;
    }
    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlista, parent, false);

        Item item = new Item(v);
        return item;
    }
    @Override
    public void onBindViewHolder(Item holder, final int position) {
        Filme filme = filmes.get(position);
        holder.texto.setText(filme.getFilme());
        holder.textoDiretor.setText(filme.getDiretor().getNome());
        holder.imgExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filmes.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    public ArrayList<Filme> getLista(){
        return filmes;
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }
}
