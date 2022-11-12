package com.example.proyectoas.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoas.Adapter.cercAdapter;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Presenter.IPresenterClose;
import com.example.proyectoas.Presenter.PresenterCercanos;
import com.example.proyectoas.R;
import com.example.proyectoas.View.ILugarView;
import com.example.proyectoas.databinding.FragmentCloseBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CloseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloseFragment extends Fragment implements ILugarView {
    private FragmentCloseBinding closeBinding;
    private cercAdapter adapter;
    private Integer mId;
    private Spinner filtro;
    private IPresenterClose presenterClose = new PresenterCercanos(this);
    private FusedLocationProviderClient fusedLocationClient;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public Double latitud, longitud;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CloseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CloseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CloseFragment newInstance(String param1, String param2) {
        CloseFragment fragment = new CloseFragment();
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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION
            );

        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    System.out.println(location.getLatitude());
                    latitud = location.getLatitude();
                    longitud = location.getLongitude();
                    presenterClose.getCercanos(latitud, longitud);
                }
            }
        });

        adapter = new cercAdapter(new ArrayList<>());
        closeBinding = FragmentCloseBinding.inflate(getLayoutInflater());
        RecyclerView listacerc = closeBinding.recyclerclose;
        listacerc.setAdapter(adapter);
        listacerc.setLayoutManager(new LinearLayoutManager(getContext()));

        Spinner spinner = closeBinding.filtradoCercano;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("5");
        arrayList.add("10");
        arrayList.add("20");
        arrayList.add("50");
        arrayList.add("100");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    adapter.filtrarCercano(5.0000);
                }if (i == 1){
                    adapter.filtrarCercano(10.0000);
                }if (i == 2){
                    adapter.filtrarCercano(20.0000);
                }if (i == 3){
                    adapter.filtrarCercano(50.0000);
                }if (i == 4){
                    adapter.filtrarCercano(100.0000);
                }

                System.out.println(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return closeBinding.getRoot();
    }

    @Override
    public void onLugarSuccess(List<Lugares> lugares) {
        adapter.reloadData(lugares);
    }

    @Override
    public void onLugarError(String msg) {
        Toast.makeText(getContext(), "Error al obtener los cercanos", Toast.LENGTH_SHORT).show();
    }
}