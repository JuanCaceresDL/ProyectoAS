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
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Detalles;
import com.example.proyectoas.Fragments.TuristFragment;
import com.example.proyectoas.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lugAdapter extends RecyclerView.Adapter<lugAdapter.ViewHolder> {

    private List<Lugares> mLugares;
    private Context context;

    public lugAdapter(List<Lugares> mLugares) {
        this.mLugares = mLugares;
    }

    public void reloadData(List<Lugares> lugares) {
        this.mLugares = lugares;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.lug_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Lugares lugares = mLugares.get(position);

        // Set item views based on your views and data model
        TextView lugNameTextView = holder.mLugNombre;
        lugNameTextView.setText(lugares.rNombre);
        TextView lugDepartTextView = holder.mLugDepartamento;
        lugDepartTextView.setText(lugares.rDepartamento);
        RatingBar lugRatingRatingBar = holder.mCalificacion;
        lugRatingRatingBar.setRating(lugares.rCalificacion);
        ImageView bookImage = holder.mLugImagen;
        holder.mId = lugares.rId;

        Glide.with(this.context).load(lugares.rImagen).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return mLugares.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mLugImagen;
        private TextView mLugNombre;
        private TextView mLugDepartamento;
        private RatingBar mCalificacion;
        public Integer mId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mLugImagen = (ImageView) itemView.findViewById(R.id.tur_image);
            mLugNombre = (TextView) itemView.findViewById(R.id.tur_name);
            mLugDepartamento = (TextView) itemView.findViewById(R.id.tur_departamento);
            mCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Detalles.class);
            intent.putExtra("idlug", mId);
            view.getContext().startActivity(intent);
        }
    }
    public void  filterAlfabetico1(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return uno.rNombre.compareTo(dos.rNombre);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico2(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return dos.rNombre.compareTo(uno.rNombre);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico3(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return uno.rCalificacion.compareTo(dos.rCalificacion);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico4(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return dos.rCalificacion.compareTo(uno.rCalificacion);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico5(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return uno.rDepartamento.compareTo(dos.rDepartamento);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico6(){
        Collections.sort(mLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                return dos.rDepartamento.compareTo(uno.rDepartamento);
            }
        });
        notifyDataSetChanged();

    }
}