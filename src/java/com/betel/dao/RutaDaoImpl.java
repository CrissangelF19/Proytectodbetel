
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Cliente;
import com.betel.modelo.Rol;
import com.betel.modelo.Ruta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaDaoImpl implements IDAO{    
     
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Ruta rut;
       

    public RutaDaoImpl() {
    }    

    @Override
    public String insertar(Object obj) throws SQLException {
         Ruta objRuta = (Ruta) obj;
        try {
           psmt = con.conectar().prepareStatement("INSERT INTO ruta VALUES (null,?,?)");
       
           psmt.setString(1, objRuta.getNombre());
           psmt.setString(2, objRuta.getEstado());
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
          Ruta objrut = (Ruta) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE ruta SET estado = ? WHERE  idRuta = ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objrut.getIdRuta()));
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
         Ruta objrut = (Ruta) obj;
        try {
         psmt = con.conectar().prepareStatement("UPDATE ruta SET nombre = ?, estado = ? WHERE idRuta = ?");
       
        psmt.setString(1, objrut.getNombre());
        psmt.setString(2, objrut.getEstado());
        psmt.setString(3, objrut.getIdRuta());
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
    public List<Ruta> listar() throws SQLException {
       List<Ruta> listaRuta = new ArrayList<>();
       try {
          psmt = con.conectar().prepareStatement("SELECT * FROM ruta WHERE estado = 'Activo'");
           rs = psmt.executeQuery();
            while (rs.next()) {                
                listaRuta.add(Ruta.load(rs));
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
       return listaRuta;
   }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM ruta WHERE idRuta = ? ");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
                
                rut =  Ruta.load(rs);                        
            }
        } catch (SQLException e) {
            System.out.println("Error en el idRuta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
       return rut;
    }

    @Override
    public int contar() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Ruta objRuta = (Ruta) id;        
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM ruta WHERE nombre LIKE ?");
            psmt.setString(1,objRuta.getNombre());
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
    public List<Ruta> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Ruta> listaBusquedaRuta = new ArrayList<>();
        Ruta rut = (Ruta) param;
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {
                
                case 0:
                    sql = "SELECT * FROM ruta WHERE nombre LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, rut.getNombre());
                    break;
                case 1:
                    sql = "SELECT * FROM ruta WHERE estado LIKE ?";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, rut.getEstado());
                    break;
                case 2:
                    sql = "SELECT * FROM ruta";
                    psmt = con.conectar().prepareStatement(sql);
                    break;
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaRuta.add(Ruta.load(rs));                               
            }
            
        } catch (Exception e) {
            System.out.println("Error en la busqueda: "+ e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaBusquedaRuta;       
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
