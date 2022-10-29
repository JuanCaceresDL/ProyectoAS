package com.example.proyectoas.View;

import com.example.proyectoas.Bean.Favoritos;

import java.util.List;

public interface IFavorView {
    void onFavoritoSuccess(List<Favoritos> favoritos);
    void onFavoritoError(String msg);
}
