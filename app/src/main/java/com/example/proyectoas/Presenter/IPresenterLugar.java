package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;

import java.util.List;

public interface IPresenterLugar {
    void getLugares(Integer uId);
    void postFavoritos(Integer fId, Integer uId);
    void deleteFavoritos(Integer fId, Integer uId);
    void onLugaresSuccess(List<Lugares> lugares);
    void onLugaresError(String msg);
}
