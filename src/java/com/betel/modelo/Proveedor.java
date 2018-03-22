
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Proveedor extends Persona{
    
    
    public Proveedor() {
       
    }
//Hereda de Persona
    
    private String idProveedor;
    private String nit;
    private String razonSocial;
    private Date fechaingreso;
    private String estadop;

    public Proveedor(String idProveedor, String nit, String razonSocial, Date fechaingreso, String estadop, String idPersona, String nombreCompleto, String tipoDocIdentidad, String nroIdentidad, String genero, String direccion, String ciudad, String barrio, String email, String celular, String telefono, String estado) {
        super(idPersona, nombreCompleto, tipoDocIdentidad, nroIdentidad, genero, direccion, ciudad, barrio, email, celular, telefono, estado);
        this.idProveedor = idProveedor;
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.fechaingreso = fechaingreso;
        this.estadop = estadop;
    }
    
    public static Proveedor load(ResultSet rs) throws SQLException{
      
        Proveedor objProveedor = new Proveedor();
        
        objProveedor.setIdProveedor(rs.getString(1));
        objProveedor.setNit(rs.getString(2));
        objProveedor.setRazonSocial(rs.getString(3));
        objProveedor.setIdPersona(rs.getString(4));
        objProveedor.setFechaIngreso(rs.getDate(5));
        objProveedor.setEstadop(rs.getString(6));
        objProveedor.setNombreCompleto(rs.getString(7));        
        objProveedor.setTipoDocIdentidad(rs.getString(8));
        objProveedor.setNroIdentidad(rs.getString(9));        
        objProveedor.setGenero(rs.getString(10));
        objProveedor.setDireccion(rs.getString(11));
        objProveedor.setCiudad(rs.getString(12));
        objProveedor.setBarrio(rs.getString(13));
        objProveedor.setEmail(rs.getString(14));
        objProveedor.setCelular(rs.getString(15));
        objProveedor.setTelefono(rs.getString(16));        
        objProveedor.setEstado(rs.getString(17));
                   
        return objProveedor;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Date getFechaIngreso() {
        return fechaingreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaingreso = fechaIngreso;
    }

    public String getEstadop() {
        return estadop;
    }

    public void setEstadop(String estadop) {
        this.estadop = estadop;
    }

    @Override
    public String toString() {
        return  razonSocial ;
    }
    
}
