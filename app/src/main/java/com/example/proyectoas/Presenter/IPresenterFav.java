package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Favoritos;

import java.util.List;

public interface IPresenterFav {
    void getFavoritos();
    void onFavoritoSuccess(List<Favoritos> favoritos);
    void onFavoritoError(String msg);
}
