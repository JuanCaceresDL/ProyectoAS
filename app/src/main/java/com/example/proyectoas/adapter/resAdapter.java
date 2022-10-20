package com.example.proyectoas.adapter;

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
import com.example.proyectoas.R;
import com.example.proyectoas.RestActivity;
import com.example.proyectoas.RestaurantActivity;
import com.example.proyectoas.TuristicoActivity;
import com.example.proyectoas.modelos.restau;
import com.example.proyectoas.modelos.turistico;

import java.util.List;

public class resAdapter extends RecyclerView.Adapter<resAdapter.ViewHolder> {

    private List<restau> rRestaur;
    private Context context;

    public resAdapter(List<restau> rRestaur) {
        this.rRestaur = rRestaur;
    }

    public void reloadData(List<restau> restaus) {
        this.rRestaur = restaus;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public resAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.res_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull resAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        restau restau = rRestaur.get(position);

        // Set item views based on your views and data model
        TextView bookNameTextView = holder.rResNombre;
        bookNameTextView.setText(restau.rNombre);
        TextView bookAuthorTextView = holder.rResDepartamento;
        bookAuthorTextView.setText(restau.rDepartamento);
        RatingBar resRatingRatingBar = holder.rCalificacion;
        resRatingRatingBar.setRating(restau.rCalificacion);
        ImageView bookImage = holder.rResImagen;
        holder.rId = restau.rId;

        Glide.with(this.context).load(restau.rImagen).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return rRestaur.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView rResImagen;
        private TextView rResNombre;
        private TextView rResDepartamento;
        private RatingBar rCalificacion;
        public Integer rId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rResImagen = (ImageView) itemView.findViewById(R.id.res_image);
            rResNombre = (TextView) itemView.findViewById(R.id.res_name);
            rResDepartamento = (TextView) itemView.findViewById(R.id.res_departamento);
            rCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
            intent.putExtra("idres", rId);
            view.getContext().startActivity(intent);
        }
    }
}
