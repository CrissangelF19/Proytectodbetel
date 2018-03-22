
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PagoCompra {
    
    private String idPago;
    private String idCompra;
    private double monto;
    private String observacion;
    private Date fecha;
    private String estado;

    public PagoCompra() {
    }

    public PagoCompra(String idPago, String idCompra, double monto, String observacion, Date fecha, String estado) {
        this.idPago = idPago;
        this.idCompra = idCompra;
        this.monto = monto;
        this.observacion = observacion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public static PagoCompra load(ResultSet rs)throws SQLException{
        
        PagoCompra objPago = new PagoCompra();        
        
        objPago.setIdPago(rs.getString(1));
        objPago.setIdCompra(rs.getString(2));
        objPago.setMonto(rs.getDouble(3));
        objPago.setObservacion(rs.getString(4));
        objPago.setFecha(rs.getDate(5));
        objPago.setEstado(rs.getString(6));       

        return objPago;
    }
    
    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PagoCompra{" + "idPago=" + idPago + ", idCompra=" + idCompra + ", monto=" + monto + ", observacion=" + observacion + ", fecha=" + fecha + ", estado=" + estado + '}';
    }   
    
}
