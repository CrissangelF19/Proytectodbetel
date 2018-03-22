
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado extends Persona{
    private String idEmpleado;
    private String idRol;
    private Date fechaIngreso;
    private String estadoz;
    
    public Empleado() {
    }

    public Empleado(String idEmpleado, String idRol, Date fechaIngreso, String estadoz, String idPersona, String nombreCompleto, String tipoDocIdentidad, String nroIdentidad, String genero, String direccion, String ciudad, String barrio, String email, String celular, String telefono, String estado) {
        super(idPersona, nombreCompleto, tipoDocIdentidad, nroIdentidad, genero, direccion, ciudad, barrio, email, celular, telefono, estado);
        this.idEmpleado = idEmpleado;
        this.idRol = idRol;
        this.fechaIngreso = fechaIngreso;
        this.estadoz = estadoz;
    }
    
    public static Empleado load(ResultSet rs) throws SQLException{
      Empleado objEmpl= new Empleado();
         
        objEmpl.setIdEmpleado(rs.getString(1));
        objEmpl.setIdPersona(rs.getString(2));
        objEmpl.setIdRol(rs.getString(3));
        objEmpl.setFechaIngreso(rs.getDate(4));
        objEmpl.setEstadoz(rs.getString(5));   
        objEmpl.setNombreCompleto(rs.getString(6));
        objEmpl.setTipoDocIdentidad(rs.getString(7));
        objEmpl.setNroIdentidad(rs.getString(8));        
        objEmpl.setGenero(rs.getString(9));
        objEmpl.setDireccion(rs.getString(10));
        objEmpl.setCiudad(rs.getString(11));
        objEmpl.setBarrio(rs.getString(12));
        objEmpl.setEmail(rs.getString(13));
        objEmpl.setCelular(rs.getString(14));
        objEmpl.setTelefono(rs.getString(15));
        objEmpl.setEstado(rs.getString(16)); 

        return objEmpl;
      }  
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getEstadoz() {
        return estadoz;
    }

    public void setEstadoz(String estadoz) {
        this.estadoz = estadoz;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", idRol=" + idRol + ", fechaIngreso=" + fechaIngreso + ", estadoz=" + estadoz + '}';
    }

    
}
