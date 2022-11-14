package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Model.ILugarModel;
import com.example.proyectoas.Model.LugarModel;
import com.example.proyectoas.View.ILugarView;

import java.util.List;

public class PresenterLugar implements IPresenterLugar{

    private ILugarView view;
    private ILugarModel model;

    public PresenterLugar(ILugarView view) {
        this.view = view;
        this.model = new LugarModel(this);
    }

    @Override
    public void getLugares() {
        this.model.getLugares();
    }

    @Override
    public void postFavoritos(Integer fId, Integer uId) {
        this.model.postFavoritos(fId,uId);
    }

    @Override
    public void deleteFavoritos(Integer fId, Integer uId) {
        this.model.deleteFavorito(fId, uId);
    }

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
