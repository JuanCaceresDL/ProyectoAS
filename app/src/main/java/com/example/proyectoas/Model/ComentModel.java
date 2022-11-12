package com.example.proyectoas.Model;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Presenter.IPresenterCom;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentModel implements IComentModel{
    private IPresenterCom presenterCom;
    private ItemsApi api;

    public ComentModel(IPresenterCom presenterCom){
        this.presenterCom = presenterCom;
        api = ApiClient.getInstance().create(ItemsApi.class);

    }

    @Override
    public void getComentarios(Integer mId) {

        Call<List<Comentarios>> comCall = api.getComentarios(mId);
        comCall.enqueue(new Callback<List<Comentarios>>() {
            @Override
            public void onResponse(Call<List<Comentarios>> call, Response<List<Comentarios>> response) {
                presenterCom.onComentSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Comentarios>> call, Throwable t) {
                presenterCom.onComentError("No hay comentarios");
            }
        });
    }
}
