package com.example.proyectoas.Bean;

import com.google.gson.annotations.SerializedName;

public class Perfil {
    @SerializedName("id")
    public Integer uId;
    @SerializedName("nombre")
    public String uNombre;
    @SerializedName("apellido")
    public String uApellido;
    @SerializedName("email")
    public String uEmail;
    @SerializedName("telefono")
    public String uTelefono;


    public Perfil(){}

    public Perfil(Integer uId, String uNombre, String uApellido, String uEmail, String uTelefono){
        this.uId = uId;
        this.uNombre = uNombre;
        this.uApellido = uApellido;
        this.uEmail = uEmail;
        this.uTelefono = uTelefono;
    }
}
