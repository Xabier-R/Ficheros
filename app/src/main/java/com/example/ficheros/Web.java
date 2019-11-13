package com.example.ficheros;

public class Web {

    private String nombre;
    private String enlace;
    private int logo;
    private int identificador;

    public Web(String nombre, String enlace, int logo, int identificador) {
        this.nombre = nombre;
        this.enlace = enlace;
        this.logo = logo;
        this.identificador = identificador;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
}
