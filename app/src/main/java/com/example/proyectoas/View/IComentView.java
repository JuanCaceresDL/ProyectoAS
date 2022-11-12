package com.example.proyectoas.View;

import com.example.proyectoas.Bean.Comentarios;
import java.util.List;

public interface IComentView {
    void onComentSuccess(List<Comentarios> comentarios);
    void onComentError(String msg);
}
