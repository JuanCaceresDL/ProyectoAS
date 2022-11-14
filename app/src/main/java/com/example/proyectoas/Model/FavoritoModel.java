package com.example.proyectoas.Model;

import android.content.SharedPreferences;

import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Presenter.IPresenterFav;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritoModel implements IFavoritoModel{
    private IPresenterFav presenterFav;
    private ItemsApi api;

    public FavoritoModel(IPresenterFav presenterFav){
        this.presenterFav = presenterFav;
        api = ApiClient.getInstance().create(ItemsApi.class);

    }

    @Override
    public void getFavoritos(Integer fId) {

        Call<List<Favoritos>> favCall = api.getFavoritos(fId);
        favCall.enqueue(new Callback<List<Favoritos>>() {
            @Override
            public void onResponse(Call<List<Favoritos>> call, Response<List<Favoritos>> response) {
                presenterFav.onFavoritoSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Favoritos>> call, Throwable t) {
                presenterFav.onFavoritoError("Aún no hay favoritos");
            }
        });

    }

    @Override
    public void postFavoritos(Integer fId, Integer uId) {
        Call<String> favCall = api.postFavoritos(fId,uId,1);
        favCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                presenterFav.onFavoritoError("Aún no hay favoritos");
            }
        });
    }
    public void deleteFavorito(Integer fId, Integer uId) {
        Call<String> favCall = api.deleteFavoritos(uId,fId);
        favCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
