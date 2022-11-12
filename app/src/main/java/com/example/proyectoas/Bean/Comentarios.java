package com.example.proyectoas.Bean;

import com.google.gson.annotations.SerializedName;

public class Comentarios {
    @SerializedName("idlug")
    public Integer cId;
    @SerializedName("idus")
    public Integer cuId;
    @SerializedName("nombre")
    public String cNombre;
    @SerializedName("comentario")
    public String cComentario;
    @SerializedName("calificacionp")
    public Float cCalificacion;

    public Comentarios(){}

    public Comentarios(Integer cId, Integer cuId,String cNombre, String cComentario, Float cCalificacion){
        this.cId = cId;
        this.cuId = cuId;
        this.cNombre = cNombre;
        this.cComentario = cComentario;
        this.cCalificacion = cCalificacion;
    }
}
