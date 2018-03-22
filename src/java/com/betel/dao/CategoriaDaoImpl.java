
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Categoria catg;

    public CategoriaDaoImpl() {
    }

    
    @Override
    public String insertar(Object obj) throws SQLException {
        Categoria objCatg = (Categoria) obj;
        try {
           psmt = con.conectar().prepareStatement("INSERT INTO categoria VALUES (null,?,?)");
       
           psmt.setString(1, objCatg.getNombre());
           psmt.setString(2, objCatg.getEstado());
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
        Categoria objCat = (Categoria) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE categoria SET estado=? WHERE idCategoria=?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objCat.getIdCategoria()));
            psmt.executeUpdate();
            respuesta = "El registro se realizó correctamente";
        } catch (SQLException e) {
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
    public String modificar(Object obj) throws SQLException {
        Categoria objCategoria = (Categoria) obj;
        try {
        psmt = con.conectar().prepareStatement("UPDATE `categoria` SET `nombre`= ?,`estado`= ? WHERE idCategoria = ?");
        psmt.setString(1, objCategoria.getNombre());
        psmt.setString(2, objCategoria.getEstado());
        psmt.setString(3, objCategoria.getIdCategoria());
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
    public List<Categoria> listar() throws SQLException {
       
        List<Categoria> listaCateg = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM `categoria` WHERE estado = 'Activo'");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaCateg.add(Categoria.load(rs));
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
        return listaCateg;
    }    

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM categoria WHERE idCategoria=?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
                
                catg =  Categoria.load(rs);
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
        
       return catg;
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Categoria objCat = (Categoria) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM categoria WHERE nombre LIKE ?");
            psmt.setString(1,objCat.getNombre());
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
    public List<Categoria> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Categoria> listaBusquedaCat = new ArrayList<>();
        Categoria cat = (Categoria) param;
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "SELECT * FROM categoria WHERE nombre LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, cat.getNombre());
                    break;
                case 1:
                    sql = "SELECT * FROM categoria WHERE estado LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, cat.getEstado());
                    break;
                case 2:
                    sql = "SELECT * FROM categoria";
                    psmt = con.conectar().prepareStatement(sql);
                    break;    
                default:
                    throw new AssertionError();
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaCat.add(Categoria.load(rs));                
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
        
        return listaBusquedaCat;
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
