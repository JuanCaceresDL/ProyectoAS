package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Perfil;
import com.example.proyectoas.Model.IPerfilModel;
import com.example.proyectoas.Model.PerfilModel;
import com.example.proyectoas.View.IPerfilView;

public class PresenterPerfil implements IPresenterPerfil{

    private IPerfilView view;
    private IPerfilModel model;

    public PresenterPerfil(IPerfilView view){
        this.view = view;
        this.model = new PerfilModel(this);
    }
    @Override
    public void getPerfil(Integer uId) {this.model.getPerfil(uId);}

    @Override
    public void onPerfilSuccess(Perfil perfil) {
        if (view == null)
            return;
        this.view.onPerfilSuccess(perfil);
    }

    @Override
    public void onPerfilError(String msg) {
        if (view == null)
            return;
        this.view.onPerfilError(msg);
    }
}
