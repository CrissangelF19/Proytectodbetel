
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PagoVenta {
    
    private String idPago;
    private String idVenta;
    private double monto;
    private String observacion;
    private Date fecha;
    private String estado;

    public PagoVenta() {
    }   

    public PagoVenta(String idPago, String idVenta, double monto, String observacion, Date fecha, String estado) {
        this.idPago = idPago;
        this.idVenta = idVenta;
        this.monto = monto;
        this.observacion = observacion;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    public static PagoVenta load(ResultSet rs)throws SQLException{
        
        PagoVenta objPago = new PagoVenta();        
        
        objPago.setIdPago(rs.getString(1));
        objPago.setIdVenta(rs.getString(2));
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

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
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
        return "Pago_Venta{" + "idPago=" + idPago + ", idVenta=" + idVenta + ", monto=" + monto + ", observacion=" + observacion + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
}
