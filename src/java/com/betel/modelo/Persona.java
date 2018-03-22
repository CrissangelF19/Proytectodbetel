
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Persona {
    private String idPersona;
    private String nombreCompleto;    
    private String tipoDocIdentidad;
    private String nroIdentidad;    
    private String genero;
    private String direccion;
    private String ciudad;    
    private String barrio;
    private String email;
    private String celular;
    private String telefono;    
    private String estado;

    public Persona() {
    }

    public Persona(String idPersona, String nombreCompleto, String tipoDocIdentidad, String nroIdentidad, String genero, String direccion, String ciudad, String barrio, String email, String celular, String telefono, String estado) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
        this.tipoDocIdentidad = tipoDocIdentidad;
        this.nroIdentidad = nroIdentidad;
        this.genero = genero;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.email = email;
        this.celular = celular;
        this.telefono = telefono;
        this.estado = estado;
    }  

    public static Persona load(ResultSet rs) throws SQLException{
     Persona objPersona  = new Persona();
        objPersona.setIdPersona(rs.getString(1));
        objPersona.setNombreCompleto(rs.getString(2));        
        objPersona.setTipoDocIdentidad(rs.getString(3));
        objPersona.setNroIdentidad(rs.getString(4));        
        objPersona.setGenero(rs.getString(5));
        objPersona.setDireccion(rs.getString(6));
        objPersona.setCiudad(rs.getString(7));
        objPersona.setBarrio(rs.getString(8));
        objPersona.setEmail(rs.getString(9));
        objPersona.setCelular(rs.getString(10));
        objPersona.setTelefono(rs.getString(11));        
        objPersona.setEstado(rs.getString(12));
                   
        return objPersona ;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoDocIdentidad() {
        return tipoDocIdentidad;
    }

    public void setTipoDocIdentidad(String tipoDocIdentidad) {
        this.tipoDocIdentidad = tipoDocIdentidad;
    }

    public String getNroIdentidad() {
        return nroIdentidad;
    }

    public void setNroIdentidad(String nroIdentidad) {
        this.nroIdentidad = nroIdentidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombreCompleto=" + nombreCompleto + ", tipoDocIdentidad=" + tipoDocIdentidad + ", nroIdentidad=" + nroIdentidad + ", genero=" + genero + ", direccion=" + direccion + ", ciudad=" + ciudad + ", barrio=" + barrio + ", email=" + email + ", celular=" + celular + ", telefono=" + telefono + ", estado=" + estado + '}';
    }    
}
