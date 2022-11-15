package com.example.proyectoas.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Detalles;
import com.example.proyectoas.Presenter.IPresenterLugar;
import com.example.proyectoas.Presenter.PresenterLugar;
import com.example.proyectoas.R;
import com.example.proyectoas.View.ILugarView;
import com.example.proyectoas.databinding.FragmentDescriptBinding;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptFragment extends Fragment implements ILugarView {
ImageButton waze, google;
Integer mId;
String uId;
Boolean esfav;
private ItemsApi api;
public Lugares detalleLug;
private IPresenterLugar presenterLugar;

private FragmentDescriptBinding fragmentDescriptBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescriptFragment() {
        // Required empty public constructor
    }

    public DescriptFragment(Integer mId, Boolean esfav) {
        this.mId = mId;
        this.esfav = esfav;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptFragment newInstance(String param1, String param2) {
        DescriptFragment fragment = new DescriptFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_descript, container, false);
        api = ApiClient.getInstance().create(ItemsApi.class);
        presenterLugar = new PresenterLugar(this);
        SharedPreferences preferences = getActivity().getSharedPreferences("estado", Context.MODE_PRIVATE);
        uId = preferences.getString("id",null);
        ToggleButton dfav = v.findViewById(R.id.favdescript);
        if (this.esfav){
            dfav.setChecked(true);
        }else {
            dfav.setChecked(false);
        }
        Call<Lugares> detallescall = api.getDetalles(mId);
        detallescall.enqueue(new Callback<Lugares>() {
            @Override
            public void onResponse(Call<Lugares> call, Response<Lugares> response) {
                TextView descript = v.findViewById(R.id.descript);
                descript.setText(response.body().rDescripcion);
                RatingBar rating = v.findViewById(R.id.rating);
                if (response.body().rCalificacion == null){
                   rating.setRating(0);
                }else {
                    rating.setRating(response.body().rCalificacion);
                }
                TextView nombr = v.findViewById(R.id.nombredes);
                nombr.setText(response.body().rNombre);
                dfav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dfav.isChecked()){
                            presenterLugar.postFavoritos(mId, Integer.parseInt(uId));
                        }
                        if (!dfav.isChecked()){
                            AlertDialog confirmacion = new AlertDialog.Builder(getActivity()).create();
                            confirmacion.setTitle("Confirma eliminación");
                            confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            presenterLugar.deleteFavoritos(mId, Integer.parseInt(uId));
                                            dialog.dismiss();
                                        }
                                    });
                            confirmacion.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dfav.setChecked(true);
                                            dialog.dismiss();
                                        }
                                    });
                            confirmacion.show();
                        }
                    }
                });

                ImageButton dvisita = v.findViewById(R.id.visita);
                dvisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog confirmacion = new AlertDialog.Builder(getActivity()).create();
                        confirmacion.setTitle("Fecha de última visita");
                        DatePicker picker = new DatePicker(getActivity());
                        picker.setMaxDate(new Date().getTime());
                        SharedPreferences preferences = getActivity().getSharedPreferences("estado", Context.MODE_PRIVATE);
                        uId = preferences.getString("id",null);
                        System.out.println(new Date().getTime());
                        confirmacion.setView(picker);
                        confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Date fecha = new Date();
                                        fecha.setYear(picker.getYear());
                                        fecha.setMonth(picker.getMonth());
                                        fecha.setDate(picker.getDayOfMonth());
                                        Call<String> visitas = api.postVisita(Integer.parseInt(uId),mId,picker.getYear()+"-"+picker.getMonth()+"-"+picker.getDayOfMonth() );
                                        visitas.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                System.out.println(response.body());
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
                ImageView image = v.findViewById(R.id.imagendescr);
                Glide.with(DescriptFragment.this).load(response.body().rImagen).into(image);
                waze=v.findViewById(R.id.waze);
                google=v.findViewById(R.id.google);
                waze.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().rWaze)));
                    }
                });
                google.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().rGoogle)));
                    }
                });
            }

            @Override
            public void onFailure(Call<Lugares> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

        // Inflate the layout for this fragment

        return v;
    }

    @Override
    public void onLugarSuccess(List<Lugares> lugares) {

    }

    @Override
    public void onLugarError(String msg) {

    }
}