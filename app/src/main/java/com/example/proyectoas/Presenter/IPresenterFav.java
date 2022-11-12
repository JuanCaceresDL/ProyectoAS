package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Favoritos;

import java.util.List;

public interface IPresenterFav {
    void getFavoritos(Integer uId);
    void onFavoritoSuccess(List<Favoritos> favoritos);
    void onFavoritoError(String msg);
}
