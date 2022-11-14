package com.example.proyectoas.Bean;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class Lugares {
    @SerializedName("id")
    public Integer rId;
    @SerializedName("nombre")
    public String rNombre;
    @SerializedName("departamento")
    public String rDepartamento;
    @SerializedName("descripcion")
    public String rDescripcion;
    @SerializedName("calificacion")
    public Float rCalificacion;
    @SerializedName("tipo")
    public String rTipo;
    @SerializedName("imagen")
    public String rImagen;
    @SerializedName("waze")
    public String rWaze;
    @SerializedName("google")
    public String rGoogle;
    @SerializedName("distancia")
    public Number rDistancia;
    @SerializedName("favorito")
    public Integer rFavorito;


    public Double distanciacast1;
    public Double distanciacast2;



    public Lugares() {
    }

    public Lugares(Integer rId, String rNombre, String rImagen, String rDepartamento, String rDescripcion ,Float rCalificacion, String rTipo, String rWaze, String rGoogle, Number rDistancia, Integer rFavorito) {
        this.rId = rId;
        this.rNombre = rNombre;
        this.rImagen = rImagen;
        this.rDepartamento = rDepartamento;
        this.rDescripcion = rDescripcion;
        this.rTipo = rTipo;
        this.rWaze = rWaze;
        this.rGoogle = rGoogle;
        this.rCalificacion = rCalificacion;
        this.rDistancia = rDistancia;
        this.rFavorito = rFavorito;
    }
}
