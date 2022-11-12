package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;

import java.util.List;

public interface IPresenterClose {
    void getCercanos(Double latitud, Double longitud);
    void onLugarSuccess(List<Lugares> lugares);
    void onLugarError(String msg);
}
