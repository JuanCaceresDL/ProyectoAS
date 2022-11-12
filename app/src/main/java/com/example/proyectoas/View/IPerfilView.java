package com.example.proyectoas.View;

import com.example.proyectoas.Bean.Perfil;

public interface IPerfilView {
    void onPerfilSuccess(Perfil perfil);
    void onPerfilError(String msg);
}
