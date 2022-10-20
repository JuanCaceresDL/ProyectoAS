package com.example.proyectoas.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectoas.R;
import com.example.proyectoas.TuristicoActivity;
import com.example.proyectoas.modelos.detalles;
import com.example.proyectoas.modelos.turistico;

import java.util.List;

public class desAdapter extends RecyclerView.Adapter<desAdapter.ViewHolder> {

    private List<detalles> dDetalles;
    private Context context;

    public desAdapter(List<detalles> dDetalles) {this.dDetalles = dDetalles;}

    public void reloadData(List<detalles> detalle){
        this.dDetalles = detalle;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public desAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.des_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        detalles detalles = dDetalles.get(position);

        // Set item views based on your views and data model
        TextView turDepartamentoTextView = holder.dComentariol;
        turDepartamentoTextView.setText(detalles.dComentariol);
        holder.idtur = detalles.dIdtur;

    }


    @Override
    public int getItemCount() {
        return dDetalles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dComentariol;
        public Integer idtur;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dComentariol =  (TextView) itemView.findViewById(R.id.comentario_adapter);

        }
    }
}
