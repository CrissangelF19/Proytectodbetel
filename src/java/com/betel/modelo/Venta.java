
package com.betel.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Venta {    
    private String idVenta;
    private String nVenta;
    private String idCliente;
    private String idEmpleado;   
    private String idEmpresa;
    private double efectivo;
    private String observacion;
    private double subtotal;
    private double iva;
    private double totalPagar;
    private double saldoPendiente;
    private Date fecha;
    private String pago;
    private String estado;
    private List<DetalleVenta> detalleVenta;

    public Venta() {
    }

    public Venta(String idVenta, String nVenta, String idCliente, String idEmpleado, String idEmpresa, double efectivo, String observacion, double subtotal, double iva, double totalPagar, double saldoPendiente, Date fecha, String pago, String estado, List<DetalleVenta> detalleVenta) {
        this.idVenta = idVenta;
        this.nVenta = nVenta;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idEmpresa = idEmpresa;
        this.efectivo = efectivo;
        this.observacion = observacion;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalPagar = totalPagar;
        this.saldoPendiente = saldoPendiente;
        this.fecha = fecha;
        this.pago = pago;
        this.estado = estado;
        this.detalleVenta = detalleVenta;
    }
    
    public static Venta load(ResultSet rs)throws SQLException{        
        Venta objVenta = new Venta();
        
        objVenta.setIdVenta(rs.getString(1));
        objVenta.setnVenta(rs.getString(2));
        objVenta.setIdCliente(rs.getString(3));
        objVenta.setIdEmpleado(rs.getString(4));
        objVenta.setIdEmpresa(rs.getString(5));
        objVenta.setEfectivo(rs.getDouble(6));
        objVenta.setObservacion(rs.getString(7));
        objVenta.setSubtotal(rs.getDouble(8));
        objVenta.setIva(rs.getDouble(9));
        objVenta.setTotalPagar(rs.getDouble(10));
        objVenta.setSaldoPendiente(rs.getDouble(11));
        objVenta.setFecha(rs.getDate(12));
        objVenta.setPago(rs.getString(13));
        objVenta.setEstado(rs.getString(14));
        
        return objVenta;        
    }    

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getnVenta() {
        return nVenta;
    }

    public void setnVenta(String nVenta) {
        this.nVenta = nVenta;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", nVenta=" + nVenta + ", idCliente=" + idCliente + ", idEmpleado=" + idEmpleado + ", idEmpresa=" + idEmpresa + ", efectivo=" + efectivo + ", observacion=" + observacion + ", subtotal=" + subtotal + ", iva=" + iva + ", totalPagar=" + totalPagar + ", saldoPendiente=" + saldoPendiente + ", fecha=" + fecha + ", pago=" + pago + ", estado=" + estado + ", detalleVenta=" + detalleVenta + '}';
    }        
}
