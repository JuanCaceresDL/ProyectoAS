package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Model.FavoritoModel;
import com.example.proyectoas.Model.IFavoritoModel;
import com.example.proyectoas.View.IFavorView;

import java.util.List;

public class PresenterFav implements IPresenterFav{

    private IFavorView view;
    private IFavoritoModel model;

    public PresenterFav(IFavorView view){
        this.view = view;
        this.model = new FavoritoModel(this);

    }
    @Override
    public void getFavoritos() {this.model.getFavoritos();}

    @Override
    public void onFavoritoSuccess(List<Favoritos> favoritos) {
        if (view == null)
            return;
        this.view.onFavoritoSuccess(favoritos);
    }

    @Override
    public void onFavoritoError(String msg) {
        if (view == null)
            return;
        this.view.onFavoritoError(msg);
    }
}
