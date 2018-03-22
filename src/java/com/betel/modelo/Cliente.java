
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cliente extends Persona {
   
    private String idCliente;
    private String idRuta;
    private Date fechaIngreso;
    private String estadoc;

    public Cliente() {
    }

    public Cliente(String idCliente, String idRuta, Date fechaIngreso, String estadoc, String idPersona, String nombreCompleto, String tipoDocIdentidad, String nroIdentidad, String genero, String direccion, String ciudad, String barrio, String email, String celular, String telefono, String estado) {
        super(idPersona, nombreCompleto, tipoDocIdentidad, nroIdentidad, genero, direccion, ciudad, barrio, email, celular, telefono, estado);
        this.idCliente = idCliente;
        this.idRuta = idRuta;
        this.fechaIngreso = fechaIngreso;
        this.estadoc = estadoc;
    }
    
    public static Cliente load(ResultSet rs) throws SQLException{
      Cliente objClient= new Cliente();
         
        objClient.setIdCliente(rs.getString(1));
        objClient.setIdPersona(rs.getString(2));
        objClient.setIdRuta(rs.getString(3));
        objClient.setFechaIngreso(rs.getDate(4));
        objClient.setEstadoc(rs.getString(5));    
        objClient.setNombreCompleto(rs.getString(6));
        objClient.setTipoDocIdentidad(rs.getString(7));
        objClient.setNroIdentidad(rs.getString(8));        
        objClient.setGenero(rs.getString(9));
        objClient.setDireccion(rs.getString(10));
        objClient.setCiudad(rs.getString(11));
        objClient.setBarrio(rs.getString(12));
        objClient.setEmail(rs.getString(13));
        objClient.setCelular(rs.getString(14));
        objClient.setTelefono(rs.getString(15));        
        objClient.setEstado(rs.getString(16)); 

        return objClient;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstadoc() {
        return estadoc;
    }

    public void setEstadoc(String estadoc) {
        this.estadoc = estadoc;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", idRuta=" + idRuta + ", fechaIngreso=" + fechaIngreso + ", estadoc=" + estadoc + '}';
    }
    
}
