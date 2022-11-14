package com.example.proyectoas.Model;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Presenter.IPresenteRestau;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestauModel implements IRestauModel{
    private ItemsApi api;
    private IPresenteRestau presenter;

    public RestauModel(IPresenteRestau presenter) {
        this.presenter = presenter;
        api = ApiClient.getInstance().create(ItemsApi.class);
    }

    @Override
    public void getLugarestau(Integer uId) {
        Call<List<Lugares>> lugCall = api.getLugarestau(uId);
        lugCall.enqueue(new Callback<List<Lugares>>() {
            @Override
            public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response) {
                presenter.onLugaresSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Lugares>> call, Throwable t) {
                presenter.onLugaresError("Error el obtener los restaurantes");
            }
        });

    }
}
