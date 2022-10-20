package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Detallelug {
    @SerializedName("idtur")
    public String lIdtur;
    @SerializedName("departamento")
    public String lDepart;
    @SerializedName("imagen")
    public String lImagen;
    @SerializedName("calificacion")
    public Float lCali;
    @SerializedName("visita")
    public String lVisita;

    public Detallelug(String lIdtur, String lDepart, String lImagen, Float lCali, String lVisita){
        this.lIdtur = lIdtur;
        this.lDepart = lDepart;
        this.lImagen = lImagen;
        this.lCali = lCali;
        this.lVisita = lVisita;
    }
}


