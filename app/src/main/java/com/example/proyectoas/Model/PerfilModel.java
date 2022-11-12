package com.example.proyectoas.Model;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Perfil;
import com.example.proyectoas.Presenter.IPresenterPerfil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilModel implements IPerfilModel{
    private IPresenterPerfil presenterPerfil;
    private ItemsApi api;

    public PerfilModel(IPresenterPerfil presenterPerfil){
        this.presenterPerfil = presenterPerfil;
        api = ApiClient.getInstance().create(ItemsApi.class);

    }

    @Override
    public void getPerfil(Integer uId) {

        Call<Perfil> perfilCall = api.getPerfil(uId);
        perfilCall.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                presenterPerfil.onPerfilSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                presenterPerfil.onPerfilError("No existe el perfil");
            }
        });
    }
}
