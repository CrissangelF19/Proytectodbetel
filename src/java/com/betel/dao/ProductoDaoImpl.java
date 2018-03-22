
package com.betel.dao;

import java.util.List;
import com.betel.modelo.Producto;
import com.betel.db.ConectarDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductoDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Producto prod;
   
    public ProductoDaoImpl(){
        
    }
    
    @Override
    public String insertar(Object obj) throws SQLException {
        Producto objProd =  (Producto) obj;           

        try {
            psmt = con.conectar().prepareStatement("INSERT INTO producto VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?)");
            psmt.setString(1, objProd.getCodProducto());
            psmt.setString(2, objProd.getNombre());
            psmt.setInt(3, objProd.getUnidadesCaja());
            psmt.setDouble(4, objProd.getPrecioVenta());
            psmt.setDouble(5, objProd.getpGanancia());
            psmt.setInt(6, objProd.getStockMin());
            psmt.setString(7, objProd.getDescripcion());
            psmt.setString(8, objProd.getIdCategoria());
            psmt.setString(9, objProd.getIdProveedor());
            psmt.setDate(10, objProd.getFechaRegistro());
            psmt.setInt(11, objProd.getUniDisponible());
            psmt.setString(12, objProd.getEstado());
            psmt.executeUpdate();
            
            respuesta = "El registro se realizo con exito";
            
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
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
         Producto objProd = (Producto) obj;
        try {            
            psmt = con.conectar().prepareStatement("UPDATE producto SET estado=? WHERE idProducto=?");
            psmt.setString(1, "Inactivo");
            psmt.setInt(2, Integer.parseInt(objProd.getIdProducto()));
            psmt.executeUpdate();
            respuesta = "El registro se realizo con exito";
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
        Producto objProd = (Producto) obj;
        try {
            psmt = con.conectar().prepareStatement("UPDATE producto SET codProducto=?, nombre=?, unidadesCaja=?, precioVenta=?, pGanancia=?, stockMin=?, descripcion=?, idCategoria=?, idProveedor=?, estado=? WHERE idProducto=?");
            psmt.setString(1, objProd.getCodProducto());
            psmt.setString(2, objProd.getNombre());            
            psmt.setInt(3, objProd.getUnidadesCaja());            
            psmt.setDouble(4, objProd.getPrecioVenta());
            psmt.setDouble(5, objProd.getpGanancia());
            psmt.setInt(6, objProd.getStockMin());
            psmt.setString(7, objProd.getDescripcion());
            psmt.setString(8, objProd.getIdCategoria());
            psmt.setString(9, objProd.getIdProveedor());
            psmt.setString(10, objProd.getEstado());
            psmt.setString(11, objProd.getIdProducto());
            psmt.executeUpdate();
            respuesta ="El registro se actualizo exitosamente";
            
        } catch (Exception e) {
           throw new SQLException("Error al actualizar: " + e.toString());
           
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            
            con.desconectar();
            
        }
        return respuesta;
   
        
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> listaProd = new ArrayList<>();
        try {
            
        psmt = con.conectar().prepareStatement("{CALL listarProd()}");
        rs = psmt.executeQuery();
            
            while (rs.next()) {                
                listaProd.add(Producto.load(rs));
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
        
        return listaProd;
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM producto WHERE idProducto=?");
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while (rs.next()) {               
                
                prod =  Producto.load(rs);
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
        
        return prod;
    }

    @Override
    public int contar() throws SQLException{
        
        int nRegistro = 0;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM producto WHERE estado = 'Activo'");
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
                
        Producto objProd = (Producto) id;
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM producto WHERE nombre LIKE ?");
            psmt.setString(1,objProd.getNombre());
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
    public List<?> busquedaPorParametro(String field, Object param) throws SQLException {
        List<Producto> listaBusquedaProd = new ArrayList<>();
        Producto prod = (Producto) param;
        int item = Integer.valueOf(field);
        String sql=null;
         try{
        switch (item) {
            case 0:
                sql = "{CALL bCodigoProduct(?)}";
                psmt=con.conectar().prepareStatement(sql);
                psmt.setString(1, prod.getCodProducto());
                break;
            case 1:
                sql = "{CALL bNombreProduct(?)}";
                psmt=con.conectar().prepareStatement(sql);
                psmt.setString(1, prod.getNombre());
                break;
            case 2:
                sql = "{CALL bCategoriaProduct(?)}";
                psmt=con.conectar().prepareStatement(sql);
                psmt.setString(1, prod.getIdCategoria());
                break;
            case 3:
                sql = "{CALL bEstadoProduct(?)}";
                psmt=con.conectar().prepareStatement(sql);
                psmt.setString(1, prod.getEstado());
                break;
            case 4:
                sql = "{CALL bTodoProduct()}";
                psmt = con.conectar().prepareStatement(sql);                    
                break;
                
            default:
                throw new AssertionError();
        }
        rs = psmt.executeQuery();
        while(rs.next()){
            listaBusquedaProd.add(Producto.load(rs));
        }
        }catch (SQLException e){
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
        return listaBusquedaProd;

    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public String generarCodigo() throws SQLException {

        String codigo = null;
         try {
         psmt=con.conectar().prepareStatement("SELECT COUNT(idProducto) FROM producto");
         rs=psmt.executeQuery();
           
            while(rs.next()){

                switch(rs.getString(1).length()){
                    case 1:
                       codigo = "P000" + rs.getString(1);
                       break;
                    case 2:
                       codigo = "P00" + rs.getString(1);
                       break;
                    case 3:
                       codigo = "P0" + rs.getString(1);
                       break;
                    case 4:
                       codigo = "P" + rs.getString(1);
                       break;
                    default: break;
                }      
            }

        } catch (Exception e) {
            throw new SQLException("Error al generar código: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
             if(rs!=null){
                rs.close();
            }
            con.desconectar();
        }


        return codigo;
    }    
    
    public String actualizarExistencia( String idProd, int unidades, String operacion) throws SQLException{        
        
        try {
            
            ProductoDaoImpl daoP = new ProductoDaoImpl();
            //Aumentar
            if(daoP.buscarPorID(idProd)!=null && operacion=="sumar"){
                prod = (Producto) daoP.buscarPorID(idProd);
                int unidadesExistencia = prod.getUniDisponible()+ unidades;
                psmt = con.conectar().prepareStatement("UPDATE producto SET uniDisponible = ? WHERE idProducto = ?");            
                psmt.setInt(1, unidadesExistencia);
                psmt.setString(2, idProd);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
                
            //Disminuir    
            }else if(daoP.buscarPorID(idProd)!=null && operacion=="restar"){
                prod = (Producto) daoP.buscarPorID(idProd);
                int unidadesExistencia = prod.getUniDisponible() - unidades;
                psmt = con.conectar().prepareStatement("UPDATE producto SET uniDisponible = ? WHERE idProducto = ?");            
                psmt.setInt(1, unidadesExistencia);
                psmt.setString(2, idProd);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
            }else{
                
                respuesta = "La actualización no se realizo con exito";
            }
            
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
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
    
    public int contarStock() throws SQLException{
        
        int nRegistro = 0;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM producto WHERE uniDisponible <= stockMin");
            rs = psmt.executeQuery();
            while (rs.next()) { 
                
                nRegistro ++;            
            }             
            
        } catch (Exception e) {
            System.out.println("Error en la consulta ContarStock: " + e);
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
    
    
    /*
    //Prueba de Metodo Listar
    public static void main(String[] args) throws SQLException {
        ProductoDaoImpl dao = new ProductoDaoImpl();
        List<Producto> listaP = new ArrayList<>();
        listaP = dao.listar();
        for (Producto producto : listaP) {
            System.out.println("Resultado: "+producto.toString());
            
        }
    }*/
    
    /*
    //Prueba de Metodo Existe 
    public static void main(String[] args) throws SQLException {      
        
        ProductoDaoImpl proDao = new ProductoDaoImpl();
        boolean valida = proDao.existe(new Producto(null, null,"Pan tajado4", 0, 0, 0, 0, null,null, null, null, null));
        
        System.out.println("Respuesta de Consulta: " + valida);
        
    }*/
    
    /*
    //Prueba de Metodo Contar
    public static void main(String[] args) throws SQLException {      
        
        ProductoDaoImpl proDao = new ProductoDaoImpl();       
        
        System.out.println("Respuesta de Consulta: " + proDao.contar());
        
    }*/
    
    /*
    //Prueba de Metodo ActualizarExistencia
    public static void main(String[] args) throws SQLException {      
        
        ProductoDaoImpl proDao = new ProductoDaoImpl(); 
        
        System.out.println("Mesaje: " + proDao.actualizarExistencia("1", 10 , "sumar"));
        
    }*/
    
    //Prueba de Metodo Buscar por ID
    /*public static void main(String[] args) throws SQLException {
        ProductoDaoImpl proDao = new ProductoDaoImpl(); 
        
        System.out.println("Mesaje: " + proDao.buscarPorID("1"));
    }*/
        
}