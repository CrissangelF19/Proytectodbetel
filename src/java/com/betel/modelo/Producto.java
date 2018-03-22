
package com.betel.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Producto {
    
    private String idProducto;
    private String codProducto;
    private String nombre;
    private int unidadesCaja;
    private double precioVenta;
    private double pGanancia;
    private int stockMin;
    private String descripcion;
    private String idCategoria;
    private String idProveedor;
    private Date fechaRegistro;
    private int uniDisponible;    
    private String estado;
    
    public Producto() {
         
    }

    public Producto(String idProducto, String codProducto, String nombre, int unidadesCaja, double precioVenta, double pGanancia, int stockMin, String descripcion, String idCategoria, String idProveedor, Date fechaRegistro, int uniDisponible, String estado) {
        this.idProducto = idProducto;
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.unidadesCaja = unidadesCaja;
        this.precioVenta = precioVenta;
        this.pGanancia = pGanancia;
        this.stockMin = stockMin;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.fechaRegistro = fechaRegistro;
        this.uniDisponible = uniDisponible;
        this.estado = estado;
    }
          
    public static Producto load(ResultSet rs)throws SQLException{
        Producto objProducto = new Producto();
        objProducto.setIdProducto(rs.getString(1));
        objProducto.setCodProducto(rs.getString(2));
        objProducto.setNombre(rs.getString(3));
        objProducto.setUnidadesCaja(rs.getInt(4));
        objProducto.setPrecioVenta(rs.getDouble(5));
        objProducto.setpGanancia(rs.getDouble(6));
        objProducto.setStockMin(rs.getInt(7));
        objProducto.setDescripcion(rs.getString(8));
        objProducto.setIdCategoria(rs.getString(9));
        objProducto.setIdProveedor(rs.getString(10));
        objProducto.setFechaRegistro(rs.getDate(11));
        objProducto.setUniDisponible(rs.getInt(12));
        objProducto.setEstado(rs.getString(13));
        
        return objProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidadesCaja() {
        return unidadesCaja;
    }

    public void setUnidadesCaja(int unidadesCaja) {
        this.unidadesCaja = unidadesCaja;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getpGanancia() {
        return pGanancia;
    }

    public void setpGanancia(double pGanancia) {
        this.pGanancia = pGanancia;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getUniDisponible() {
        return uniDisponible;
    }

    public void setUniDisponible(int uniDisponible) {
        this.uniDisponible = uniDisponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", codProducto=" + codProducto + ", nombre=" + nombre + ", unidadesCaja=" + unidadesCaja + ", precioVenta=" + precioVenta + ", pGanancia=" + pGanancia + ", stockMin=" + stockMin + ", descripcion=" + descripcion + ", idCategoria=" + idCategoria + ", idProveedor=" + idProveedor + ", fechaRegistro=" + fechaRegistro + ", uniDisponible=" + uniDisponible + ", estado=" + estado + '}';
    }
  
}
