package com.example.proyectoas.Model;

public interface IFavoritoModel {
    void getFavoritos(Integer fId);
    void postFavoritos(Integer fId, Integer uId);
    void deleteFavorito(Integer fId, Integer uId);
}
