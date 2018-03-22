
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DetalleCompra {
    
    private String idDetalleCompra;
    private String idCompra;
    private String idProducto;
    private double precioCompra;
    private double descuento;
    private int cajas;
    private int unidades;
    private int totalUnidades;

    public DetalleCompra() {
    }

    public DetalleCompra(String idDetalleCompra, String idCompra, String idProducto, double precioCompra, double descuento, int cajas, int unidades, int totalUnidades) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.precioCompra = precioCompra;
        this.descuento = descuento;
        this.cajas = cajas;
        this.unidades = unidades;
        this.totalUnidades = totalUnidades;
    }
    
    public static DetalleCompra load(ResultSet rs)throws SQLException{
        
        DetalleCompra objDC = new DetalleCompra();
        
        objDC.setIdDetalleCompra(rs.getString(1));
        objDC.setIdCompra(rs.getString(2));
        objDC.setIdProducto(rs.getString(3));
        objDC.setPrecioCompra(rs.getDouble(4));
        objDC.setDescuento(rs.getDouble(5));
        objDC.setCajas(rs.getInt(6));
        objDC.setUnidades(rs.getInt(7));
        objDC.setTotalUnidades(rs.getInt(8));
        
        return objDC;        
    }

    public String getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(String idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
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

    @Override
    public String toString() {
        return "DetalleCompra{" + "idDetalleCompra=" + idDetalleCompra + ", idCompra=" + idCompra + ", idProducto=" + idProducto + ", precioCompra=" + precioCompra + ", descuento=" + descuento + ", cajas=" + cajas + ", unidades=" + unidades + ", totalUnidades=" + totalUnidades + '}';
    }   
    
}
