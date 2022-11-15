package com.example.proyectoas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Detalles;
import com.example.proyectoas.Fragments.TuristFragment;
import com.example.proyectoas.Presenter.IPresenterFav;
import com.example.proyectoas.Presenter.IPresenterLugar;
import com.example.proyectoas.Presenter.PresenterFav;
import com.example.proyectoas.Presenter.PresenterLugar;
import com.example.proyectoas.R;
import com.example.proyectoas.View.IFavorView;
import com.example.proyectoas.View.ILugarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class favAdapter extends RecyclerView.Adapter<favAdapter.ViewHolder> {

    private List<Favoritos> mFavoritos;
    private List<Favoritos> mFavoritosoriginal;
    private Context context;
    private IFavorView favorView;
    private IPresenterFav presenterFav;
    private String uId;
    private Boolean esfav;

    public favAdapter(List<Favoritos> mFavoritos, IFavorView favorView, String uId) {
        this.mFavoritos = mFavoritos;
        this.mFavoritosoriginal = mFavoritos;
        this.favorView = favorView;
        this.presenterFav = new PresenterFav(favorView);
        this.uId = uId;
    }

    public void reloadData(List<Favoritos> favoritos) {
        this.mFavoritosoriginal = favoritos;
        mFavoritos.clear();
        mFavoritos.addAll(mFavoritosoriginal);
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
        TextView lugFecha = holder.mFavFecha;

        if (favoritos.fVisita != null){
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String fecha = df.format(favoritos.fVisita);
            lugFecha.setText(fecha);
        }else {
            lugFecha.setText("No se ha visitado");
        }

        holder.mId = favoritos.fId;
        holder.lId = favoritos.fIdlug;

        if (favoritos.fFavorito == null){
            holder.mFavorito.setChecked(false);
        }else {
            holder.mFavorito.setChecked(true);
        }
        if (holder.mFavorito.isChecked()){
            holder.esfav= true;
        }else {
            holder.esfav=false;
        }
        holder.mFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mFavorito.isChecked()){
                    holder.esfav = true;
                    presenterFav.postFavoritos(favoritos.fIdlug, Integer.parseInt(uId));
                }
                if (!holder.mFavorito.isChecked()){
                    AlertDialog confirmacion = new AlertDialog.Builder(context).create();
                    confirmacion.setTitle("Confirma eliminación");
                    confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    presenterFav.deleteFavoritos(favoritos.fIdlug, Integer.parseInt(uId));
                                    holder.esfav = false;
                                    mFavoritos.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
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
        private TextView mFavFecha;
        private RatingBar fCalificacion;
        private ToggleButton mFavorito;
        public Integer mId, lId;
        public Boolean esfav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFavImagen = (ImageView) itemView.findViewById(R.id.fav_image);
            mFavNombre = (TextView) itemView.findViewById(R.id.fav_name);
            mFavDepartamento = (TextView) itemView.findViewById(R.id.fav_departamento);
            fCalificacion = (RatingBar) itemView.findViewById(R.id.ratingBar);
            mFavFecha = (TextView) itemView.findViewById(R.id.fav_fecha);
            mFavorito = itemView.findViewById(R.id.favbutton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Detalles.class);
            intent.putExtra("idlug", lId);
            intent.putExtra("esfavorito", esfav);
            view.getContext().startActivity(intent);
        }
    }
    public void  filterAlfabetico1(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return uno.fNombre.compareTo(dos.fNombre);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico2(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>(){
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return dos.fNombre.compareTo(uno.fNombre);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico3(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return uno.fCalificacion.compareTo(dos.fCalificacion);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico4(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return dos.fCalificacion.compareTo(uno.fCalificacion);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico5(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>(){
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return uno.fDepartamento.compareTo(dos.fDepartamento);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico6(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                return dos.fDepartamento.compareTo(uno.fDepartamento);
            }
        });
        notifyDataSetChanged();

    }
    public void  filterAlfabetico7(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(Favoritos uno, Favoritos dos) {

                if (uno.fVisita == null){
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    LocalDate date1 = LocalDate.of(11111, 1, 1);
                    Date date = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
                    uno.fVisita2 = new Date();
                    uno.fVisita2 = date;
                }else {
                    uno.fVisita2 = uno.fVisita;
                }
                if (dos.fVisita == null){
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    LocalDate date1 = LocalDate.of(11111, 1, 1);
                    Date date = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
                    dos.fVisita2 = new Date();
                    dos.fVisita2 = date;
                }else {
                    dos.fVisita2 = dos.fVisita;
                }
                return uno.fVisita2.compareTo(dos.fVisita2);
            }
        });
        notifyDataSetChanged();

    }public void  filterAlfabetico8(){
        Collections.sort(mFavoritos, new Comparator<Favoritos>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(Favoritos uno, Favoritos dos) {
                if (uno.fVisita == null){
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    LocalDate date1 = LocalDate.of(1, 1, 1);
                    Date date = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
                    uno.fVisita2 = new Date();
                    uno.fVisita2 = date;
                }else {
                    uno.fVisita2 = uno.fVisita;
                }
                if (dos.fVisita == null){
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    LocalDate date1 = LocalDate.of(1, 1, 1);
                    Date date = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
                    dos.fVisita2 = new Date();
                    dos.fVisita2 = date;
                }else {
                    dos.fVisita2 = dos.fVisita;
                }
                return dos.fVisita2.compareTo(uno.fVisita2);
            }
        });
        notifyDataSetChanged();

    }

    public void filterlugares(String tipo){
        List<Favoritos> collection;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            collection = mFavoritosoriginal.stream()
                    .filter(i -> i.fTipo.matches(tipo))
                    .collect(Collectors.toList());
            mFavoritos.clear();
            mFavoritos.addAll(collection);
        }
        notifyDataSetChanged();

    }
}
