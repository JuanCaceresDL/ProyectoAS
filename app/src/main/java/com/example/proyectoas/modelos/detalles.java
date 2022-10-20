package com.example.proyectoas.modelos;

import com.google.gson.annotations.SerializedName;

public class detalles {
    @SerializedName("idt")
    public Integer dIdt;
    @SerializedName("idtur")
    public Integer dIdtur;
    @SerializedName("departamentol")
    public String dDepartamentol;
    @SerializedName("imagenl")
    public String dImagenl;
    @SerializedName("emaill")
    public String dEmaill;
    @SerializedName("comentariol")
    public String dComentariol;
    @SerializedName("ratingl")
    public Float dRatingl;

    @SerializedName("idr")
    public Integer dIdr;
    @SerializedName("idres")
    public Integer dIdres;
    @SerializedName("departamentor")
    public String dDepartamentor;
    @SerializedName("imagenr")
    public String dImagenr;
    @SerializedName("emailr")
    public String dEmailr;
    @SerializedName("comentarior")
    public String dComentarior;
    @SerializedName("ratingr")
    public Float dRatingr;


    public detalles (){
    }

    public detalles( Integer dIdt, Integer dIdtur, String dDepartamentol, String dImagenl, String dEmaill, String dComentariol, Float dRatingl,Integer dIdr, Integer dIdres, String dDepartamentor, String dImagenr, String dEmailr, String dComentarior, Float dRatingr) {
        this.dIdt = dIdt;
        this.dIdtur = dIdtur;
        this.dDepartamentol = dDepartamentol;
        this.dImagenl = dImagenl;
        this.dEmaill = dEmaill;
        this.dComentariol = dComentariol;
        this.dRatingl = dRatingl;

        this.dIdr = dIdr;
        this.dIdres = dIdres;
        this.dDepartamentor = dDepartamentor;
        this.dImagenr = dImagenr;
        this.dEmailr = dEmailr;
        this.dComentarior = dComentarior;
        this.dRatingr = dRatingr;
    }

}
