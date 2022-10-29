package com.example.proyectoas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Fragments.TuristFragment;
import com.example.proyectoas.R;

import java.util.List;

public class favAdapter extends RecyclerView.Adapter<favAdapter.ViewHolder> {

    private List<Favoritos> mFavoritos;
    private Context context;

    public favAdapter(List<Favoritos> mFavoritos) {
        this.mFavoritos = mFavoritos;
    }

    public void reloadData(List<Favoritos> favoritos) {
        this.mFavoritos = favoritos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fav_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Favoritos favoritos = mFavoritos.get(position);

        // Set item views based on your views and data model
        TextView lugNameTextView = holder.mFavNombre;
        lugNameTextView.setText(favoritos.fNombre);
        TextView lugDepartTextView = holder.mFavDepartamento;
        lugDepartTextView.setText(favoritos.fDepartamento);
        RatingBar lugRatingRatingBar = holder.fCalificacion;
        lugRatingRatingBar.setRating(favoritos.fCalificacion);
        ImageView bookImage = holder.mFavImagen;
        holder.mId = favoritos.fId;

        Glide.with(this.context).load(favoritos.fImagen).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return mFavoritos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mFavImagen;
        private TextView mFavNombre;
        private TextView mFavDepartamento;
        private RatingBar fCalificacion;
        public Integer mId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFavImagen = (ImageView) itemView.findViewById(R.id.fav_image);
            mFavNombre = (TextView) itemView.findViewById(R.id.fav_name);
            mFavDepartamento = (TextView) itemView.findViewById(R.id.fav_departamento);
            fCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), TuristFragment.class);
            intent.putExtra("idlug", mId);
            view.getContext().startActivity(intent);
        }
    }
}
