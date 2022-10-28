package com.example.proyectoas.API;

import com.example.proyectoas.modelos.Detallelug;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DetallApi {
    @FormUrlEncoded
    @POST("/android/getDetalles.php")
    Call<Detallelug> getDeta(@Field("idtur") Integer lId);
}
