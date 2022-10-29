package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Model.IRestauModel;
import com.example.proyectoas.Model.RestauModel;
import com.example.proyectoas.View.ILugarView;

import java.util.List;

public class PresenteRestau implements IPresenteRestau{

    private ILugarView view;
    private IRestauModel model;

    public PresenteRestau(ILugarView view){
        this.view = view;
        this.model = new RestauModel(this);

    }
    @Override
    public void getLugarestau() {this.model.getLugarestau();}

    @Override
    public void onLugaresSuccess(List<Lugares> lugares) {
        if (view == null)
            return;
        this.view.onLugarSuccess(lugares);
    }

    @Override
    public void onLugaresError(String msg) {
        if (view == null)
            return;
        this.view.onLugarError(msg);
    }
}
