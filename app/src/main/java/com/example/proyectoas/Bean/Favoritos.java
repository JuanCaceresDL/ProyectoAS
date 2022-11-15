package com.example.proyectoas.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Favoritos {
    @SerializedName("id")
    public Integer fId;
    @SerializedName("nombre")
    public String fNombre;
    @SerializedName("idlug")
    public Integer fIdlug;
    @SerializedName("tipo")
    public String fTipo;
    @SerializedName("departamento")
    public String fDepartamento;
    @SerializedName("imagen")
    public String fImagen;
    @SerializedName("calificacion")
    public Float fCalificacion;
    @SerializedName("favorito")
    public Integer fFavorito;
    @SerializedName("visita")
    public Date fVisita;
    @SerializedName("visita2")
    public Date fVisita2;

    public Favoritos(){}

    public Favoritos(Integer fId, String fNombre, Integer fIdlug, String fTipo ,String fDepartamento, String fImagen, Float fCalificacion, Integer fFavorito, Date fVisita){
        this.fId = fId;
        this.fNombre = fNombre;
        this.fIdlug = fIdlug;
        this.fTipo = fTipo;
        this.fDepartamento = fDepartamento;
        this.fImagen = fImagen;
        this.fCalificacion = fCalificacion;
        this.fFavorito = fFavorito;
        this.fVisita = fVisita;
    }
}
