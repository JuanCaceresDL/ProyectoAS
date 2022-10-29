package com.example.proyectoas.Model;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Presenter.IPresenterLugar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LugarModel implements ILugarModel{
    private IPresenterLugar presenter;
    private ItemsApi api;

    public LugarModel(IPresenterLugar presenter) {
        this.presenter = presenter;
        api = ApiClient.getInstance().create(ItemsApi.class);
    }

    @Override
    public void getLugares() {
        Call<List<Lugares>> lugCall = api.getLugares();
        lugCall.enqueue(new Callback<List<Lugares>>() {
            @Override
            public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response) {
                presenter.onLugaresSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Lugares>> call, Throwable t) {
                presenter.onLugaresError("Error el obtener los lugares turísticos");
            }
        });
    }
}
