
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Empresa {
    
    private String idEmpresa;       
    private String nombre;
    private String nit; 
    private String propietario;    
    private String email;
    private String telefono;
    private String iva;
    private String direccion;
    private String ciudad;
    private String barrio;
    private String codigoPostal;
    private String logo;

    public Empresa() {
    }

    public Empresa(String idEmpresa, String nombre, String nit, String propietario, String email, String telefono, String iva, String direccion, String ciudad, String barrio, String codigoPostal, String logo) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nit = nit;
        this.propietario = propietario;
        this.email = email;
        this.telefono = telefono;
        this.iva = iva;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;
        this.logo = logo;
    }

                
    public static Empresa load(ResultSet rs)throws SQLException{        
        Empresa objEmp = new Empresa();
        
        objEmp.setIdEmpresa(rs.getString(1));
        objEmp.setNombre(rs.getString(2));
        objEmp.setNit(rs.getString(3));                
        objEmp.setPropietario(rs.getString(4));
        objEmp.setEmail(rs.getString(5));
        objEmp.setTelefono(rs.getString(6));
        objEmp.setIva(rs.getString(7));
        objEmp.setDireccion(rs.getString(8));
        objEmp.setCiudad(rs.getString(9));
        objEmp.setBarrio(rs.getString(10));
        objEmp.setCodigoPostal(rs.getString(11));
        objEmp.setLogo(rs.getString(12));
        
        return objEmp;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", nit=" + nit + ", propietario=" + propietario + ", email=" + email + ", telefono=" + telefono + ", iva=" + iva + ", direccion=" + direccion + ", ciudad=" + ciudad + ", barrio=" + barrio + ", codigoPostal=" + codigoPostal + ", logo=" + logo + '}';
    }
   
}


