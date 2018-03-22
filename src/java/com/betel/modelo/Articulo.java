
package com.betel.modelo;

public class Articulo {
    
    private String idProducto;
    private int cajas;
    private int unid;
    private double precioUnid;
    private double dscto;

    public Articulo(String idProducto, int cajas, int unid, double precioUnid, double dscto) {
        this.idProducto = idProducto;
        this.cajas = cajas;
        this.unid = unid;
        this.precioUnid = precioUnid;
        this.dscto = dscto;
    }

    public Articulo() {
    }

    /**
     * @return the idProducto
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the cajas
     */
    public int getCajas() {
        return cajas;
    }

    /**
     * @param cajas the cajas to set
     */
    public void setCajas(int cajas) {
        this.cajas = cajas;
    }

    /**
     * @return the unid
     */
    public int getUnid() {
        return unid;
    }

    /**
     * @param unid the unid to set
     */
    public void setUnid(int unid) {
        this.unid = unid;
    }

    /**
     * @return the precioUnid
     */
    public double getPrecioUnid() {
        return precioUnid;
    }

    /**
     * @param precioUnid the precioUnid to set
     */
    public void setPrecioUnid(double precioUnid) {
        this.precioUnid = precioUnid;
    }

    /**
     * @return the dscto
     */
    public double getDscto() {
        return dscto;
    }

    /**
     * @param dscto the dscto to set
     */
    public void setDscto(double dscto) {
        this.dscto = dscto;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idProducto=" + idProducto + ", cajas=" + cajas + ", unid=" + unid + ", precioUnid=" + precioUnid + ", dscto=" + dscto + '}';
    }
    
}
