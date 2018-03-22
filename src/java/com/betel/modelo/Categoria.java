
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria {   
    private String idCategoria;
    private String nombre;
    private String estado;
    
    
    public Categoria() {
    }

    public static Categoria load(ResultSet rs)throws SQLException{
        Categoria objCategoria = new Categoria();
        objCategoria.setIdCategoria(rs.getString(1));
        objCategoria.setNombre(rs.getString(2));
        objCategoria.setEstado(rs.getString(3));
        
        return objCategoria;
    }
    
    public Categoria(String idCategoria, String nombre, String estado) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
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
