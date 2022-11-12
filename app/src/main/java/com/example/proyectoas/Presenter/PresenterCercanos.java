package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Model.CercanosModel;
import com.example.proyectoas.Model.ICloseModel;
import com.example.proyectoas.View.ILugarView;

import java.util.List;

public class PresenterCercanos implements IPresenterClose{

    private ILugarView view;
    private ICloseModel model;

    public PresenterCercanos(ILugarView view){
        this.view = view;
        this.model = new CercanosModel(this);
    }
    @Override
    public void getCercanos(Double latitud, Double longitud) {this.model.getCercanos(latitud, longitud);}

    @Override
    public void onLugarSuccess(List<Lugares> lugares) {
        if (view == null)
            return;
        this.view.onLugarSuccess(lugares);
    }

    @Override
    public void onLugarError(String msg) {
        if (view == null)
            return;
        this.view.onLugarError(msg);
    }
}
