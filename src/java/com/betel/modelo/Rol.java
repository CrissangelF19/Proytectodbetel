
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rol {
    
    private String idRol;
    private String nombre;
    private String estado;

    public Rol() {
    }

    public Rol(String idRol, String nombre, String estado) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.estado = estado;
    }

    public static Rol load(ResultSet rs)throws SQLException{
        
        Rol objRol = new Rol();
        objRol.setIdRol(rs.getString(1));
        objRol.setNombre(rs.getString(2));
        objRol.setEstado(rs.getString(3));

        return objRol;
    }
    
    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
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
