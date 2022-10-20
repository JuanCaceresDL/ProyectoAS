package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class restau {
    @SerializedName("id")
    public Integer rId;
    @SerializedName("nombre")
    public String rNombre;
    @SerializedName("imagen")
    public String rImagen;
    @SerializedName("departamento")
    public String rDepartamento;
    @SerializedName("calificacion")
    public Float rCalificacion;

    public restau() {
    }

    public restau(Integer rId,String rNombre, String rImagen, String rDepartamento, Float rCalificacion) {
        this.rId = rId;
        this.rNombre = rNombre;
        this.rImagen = rImagen;
        this.rDepartamento = rDepartamento;
        this.rCalificacion = rCalificacion;
    }
}
