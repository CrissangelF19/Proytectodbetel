
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ruta {
    
    private String idRuta;
    private String nombre;
    private String estado;

    public Ruta() {
    }

    public Ruta(String idRuta, String nombre, String estado) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.estado = estado;
    }

    
     public static Ruta load(ResultSet rs)throws SQLException{
        
        Ruta objRuta = new Ruta();
        objRuta.setIdRuta(rs.getString(1));
        objRuta.setNombre(rs.getString(2));
        objRuta.setEstado(rs.getString(3));

        return objRuta;
    }
    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
