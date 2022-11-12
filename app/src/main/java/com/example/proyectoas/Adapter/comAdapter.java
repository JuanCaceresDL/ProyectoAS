package com.example.proyectoas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.R;

import java.util.List;

public class comAdapter extends RecyclerView.Adapter<comAdapter.ViewHolder> {

    private List<Comentarios> mComentarios;
    private Context context;

    public comAdapter(List<Comentarios> comentarios){this.mComentarios = comentarios;}

    public void reloadData(List<Comentarios> comentarios) {
        this.mComentarios = comentarios;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.com_adapter, parent, false);

        // Return a new holder instance
        comAdapter.ViewHolder viewHolder = new comAdapter.ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull comAdapter.ViewHolder holder, int position) {
        Comentarios comentarios = mComentarios.get(position);

        TextView nombrecom = holder.mNombre;
        nombrecom.setText(comentarios.cNombre);
        TextView comentcom = holder.mComentario;
        comentcom.setText(comentarios.cComentario);
        RatingBar ratinper = holder.mCalificacionp;
        ratinper.setRating(comentarios.cCalificacion);
        holder.mId = comentarios.cId;
        holder.uId = comentarios.cuId;

    }

    @Override
    public int getItemCount() {return mComentarios.size();}


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNombre;
        private TextView mComentario;
        private RatingBar mCalificacionp;
        public Integer mId, uId;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            mNombre = itemView.findViewById(R.id.nombreus);
            mComentario = itemView.findViewById(R.id.comentarious);
            mCalificacionp = itemView.findViewById(R.id.ratingp);
        }
    }
}
