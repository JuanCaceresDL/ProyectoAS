package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class Detallerest {
    @SerializedName("idres")
    public String rIdres;
    @SerializedName("departamento")
    public String rDepart;
    @SerializedName("imagen")
    public String rImagen;
    @SerializedName("calificacion")
    public Float rCali;
    @SerializedName("visita")
    public String rVisita;
    public Detallerest(String rIdres, String rDepart, String rImagen, Float rCali, String rVisita){
        this.rIdres = rIdres;
        this.rDepart = rDepart;
        this.rImagen = rImagen;
        this.rCali = rCali;
        this.rVisita = rVisita;
    }
}
