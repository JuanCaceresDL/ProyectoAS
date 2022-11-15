package com.example.proyectoas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Detalles;
import com.example.proyectoas.Presenter.IPresenterLugar;
import com.example.proyectoas.Presenter.PresenterLugar;
import com.example.proyectoas.R;
import com.example.proyectoas.View.ILugarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class cercAdapter extends RecyclerView.Adapter<cercAdapter.ViewHolder> {

    private List<Lugares> cLugares;
    private List<Lugares> copia = new ArrayList<>();
    private ILugarView lugarView;
    private IPresenterLugar presenterLugar;
    private Context context;
    private String uId;

    public cercAdapter(List<Lugares> cLugares,ILugarView lugarView, String uId){
        this.cLugares = cLugares;
        this.lugarView = lugarView;
        this.presenterLugar = new PresenterLugar(lugarView);
        this.uId = uId;
    }

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
        lugKiloText.setText(new StringBuilder().append(String.valueOf(lugares.rDistancia)).append(" Km").toString());
        ImageView bookImage = holder.cLugImagen;
        holder.mId = lugares.rId;

        if (lugares.rFavorito == null){
            holder.mFavorito.setChecked(false);
        }else {
            holder.mFavorito.setChecked(true);
        }
        holder.mFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mFavorito.isChecked()){
                    presenterLugar.postFavoritos(lugares.rId, Integer.parseInt(uId));
                }
                if (!holder.mFavorito.isChecked()){
                    AlertDialog confirmacion = new AlertDialog.Builder(context).create();
                    confirmacion.setTitle("Confirma eliminación");
                    confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    presenterLugar.deleteFavoritos(lugares.rId, Integer.parseInt(uId));
                                    dialog.dismiss();
                                }
                            });
                    confirmacion.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.mFavorito.setChecked(true);
                                    dialog.dismiss();
                                }
                            });
                    confirmacion.show();
                }
            }
        });

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
        private ToggleButton mFavorito;
        public Integer mId;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cLugImagen = (ImageView) itemView.findViewById(R.id.lug_image);
            cLugNombre = (TextView) itemView.findViewById(R.id.lug_name);
            cLugDepartamento = (TextView) itemView.findViewById(R.id.lug_departamento);
            cCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBarcerc);
            cKilometraje = itemView.findViewById(R.id.kilometraje);
            mFavorito = itemView.findViewById(R.id.favbutton);
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
    public void  filterDistancia(){
        Collections.sort(cLugares, new Comparator<Lugares>() {
            @Override
            public int compare(Lugares uno, Lugares dos) {
                Double casteo1 = uno.rDistancia.doubleValue();
                Double casteo2 = dos.rDistancia.doubleValue();
                uno.distanciacast1 = casteo1;
                dos.distanciacast2 = casteo2;
                return uno.distanciacast1.compareTo(dos.distanciacast2);
            }
        });
        notifyDataSetChanged();

    }

}
