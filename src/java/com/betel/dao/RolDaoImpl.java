
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Rol rol;

    public RolDaoImpl() {
    }

    @Override
    public String insertar(Object obj) throws SQLException {
        Rol objRol = (Rol) obj;
        try {
           psmt = con.conectar().prepareStatement("INSERT INTO rol VALUES (null,?,?)");
       
           psmt.setString(1, objRol.getNombre());
           psmt.setString(2, objRol.getEstado());
           psmt.executeUpdate();
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
        Rol objRol = (Rol) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE rol SET estado = ? WHERE  idRol = ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objRol.getIdRol()));
            psmt.executeUpdate();
            respuesta = "El registro se realizó correctamente";
        } catch (SQLException e) {
            throw new SQLException("Error al Eliminar: " + e.toString()); 
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            con.desconectar();
        }
        return respuesta;
    }

    @Override
    public String modificar(Object obj) throws SQLException {
        Rol objRol = (Rol) obj;
        try {
        psmt = con.conectar().prepareStatement("UPDATE rol SET nombre = ?, estado = ? WHERE idRol = ?");
        psmt.setString(1, objRol.getNombre());
        psmt.setString(2, objRol.getEstado());
        psmt.setString(3, objRol.getIdRol());
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
    public List<Rol> listar() throws SQLException {
        
        List<Rol> listaRol = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM `rol` WHERE estado = 'Activo'");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaRol.add(Rol.load(rs));
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
       return listaRol;
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM rol WHERE idRol = ?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
                
                rol =  Rol.load(rs);                        
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
        
       return rol;
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Rol objRol = (Rol) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM rol WHERE nombre LIKE ?");
            psmt.setString(1,objRol.getNombre());
            rs = psmt.executeQuery();
            
            while (rs.next()) {                
                respuesta = true;                  
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
        
        return respuesta;        
    }

    @Override
    public List<Rol> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Rol> listaBusquedaRol = new ArrayList<>();
        Rol rol = (Rol) param;
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "SELECT * FROM rol WHERE nombre LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, rol.getNombre());
                    break;
                case 1:
                    sql = "SELECT * FROM rol WHERE estado LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, rol.getEstado());
                    break;
                case 2:
                    sql = "SELECT * FROM `rol`";
                    psmt = con.conectar().prepareStatement(sql);
                    break;    
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaRol.add(Rol.load(rs));                               
            }
            
        } catch (Exception e) {
            System.out.println("Error em la consulta: "+ e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaBusquedaRol;       
        
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
