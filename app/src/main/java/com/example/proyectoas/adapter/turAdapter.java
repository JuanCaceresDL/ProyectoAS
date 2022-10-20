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
import com.example.proyectoas.TuristicoActivity;
import com.example.proyectoas.modelos.turistico;

import java.util.List;

public class turAdapter extends RecyclerView.Adapter<turAdapter.ViewHolder> {

    private List<turistico> mTuristico;
    private Context context;

    public turAdapter(List<turistico> mTuristico) {
        this.mTuristico = mTuristico;
    }

    public void reloadData(List<turistico> turist) {
        this.mTuristico = turist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public turAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.tur_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull turAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        turistico turistico = mTuristico.get(position);

        // Set item views based on your views and data model
        TextView bookNameTextView = holder.mTurNombre;
        bookNameTextView.setText(turistico.mNombre);
        TextView bookAuthorTextView = holder.mTurDepartamento;
        bookAuthorTextView.setText(turistico.mDepartamento);
        RatingBar turRatingRatingBar = holder.mCalificacion;
        turRatingRatingBar.setRating(turistico.mCalificacion);
        ImageView bookImage = holder.mTurImagen;
        holder.mId = turistico.mId;

        Glide.with(this.context).load(turistico.mImagen).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return mTuristico.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mTurImagen;
        private TextView mTurNombre;
        private TextView mTurDepartamento;
        private RatingBar mCalificacion;
        public Integer mId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTurImagen = (ImageView) itemView.findViewById(R.id.tur_image);
            mTurNombre = (TextView) itemView.findViewById(R.id.tur_name);
            mTurDepartamento = (TextView) itemView.findViewById(R.id.tur_departamento);
            mCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), TuristicoActivity.class);
            intent.putExtra("idtur", mId);
            view.getContext().startActivity(intent);
        }
    }
}