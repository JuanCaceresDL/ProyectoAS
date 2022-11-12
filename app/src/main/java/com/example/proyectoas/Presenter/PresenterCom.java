package com.example.proyectoas.Presenter;

import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Model.ComentModel;
import com.example.proyectoas.Model.IComentModel;
import com.example.proyectoas.View.IComentView;

import java.util.List;

public class PresenterCom implements IPresenterCom{

    private IComentView view;
    private IComentModel model;

    public PresenterCom(IComentView view){
        this.view = view;
        this.model = new ComentModel(this);
    }

    @Override
    public void getComent(Integer mId) {this.model.getComentarios(mId);}

    @Override
    public void onComentSuccess(List<Comentarios> comentarios) {
        if (view == null)
            return;
        this.view.onComentSuccess(comentarios);
    }

    @Override
    public void onComentError(String msg) {
        if (view == null)
            return;
        this.view.onComentError(msg);
    }
}
