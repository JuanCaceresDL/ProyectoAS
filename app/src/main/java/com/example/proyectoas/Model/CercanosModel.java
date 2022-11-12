package com.example.proyectoas.Model;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Presenter.IPresenterClose;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CercanosModel implements ICloseModel{

    private IPresenterClose presenterClose;
    private ItemsApi api;

    public CercanosModel(IPresenterClose presenterClose){
        this.presenterClose = presenterClose;
        api = ApiClient.getInstance().create(ItemsApi.class);

    }
    @Override
    public void getCercanos(Double latitud, Double longitud) {
        Call<List<Lugares>> lugcall = api.getCercanos(latitud,longitud);
        lugcall.enqueue(new Callback<List<Lugares>>() {
            @Override
            public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response) {
                presenterClose.onLugarSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Lugares>> call, Throwable t) {
                presenterClose.onLugarError("error al mostrar los cercanos");
            }
        });

    }
}
