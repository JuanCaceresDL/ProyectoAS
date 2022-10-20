package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class turistico {
    @SerializedName("id")
    public Integer mId;
    @SerializedName("nombre")
    public String mNombre;
    @SerializedName("imagen")
    public String mImagen;
    @SerializedName("departamento")
    public String mDepartamento;
    @SerializedName("calificacion")
    public Float mCalificacion;

    public turistico() {
    }

    public turistico( Integer mId, String mNombre, String mImagen, String mDepartamento, Float mCalificacion) {
        this.mId = mId;
        this.mNombre = mNombre;
        this.mImagen = mImagen;
        this.mDepartamento = mDepartamento;
        this.mCalificacion = mCalificacion;
    }
}
