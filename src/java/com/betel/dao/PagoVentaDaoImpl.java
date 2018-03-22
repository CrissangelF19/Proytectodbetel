
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.PagoVenta;
import com.betel.modelo.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PagoVentaDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    PagoVenta pagoventa;

    public PagoVentaDaoImpl() {
    }

    @Override
    public String insertar(Object obj) throws SQLException {
        PagoVenta objPVenta = (PagoVenta) obj;
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO pago_venta VALUES (null,?,?,?,?,?)");
            
            psmt.setString(1, objPVenta.getIdVenta());
            psmt.setDouble(2, objPVenta.getMonto());
            psmt.setString(3, objPVenta.getObservacion());
            psmt.setDate(4, objPVenta.getFecha());
            psmt.setString(5, objPVenta.getEstado());
            psmt.executeUpdate();         
            
            VentaDaoImpl daoVenta = new VentaDaoImpl();
            
            Venta v = (Venta) daoVenta.buscarPorIDVenta(objPVenta.getIdVenta());
            
            //Actualizar SaldoPendiente y Pago "Pagado"
            if (v.getSaldoPendiente() <= objPVenta.getMonto()) {
                
                daoVenta.actualizarPago(v.getnVenta());
                
                daoVenta.actualizarSaldoPendiente(v.getnVenta(), objPVenta.getMonto(), "restar");
                
            }else if (v.getSaldoPendiente() > objPVenta.getMonto()) {
                
                daoVenta.actualizarSaldoPendiente(v.getnVenta(), objPVenta.getMonto(), "restar");                
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
    
    //######### Metodo Para Listar Pagos de Venta por ID ##########
    public List<PagoVenta> listarPagoVenta(String id) throws SQLException {
        
        List<PagoVenta> listaPVenta = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM pago_venta WHERE idVenta = ?");            
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaPVenta.add(PagoVenta.load(rs));             
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Pagoa Venta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaPVenta;
    }
    
    /*public static void main(String[] args) throws SQLException {
        
        PagoVentaDaoImpl dao = new PagoVentaDaoImpl();
        
        List<PagoVenta> listaPago = new ArrayList<>();
        
        listaPago = dao.listarPagoVenta("2");
        
        for (PagoVenta p : listaPago) {
            
            System.out.println("Pago: " + p);            
        }
    }*/
}
