package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Comentarios;

import java.util.List;

public interface IPresenterCom {
    void getComent(Integer mId);
    void postComentarios(Integer uId, Integer lId, String comentario);
    void onComentSuccess(List<Comentarios> comentarios);
    void onComentError(String msg);
}
