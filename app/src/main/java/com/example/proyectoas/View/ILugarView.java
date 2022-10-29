package com.example.proyectoas.View;

import com.example.proyectoas.Bean.Lugares;

import java.util.List;

public interface ILugarView {
    void onLugarSuccess(List<Lugares> lugares);
    void onLugarError(String msg);
}
