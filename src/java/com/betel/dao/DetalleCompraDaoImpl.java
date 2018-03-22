
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.DetalleCompra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    DetalleCompra dComp;
    int a=0;

    public DetalleCompraDaoImpl() {
    }    

    @Override
    public String insertar(Object obj) throws SQLException {
        DetalleCompra objDCompra = (DetalleCompra) obj;
        ProductoDaoImpl daoProd = new ProductoDaoImpl();
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO detalle_compra VALUES (null,?,?,?,?,?,?,?)");
            
            psmt.setString(1, objDCompra.getIdCompra());
            psmt.setString(2, objDCompra.getIdProducto());
            psmt.setDouble(3, objDCompra.getPrecioCompra());
            psmt.setDouble(4, objDCompra.getDescuento());
            psmt.setInt(5, objDCompra.getCajas());
            psmt.setInt(6, objDCompra.getUnidades());
            psmt.setInt(7, objDCompra.getTotalUnidades());
            
            psmt.executeUpdate();
            
            //Aumenta en Unidades Disponibles
            daoProd.actualizarExistencia(objDCompra.getIdProducto(), objDCompra.getTotalUnidades(), "sumar");
            
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
    public List<DetalleCompra> listar() throws SQLException {
        
        List<DetalleCompra> listaDCompra = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarDtCompra()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaDCompra.add(DetalleCompra.load(rs));                
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
        
        return listaDCompra;
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
    
    public int buscarPorIDHistCompra(String id) throws SQLException {
        try {
            
            psmt = con.conectar().prepareStatement("SELECT * FROM detalle_compra WHERE idProducto = ?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {               
                
                dComp =  DetalleCompra.load(rs);
                a=a+dComp.getTotalUnidades();
                
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
    
    public List<DetalleCompra> listarDC(String id) throws SQLException {
        
        List<DetalleCompra> listaDCompra = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarDtCompra(?)}");            
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaDCompra.add(DetalleCompra.load(rs));                
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
        
        return listaDCompra;
    }
        
    //############## Metodo para Registrar Detalle de Compra #########
    /*public static void main(String[] args) throws SQLException {
        
        DetalleCompraDaoImpl objDao = new DetalleCompraDaoImpl();
                
        DetalleCompra dt = new DetalleCompra(null, "1", "2", 12, 15, 12, 1, 15);
        
        objDao.insertar(dt);
        
    }*/
    
    //Prueba de Metodo Buscar por ID Contar
    /*public static void main(String[] args) throws SQLException {
        DetalleCompraDaoImpl objDao = new DetalleCompraDaoImpl();
        
        System.out.println("Mensaje: " + objDao.buscarPorIDHist("10"));       
       
    }*/
    
    //Prueba de Metodo listar Detalle Compra por ID 
    /*public static void main(String[] args) throws SQLException {
        DetalleCompraDaoImpl objDao = new DetalleCompraDaoImpl();
        List<DetalleCompra> listDCompra = new ArrayList(); 
        
        listDCompra = objDao.listarDC("2");
        
        for (DetalleCompra d : listDCompra) {
            
            System.out.println("Mensaje: " + d);                        
        }
    }*/
}
