package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class turistico {
    @SerializedName("nombre")
    public String mNombre;
    @SerializedName("imagen")
    public String mImagen;
    @SerializedName("departamento")
    public String mDepartamento;

    public turistico() {
    }

    public turistico(String mNombre, String mImagen, String mDepartamento) {
        this.mNombre = mNombre;
        this.mImagen = mImagen;
        this.mDepartamento = mDepartamento;
    }
}
