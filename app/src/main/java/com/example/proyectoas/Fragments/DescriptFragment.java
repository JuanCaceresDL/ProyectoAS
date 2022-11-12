package com.example.proyectoas.Fragments;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Detalles;
import com.example.proyectoas.R;
import com.example.proyectoas.databinding.FragmentDescriptBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptFragment extends Fragment {
ImageButton waze, google;
Integer mId;
private ItemsApi api;
public Lugares detalleLug;

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

    public DescriptFragment(Integer mId) {
        this.mId = mId;
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
        Call<Lugares> detallescall = api.getDetalles(mId);
        detallescall.enqueue(new Callback<Lugares>() {
            @Override
            public void onResponse(Call<Lugares> call, Response<Lugares> response) {
                TextView descript = v.findViewById(R.id.descript);
                descript.setText(response.body().rDescripcion);
                RatingBar rating = v.findViewById(R.id.rating);
                rating.setRating(response.body().rCalificacion);
                TextView nombr = v.findViewById(R.id.nombredes);
                nombr.setText(response.body().rNombre);
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

}