package com.example.proyectoas.Model;

public interface ILugarModel {
    void getLugares();
    void postFavoritos(Integer fId, Integer uId);
    void deleteFavorito(Integer fId, Integer uId);
}
