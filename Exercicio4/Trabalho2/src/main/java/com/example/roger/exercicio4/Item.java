package com.example.roger.exercicio4;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Item extends RecyclerView.ViewHolder{

    public TextView texto, textoDiretor;
    public ImageButton imgExcluir;

    public Item(View itemView) {
        super(itemView);
        this.texto = (TextView) itemView.findViewById(R.id.textNome);
        this.textoDiretor = (TextView) itemView.findViewById(R.id.textDiretor);
        this.imgExcluir = (ImageButton) itemView.findViewById(R.id.imgExcluir);


    }
}
