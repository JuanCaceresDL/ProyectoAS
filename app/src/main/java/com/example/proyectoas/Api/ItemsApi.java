package com.example.proyectoas.Api;

import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Bean.Favoritos;
import com.example.proyectoas.Bean.Lugares;
import com.example.proyectoas.Bean.Perfil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ItemsApi {
    @GET("/android/proy2/getLugarestur.php")
    Call<List<Lugares>> getLugares();

    @GET("/android/proy2/getLugarestau.php")
    Call<List<Lugares>> getLugarestau();

    @GET("/android/proy2/getCercanos.php")
    Call<List<Lugares>> getCercanos(@Query("latitud") Double latitud, @Query("longitud") Double longitud);

    @FormUrlEncoded
    @POST("android/proy2/getFavoritos.php")
    Call<List<Favoritos>> getFavoritos(@Field("idus") Integer uId);

    @FormUrlEncoded
    @POST("android/proy2/getComentarios.php")
    Call<List<Comentarios>> getComentarios(@Field("idlug") Integer mId);

    @FormUrlEncoded
    @POST("android/proy2/getDetalles.php")
    Call<Lugares> getDetalles(@Field("idlug") Integer mId);

    @FormUrlEncoded
    @POST("android/proy2/comentarios.php")
    Call<Comentarios> getComent(@Field("idlug") Integer mId,@Field("idus") Integer cuId, @Field("comentario") String cComentario, @Field("calificacionp") Float cCalificaicon);

    @FormUrlEncoded
    @POST("android/proy2/getPerfil.php")
    Call<Perfil> getPerfil(@Field("idus") Integer uId);

    @FormUrlEncoded
    @POST("android/proy2/saveperf.php")
    Call<Perfil> getSave(@Field("idus") Integer uId, @Field("nombre") String t_nombre, @Field("apellido") String t_apellido, @Field("correo") String t_email, @Field("telefono") String t_telefono, @Field("contraseña") String t_password);
}
