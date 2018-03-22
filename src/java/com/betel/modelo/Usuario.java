
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Usuario {
    
    private String idUsuario;
    private String nombreCompleto;   
    private String usuario;
    private String clave;
    private String idRol;
    private Date fechaIngreso;
    private String estado;

    public Usuario() {
    }    

    public Usuario(String idUsuario, String nombreCompleto, String usuario, String clave, String idRol, Date fechaIngreso, String estado) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.clave = clave;
        this.idRol = idRol;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }
    
    public static Usuario load(ResultSet rs)throws SQLException{
        
        Usuario objUsuario = new Usuario();
        objUsuario.setIdUsuario(rs.getString(1));
        objUsuario.setNombreCompleto(rs.getString(2));        
        objUsuario.setUsuario(rs.getString(3));
        objUsuario.setClave(rs.getString(4));
        objUsuario.setIdRol(rs.getString(5));
        objUsuario.setFechaIngreso(rs.getDate(6));
        objUsuario.setEstado(rs.getString(7));
        
        return objUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreCompleto=" + nombreCompleto + ", usuario=" + usuario + ", clave=" + clave + ", idRol=" + idRol + ", fechaIngreso=" + fechaIngreso + ", estado=" + estado + '}';
    }       
}
