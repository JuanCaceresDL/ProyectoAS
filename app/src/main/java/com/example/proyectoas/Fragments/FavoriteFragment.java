package com.example.proyectoas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoas.Adapter.favAdapter;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Presenter.IPresenterFav;
import com.example.proyectoas.Presenter.PresenterFav;
import com.example.proyectoas.View.IFavorView;
import com.example.proyectoas.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements IFavorView {
    Integer uId;
    private FragmentFavoriteBinding fragmentFavoriteBinding;
    private favAdapter adapter;
    private IPresenterFav presenterFav = new PresenterFav(this);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public FavoriteFragment(Integer uId) {
        this.uId = uId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        adapter = new favAdapter(new ArrayList<>());
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        RecyclerView listafav = fragmentFavoriteBinding.recyclerfav;
        listafav.setAdapter(adapter);
        listafav.setLayoutManager(new LinearLayoutManager(getContext()));
        presenterFav.getFavoritos(uId);
        // Inflate the layout for this fragment

        Spinner spinner = fragmentFavoriteBinding.filtradoFav;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A-Z");
        arrayList.add("Z-A");
        arrayList.add("Calificación menor mayor");
        arrayList.add("Calificación mayor menor");
        arrayList.add("A-Z Departamento");
        arrayList.add("Z-A Departamento");
        arrayList.add("Visita reciente antigua");
        arrayList.add("Visita antigua reciente");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    adapter.filterAlfabetico1();
                }if (i == 1){
                    adapter.filterAlfabetico2();
                }if (i == 2){
                    adapter.filterAlfabetico3();
                }if (i == 3){
                    adapter.filterAlfabetico4();
                }if (i == 4){
                    adapter.filterAlfabetico5();
                }if (i == 5){
                    adapter.filterAlfabetico6();
                }if (i == 6){
                    adapter.filterAlfabetico7();
                }if (i == 7){
                    adapter.filterAlfabetico8();
                }
                fragmentFavoriteBinding.recyclerfav.scrollToPosition(0);
                System.out.println(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return fragmentFavoriteBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onFavoritoSuccess(List<Favoritos> favoritos) {adapter.reloadData(favoritos);}

    @Override
    public void onFavoritoError(String msg) {
        Toast.makeText(getContext(), "Aún no tiene favoritos", Toast.LENGTH_SHORT).show();
    }
}