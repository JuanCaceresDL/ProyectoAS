package com.example.proyectoas.Model;

public interface IComentModel {
    void getComentarios(Integer mId);
    void postComentarios(Integer uId, Integer lId, String comentario);
}
