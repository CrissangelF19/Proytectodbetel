
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Compra {
    private String idCompra;
    private String nCompra;
    private String idProveedor;        
    private String observacion;
    private double neto;
    private double iva;
    private double totalPagar;
    private double saldoPendiente;
    private Date fecha;
    private String pago;
    private String estado;
    private List<DetalleCompra> detalleCompra;

    public Compra() {
    }

    public Compra(String idCompra, String nCompra, String idProveedor, String observacion, double neto, double iva, double totalPagar, double saldoPendiente, Date fecha, String pago, String estado, List<DetalleCompra> detalleCompra) {
        this.idCompra = idCompra;
        this.nCompra = nCompra;
        this.idProveedor = idProveedor;
        this.observacion = observacion;
        this.neto = neto;
        this.iva = iva;
        this.totalPagar = totalPagar;
        this.saldoPendiente = saldoPendiente;
        this.fecha = fecha;
        this.pago = pago;
        this.estado = estado;
        this.detalleCompra = detalleCompra;
    }   
    
    public static Compra load(ResultSet rs)throws SQLException{
        Compra objCompra = new Compra();
        
        objCompra.setIdCompra(rs.getString(1));
        objCompra.setnCompra(rs.getString(2));
        objCompra.setIdProveedor(rs.getString(3));        
        objCompra.setObservacion(rs.getString(4));
        objCompra.setNeto(rs.getDouble(5));
        objCompra.setIva(rs.getDouble(6));
        objCompra.setTotalPagar(rs.getDouble(7));
        objCompra.setSaldoPendiente(rs.getDouble(8));
        objCompra.setFecha(rs.getDate(9));
        objCompra.setPago(rs.getString(10));
        objCompra.setEstado(rs.getString(11));        
        
        return objCompra;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getnCompra() {
        return nCompra;
    }

    public void setnCompra(String nCompra) {
        this.nCompra = nCompra;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleCompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", nCompra=" + nCompra + ", idProveedor=" + idProveedor + ", observacion=" + observacion + ", neto=" + neto + ", iva=" + iva + ", totalPagar=" + totalPagar + ", saldoPendiente=" + saldoPendiente + ", fecha=" + fecha + ", pago=" + pago + ", estado=" + estado + ", detalleCompra=" + detalleCompra + '}';
    }        
}
