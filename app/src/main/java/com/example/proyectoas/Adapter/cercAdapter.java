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
import com.example.proyectoas.R;

import java.util.ArrayList;
import java.util.List;

public class cercAdapter extends RecyclerView.Adapter<cercAdapter.ViewHolder> {

    private List<Lugares> cLugares;
    private List<Lugares> copia = new ArrayList<>();
    private Context context;

    public cercAdapter(List<Lugares> cLugares){this.cLugares = cLugares;}

    public void reloadData(List<Lugares> cLugares){
        this.cLugares = cLugares;
        this.copia.addAll(cLugares);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        View contactView = inflater.inflate(R.layout.cerc_adapter, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Lugares lugares = cLugares.get(position);

        // Set item views based on your views and data model
        TextView lugNameTextView = holder.cLugNombre;
        lugNameTextView.setText(lugares.rNombre);
        TextView lugDepartTextView = holder.cLugDepartamento;
        lugDepartTextView.setText(lugares.rDepartamento);
        RatingBar lugRatingRatingBar = holder.cCalificacion;
        lugRatingRatingBar.setRating(lugares.rCalificacion);
        TextView lugKiloText = holder.cKilometraje;
        lugKiloText.setText(String.valueOf(lugares.rDistancia));
        ImageView bookImage = holder.cLugImagen;
        holder.mId = lugares.rId;

        Glide.with(this.context).load(lugares.rImagen).into(bookImage);
    }

    @Override
    public int getItemCount() {return cLugares.size();}

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView cLugImagen;
        private TextView cLugNombre;
        private TextView cLugDepartamento;
        private TextView cKilometraje;
        private RatingBar cCalificacion;
        public Integer mId;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cLugImagen = (ImageView) itemView.findViewById(R.id.lug_image);
            cLugNombre = (TextView) itemView.findViewById(R.id.lug_name);
            cLugDepartamento = (TextView) itemView.findViewById(R.id.lug_departamento);
            cCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBarcerc);
            cKilometraje = itemView.findViewById(R.id.kilometraje);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Detalles.class);
            intent.putExtra("idlug", mId);
            view.getContext().startActivity(intent);
        }
    }

    public void filtrarCercano(Double distancia){
        List<Lugares> copia2 = new ArrayList<>();
        for (Lugares lugares:
             this.copia) {
            Double casteo = lugares.rDistancia.doubleValue();
            if (casteo < distancia) {
                copia2.add(lugares);
            }
        }
        this.cLugares = copia2;
        notifyDataSetChanged();
    }

}
