
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DetalleVenta {
    
    private String idDetalleVenta;
    private String idVenta;
    private String idProducto;    
    private int cajas;
    private int unidades;
    private int totalUnidades;
    private double precioVenta;
    private double descuento;    

    public DetalleVenta() {
    }

    public DetalleVenta(String idDetalleVenta, String idVenta, String idProducto, int cajas, int unidades, int totalUnidades, double precioVenta, double descuento) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cajas = cajas;
        this.unidades = unidades;
        this.totalUnidades = totalUnidades;
        this.precioVenta = precioVenta;
        this.descuento = descuento;
    }
    
    public static DetalleVenta load(ResultSet rs)throws SQLException{        
        DetalleVenta objDV = new DetalleVenta();
        
        objDV.setIdDetalleVenta(rs.getString(1));
        objDV.setIdVenta(rs.getString(2));
        objDV.setIdProducto(rs.getString(3));
        objDV.setCajas(rs.getInt(4));
        objDV.setUnidades(rs.getInt(5));
        objDV.setTotalUnidades(rs.getInt(6));
        objDV.setPrecioVenta(rs.getDouble(7));
        objDV.setDescuento(rs.getDouble(8));       
        
        return objDV;        
    }

    public String getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(String idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCajas() {
        return cajas;
    }

    public void setCajas(int cajas) {
        this.cajas = cajas;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idVenta=" + idVenta + ", idProducto=" + idProducto + ", cajas=" + cajas + ", unidades=" + unidades + ", totalUnidades=" + totalUnidades + ", precioVenta=" + precioVenta + ", descuento=" + descuento + '}';
    }
    
}
