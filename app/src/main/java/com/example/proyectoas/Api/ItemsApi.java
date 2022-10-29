package com.example.proyectoas.Api;

import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Bean.Lugares;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ItemsApi {
    @GET("/android/proy2/getLugarestur.php")
    Call<List<Lugares>> getLugares();

    @GET("/android/proy2/getLugarestau.php")
    Call<List<Lugares>> getLugarestau();

    @FormUrlEncoded
    @POST("android/proy2/getFavoritos.php")
    Call<List<Favoritos>> getFavoritos(@Field("idus") Integer lId);
}
