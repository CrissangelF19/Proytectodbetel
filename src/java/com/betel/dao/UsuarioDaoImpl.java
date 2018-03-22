
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Encriptar;
import java.sql.Date;
import com.betel.modelo.Usuario;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.taglibs.standard.lang.jstl.Coercions;

public class UsuarioDaoImpl implements IDAO{
    
    ConectarDB con;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Usuario usuario;

    public UsuarioDaoImpl() {
        con = new ConectarDB();
        con.setDriver("com.mysql.jdbc.Driver");
        con.setUrl("jdbc:mysql://localhost:3306/dbetel");
        con.setUsuario("root");
        con.setPassword("");
    }

    @Override
    public String insertar(Object obj) throws SQLException {
        Usuario objUsuario = (Usuario) obj;
        try {
           psmt = con.conectar().prepareStatement("INSERT INTO usuario VALUES (null,?,?,?,?,?,?)");
       
           psmt.setString(1, objUsuario.getNombreCompleto());           
           psmt.setString(2, objUsuario.getUsuario());
           psmt.setString(3, Encriptar.sha1(objUsuario.getClave()));
           psmt.setString(4, objUsuario.getIdRol());
           psmt.setDate(5, objUsuario.getFechaIngreso());
           psmt.setString(6, objUsuario.getEstado());
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
        Usuario objUsuario = (Usuario) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE usuario SET estado = ? WHERE  idUsuario = ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objUsuario.getIdUsuario()));
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
        Usuario objUsuario = (Usuario) obj;
        try {
        psmt = con.conectar().prepareStatement("UPDATE usuario SET nombreCompleto = ?, usuario = ?, clave = ?, idRol = ?, fechaIngreso = ?, estado = ?  WHERE idUsuario = ?");
        psmt.setString(1, objUsuario.getNombreCompleto());        
        psmt.setString(2, objUsuario.getUsuario());
        psmt.setString(3, Encriptar.sha1(objUsuario.getClave()));
        psmt.setString(4, objUsuario.getIdRol());
        psmt.setDate(5, objUsuario.getFechaIngreso());
        psmt.setString(6, objUsuario.getEstado());
        psmt.setString(7, objUsuario.getIdUsuario());
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
    public List<Usuario> listar() throws SQLException {
        List<Usuario> listaUsuario = new ArrayList();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listar_U()}");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaUsuario.add(Usuario.load(rs));
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
       return listaUsuario;
        
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
                
                usuario =  Usuario.load(rs);
                                              
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
        
       return usuario;
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Usuario objUser = (Usuario) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM usuario WHERE usuario LIKE ?");
            psmt.setString(1,objUser.getUsuario());
            rs = psmt.executeQuery();
            
            while (rs.next()) {                
                respuesta = true;                  
            }
            
        } catch (SQLException e) {
            System.out.println("Error en la consulta Usuario: " + e);
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
    public List<Usuario> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Usuario> listaBusquedaUs = new ArrayList<>();
        Usuario usuario = (Usuario) param;        
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "{CALL bNombreUsuario (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, usuario.getNombreCompleto());
                    break;                
                case 1:
                    sql = "{CALL bUsuario (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, usuario.getUsuario());
                    break;
                case 2:
                    sql = "{CALL bRolUsuario (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, usuario.getIdRol());
                    break;
                case 3:
                    sql = "{CALL bEstadoUsuario (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, usuario.getEstado());
                    break;
                case 4:
                    sql = "{CALL bTodoUsuario ()}";
                    psmt = con.conectar().prepareStatement(sql);                    
                    break;    
                    
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaUs.add(Usuario.load(rs));                                              
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
        
        return listaBusquedaUs;  
        
    }

    @Override
    public List<Usuario> existeUsuario(String usuario, String clave) throws Exception {
        List<Usuario> listaUsuario = new ArrayList();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND clave = ? AND estado = 'Activo'");
            psmt.setString(1, usuario);
            psmt.setString(2, Encriptar.sha1(clave));
            rs = psmt.executeQuery();
            while(rs.next()){
                listaUsuario.add(Usuario.load(rs));
            }
                  
        } catch (SQLException e) {
            out.println("Error al listar los usuarios: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            con.desconectar();
        }
        
        return listaUsuario;
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }      
    
}
