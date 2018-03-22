
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Empleado;
import com.betel.modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDaoImpl implements IDAO{

  ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Empleado empl;

    public EmpleadoDaoImpl() {
    }
    
    
    @Override
    public String insertar(Object obj) throws SQLException {
        Empleado objEmpl = (Empleado) obj;
     
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO persona VALUES (null,?,?,?,?,?,?,?,?,?,?,?)");
            
            psmt.setString(1, objEmpl.getNombreCompleto());
            psmt.setString(2, objEmpl.getTipoDocIdentidad());
            //Para Rgistrar Null
            if (objEmpl.getTipoDocIdentidad().equals("Seleccione")) {
                psmt.setString(3, ""); 
            }else{
                    psmt.setString(3, objEmpl.getNroIdentidad()); 
            }           
            psmt.setString(4, objEmpl.getGenero());
            psmt.setString(5, objEmpl.getDireccion());
            psmt.setString(6, objEmpl.getCiudad());
            psmt.setString(7, objEmpl.getBarrio());
            psmt.setString(8,objEmpl.getEmail());
            psmt.setString(9,objEmpl.getCelular());
            psmt.setString(10,objEmpl.getTelefono());           
            psmt.setString(11,objEmpl.getEstado());
           
            psmt.executeUpdate();
            
            Persona p =  (Persona) buscarPorID(objEmpl.getNroIdentidad());
            
            System.out.println("Id de persona: " + buscarPorID(objEmpl.getNroIdentidad()));
            System.out.println("documento identidad: " + objEmpl.getNroIdentidad());
            
            psmt = con.conectar().prepareStatement("INSERT INTO empleado VALUES(null,?,?,?,?)");
            psmt.setString(1, p.getIdPersona());
            psmt.setString(2, objEmpl.getIdRol());
            psmt.setDate(3, objEmpl.getFechaIngreso());
            psmt.setString(4, objEmpl.getEstadoz());
            psmt.executeUpdate();
            respuesta = "El registro se realizo con exito";
        } catch (NumberFormatException | SQLException e) {
           //  throw new SQLException("Error al registrar: " + e.toString());
            System.out.println("Problemas en el serverE: " + e.toString());
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
        Empleado objempl = (Empleado) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE empleado SET estado = ? WHERE  idEmpleado= ?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objempl.getIdEmpleado()));
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
        Empleado objempl = (Empleado) obj;
        try {
            psmt = con.conectar().prepareStatement("{CALL actualizarEmpl(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           
            psmt.setString(1, objempl.getIdEmpleado());
            psmt.setString(2, objempl.getIdPersona());
            psmt.setString(3, objempl.getIdRol());
            psmt.setString(4, objempl.getEstadoz());
            psmt.setString(5, objempl.getNombreCompleto());            
            psmt.setString(6, objempl.getTipoDocIdentidad());
            psmt.setString(7, objempl.getNroIdentidad());            
            psmt.setString(8, objempl.getGenero());
            psmt.setString(9, objempl.getDireccion());
            psmt.setString(10, objempl.getCiudad());
            psmt.setString(11, objempl.getBarrio());
            psmt.setString(12, objempl.getEmail());
            psmt.setString(13, objempl.getCelular());
            psmt.setString(14, objempl.getTelefono());
           
            
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
    public List<Empleado> listar() throws SQLException {
        List<Empleado> listaEmpl= new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("{CALL listarEmpleado ()}");
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaEmpl.add(Empleado.load(rs));
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
       return listaEmpl;
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
        Empleado objempl = (Empleado) id;        
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("{CALL bNIdentidadEmpl(?)}");
            psmt.setString(1,objempl.getNroIdentidad());
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
    public List<Empleado> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Empleado> listaBusquedaEmpl = new ArrayList<>();
        Empleado Empl = (Empleado) param;        
        int item = Integer.valueOf(field);
        String sql = null;
        try {
            switch (item) {                
                case 0:
                    sql = "{CALL  bNombreEmpl  (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Empl.getNombreCompleto());
                    break;                            
                case 1:
                    sql = "{CALL  bRolEmpl (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Empl.getIdRol());
                    break;
                case 2:
                    sql = "{CALL  bEstadoEmpl  (?)}";
                    psmt = con.conectar().prepareStatement(sql);
                    psmt.setString(1, Empl.getEstadoz());
                    break;
                case 3:
                    sql = "{CALL  bTodoEmpl  ()}";
                    psmt = con.conectar().prepareStatement(sql);                    
                    break;    
                    
                default:
                    throw new AssertionError();
            }
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaBusquedaEmpl.add(Empleado.load(rs));                                              
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
        
        return listaBusquedaEmpl;  
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public Empleado consultaEmpleado(String id) throws SQLException {
        List<Empleado> listaEmpleado = new ArrayList<>();
        System.out.println("mostrar ide :" + id);
        try {
            psmt = con.conectar().prepareStatement("{CALL consultarEmpl(?)}");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
               listaEmpleado.add(Empleado.load(rs));
            }
//            for (Proveedor proveedor : listaProveedor) {
//                System.out.println("Valor : " + proveedor.toString() );
//            }
        } catch (Exception e) {
            System.out.println("Error en la consulta Empl: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
       return  listaEmpleado.get(0);
        
    }
}
