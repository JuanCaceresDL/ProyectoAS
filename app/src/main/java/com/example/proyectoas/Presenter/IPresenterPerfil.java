package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Perfil;

public interface IPresenterPerfil {
    void getPerfil(Integer uId);
    void onPerfilSuccess(Perfil perfil);
    void onPerfilError(String msg);
}
