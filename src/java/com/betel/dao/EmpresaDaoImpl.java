
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpresaDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Empresa emp;

    public EmpresaDaoImpl() {
    }
        
    @Override
    public String insertar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String eliminar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(Object obj) throws SQLException {
        Empresa objEmp = (Empresa) obj;
        try {
        psmt = con.conectar().prepareStatement("UPDATE empresa SET nombre=?, nit=?, propietario=?, email=?, telefono=?, iva=?, direccion=?, ciudad=?, barrio=?, codPostal=?, logo=? WHERE idEmpresa=?");
        psmt.setString(1, objEmp.getNombre());
        psmt.setString(2, objEmp.getNit());
        psmt.setString(3, objEmp.getPropietario());
        psmt.setString(4, objEmp.getEmail());
        psmt.setString(5, objEmp.getTelefono());
        psmt.setString(6, objEmp.getIva());
        psmt.setString(7, objEmp.getDireccion());
        psmt.setString(8, objEmp.getCiudad());
        psmt.setString(9, objEmp.getBarrio());
        psmt.setString(10, objEmp.getCodigoPostal());
        psmt.setString(11, objEmp.getLogo());        
        psmt.setString(12, objEmp.getIdEmpresa());
        
        psmt.executeUpdate();
        respuesta = "El registro se Actualizo con exito";
        } catch (Exception e) {
            throw new SQLException("Error al Actualizar: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            
            con.desconectar();
            
        }
        return respuesta;
    }

    @Override
    public List<Empresa> listar() throws SQLException {
        
        List<Empresa> listaEmp = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM empresa");
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaEmp.add(Empresa.load(rs));                
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
       return listaEmp;
       
        
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM empresa WHERE idEmpresa = ?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) { 
                
                emp =   Empresa.load(rs);                        
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
       return emp;
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) {
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
    
    
}
