package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;

import java.util.List;

public interface IPresenteRestau {
    void getLugarestau();
    void onLugaresSuccess(List<Lugares> lugares);
    void onLugaresError(String msg);
}
