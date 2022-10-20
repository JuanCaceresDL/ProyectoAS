package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class restau {
    @SerializedName("nombre")
    public String rNombre;
    @SerializedName("imagen")
    public String rImagen;
    @SerializedName("departamento")
    public String rDepartamento;

    public restau() {
    }

    public restau(String rNombre, String rImagen, String rDepartamento) {
        this.rNombre = rNombre;
        this.rImagen = rImagen;
        this.rDepartamento = rDepartamento;
    }
}
