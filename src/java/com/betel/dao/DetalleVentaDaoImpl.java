
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.DetalleVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DetalleVentaDaoImpl implements IDAO{    
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    DetalleVenta dVenta;
    int a=0;
    
    public DetalleVentaDaoImpl() {
    }    

    @Override
    public String insertar(Object obj) throws SQLException {
        
        DetalleVenta objDVenta = (DetalleVenta) obj;
        ProductoDaoImpl daoProd = new ProductoDaoImpl();
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO detalle_venta VALUES (null,?,?,?,?,?,?,?)");
            
            psmt.setString(1, objDVenta.getIdVenta());
            psmt.setString(2, objDVenta.getIdProducto());
            psmt.setInt(3, objDVenta.getCajas());
            psmt.setInt(4, objDVenta.getUnidades());
            psmt.setInt(5, objDVenta.getTotalUnidades());
            psmt.setDouble(6, objDVenta.getPrecioVenta());
            psmt.setDouble(7, objDVenta.getDescuento());
            
            psmt.executeUpdate();
            
            //Disminuye en Unidades Disponibles
            daoProd.actualizarExistencia(objDVenta.getIdProducto(), objDVenta.getTotalUnidades(), "restar");
            
            respuesta = "El registro exitoso";
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            
            con.desconectar();            
        }
        
        return respuesta;
    }

    @Override
    public String eliminar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleVenta> listar() throws SQLException {
        List<DetalleVenta> listaDVenta = new ArrayList<>();        
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarDtVenta()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaDVenta.add(DetalleVenta.load(rs));
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Detalle_Venta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaDVenta;
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> busquedaPorParametro(String field, Object param) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int buscarPorIDHistVenta(String id) throws SQLException {
        try {
            
            psmt = con.conectar().prepareStatement("SELECT * FROM detalle_venta WHERE idProducto = ?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                
                dVenta = DetalleVenta.load(rs);
                a=a+dVenta.getTotalUnidades();
                
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta DCompra: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return a;
    }
    
    public List<DetalleVenta> listarDV(String id) throws SQLException {
        List<DetalleVenta> listaDVenta = new ArrayList<>();        
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarDtVenta(?)}");
            psmt.setString(1,id);            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaDVenta.add(DetalleVenta.load(rs));
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Detalle_Venta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaDVenta;
    }
    
    /*
    //############## Metodo para Registrar Detalle de Venta #########
    public static void main(String[] args) throws SQLException {
        
        DetalleVentaDaoImpl objDao = new DetalleVentaDaoImpl();
        
        DetalleVenta dt = new DetalleVenta(null, "1", "1", 1, 1, 1, 1, 1);        
        
        objDao.insertar(dt);
        
    }*/
    
    /*
    //Prueba de Metodo Buscar por ID Contar
    public static void main(String[] args) throws SQLException {
        DetalleVentaDaoImpl objDao = new DetalleVentaDaoImpl();
        
        System.out.println("Mensaje: " + objDao.buscarPorIDHistVenta("3"));       
       
    }*/
    
    /*
    //Prueba de Metodo listar Detalle Venta por ID 
    public static void main(String[] args) throws SQLException {
        DetalleVentaDaoImpl objDao = new DetalleVentaDaoImpl();
        
        List<DetalleVenta> listDVenta = new ArrayList();
        
        listDVenta = objDao.listarDV("3");
        
        for (DetalleVenta d : listDVenta) {
            
            System.out.println("Mensaje: " + d);                        
        }
        
    }*/
    
}
