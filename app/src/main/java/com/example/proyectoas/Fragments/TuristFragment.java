package com.example.proyectoas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoas.Adapter.lugAdapter;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Presenter.IPresenterLugar;
import com.example.proyectoas.Presenter.PresenterLugar;
import com.example.proyectoas.View.ILugarView;
import com.example.proyectoas.databinding.FragmentTuristBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TuristFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TuristFragment extends Fragment implements ILugarView {
    private FragmentTuristBinding turistBinding;
    private lugAdapter adapter;
    private IPresenterLugar presenterLugar = new PresenterLugar(this);
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Lugares> lugList;

    public TuristFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TuristFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TuristFragment newInstance(String param1, String param2) {
        TuristFragment fragment = new TuristFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new lugAdapter(new ArrayList<>());
        turistBinding = FragmentTuristBinding.inflate(getLayoutInflater());
        RecyclerView listatur = turistBinding.recyclertur;
        listatur.setAdapter(adapter);
        listatur.setLayoutManager(new LinearLayoutManager(getContext()));
        presenterLugar.getLugares();
        // Inflate the layout for this fragment
        return turistBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onLugarSuccess(List<Lugares> lugares) {
        adapter.reloadData(lugares);
    }

    @Override
    public void onLugarError(String msg) {
        Toast.makeText(getContext(), "Error al obtener los lugares", Toast.LENGTH_SHORT).show();
    }
}