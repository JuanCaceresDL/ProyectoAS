package com.example.proyectoas.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.proyectoas.Adapter.contrAdapter;
import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Perfil;
import com.example.proyectoas.HomeActivity;
import com.example.proyectoas.Loading;
import com.example.proyectoas.Login;
import com.example.proyectoas.R;
import com.example.proyectoas.Registro;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
Integer uId;
EditText t_nombre, t_apellido, t_telefono;
TextView t_email;
Button b_editar, b_contra;
ImageButton b_cerrar;
String pnombre,papellido, ptelefono;
private contrAdapter contrAdapter;

private ItemsApi api;
public Perfil perfil;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(Integer uId) {
        this.uId = uId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        api = ApiClient.getInstance().create(ItemsApi.class);
        contrAdapter = new contrAdapter(v.getContext(),uId);
        t_nombre = v.findViewById(R.id.nombreperfil);
        t_apellido = v.findViewById(R.id.apellidoperfil);
        t_email = v.findViewById(R.id.correoperfil);
        t_telefono = v.findViewById(R.id.telefonoperfil);
        b_editar = v.findViewById(R.id.editarperf);
        b_cerrar = v.findViewById(R.id.cerrar);
        b_contra = v.findViewById(R.id.editarcontra);

        Call<Perfil> perfilCall = api.getPerfil(uId);
        perfilCall.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {


                t_nombre.setText(response.body().uNombre);
                t_apellido.setText(response.body().uApellido);
                t_email.setText(response.body().uEmail);
                t_telefono.setText(response.body().uTelefono);
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
        b_editar.setOnClickListener(view -> editando());

        b_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mispreferencias = getActivity().getSharedPreferences("estado", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mispreferencias.edit();
                editor.putString("id",null);
                editor.commit();
                Intent intent_home = new Intent(getActivity(), Login.class);
                startActivity(intent_home);
                getActivity().onBackPressed();
            }
        });

        b_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contrAdapter.showDialog();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void editando(){
        Loading loading = new Loading(getActivity());
        loading.showDialog("Editando");
        if (true){
            pnombre = t_nombre.getText().toString();
            papellido = t_apellido.getText().toString();
            ptelefono = t_telefono.getText().toString();
            Call<Perfil> perfilsave = api.getSave(uId,pnombre,papellido,ptelefono);
            perfilsave.enqueue(new Callback<Perfil>() {
                @Override
                public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                    loading.hideDialog();
                    if (response.equals("El usuario fue editado exitosamente")) {
                        Toast.makeText(getActivity(), "Sus datos se cambiaron exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "algo", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Perfil> call, Throwable t) {
                    loading.hideDialog();
                }
            });
        }

    }
}