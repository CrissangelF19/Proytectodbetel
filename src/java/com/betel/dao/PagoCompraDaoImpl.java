
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.dao.CompraDaoImpl;
import com.betel.modelo.Compra;
import com.betel.modelo.PagoCompra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PagoCompraDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    PagoCompra pagoCompra;   

    public PagoCompraDaoImpl() {
    }

    @Override
    public String insertar(Object obj) throws SQLException {
        
        PagoCompra objPCompra = (PagoCompra) obj;
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO pago_compra VALUES (null,?,?,?,?,?)");
            
            psmt.setString(1, objPCompra.getIdCompra());
            psmt.setDouble(2, objPCompra.getMonto());
            psmt.setString(3, objPCompra.getObservacion());
            psmt.setDate(4, objPCompra.getFecha());
            psmt.setString(5, objPCompra.getEstado());
            psmt.executeUpdate();
            
            CompraDaoImpl daoCompra = new CompraDaoImpl();
            
            Compra c = (Compra) daoCompra.buscarPorIDCompra(objPCompra.getIdCompra());
            
            //Actualizar SaldoPendiente y Pago "Pagado"
            if (c.getSaldoPendiente()<= objPCompra.getMonto()) {
                
                daoCompra.actualizarPago(c.getnCompra()); 
                
                daoCompra.actualizarSaldoPendiente(c.getnCompra(), objPCompra.getMonto(), "restar");
                
            }else if (c.getSaldoPendiente()> objPCompra.getMonto()) {
                
                daoCompra.actualizarSaldoPendiente(c.getnCompra(), objPCompra.getMonto(), "restar");              
            }
            
            respuesta = "El registro exitoso";
            
        } catch (Exception e) {
            throw new SQLException("Error al registrar PagoCompra: " + e.toString());
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
    public List<?> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    @Override
    public int contar() throws SQLException {
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
    
    //######### Metodo Para Listar Pagos de Compra por ID ##########
    public List<PagoCompra> listarPagoCompra(String id) throws SQLException {
        
        List<PagoCompra> listaPCompra = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM pago_compra WHERE idCompra = ?");            
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaPCompra.add(PagoCompra.load(rs));               
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Pago Compra: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaPCompra;
    }
        
    /*public static void main(String[] args) throws SQLException {
        
        PagoCompraDaoImpl dao = new PagoCompraDaoImpl();
        
        List<PagoCompra> listaPCompra = new ArrayList<>();
        
        listaPCompra = dao.listarPagoCompra("1");
        
        for (PagoCompra p : listaPCompra) {
            
            System.out.println("Pago: " + p);            
        }
    }*/
}
