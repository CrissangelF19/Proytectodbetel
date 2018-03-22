    
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Persona;
import com.betel.modelo.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDaoImpl implements IDAO{
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Proveedor prov;

    public ProveedorDaoImpl() {
    }    

    @Override
    public String insertar(Object obj) throws SQLException {
        Proveedor objprov = (Proveedor) obj;     
        try {
            
            psmt = con.conectar().prepareStatement("INSERT INTO persona VALUES (null,?,?,?,?,?,?,?,?,?,?,?)");
            
            psmt.setString(1, objprov.getNombreCompleto());            
            psmt.setString(2, objprov.getTipoDocIdentidad());
            
            //Para Rgistrar Null
            if (objprov.getTipoDocIdentidad().equals("Seleccione")) {
                psmt.setString(3, ""); 
            }else{
                    psmt.setString(3, objprov.getNroIdentidad()); 
            }           
                       
            psmt.setString(4, objprov.getGenero());
            psmt.setString(5, objprov.getDireccion());
            psmt.setString(6, objprov.getCiudad());
            psmt.setString(7, objprov.getBarrio());
            psmt.setString(8, objprov.getEmail());
            psmt.setString(9, objprov.getCelular());
            psmt.setString(10, objprov.getTelefono());            
            psmt.setString(11, objprov.getEstado());
           
            psmt.executeUpdate();
            
            Persona p =  (Persona) buscarPorID(objprov.getNroIdentidad());
            
            System.out.println("Id de persona: " + buscarPorID(objprov.getNroIdentidad()));
            System.out.println("documento identidad: " + objprov.getNroIdentidad());
            
            psmt = con.conectar().prepareStatement("INSERT INTO proveedor VALUES(null,?,?,?,?,?)");
            psmt.setString(1, objprov.getNit());
            psmt.setString(2, objprov.getRazonSocial());
            psmt.setString(3, p.getIdPersona());
            psmt.setDate(4, objprov.getFechaIngreso());
            psmt.setString(5, objprov.getEstadop());
            psmt.executeUpdate();
            respuesta = "El registro se realizo con exito";
        } catch (NumberFormatException | SQLException e) {
           //  throw new SQLException("Error al registrar: " + e.toString());
                System.out.println("Problemas en el server: " + e.toString());
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
        Proveedor objprov = (Proveedor) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE proveedor SET estado = ? WHERE  idProveedor= ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objprov.getIdProveedor()));
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
         Proveedor objprov = (Proveedor) obj;
        try {
            psmt = con.conectar().prepareStatement("{CALL actualizarProv(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
          
            psmt.setString(1, objprov.getIdProveedor());
            psmt.setString(2, objprov.getNit());
            psmt.setString(3, objprov.getRazonSocial());
            psmt.setString(4, objprov.getIdPersona());
            psmt.setString(5, objprov.getEstadop());
            psmt.setString(6,   objprov.getNombreCompleto());
            psmt.setString(7, objprov.getTipoDocIdentidad());
            psmt.setString(8, objprov.getNroIdentidad());            
            psmt.setString(9, objprov.getGenero());
            psmt.setString(10, objprov.getDireccion());
            psmt.setString(11, objprov.getCiudad());
            psmt.setString(12,   objprov.getBarrio());
            psmt.setString(13, objprov.getEmail());
            psmt.setString(14, objprov.getCelular());
            psmt.setString(15, objprov.getTelefono());
           
            
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
    public List<Proveedor> listar() throws SQLException {
       List<Proveedor> listaProveedor = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("{CALL listarProv()}");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaProveedor.add(Proveedor.load(rs));
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
       return listaProveedor;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object id) throws SQLException{
        Proveedor objProv = (Proveedor) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("{CALL bNitProv (?)}");
            psmt.setString(1,objProv.getNit());
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
    public List<Proveedor> busquedaPorParametro(String field, Object param) throws SQLException {
         List<Proveedor> listaBusquedaProv = new ArrayList<>();
        Proveedor prov = (Proveedor) param;
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "{CALL bNitProv (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, prov.getNit());
                    break;
                case 1:
                    sql = "{CALL bRazonSocialProv (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, prov.getRazonSocial());
                    break;
                case 2:
                    sql = "{CALL bEstadoProv (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                     psmt.setString(1, prov.getEstadop());
                    break;
                 case 3:
                    sql = "{CALL bTodosProv ()}";
                    psmt = con.conectar().prepareStatement(sql);
                    break;    
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaProv.add(Proveedor.load(rs));                               
            }
            
        } catch (Exception e) {
            System.out.println("Error em la consultaP: "+ e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaBusquedaProv;     
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Proveedor consultaProveedor (String id) throws SQLException {
        List<Proveedor> listaProveedor = new ArrayList<>();
        System.out.println("mostrar id :" + id);
        try {
            psmt = con.conectar().prepareStatement("{CALL  consultarProv (?)}");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
               listaProveedor.add(Proveedor.load(rs));
            }
            for (Proveedor proveedor : listaProveedor) {
                System.out.println("Valor : " + proveedor.toString() );
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta Prov: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
       return  listaProveedor.get(0);
        
    }
    
    public boolean existeRSocial(Object id) throws SQLException{
        Proveedor objProv = (Proveedor) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("{CALL bRazonSocialProv (?)}");
            psmt.setString(1,objProv.getRazonSocial());
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
    
    public boolean existeNIdentidad(Object id) throws SQLException{
        Proveedor objProv = (Proveedor) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("{CALL bNIdentidadProv (?)}");
            psmt.setString(1,objProv.getNroIdentidad());
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
    
//    public static void main(String[] args) throws SQLException {
//        ProveedorDaoImpl provDao = new ProveedorDaoImpl();
//        
//       List<Proveedor> listaProveedor = new ArrayList<>();
//       listaProveedor = (List<Proveedor>) provDao.consultaProveedor("1");
////        System.out.println("Proveedor: "+ .getApellidos());
////         System.out.println("Proveedor: "+ provDao.consultaProveedor("1").getNombres());
//        
//       
//        
//    }
//    
}
