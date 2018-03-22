
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Cliente;
import com.betel.modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDaoImpl implements IDAO{
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Cliente client;

    public ClienteDaoImpl(){
    }

    @Override
    public String insertar(Object obj) throws SQLException {
        Cliente objClient = (Cliente) obj;     
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO persona VALUES (null,?,?,?,?,?,?,?,?,?,?,?)");
 
            psmt.setString(1, objClient.getNombreCompleto());
            psmt.setString(2, objClient.getTipoDocIdentidad());
              //Para Rgistrar Null
            if (objClient.getTipoDocIdentidad().equals("Seleccione")) {
                psmt.setString(3, ""); 
            }else{
                    psmt.setString(3, objClient.getNroIdentidad()); 
            }
            
            psmt.setString(4, objClient.getGenero());
            psmt.setString(5, objClient.getDireccion());
            psmt.setString(6, objClient.getCiudad());
            psmt.setString(7, objClient.getBarrio());
            psmt.setString(8,objClient.getEmail());
            psmt.setString(9,objClient.getCelular());
            psmt.setString(10,objClient.getTelefono());
            psmt.setString(11,objClient.getEstado());
           
            psmt.executeUpdate();
            
            Persona p =  (Persona) buscarPorID(objClient.getNroIdentidad());
            
            System.out.println("Id de persona: " + buscarPorID(objClient.getNroIdentidad()));
            System.out.println("documento identidad: " + objClient.getNroIdentidad());
            
            psmt = con.conectar().prepareStatement("INSERT INTO cliente VALUES(null,?,?,?,?)");
            psmt.setString(1, p.getIdPersona());
            psmt.setString(2, objClient.getIdRuta());
            psmt.setDate(3, objClient.getFechaIngreso());
            psmt.setString(4, objClient.getEstadoc());
            psmt.executeUpdate();
            respuesta = "El registro se realizo con exito";
        } catch (NumberFormatException | SQLException e) {
           //  throw new SQLException("Error al registrar: " + e.toString());
                System.out.println("Problemas en el server1: " + e.toString());
                  e.printStackTrace();
        
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
    public String eliminar(Object obj) throws SQLException {
        Cliente objclient = (Cliente) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE cliente SET estado = ? WHERE  idCliente= ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objclient.getIdCliente()));
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
       Cliente objclient = (Cliente) obj;
        try {
            psmt = con.conectar().prepareStatement("{CALL actualizarClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           
            psmt.setString(1, objclient.getIdCliente());
            psmt.setString(2, objclient.getIdPersona());
            psmt.setString(3, objclient.getIdRuta());
            psmt.setString(4, objclient.getEstadoc());
            psmt.setString(5, objclient.getNombreCompleto());
            psmt.setString(6, objclient.getTipoDocIdentidad());
            psmt.setString(7, objclient.getNroIdentidad());            
            psmt.setString(8, objclient.getGenero());
            psmt.setString(9, objclient.getDireccion());
            psmt.setString(10, objclient.getCiudad());
            psmt.setString(11, objclient.getBarrio());
            psmt.setString(12, objclient.getEmail());
            psmt.setString(13, objclient.getCelular());
            psmt.setString(14, objclient.getTelefono());
           
            
            psmt.executeUpdate();
            respuesta = "El registro se realizo con exito";
        } catch (Exception e) {
             throw new SQLException("Error al actualizar: " + e.toString());
             
        
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
    public List<Cliente> listar() throws SQLException {
        List<Cliente> listaCliente= new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("{CALL listarCliente()}");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaCliente.add(Cliente.load(rs));
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
       return listaCliente;
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM persona WHERE nroIdentidad=?");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
               listaPersonas.add(Persona.load(rs));
            }
           // for (Persona persona : listaPersonas) {
              // System.out.println("nombre : " + persona.getIdPersona());
           // }
        } catch (Exception e) {
            System.out.println("Error en el Id: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
       return listaPersonas.get(0);
    }

    @Override
    public int contar() throws SQLException{
        int nRegistro = 0;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM cliente WHERE estado = 'Activo'");
            rs = psmt.executeQuery();
            while (rs.next()) { 
                
                nRegistro ++;            
            }             
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Contar: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return nRegistro;
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Cliente objClient = (Cliente) id;      
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("{CALL bNIdentidadClient(?)}");
            psmt.setString(1,objClient.getNroIdentidad());
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
    public List<Cliente> busquedaPorParametro(String field, Object param) throws SQLException {
     List<Cliente> listaBusquedaClient = new ArrayList<>();
        Cliente Client = (Cliente) param;        
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "{CALL  bNombreClient (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Client.getNombreCompleto());
                    break;
                case 1:
                    sql = "{CALL  bRutaClient (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Client.getIdRuta());
                    break;
                case 2:
                    sql = "{CALL  bEstadoClient (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Client.getEstadoc());
                    break;
                case 3:
                    sql = "{CALL  bTodoClient ()}";
                    psmt = con.conectar().prepareStatement(sql);                    
                    break;    
                    
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaClient.add(Cliente.load(rs));                                              
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
        
        return listaBusquedaClient;  
        
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Cliente consultaCliente(String id) throws SQLException {
        List<Cliente> listaCliente = new ArrayList<>();
        System.out.println("mostrar idc :" + id);
        try {
            psmt = con.conectar().prepareStatement("{CALL consultarClient(?)}");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
               listaCliente.add(Cliente.load(rs));
            }
            for (Cliente cliente : listaCliente) {
                System.out.println("Valor : " + cliente.toString() );
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta Client: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
       return  listaCliente.get(0);
        
    }
    
}
