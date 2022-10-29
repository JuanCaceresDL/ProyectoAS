package com.example.proyectoas.Bean;

import com.google.gson.annotations.SerializedName;

public class Favoritos {
    @SerializedName("id")
    public Integer fId;
    @SerializedName("nombre")
    public String fNombre;
    @SerializedName("departamento")
    public String fDepartamento;
    @SerializedName("imagen")
    public String fImagen;
    @SerializedName("calificacion")
    public Float fCalificacion;
    @SerializedName("favorito")
    public Integer fFavorito;

    public Favoritos(){}

    public Favoritos(Integer fId, String fNombre, String fDepartamento, String fImagen, Float fCalificacion, Integer fFavorito){
        this.fId = fId;
        this.fNombre = fNombre;
        this.fDepartamento = fDepartamento;
        this.fImagen = fImagen;
        this.fCalificacion = fCalificacion;
        this.fFavorito = fFavorito;

    }
}
