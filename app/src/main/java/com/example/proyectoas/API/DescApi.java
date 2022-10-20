package com.example.proyectoas.API;

import com.example.proyectoas.modelos.Detallelug;
import com.example.proyectoas.modelos.Detallerest;
import com.example.proyectoas.modelos.detalles;
import com.example.proyectoas.modelos.turistico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DescApi {
    @FormUrlEncoded
    @POST("android/getDetalleslugar.php")
    Call<Detallelug> getDeta(@Field("idtur") Integer lId);

    @FormUrlEncoded
    @POST("android/getDetallesrest.php")
    Call<Detallerest> getDetares(@Field("idres") Integer lId);

    @GET("android/getDetalles.php")
    Call<List<detalles>> getDet();
}
