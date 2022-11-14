package com.example.proyectoas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class comAdapter extends RecyclerView.Adapter<comAdapter.ViewHolder> {

    private List<Comentarios> mComentarios;
    private Context context;
    String uId;

    public comAdapter(List<Comentarios> comentarios, String uId){
        this.mComentarios = comentarios;
        this.uId = uId;
    }

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
        if (comentarios.cCalificacion == null){
            ratinper.setRating(0);
        }else {
            ratinper.setRating(comentarios.cCalificacion);
        }
        holder.mId = comentarios.cId;
        holder.uId = comentarios.cuId;

        if (comentarios.cuId == Integer.parseInt(uId)){
            holder.basurero.setVisibility(View.VISIBLE);
        }else {
            holder.basurero.setVisibility(View.GONE);
        }

        holder.basurero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog confirmacion = new AlertDialog.Builder(context).create();
                confirmacion.setTitle("Confirma eliminación");
                confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ItemsApi commentAPI = ApiClient.getInstance().create(ItemsApi.class);
                                Call<String> comentariosCall = commentAPI.deleteComentarios(comentarios.comId,comentarios.cId);
                                comentariosCall.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        System.out.println(response.body());
                                        mComentarios.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        System.out.println(t.toString());
                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                confirmacion.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                confirmacion.show();
            }
        });

    }

    @Override
    public int getItemCount() {return mComentarios.size();}


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNombre;
        private TextView mComentario;
        private RatingBar mCalificacionp;
        public Integer mId, uId;
        public ImageButton basurero;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            mNombre = itemView.findViewById(R.id.nombreus);
            mComentario = itemView.findViewById(R.id.comentarious);
            mCalificacionp = itemView.findViewById(R.id.ratingp);
            basurero = itemView.findViewById(R.id.trash);
        }
    }

    public void AddComentario(Comentarios comentarios){
        mComentarios.add(comentarios);
        notifyDataSetChanged();
    }
}
